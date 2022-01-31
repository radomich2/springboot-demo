package com.opuscapita.demo.orders;

import com.opuscapita.demo.error.ApiClientError;
import com.opuscapita.demo.error.ApiNotFoundError;
import com.opuscapita.demo.orders.dto.OrderInfoDto;
import com.opuscapita.demo.orders.dto.OrderInfoItemDto;
import com.opuscapita.demo.orders.model.Order;
import com.opuscapita.demo.orders.model.OrderItem;
import com.opuscapita.demo.orders.model.OrderItemsRepository;
import com.opuscapita.demo.orders.model.OrdersRepository;
import com.opuscapita.demo.products.model.Product;
import com.opuscapita.demo.products.model.ProductsRepository;
import com.opuscapita.demo.sequences.SequenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy");

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private SequenceNumberService sequenceNumberService;

    @Transactional(readOnly = true)
    public Order getOrder(String id) {
        return ordersRepository.findById(id).orElseThrow(() -> new ApiNotFoundError("Order not found!"));
    }

    @Transactional
    public Order addOrder(OrderInfoDto orderInfo) {
        String id = generateOrderId();
        Order order = new Order(id);
        List<OrderItem> items = orderInfo.getItems().stream()
            .map(itemDto -> createOrderItem(itemDto, order))
            .collect(Collectors.toList());
        order.setDescription(orderInfo.getDescription());
        order.recalculateTotalPrice();
        ordersRepository.save(order);
        return order;
    }

    @Transactional
    public Order updateOrder(String id, OrderInfoDto orderInfo) {
        Order order = ordersRepository.findByIdForUpdate(id).orElseThrow(() -> new ApiNotFoundError("Order not found!"));
        if (order.getVersion() != orderInfo.getVersion()) {
            throw ApiClientError.OUTDATED_VERSION.get("Version = " + order.getVersion());
        }
        Map<Long, OrderItem> items = order.getItemsMappedByProductId();
        for (OrderInfoItemDto itemDto : orderInfo.getItems()) {
            Long productId = itemDto.getProductId();

            // Existing Product
            if (items.containsKey(productId)) {
                OrderItem item = items.get(productId);
                if (itemDto.shouldBeDeleted()) {
                    order.removeItem(item);
                } else {
                    item.update(itemDto.getQuantity());
                }
            }
            // New product
            else {
                if (itemDto.shouldBeDeleted()) {
                    throw ApiClientError.OTHER_ERROR.get("Can't delete new product. ID " + productId);
                }
                createOrderItem(itemDto, order);
            }
        }
        if (!order.hasItems()) {
            throw ApiClientError.OTHER_ERROR.get("Can't remove all items from order");
        }
        order.setDescription(orderInfo.getDescription());
        order.recalculateTotalPrice();
        order.forceUpdatedAtChange();
        return order;
    }

    @Transactional
    public void deleteOrder(String id) {
        Order order = ordersRepository.findById(id).orElseThrow(() -> new ApiNotFoundError("Order not found!"));
        ordersRepository.delete(order);
    }

    private OrderItem createOrderItem(OrderInfoItemDto itemDto, Order order) {
        Product product = productsRepository.findById(itemDto.getProductId()).get();
        return order.addItem(product, itemDto.getQuantity());
    }

    private String generateOrderId() {
        return "ORD-" + DATE_FORMAT.format(LocalDate.now()) + "-" + sequenceNumberService.getNextOrderNumber();
    }
}
