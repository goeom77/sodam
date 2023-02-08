package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.service.ClientService;
import com.samsung.sodam.db.entity.Client;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    @ApiOperation(value = "client 상세정보 조회", notes="client 본인이 사용하는 client 정보 조회")
    @GetMapping("/{id}")
    public Client getClientInfo(@PathVariable String id) {
        return clientService.getClientDetail(id);
    }
}
