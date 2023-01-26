package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.TroubleCommentRequest;
import com.samsung.sodam.api.request.TroubleRequest;
import com.samsung.sodam.api.response.TroubleCommentResponse;
import com.samsung.sodam.api.response.TroubleDetailResponse;
import com.samsung.sodam.api.response.TroubleListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/trouble")
public class TroubleController {

    @GetMapping(value = "/{category}")
    //@ApiOperation(value="고민게시판 목록", notes="카테고리별 고민게시판 전체목록")
    public ResponseEntity<TroubleListResponse> getTroubleList(@PathVariable String category, @RequestParam int page,
                                                              @RequestParam int windowSize, @RequestParam String searchWord ) {

        // dummy
        TroubleListResponse troubleListResponse = new TroubleListResponse();
        List<TroubleDetailResponse> list = new ArrayList<>();

        TroubleDetailResponse troubleDetailResponse = new TroubleDetailResponse();
        troubleDetailResponse.setBoardId(1);
        troubleDetailResponse.setCategory("청소년");
        troubleDetailResponse.setType("진로");
        troubleDetailResponse.setTitle("진로를 정하는게 어렵습니다.");
        troubleDetailResponse.setContent("좋아하는 일 vs 잘하는 일");
        troubleDetailResponse.setCreatedAt("2023-01-25 23:32:00");
        troubleDetailResponse.setClientId("kim");
        troubleDetailResponse.setClientName("김고딩");
        list.add(troubleDetailResponse);

        troubleListResponse.setList(list);
        troubleListResponse.setTotalCount(1);

        if(searchWord.equals("") || searchWord == null) {   // 카테고리별 목록

        } else { // 카테고리 & 검색 목록

        }
        return new ResponseEntity<>(troubleListResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{category}/{type}")
    //@ApiOperation(value="고민게시판 목록", notes="카테고리/타입별 고민게시판 전체목록")
    public ResponseEntity<TroubleListResponse> getTroubleTagList(@PathVariable String category, @PathVariable String type, @RequestParam int page,
                                                                 @RequestParam int windowSize, @RequestParam String SearchWord ) {

        // dummy
        TroubleListResponse troubleListResponse = new TroubleListResponse();
        List<TroubleDetailResponse> list = new ArrayList<>();

        TroubleDetailResponse troubleDetailResponse = new TroubleDetailResponse();
        troubleDetailResponse.setBoardId(1);
        troubleDetailResponse.setCategory("청소년");
        troubleDetailResponse.setType("진로");
        troubleDetailResponse.setTitle("진로를 정하는게 어렵습니다.");
        troubleDetailResponse.setContent("좋아하는 일 vs 잘하는 일");
        troubleDetailResponse.setCreatedAt("2023-01-25 23:32:00");
        troubleDetailResponse.setClientId("kim");
        troubleDetailResponse.setClientName("김고딩");
        list.add(troubleDetailResponse);

        troubleListResponse.setList(list);
        troubleListResponse.setTotalCount(1);

        if(SearchWord.equals("") || SearchWord == null) {   // 카테고리 & 타입별 목록

        } else { // 카테고리 & 타입 & 검색 목록

        }
        return new ResponseEntity<>(troubleListResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    //@ApiOperation(value="고민게시글 작성", notes="고민게시글 작성")
    public ResponseEntity<HttpStatus> writeTrouble(@RequestBody TroubleRequest dto) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    //@ApiOperation(value="고민게시글 상세보기", notes="하나의 고민게시글에 대한 상세정보")
    public ResponseEntity<TroubleDetailResponse> getTrouble(@PathVariable int id) {

        // dummy
        TroubleDetailResponse troubleDetailResponse = new TroubleDetailResponse();
        troubleDetailResponse.setBoardId(1);
        troubleDetailResponse.setCategory("청소년");
        troubleDetailResponse.setType("진로");
        troubleDetailResponse.setTitle("진로를 정하는게 어렵습니다.");
        troubleDetailResponse.setContent("좋아하는 일 vs 잘하는 일");
        troubleDetailResponse.setCreatedAt("2023-01-25 23:32:00");
        troubleDetailResponse.setClientId("kim");
        troubleDetailResponse.setClientName("김고딩");
        troubleDetailResponse.setViews(10);
        troubleDetailResponse.setCommentCnt(1);

        List<TroubleCommentResponse> list = new ArrayList<>();
        TroubleCommentResponse troubleCommentResponse = new TroubleCommentResponse();
        troubleCommentResponse.setContent("좋아하는 일");
        troubleCommentResponse.setCounselorName("김상담");
        troubleCommentResponse.setLikes(2);
        troubleCommentResponse.setCreatedAt("2023-01-26 23:32:00");
        list.add(troubleCommentResponse);
        troubleDetailResponse.setComments(list);

        return new ResponseEntity<>(troubleDetailResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    //@ApiOperation(value="고민게시글 수정", notes="작성자만 고민게시글 수정")
    public ResponseEntity<HttpStatus> updateTrouble(@RequestBody TroubleRequest dto) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    //@ApiOperation(value="고민게시글 삭제", notes="작성자만 고민게시글 삭제")
    public ResponseEntity<HttpStatus> deleteTrouble(@PathVariable int id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/my-trouble")
    //@ApiOperation(value="고민게시글 내글보기", notes="내가 작성한 고민게시글 목록")
    public ResponseEntity<TroubleListResponse> getMyTroubleList() {

        // dummy
        TroubleListResponse troubleListResponse = new TroubleListResponse();
        List<TroubleDetailResponse> list = new ArrayList<>();

        TroubleDetailResponse troubleDetailResponse = new TroubleDetailResponse();
        troubleDetailResponse.setBoardId(1);
        troubleDetailResponse.setCategory("청소년");
        troubleDetailResponse.setType("진로");
        troubleDetailResponse.setTitle("진로를 정하는게 어렵습니다.");
        troubleDetailResponse.setContent("좋아하는 일 vs 잘하는 일");
        troubleDetailResponse.setCreatedAt("2023-01-25 23:32:00");
        troubleDetailResponse.setClientId("kim");
        troubleDetailResponse.setClientName("김고딩");
        list.add(troubleDetailResponse);

        troubleListResponse.setList(list);
        troubleListResponse.setTotalCount(1);

        return new ResponseEntity<>(troubleListResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/comment")
    public ResponseEntity<HttpStatus> insertComment(@RequestBody TroubleCommentRequest dto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/comment/{seq}")
    public ResponseEntity<HttpStatus> updateComment(@RequestBody TroubleCommentRequest dto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/comment/{seq}")
    public ResponseEntity<HttpStatus> deleteComment() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/comment/{seq}")
    public ResponseEntity<HttpStatus> commentLike() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
