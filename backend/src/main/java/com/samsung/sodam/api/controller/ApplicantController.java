package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.ConsultApplicantRequest;
import com.samsung.sodam.api.service.ApplicantService;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.repository.ApplicantRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicantController {

    final
    ApplicantService service;

    public ApplicantController(ApplicantService service) {
        this.service = service;
    }

    @GetMapping("/consultApplicant")
    @ApiOperation(value = "나의 고객 조회(상담사용)")
    public List<ConsultApplicant> makeConsultApplicant(String counselorId) {
        return service.getMyApplicant(counselorId);
    }
}
