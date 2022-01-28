package com.example.demo.repository;

import com.example.demo.model.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmRepository extends JpaRepository<Firm, Integer> {
}