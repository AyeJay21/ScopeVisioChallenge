package com.example.codingchallenge.insuranceApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceApplicationRepository extends JpaRepository<InsuranceApplication, UUID> {
}
