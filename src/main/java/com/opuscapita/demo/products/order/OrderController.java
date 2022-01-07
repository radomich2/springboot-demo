package com.opuscapita.demo.products.order;

import com.opuscapita.demo.config.BeansConfig;
import com.opuscapita.demo.error.ValidationErrorResponseDto;
import com.opuscapita.demo.products.dto.BestSellerDto;
import com.opuscapita.demo.products.dto.ProductDto;
import com.opuscapita.demo.products.model.Orders;
import com.opuscapita.demo.products.model.Product;
import com.opuscapita.demo.products.model.ProjectQuantity;
import com.opuscapita.demo.products.product.ProductInfoDto;
import com.opuscapita.demo.products.product.ProductMapper;
import com.opuscapita.demo.products.product.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    private OrderService service;

    private OrderMapper orderMapper;

    @Autowired
    public OrderController(
        @Qualifier(BeansConfig.ORDER_SERVICE_REPOSITORY) OrderService service,OrderMapper orderMapper){
        this.service = service;
        this.orderMapper = orderMapper;
    }


    @PostMapping("/order")
    @ApiOperation(value = "Add new order", notes = "Validates input data, creates new order, returns all data of it.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "New order saved."),
        @ApiResponse(code = 400, message = "Input validation failed.", response = ValidationErrorResponseDto.class)
    })
    public OrderDto addOrder(@Valid @RequestBody OrderInfoDto orderInfoDto) {
        System.out.println("order infoDTO"+orderInfoDto.getOrderDescription());
        System.out.println("order infoDTO"+orderInfoDto.getProductDetails().getProductDetail().size());
        Orders order=  service.addOrder(orderInfoDto);
           return orderMapper.map(order);
    }

    @PutMapping("/orderupdate")
    @ApiOperation(value = "update the order", notes = " update order base on order id, returns all data of it.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "New order saved."),
        @ApiResponse(code = 400, message = "Input validation failed.", response = ValidationErrorResponseDto.class)
    })
    public OrderDto updateOrder(@Valid @RequestBody UpdateInfoOrderDto orderInfoDto){
        Orders order= service.updateOrder(orderInfoDto);
        return orderMapper.map(order);
    }

    @DeleteMapping("/orderRemove")
    @ApiOperation(value = "remove the order", notes = " remove order based on order id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "New order saved."),
        @ApiResponse(code = 400, message = "Input validation failed.", response = ValidationErrorResponseDto.class)
    })
    public void removeOrder(@Valid @RequestBody UpdateInfoOrderDto orderInfoDto){
        System.out.println("here");
         service.remove(orderInfoDto);

    }

    @GetMapping("/bestsellers")
    public List<BestSellerDto> getBestSellingProducts() {
        System.out.println("Best sellers");
       return service.getBestSellingProducts();

    }

}
