package com.soni.repositories;

import com.soni.model.primary.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByMerchantIdAndMerchantOrderId(String merchantId, String merchantOrderId);
}
