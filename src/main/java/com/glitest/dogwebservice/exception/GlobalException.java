package com.glitest.dogwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException {

    private String errorMessage;

    private String errorCause;

    private LocalDateTime localDateTime;

}
