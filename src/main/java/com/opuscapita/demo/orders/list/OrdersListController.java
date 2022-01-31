package com.opuscapita.demo.orders.list;

import com.opuscapita.demo.dto.ListDto;
import com.opuscapita.demo.orders.model.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrdersListController {

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/orders")
    public ListDto<OrderListRowDto> getOrders() {
        List<OrderListRowDto> rows = ordersRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
            .stream()
            .map(o -> new OrderListRowDto(o.getId(), o.getTotalPrice(), o.getDescription()))
            .collect(Collectors.toList());
        return new ListDto<>(rows);
    }
}
