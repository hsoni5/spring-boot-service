package com.soni.repositories.document;

import com.soni.model.document.MultiModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiModelRepository extends MongoRepository<MultiModel, String> {
}
