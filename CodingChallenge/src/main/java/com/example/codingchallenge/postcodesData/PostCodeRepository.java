package com.example.codingchallenge.postcodesData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostCodeRepository extends JpaRepository<PostCode, UUID> {
}
