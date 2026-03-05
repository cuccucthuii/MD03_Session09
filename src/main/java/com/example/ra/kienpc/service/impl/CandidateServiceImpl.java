package com.example.ra.kienpc.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ra.kienpc.model.dto.CandidateApplyDto;
import com.example.ra.kienpc.model.entity.Candidate;
import com.example.ra.kienpc.repository.ICandidateRepository;
import com.example.ra.kienpc.service.ICandidateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CandidateServiceImpl implements ICandidateService {
    private final ICandidateRepository candidateRepository;
    private final Cloudinary cloudinary;

    @Override
    public Candidate candidateApply(CandidateApplyDto reuqest) throws IOException {
        MultipartFile cvFile = reuqest.getCvFile();
        String fileName = cvFile.getOriginalFilename();
        if (cvFile == null || cvFile.isEmpty()) {
            throw new RuntimeException("CV khong dc de trong!");
        }
        if (!fileName.endsWith(".pdf")) {
            throw new RuntimeException("CV phai la dinh dang PDF");
        }
        Map uploadFile = cloudinary.uploader().upload(
                cvFile.getBytes(),
                ObjectUtils.emptyMap()
        );
        String url = (String) uploadFile.get("url");
        // To entity
        Candidate candidate = Candidate.builder()
                .name(reuqest.getName())
                .email(reuqest.getEmail())
                .cvUrl(url)
                .build();
        return candidateRepository.save(candidate);
    }
}
