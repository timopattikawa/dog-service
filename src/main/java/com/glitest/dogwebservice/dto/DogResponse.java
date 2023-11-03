package com.glitest.dogwebservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class DogResponse {
    String status;
    Object data;
}

