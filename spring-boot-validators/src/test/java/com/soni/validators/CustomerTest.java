package com.soni.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soni.validators.controller.requestbody.ValidateRequestBodyController;
import com.soni.validators.dto.CustomerRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class CustomerTest {
//    @Autowired
//    public MockMvc mvc;
//
//    // This object will be magically initialized by the initFields method below.
//    private JacksonTester<CustomerRequest> jsonTester;
//    @InjectMocks
//    private ValidateRequestBodyController customerController;
//
//    @BeforeEach
//    public void setup() {
//        JacksonTester.initFields(this, new ObjectMapper());
//        // MockMvc standalone approach
//        mvc = MockMvcBuilders.standaloneSetup(customerController)
//                .build();
//    }
//
//    @Test
//    void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
//        CustomerRequest customerRequest = CustomerRequest.builder().name("hari").email("ss").build();
//
//        MockHttpServletResponse response = mvc.perform(
//                MockMvcRequestBuilders.post("/customers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonTester.write(customerRequest).getJson())
//        ).andReturn().getResponse();
//        System.out.println(response);
//        Assertions.assertNotNull(response);
//    } @Autowired
////    public MockMvc mvc;
////
////    // This object will be magically initialized by the initFields method below.
////    private JacksonTester<CustomerRequest> jsonTester;
////    @InjectMocks
////    private ValidateRequestBodyController customerController;
////
////    @BeforeEach
////    public void setup() {
////        JacksonTester.initFields(this, new ObjectMapper());
////        // MockMvc standalone approach
////        mvc = MockMvcBuilders.standaloneSetup(customerController)
////                .build();
////    }
////
////    @Test
////    void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
////        CustomerRequest customerRequest = CustomerRequest.builder().name("hari").email("ss").build();
////
////        MockHttpServletResponse response = mvc.perform(
////                MockMvcRequestBuilders.post("/customers")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(jsonTester.write(customerRequest).getJson())
////        ).andReturn().getResponse();
////        System.out.println(response);
////        Assertions.assertNotNull(response);
////    }
}

