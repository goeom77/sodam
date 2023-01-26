package com.samsung.sodam.api.controller;

import com.samsung.sodam.db.entity.Enterprise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/enterprise")
public class EnterpriseController {
    @PostMapping(value = "/")
    //@ApiOperation(value="회사 등록", notes="새로운 회사 등록")
    public ResponseEntity<Integer> createEnterprise(@RequestBody Enterprise enterprise) {
        // 회사 등록 후 회사 아이디 가져오기
        return new ResponseEntity<>(enterprise.getEnterpriseId(), HttpStatus.OK);
    }

}
