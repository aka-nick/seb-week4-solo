package seb.week4solo.week4solo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import seb.week4solo.week4solo.domain.Member;
import seb.week4solo.week4solo.domain.Region;
import seb.week4solo.week4solo.domain.Role;
import seb.week4solo.week4solo.domain.Sector;
import seb.week4solo.week4solo.dto.MemberDto;
import seb.week4solo.week4solo.repository.MemberRepository;
import seb.week4solo.week4solo.util.RegionUtils;
import seb.week4solo.week4solo.util.SectorUtils;

import java.util.Optional;

@Slf4j
@Transactional
@Service
public class MemberService {

    private MemberRepository memberRepository;
    private SectorUtils sectorUtils;
    private RegionUtils regionUtils;

    public MemberService(MemberRepository memberRepository, SectorUtils sectorUtils, RegionUtils regionUtils) {
        this.memberRepository = memberRepository;
        this.sectorUtils = sectorUtils;
        this.regionUtils = regionUtils;
    }

    public Member join(MemberDto.JoinPost joinPost) {

        Sector sector = sectorUtils.find(joinPost.getSectorCode())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 업종"));

        Region region = regionUtils.find(joinPost.getRegionCode())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 지역"));

        log.info("######  gender :" + joinPost.getGender());

        Member member = Member.builder()
                .username(joinPost.getUsername())
                .password(joinPost.getPassword())
                .name(joinPost.getName())
                .role(Role.USER.getRoleString())
                .gender(joinPost.getGender().getGenderNumber())
                .companyName(joinPost.getCompanyName())
                .sector(sector)
                .region(region)
                .build();

        return memberRepository.save(member);
    }

    public Optional<Member> login(MemberDto.LoginPost loginPost) {
        Optional<Member> member = memberRepository.findByUsername(loginPost.getUsername());

        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "아이디 또는 비밀번호가 잘못됨");
        }
        else if (!member.get().password.equals(loginPost.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "아이디 또는 비밀번호가 잘못됨");
        }

        return member;
    }

    public Page<Member> memberList(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
    public Page<Member> memberList(Pageable pageable, Long sectorCode, Long regionCode) {
        if (sectorCode != null && regionCode != null) {
            return memberRepository.findAllBySectorAndRegion(pageable,
                    new Sector(sectorCode, null),
                    new Region(regionCode, null));
        }
        else if (sectorCode != null) {
            return memberRepository.findAllBySector(pageable, new Sector(sectorCode, null));
        }
        else {
            return memberRepository.findAllByRegion(pageable, new Region(regionCode, null));
        }
    }
}
