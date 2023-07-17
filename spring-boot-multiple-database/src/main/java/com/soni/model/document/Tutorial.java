package com.soni.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tutorials")
@Data
public class Tutorial {
    @Id
    private String id;

    private String title;
    private String description;
    private boolean published;
}
