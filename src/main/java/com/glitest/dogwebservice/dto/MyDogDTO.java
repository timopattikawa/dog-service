package com.glitest.dogwebservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyDogDTO {

    private String name;
    private String type;
    private String subType;
    private String image;

}
