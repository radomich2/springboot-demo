package com.opuscapita.demo.products.order;


import com.opuscapita.demo.products.dto.BestSellerDto;
import com.opuscapita.demo.products.model.OrderProduct;
import com.opuscapita.demo.products.model.Orders;
import com.opuscapita.demo.products.model.ProjectQuantity;

import java.util.List;

public interface OrderService {

    Orders addOrder(OrderInfoDto OrderInfoDTO);

    Orders updateOrder(OrderInfoDto orderInfoDto);

    void remove(OrderInfoDto orderInfoDto);

    List<BestSellerDto> getBestSellingProducts();
}
