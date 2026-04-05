package com.example.Metoda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportDTO {
    String combination;
    long count;
    double sum;
}