package com.example.test.service;


import com.example.test.dto.MemberDTO;
import org.springframework.http.ResponseEntity;

public interface RestTemplateService {
    public String getTest();

    public String getName();

    public String getName2();

    public ResponseEntity<MemberDTO> postDto();
    public ResponseEntity<MemberDTO> addHeader();
}
