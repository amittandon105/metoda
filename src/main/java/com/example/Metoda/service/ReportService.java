package com.example.Metoda.service;
import com.example.Metoda.dto.ReportDTO;
import com.example.Metoda.entity.Txn;
import com.example.Metoda.repository.TxnRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    private final TxnRepository repo;

    public ReportService(TxnRepository repo) {
        this.repo = repo;
    }

    public List<ReportDTO> generateReport() {

        List<Txn> txns = repo.findAll();

        Map<Integer, Stats> map = new HashMap<>();

        for (Txn txn : txns) {

            int key =
                    ("SUCCESS".equalsIgnoreCase(txn.getStatus()) ? 1 : 0) << 0 |
                            ("DOMESTIC".equalsIgnoreCase(txn.getPaymentType()) ? 1 : 0) << 1 |
                            ("ONLINE".equalsIgnoreCase(txn.getChannel()) ? 1 : 0) << 2 |
                            ("LOW".equalsIgnoreCase(txn.getRisk()) ? 1 : 0) << 3 |
                            ("INR".equalsIgnoreCase(txn.getCurrency()) ? 1 : 0) << 4;

            map.computeIfAbsent(key, k -> new Stats()).add(txn.getAmount());
        }

        List<ReportDTO> result = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            Stats s = map.getOrDefault(i, new Stats());

            result.add(new ReportDTO(
                    decode(i),
                    s.count,
                    s.sum
            ));
        }

        return result;
    }

    // Decode key → readable output
    private String decode(int key) {
        return "status=" + (((key >> 0) & 1) == 1 ? "SUCCESS" : "FAIL") +
                ", payment=" + (((key >> 1) & 1) == 1 ? "DOMESTIC" : "CROSS") +
                ", channel=" + (((key >> 2) & 1) == 1 ? "ONLINE" : "OFFLINE") +
                ", risk=" + (((key >> 3) & 1) == 1 ? "LOW" : "HIGH") +
                ", currency=" + (((key >> 4) & 1) == 1 ? "INR" : "USD");
    }

    static class Stats {
        long count;
        double sum;

        void add(double amt) {
            count++;
            sum += amt;
        }
    }
}