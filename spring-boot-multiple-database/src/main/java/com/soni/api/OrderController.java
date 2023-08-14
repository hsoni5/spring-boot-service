package com.soni.api;

import com.soni.dto.request.CreateOrderRequest;
import com.soni.dto.response.CreateOrderResponse;
import com.soni.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/orders")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("")
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public CreateOrderResponse orderDetail(@PathVariable Long id) {
        return orderService.orderDetail(id);
    }
}