package com.virtusa.pipeline.deal;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DealRepository
        extends MongoRepository<Deal, String> {
}
