package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.HelpDeskRequest;
import com.samsung.sodam.api.service.AdminServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final AdminServiceImpl service;

    public AdminController(AdminServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/notice/writing")
    @ApiOperation(value = "공지사항 등록", notes = "관리자가 등록하는 공지사항")
    public ResponseEntity<HttpStatus> saveNotice(@RequestBody HelpDeskRequest request) {
        try {
            service.saveNotice(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/notice/{id}")
    @ApiOperation(value = "공지사항 수정", notes = "관리자면 모든 게시글 수정 가능")
    public ResponseEntity<HttpStatus> updateNotice(@PathVariable Long id, @RequestBody HelpDeskRequest request) {
        try {
            service.updateNotice(id, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(value = "/notice/{id}")
    @ApiOperation(value = "공지사항 삭제", notes = "관리자면 모든 게시글 삭제 가능")
    public ResponseEntity<HttpStatus> deleteNotice(@PathVariable Long id) {
        try {
            service.deleteNotice(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/qna/comment/writing")
    @ApiOperation(value = "문의게시판 댓글 등록", notes = "관리자가 댓글 작성")
    public ResponseEntity<HttpStatus> saveQnaAnswer(@RequestBody HelpDeskRequest request) {
        try {
            service.saveQnaAnswer(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/qna/comment/{id}")
    @ApiOperation(value = "문의게시판 댓글 수정", notes = "관리자면 모든 댓글 수정 가능")
    public ResponseEntity<HttpStatus> updateQnaAnswer(@PathVariable Long id, @RequestBody HelpDeskRequest request) {
        try {
            service.updateQnaAnswer(id, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(value = "/qna/comment/{id}")
    @ApiOperation(value = "문의게시판 댓글 삭제", notes = "관리자면 모든 댓글 삭제 가능")
    public ResponseEntity<HttpStatus> deleteQnaAnswer(@PathVariable Long id) {
        try {
            service.deleteQnaAnswer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
