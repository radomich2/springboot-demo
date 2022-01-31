package com.opuscapita.demo.orders;

import com.opuscapita.demo.orders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.opuscapita.demo.orders.dto.OrderDto;
import com.opuscapita.demo.orders.dto.OrderInfoDto;
import com.opuscapita.demo.orders.dto.OrderInfoDto.OnCreate;
import com.opuscapita.demo.orders.dto.OrderInfoDto.OnUpdate;

import javax.validation.groups.Default;

@RestController
public class OrdersController {

    private OrderMapper orderMapper;
    private OrdersService ordersService;

    @Autowired
    public OrdersController(OrderMapper orderMapper, OrdersService ordersService) {
        this.orderMapper = orderMapper;
        this.ordersService = ordersService;
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable String id) {
        Order order = ordersService.getOrder(id);
        return orderMapper.map(order);
    }

    @PostMapping("/orders")
    public OrderDto addOrder(
        @Validated({Default.class, OnCreate.class}) @RequestBody OrderInfoDto orderDto) {

        Order order = ordersService.addOrder(orderDto);
        return orderMapper.map(order);
    }

    @PatchMapping("/orders/{id}")
    public OrderDto updateOrder(
        @PathVariable String id,
        @Validated({Default.class, OnUpdate.class}) @RequestBody OrderInfoDto orderDto) {

        Order order = ordersService.updateOrder(id, orderDto);
        return orderMapper.map(order);
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        ordersService.deleteOrder(id);
    }
}
