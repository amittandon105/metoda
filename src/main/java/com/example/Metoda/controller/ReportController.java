package com.example.Metoda.controller;
import com.example.Metoda.dto.ReportDTO;
import com.example.Metoda.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/yearly")
    public List<ReportDTO> getReport() {
        return service.generateReport();
    }
}