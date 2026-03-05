package com.example.ra.kienpc.service;

import com.example.ra.kienpc.model.dto.CandidateApplyDto;
import com.example.ra.kienpc.model.entity.Candidate;

import java.io.IOException;

public interface ICandidateService {
    Candidate candidateApply(CandidateApplyDto reuqest) throws IOException;
}
