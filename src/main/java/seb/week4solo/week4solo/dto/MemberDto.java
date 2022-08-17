package seb.week4solo.week4solo.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import seb.week4solo.week4solo.domain.Gender;
import seb.week4solo.week4solo.domain.Member;

import java.util.Arrays;
import java.util.List;

public class MemberDto {
    private MemberDto() {
    }

    @Data
    @Getter
    @RequiredArgsConstructor
    public static class JoinPost {
        private final String username;
        private final String password;
        private final String name;
        private final int genderNumber;
        private final String companyName;
        private final long sectorCode;
        private final long regionCode;

        public Gender getGender() {
            Gender result;
            if (genderNumber == 1) {
                result = Gender.MALE;
            }
            else if (genderNumber == 2) {
                result = Gender.FEMALE;
            }
            else {
                result = Gender.NO_SELECTION;
            }
            return result;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class LoginPost {
        private final String username;
        private final String password;
    }

    @Getter
    @RequiredArgsConstructor
    public static class FindGet {
        private final int sectorCode;
        private final int regionCode;
    }

    @Getter
    @RequiredArgsConstructor
    public static class JoinResponse {
        private final Long userNumber;
        private final String userId;
        private final String userName;
        private final String companyName;

        public static JoinResponse of(Member member) {
            return new JoinResponse(member.getId(),
                    member.getUsername(),
                    member.getName(),
                    member.getCompanyName());
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static class LoginResponse {
        private final Long userNumber;
        private final String userId;
        private final String userName;
        private final String gender;
        private final String companyName;
        private final Long sectorCode;
        private final Long regionCode;

        public static LoginResponse of(Member member) {
            return new LoginResponse(member.getId(),
                    member.getUsername(),
                    member.getName(),
                    Arrays.stream(Gender.values())
                            .filter(v -> v.getGenderNumber() == member.getGender())
                            .findAny()
                            .orElse(null)
                            .getGenderKoreanString(),
                    member.getCompanyName(),
                    member.getSector().getSectorCode(),
                    member.getRegion().getRegionCode());
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class MembersResponse<T> {
        private final List<T> content;
        private final PageData pagedata;

        public static MembersResponse of(Page<Member> members) {
            return new MembersResponse(members.getContent(),
                    new PageData(members.getNumberOfElements(),
                            members.getNumber(),
                            members.getTotalElements(),
                            members.getTotalPages()));
        }

        @Getter
        @RequiredArgsConstructor
        static class PageData {
            private final int nowCount;
            private final int nowPage;
            private final long totalCounts;
            private final int totalPages;
        }
    }
}
