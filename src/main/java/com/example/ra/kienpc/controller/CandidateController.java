package com.example.ra.kienpc.controller;

import com.example.ra.kienpc.model.ApiResponse;
import com.example.ra.kienpc.model.dto.CandidateApplyDto;
import com.example.ra.kienpc.model.entity.Candidate;
import com.example.ra.kienpc.service.ICandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    @Autowired
    private ICandidateService candidateService;

    @PostMapping
    public ResponseEntity<ApiResponse<Candidate>> candidateApply(@Valid @ModelAttribute CandidateApplyDto candidateApplyDto) throws IOException {
        Candidate candidate = candidateService.candidateApply(candidateApplyDto);
        ApiResponse<Candidate> apiResponse = new ApiResponse<>("Create","Success", candidate);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
