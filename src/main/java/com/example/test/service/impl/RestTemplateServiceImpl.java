package com.example.test.service.impl;

import com.example.test.dto.MemberDTO;
import com.example.test.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public String getTest(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/test")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }
    @Override
    public String getName(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/name")
                .queryParam("name", "Chanho")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/path-variable/{name}")
                .encode()
                .build()
                .expand("Chanho")
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();

    }
    @Override
    public ResponseEntity<MemberDTO> postDto(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/member")
                .queryParam("name", "Chanho")
                .queryParam("email", "ccc@CCC.com")
                .queryParam("organization", "Test")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("cksgh");
        memberDTO.setEmail("aaa@aaa.com");
        memberDTO.setOrganization("Test");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }

    @Override
    public ResponseEntity<MemberDTO> addHeader(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/add-header")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("cksgh");
        memberDTO.setEmail("aaa@aaa.com");
        memberDTO.setOrganization("Test");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri)
                .header("test-header", "Test")
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }
}
