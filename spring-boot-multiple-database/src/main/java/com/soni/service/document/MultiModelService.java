package com.soni.service.document;

import com.soni.dto.Error;
import com.soni.dto.request.CreateOrderRequest;
import com.soni.dto.response.CreateOrderResponse;
import com.soni.model.document.MultiModel;
import com.soni.model.primary.Order;
import com.soni.repositories.document.MultiModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class MultiModelService {
    @Autowired
    private MultiModelRepository multiModelRepository;
    public void createDocument() {
        MultiModel multiModel = MultiModel.builder().name("Test").price(new BigDecimal(200)).description("desc").build();
        multiModelRepository.save(multiModel);
    }
}
