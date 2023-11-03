package com.glitest.dogwebservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyDog {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String subType;
    private String image;
}
