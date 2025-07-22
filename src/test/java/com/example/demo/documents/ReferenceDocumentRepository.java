package com.example.demo.documents;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReferenceDocumentRepository extends MongoRepository<ReferenceDocument, ObjectId> {
}
