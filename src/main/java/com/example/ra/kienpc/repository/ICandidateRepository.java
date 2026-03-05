package com.example.ra.kienpc.repository;

import com.example.ra.kienpc.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandidateRepository extends JpaRepository<Candidate, Long> {
}
