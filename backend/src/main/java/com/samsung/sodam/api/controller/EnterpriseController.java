package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.EnterpriseRequest;
import com.samsung.sodam.api.service.EnterpriseService;
import com.samsung.sodam.db.entity.Enterprise;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/enterprise")
@RequiredArgsConstructor
public class EnterpriseController {
    private final EnterpriseService enterpriseService;
    @PostMapping(value = "/")
    @ApiOperation(value="회사 등록", notes="새로운 회사 등록 후 회사 PK 리턴")
    public ResponseEntity<Integer> createEnterprise(@RequestBody EnterpriseRequest enterpriseRequest) {
        // 회사 등록 후 회사 아이디 가져오기
        int enterpriseId = enterpriseService.addEnterprise(enterpriseRequest);
        return new ResponseEntity<>(enterpriseId, HttpStatus.OK);
    }    
    @GetMapping(value = "/")
    @ApiOperation(value="사업자 등록코드 유효 확인", notes="사업자 등록 코드 유효 여부 확인")
    public HttpStatus checkCorporateCode(@RequestParam String Code) {
        // Code 검사하기
        return HttpStatus.OK;
    }
    @GetMapping(value = "/search")
    @ApiOperation(value="사업자 검색", notes="사업자 검색(상호명)")
    public ResponseEntity<List<Enterprise>> searchByName(@RequestParam String searchWord, Pageable pageable) {
        // 입력된 단어로 검색하기
        List<Enterprise> list = enterpriseService.searchByName(searchWord, pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
