package com.opuscapita.demo.orders.reports;

import com.opuscapita.demo.orders.model.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderReportsController {

    @Autowired
    private OrderItemsRepository orderItemRepository;

    @GetMapping("/orders/bestsellers")
    public List<BestsellerDto> getBestSellers() {
        Pageable topThree = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "totalQty"));
        return orderItemRepository.getBestsellingProducts(topThree);
    }

}
