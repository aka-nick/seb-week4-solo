package seb.week4solo.week4solo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import seb.week4solo.week4solo.domain.Gender;
import seb.week4solo.week4solo.domain.Member;
import seb.week4solo.week4solo.dto.MemberDto;
import seb.week4solo.week4solo.service.MemberService;
import seb.week4solo.week4solo.util.RegionUtils;
import seb.week4solo.week4solo.util.SectorUtils;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Validated
@Controller
public class MemberController {

    private final MemberService memberService;
    private final SectorUtils sectorUtils;
    private final RegionUtils regionUtils;

    public MemberController(MemberService memberService, SectorUtils sectorUtils, RegionUtils regionUtils) {
        this.memberService = memberService;
        this.sectorUtils = sectorUtils;
        this.regionUtils = regionUtils;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/join")
    public String join(Model model) {

        makeSelectorForGender(model);
        makeSelectorForSectorOrRegion(model);

        return "join";
    }

    @PostMapping("/join")
    public String joinProc(MemberDto.JoinPost joinPost, RedirectAttributes redirectAttributes) {

        memberService.join(joinPost);
        redirectAttributes.addAttribute("joined", true);

        return "redirect:/login";
    }
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ResponseEntity handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(403)
                .body(e.getMessage());
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProc(MemberDto.LoginPost loginPost, HttpSession session, RedirectAttributes redirectAttributes) {

        memberService.login(loginPost);
        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", 1);

        return "redirect:/members";
    }

    @GetMapping("/members")
    public String members(@PageableDefault(page = 0, size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                          @Positive @RequestParam(value = "sectorCode", required = false) Long sectorCode,
                          @Positive @RequestParam(value = "regionCode", required = false) Long regionCode,
                          Model model) {

        makeSelectorForSectorOrRegion(model);

        Page<Member> members = null;
        if (sectorCode != null || regionCode != null) {
            members = memberService.memberList(pageable, sectorCode, regionCode);
            model.addAttribute("findSectorCode", sectorCode);
            model.addAttribute("findRegionCode", regionCode);
        }
        else {
            members = memberService.memberList(pageable);
        }
        model.addAttribute("members", members);
        model.addAttribute("page", pageable.getPageNumber());

        return "members/list";
    }

    private void makeSelectorForGender(Model model) {
        model.addAttribute("genderMap", Stream.of(Gender.values())
                .collect(Collectors.toMap(Gender::getGenderNumber, Gender::getGenderKoreanString)));
    }
    private void makeSelectorForSectorOrRegion(Model model) {
        model.addAttribute("sectorList", sectorUtils.findAll());
        model.addAttribute("regionList", regionUtils.findAll());
    }


}
