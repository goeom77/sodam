package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.service.ClientService;
import com.samsung.sodam.db.entity.Client;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/{id}")
    @ApiOperation(value="client 정보 수정", notes="고객 정보 수정 - email, 전화번호")
    public HttpStatus editProfilecClient(@PathVariable String id, @RequestBody ClientRequest request) {
        try {
            System.out.println("editProfileClient- parameter test");
            clientService.editProfile(request, id);
            return HttpStatus.OK;
        } catch(Exception e){
            e.printStackTrace();
            return HttpStatus.NOT_FOUND;
        }
    }
}
