package com.example.demo;

import com.example.demo.documents.ReferenceDocument;
import com.example.demo.documents.ReferenceDocumentRepository;
import com.example.demo.documents.SomeDocument;
import com.example.demo.documents.SomeDocumentRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Import(TestcontainersConfiguration.class)
@TestConfiguration
@DataMongoTest
class Demo1ApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private SomeDocumentRepository someDocumentRepository;
    @Autowired
    private ReferenceDocumentRepository referenceDocumentRepository;
    @Test
    void contextLoads() {

        SomeDocument document = SomeDocument.builder()
                .text("test").build();
        SomeDocument saved = someDocumentRepository.save(document);

        ReferenceDocument newReferenceDoc = ReferenceDocument.builder()
                .text("referenceDocText")
                .someDocument(saved)
                .build();
        ObjectId savedReferenceDocumentId = referenceDocumentRepository.save(newReferenceDoc).getId();

        ReferenceDocument referenceDocumentFromDb = referenceDocumentRepository.findById(savedReferenceDocumentId)
                .orElse(null);
        Assertions.assertNotNull(referenceDocumentFromDb);
        SomeDocument someDocumentFromReference = referenceDocumentFromDb.getSomeDocument();
        Assertions.assertEquals(saved.getId(), someDocumentFromReference.getId());
        Assertions.assertEquals(saved.getText(), someDocumentFromReference.getText());

        Assertions.assertDoesNotThrow(() -> someDocumentRepository.save(someDocumentFromReference));
    }
}
