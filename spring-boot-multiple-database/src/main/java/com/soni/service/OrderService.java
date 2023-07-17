package com.soni.service;

import com.soni.dto.Error;
import com.soni.dto.request.CreateOrderRequest;
import com.soni.dto.response.CreateOrderResponse;
import com.soni.model.primary.Order;
import com.soni.repositories.OrderRepository;
import com.soni.repositories.document.MultiModelRepository;
import com.soni.service.document.MultiModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MultiModelRepository multiModelRepository;

    @Autowired
    MultiModelService multiModelService;
    @Autowired
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        if (orderRepository.findByMerchantIdAndMerchantOrderId(request.getMerchantId(), request.getMerchantOrderId()).isPresent()) {
            CreateOrderResponse response = CreateOrderResponse.builder().build();
            response.setError(Error.builder().code(400).message("duplicate order").build());
            return response;
        }
        Order order = Order.builder().userId(request.getUserId()).merchantOrderId(request.getMerchantOrderId()).merchantId(request.getMerchantId()).amount(request.getAmount()).trackingId(UUID.randomUUID().toString()).build();
        orderRepository.save(order);
        return buildResponse(order);
    }
    public CreateOrderResponse orderDetail(Long id) {
        multiModelService.createDocument();
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            CreateOrderResponse response = CreateOrderResponse.builder().build();
            response.setError(Error.builder().code(404).message("order does not exist").build());
            return response;
        }
        return buildResponse(order.get());
    }

    private CreateOrderResponse buildResponse(Order order) {
        return CreateOrderResponse.builder().id(order.getId()).trackingId(order.getTrackingId()).merchantId(order.getMerchantId()).merchantOrderId(order.getMerchantOrderId()).userId(order.getUserId()).amount(order.getAmount()).build();
    }
}
