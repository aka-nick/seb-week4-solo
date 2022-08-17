package seb.week4solo.week4solo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seb.week4solo.week4solo.domain.Member;
import seb.week4solo.week4solo.dto.MemberDto;
import seb.week4solo.week4solo.service.MemberService;

import javax.validation.constraints.Positive;
import java.util.Optional;

@Slf4j
@RequestMapping("/api/v1")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody MemberDto.JoinPost joinPost) {
        Member joinedMember = memberService.join(joinPost);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MemberDto.JoinResponse.of(joinedMember));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberDto.LoginPost loginPost) {
        Optional<Member> loginMember = memberService.login(loginPost);

        return ResponseEntity.status(200)
                .body(MemberDto.LoginResponse.of(loginMember.orElseThrow()));
    }

    @GetMapping("/members")
    public ResponseEntity members(@PageableDefault(page = 0, size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                      Pageable pageable,
                                  @Positive @RequestParam(value = "sectorCode", required = false) Long sectorCode,
                                  @Positive @RequestParam(value = "regionCode", required = false) Long regionCode) {

        Page<Member> members = null;
        if (sectorCode != null || regionCode != null) {
            members = memberService.memberList(pageable, sectorCode, regionCode);
        }
        else {
            members = memberService.memberList(pageable);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(MemberDto.MembersResponse.of(members));
    }

}
