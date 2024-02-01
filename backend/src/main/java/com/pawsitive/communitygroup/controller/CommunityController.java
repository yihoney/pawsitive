package com.pawsitive.communitygroup.controller;

import static org.springframework.http.HttpStatus.OK;

import com.pawsitive.communitygroup.dto.request.CommunityCreateReq;
import com.pawsitive.communitygroup.dto.response.CommunityDetailRes;
import com.pawsitive.communitygroup.service.CommunityService;
import com.pawsitive.doggroup.dto.response.DogDetailRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "07.Community")
@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping
    @Operation(summary = "커뮤니티 전체 조회",
        description = "커뮤니티 <strong>전체 조회</strong>를 한다. 파라미터로 카테고리 번호가 넘어온다면 <strong>카테고리별 커뮤니티 목록을 조회</strong> 한다.",
        tags = {"07.Community"},
        responses = {
            @ApiResponse(responseCode = "200", description = "전체 커뮤니티 목록 또는 카테고리에 해당되는 전체 커뮤니티 목록을 정상적으로 반환한다."),
        }
    )
    public ResponseEntity<List<CommunityDetailRes>> getCommunityList(
        @RequestParam(required = false) Integer categoryNo) {
        if (categoryNo == null) {
            return ResponseEntity
                .status(OK)
                .body(communityService.getCommunityList());
        }
        return ResponseEntity
            .status(OK)
            .body(communityService.getCommunityListByCommunityCategoryNo(categoryNo));
    }

    @GetMapping("/recommendation")
    @Operation(summary = "커뮤니티 인기글 조회",
        description = "<strong>인기글 목록을 파라미터로 전달 받은 조회할 갯수만큼 조회</strong> 한다.",
        tags = {"07.Community"},
        responses = {
            @ApiResponse(responseCode = "200", description = "인기 커뮤니티 글 목록을 정상적으로 반환한다."),
        }
    )
    public ResponseEntity<List<CommunityDetailRes>> getRecommendationCommunityList(
        @RequestParam Integer num) {
        return ResponseEntity
            .status(OK)
            .body(communityService.getRecommendationCommunityList(num));
    }

    @GetMapping("/{boardNo}")
    @Operation(summary = "커뮤니티 상세 조회",
        description = "커뮤니티를 <strong>커뮤니티 고유번호</strong>로 <strong>상세 조회</strong> 한다.",
        tags = {"07.Community"},
        responses = {
            @ApiResponse(responseCode = "200", description = "커뮤니티 고유번호에 해당되는 커뮤니티 세부사항을 정상적으로 반환한다."),
        }
    )
    public ResponseEntity<CommunityDetailRes> getCommunity(@PathVariable int boardNo) {
        return ResponseEntity
            .status(OK)
            .body(communityService.getCommunity(boardNo));
    }

    @PostMapping
    @Operation(summary = "커뮤니티 글 등록", description = "전달받은 입력 정보를 커뮤니티 글 테이블에 등록합니다.",
        tags = {"07.Community"},
        responses = {
            @ApiResponse(responseCode = "201", description = "커뮤니티 등록 성공"),
            @ApiResponse(responseCode = "400", description = "커뮤니티 글 등록에 필요한 정보가 유효하지 않음")
        }
    )
    public ResponseEntity<DogDetailRes> createCommunity(@Valid @RequestPart CommunityCreateReq req,
                                                        @RequestPart(required = false)
                                                        MultipartFile image) throws Exception {

//        Community Community = dogService.createDog(req, video, images);

        return ResponseEntity
            .status(OK)
            .body(null);
    }


}
