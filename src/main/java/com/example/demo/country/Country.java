package com.example.demo.country;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("countries")
@Data
@Builder
public class Country {

    @Id
    private String id;

    private String name;

}
