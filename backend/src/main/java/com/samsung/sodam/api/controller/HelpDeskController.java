package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.HelpDeskRequest;
import com.samsung.sodam.api.response.QnABoardResponse;
import com.samsung.sodam.api.response.QnAOneBoardResponse;
import com.samsung.sodam.api.service.HelpDeskServiceImpl;
import com.samsung.sodam.db.entity.Notice;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/help-desk")
public class HelpDeskController {

    private final HelpDeskServiceImpl service;

    public HelpDeskController(HelpDeskServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/notice/list")
    @ApiOperation(value = "공지사항 전체 목록보기", notes = "공지사항 전체 게시글")
    public ResponseEntity<Page<Notice>> getAllNoticeList(Pageable pageable,
                                                         @RequestParam(name = "searchword", required = false) String searchWord) {

        try {
            Page<Notice> list = service.getAllNoticeList(searchWord, pageable);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/notice/{id}")
    @ApiOperation(value = "공지사항 상세보기", notes = "공지사항 상세")
    public ResponseEntity<Notice> getOneNotice(@PathVariable Long id) {

        try {
            Notice notice = service.getOneNotice(id);
            return new ResponseEntity<>(notice, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/qna/list")
    @ApiOperation(value = "문의글 전체 목록", notes = "문의사항 전체 목록 및 검색")
    public ResponseEntity<Page<QnABoardResponse>> getAllQnaBoard(Pageable pageable, @AuthenticationPrincipal UserDetails user,
                                                           @RequestParam(name = "searchword", required = false) String searchWord) {
        try {
            String userId = "";
            if(user != null)
                userId = user.getUsername();

            Page<QnABoardResponse> list = service.getAllQnaBoard(userId, searchWord, pageable);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="/my-qna")
    @ApiOperation(value="문의게시글 내글보기", notes="내가 작성한 문의게시글 목록")
    public ResponseEntity<Page<QnABoardResponse>> getMyTroubleList(Pageable pageable, @AuthenticationPrincipal UserDetails user) {

        try {
            String userId = "";
            if(user != null)
                userId = user.getUsername();

            Page<QnABoardResponse> list = service.getMyQnaBoard(userId, pageable);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/qna/{id}")
    @ApiOperation(value = "문의글 상세보기", notes = "문의글 상세정보와 댓글")
    public ResponseEntity<QnAOneBoardResponse> getOneQnaBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        try {
            String userId = "";
            if(user != null)
                userId = user.getUsername();

            QnAOneBoardResponse list = service.getOneQnaBoard(userId, id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/qna/writing")
    @ApiOperation(value = "문의글 작성", notes = "상담사, 내담자 문의글 등록")
    public ResponseEntity<HttpStatus> saveQnaBoard(@RequestBody HelpDeskRequest request, @AuthenticationPrincipal UserDetails user) {
        try {
            service.saveQnaBoard(user.getUsername(), request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/qna/{id}")
    @ApiOperation(value = "문의글 수정", notes = "작성자(내담자, 상담사) 문의글 수정")
    public ResponseEntity<HttpStatus> updateQnaBoard(@PathVariable Long id, @RequestBody HelpDeskRequest request) {
        try {
            service.updateQnaBoard(id, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(value = "/qna/{id}")
    @ApiOperation(value = "문의글 삭제", notes = "작성자(내담자, 상담사) 문의글 삭제")
    public ResponseEntity<HttpStatus> deleteQnaBoard(@PathVariable Long id) {
        try {
            service.deleteQnaBoard(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
