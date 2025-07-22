package com.example.demo.documents;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDocument {
    @Id
    private ObjectId id;
    private String text;
    @DocumentReference(lazy = true)
    @JsonBackReference
    private SomeDocument someDocument;
}

