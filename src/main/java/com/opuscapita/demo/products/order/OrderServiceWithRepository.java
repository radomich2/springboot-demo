package com.opuscapita.demo.products.order;

import com.opuscapita.demo.products.dto.BestSellerDto;
import com.opuscapita.demo.products.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


public class OrderServiceWithRepository implements OrderService {

    private OrderRepository orderRepository;
    private ProductsRepository productsRepository;
    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderServiceWithRepository(OrderRepository orderRepository, ProductsRepository productsRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.productsRepository = productsRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public Orders addOrder(OrderInfoDto orderInfoDTO) {
        System.out.println("here in addproduct method" + orderInfoDTO.getOrderDescription());
        Orders order = new Orders();
        order.setOrderDecription(orderInfoDTO.getOrderDescription());
        order.setPrice(Integer.valueOf(orderInfoDTO.getPrice()));
        for (int i = 0; i < orderInfoDTO.getProductDetails().getProductDetail().size(); i++) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(productsRepository.findById(Long.parseLong(orderInfoDTO.getProductDetails().getProductDetail().get(i).getProductId())).get());
            orderProduct.setQuantity(Integer.valueOf(orderInfoDTO.getProductDetails().getProductDetail().get(i).getProductNumbers()));
            orderProduct.setOneOrders(order);
            order.getOrderProducts().add(orderProduct);
        }
        orderRepository.save(order);
        return order;
    }

    public Orders updateOrder(OrderInfoDto orderInfoDto) {
        System.out.println("ready for update for update");
        Orders order = null;
        UpdateInfoOrderDto updateDTO = (UpdateInfoOrderDto) orderInfoDto;
        System.out.println("is added " + updateDTO.getIsAdd() + "is remove " + updateDTO.getIsRemoved());
        if (updateDTO.IsAdd) {
            order = addingNewProductToOrder(updateDTO);
        } else if (updateDTO.IsRemoved) {
            order = removingOldProductFromOrder(updateDTO);
        }
        if (updateDTO.getIsPricedAltered()) {
            order = alterThePrice(updateDTO);
        }
        if (updateDTO.getIsQuantityAltered()) {
            System.out.println("amount cheanged");
            order = alterQuantityOfProducts(updateDTO);
        }
        return order;
    }

    @Override
    public void remove(OrderInfoDto orderInfoDto) {
        Orders order = orderRepository.findById(Long.valueOf(orderInfoDto.getId())).get();
        orderRepository.delete(order);
    }

    @Override
    public List<BestSellerDto> getBestSellingProducts() {
        List<ProjectQuantity> pqList = orderProductRepository.getBestSellers();
        System.out.println("here ");
        List<BestSellerDto> bsList=new ArrayList<>();
        for(ProjectQuantity pq:pqList){
            bsList.add(new BestSellerDto(pq.getPid(),
                productsRepository.findById(Long.valueOf(pq.getPid())).get().getProductName(),
                pq.getTotalQuantity()));
        }
        System.out.println(pqList.size() + "  " + pqList.get(0).getTotalQuantity());
        return bsList;
    }

    private Orders alterQuantityOfProducts(UpdateInfoOrderDto updateDTO) {
        Orders order = orderRepository.findById(Long.valueOf(updateDTO.getId())).get();
        Map<String, OrderProduct> orderProductMap = new HashMap<>();
        order.getOrderProducts().stream().forEach(p -> orderProductMap.put(String.valueOf(p.getProduct().getProductId()), p));
        Set<String> productIdThatExist = new HashSet<>();
        order.getOrderProducts().stream().forEach(p -> productIdThatExist.add(String.valueOf(p.getProduct().getProductId())));
        for (int i = 0; i < updateDTO.getProductDetails().getProductDetail().size(); i++) {
            ProductDetail p = updateDTO.getProductDetails().getProductDetail().get(i);
            if (productIdThatExist.contains(p.getProductId())) {
                if (!String.valueOf(orderProductMap.get(p.getProductId()).getQuantity()).equals(p.getProductNumbers())) {
                    orderProductMap.get(p.getProductId()).setQuantity(Integer.valueOf(p.productNumbers));
                    order.getOrderProducts().add(orderProductMap.get(p.getProductId()));
                }
            }
        }
        return orderRepository.save(order);
    }

    private Orders alterThePrice(UpdateInfoOrderDto updateDTO) {
        Orders order = orderRepository.findById(Long.valueOf(updateDTO.getId())).get();
        order.setPrice(Integer.valueOf(updateDTO.getPrice()));
        return orderRepository.save(order);
    }

    public Orders removingOldProductFromOrder(UpdateInfoOrderDto updateDTO) {
        Orders order = orderRepository.findById(Long.valueOf(updateDTO.getId())).get();
        Set<String> productIdThatExist = new HashSet<>();
        order.getOrderProducts().stream().forEach(p -> productIdThatExist.add(String.valueOf(p.getProduct().getProductId())));
        for (int i = 0; i < updateDTO.getProductDetails().getProductDetail().size(); i++) {
            ProductDetail p = updateDTO.getProductDetails().getProductDetail().get(i);
            if (productIdThatExist.contains(p.getProductId())) {
                OrderProduct mustDeleteOrderProduct = order.getOrderProducts().stream().filter(op -> String.valueOf(op.getProduct().getProductId()).equals(p.getProductId())).findAny().get();
                order.getOrderProducts().remove(mustDeleteOrderProduct);
            }
        }
        orderRepository.save(order);
        return order;
    }

    private Orders addingNewProductToOrder(UpdateInfoOrderDto updateDTO) {
        Orders order = orderRepository.findById(Long.valueOf(updateDTO.getId())).get();
        Set<String> productIdThatExist = new HashSet<>();
        order.getOrderProducts().stream().forEach(p -> productIdThatExist.add(String.valueOf(p.getProduct().getProductId())));
        for (int i = 0; i < updateDTO.getProductDetails().getProductDetail().size(); i++) {
            ProductDetail p = updateDTO.getProductDetails().getProductDetail().get(i);
            if (!productIdThatExist.contains(p.getProductId())) {
                OrderProduct op = new OrderProduct();
                op.setQuantity(Integer.valueOf(p.getProductNumbers()));
                op.setProduct(productsRepository.findById(Long.valueOf(p.getProductId())).get());
                op.setOneOrders(order);
                order.getOrderProducts().add(op);
            }
        }
        orderRepository.save(order);
        return order;
    }


}
