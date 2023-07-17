package com.soni.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "MultiModel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MultiModel {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}