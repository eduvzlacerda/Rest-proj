package com.example.rest_proj.model.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
public class OperationStatusModel {
    private String operationResult;
    private String operationName;
}
