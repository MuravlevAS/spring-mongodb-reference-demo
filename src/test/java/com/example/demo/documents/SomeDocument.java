package com.example.demo.documents;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class SomeDocument {
     @Id
     private ObjectId id;
     private String text;

     @JsonManagedReference
     @ReadOnlyProperty
     @DocumentReference(lookup = "{ 'someDocument' : ?#{#self._id} }")
     List<ReferenceDocument> references;
}

