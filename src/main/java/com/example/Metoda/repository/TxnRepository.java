package com.example.Metoda.repository;

import com.example.Metoda.entity.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn, Integer> {
}