package seb.week4solo.week4solo.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import seb.week4solo.week4solo.domain.Member;
import seb.week4solo.week4solo.domain.Region;
import seb.week4solo.week4solo.domain.Role;
import seb.week4solo.week4solo.domain.Sector;
import seb.week4solo.week4solo.dto.MemberDto;
import seb.week4solo.week4solo.service.MemberService;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@WebMvcTest(MemberApiController.class)
class MemberApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    private static final String COMMON_ENDPOINT = "/api/v1";
    private static final MediaType ENCODING = MediaType.APPLICATION_JSON;

    @DisplayName("회원 가입 테스트")
    @Test
    void joinTest() throws Exception {
        // given
        MemberDto.JoinPost payload =
                new MemberDto.JoinPost("testUsername",
                        "testPassword",
                        "테스터",
                        1,
                        "testCompanyName",
                        7,
                        1);
        String requestJsonString = gson.toJson(payload);

        Member joinedMember = Member.builder()
                .username(payload.getUsername())
                .password(payload.getPassword())
                .name(payload.getName())
                .role(Role.USER.getRoleString())
                .gender(payload.getGender().getGenderNumber())
                .companyName(payload.getCompanyName())
                .sector(new Sector(7L, "백엔드"))
                .region(new Region(1L, "서울특별시"))
                .build();
        joinedMember.setId(1L);

        given(memberService.join(Mockito.any(MemberDto.JoinPost.class)))
                .willReturn(joinedMember);


        // when
        ResultActions actions = this.mockMvc.perform(
                post(COMMON_ENDPOINT + "/join")
                        .accept(ENCODING)
                        .contentType(ENCODING)
                        .content(requestJsonString)
        );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(payload.getUsername()))
                .andExpect(jsonPath("$.userName").value(payload.getName()))
                .andExpect(jsonPath("$.companyName").value(payload.getCompanyName()))
                .andDo(
                        document(
                                "join-member",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                requestFields(
                                        List.of(
                                                fieldWithPath("username").type(JsonFieldType.STRING).description("유저 아이디"),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("유저 비밀번호"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
                                                fieldWithPath("genderNumber").type(JsonFieldType.NUMBER).description("성별 코드 [남자:1, 여자:2, 선택하지 않음:3]"),
                                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사명"),
                                                fieldWithPath("sectorCode").type(JsonFieldType.NUMBER).description("업종 코드"),
                                                fieldWithPath("regionCode").type(JsonFieldType.NUMBER).description("지역 코드")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("userNumber").type(JsonFieldType.NUMBER).description("유저 식별자"),
                                                fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
                                                fieldWithPath("userName").type(JsonFieldType.STRING).description("유저 이름"),
                                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사명")
                                        )
                                )
                        )
                );

    }

    @DisplayName("로그인 테스트")
    @Test
    void loginTest() throws Exception {
        // given
        MemberDto.LoginPost payload = new MemberDto.LoginPost(
                "testUsername",
                "testPassword"
        );
        String requestJsonString = gson.toJson(payload);

        MemberDto.LoginResponse loginMember = new MemberDto.LoginResponse(
                1L,
                "testUsername",
                "testName",
                "남자",
                "Alphabet",
                7L,
                1L
        );

        Member joinedMember = Member.builder()
                .username("testUsername")
                .name("testName")
                .gender(1)
                .companyName("Alphabet")
                .sector(new Sector(7L, "백엔드"))
                .region(new Region(1L, "서울특별시"))
                .build();
        joinedMember.setId(1L);

        given(memberService.login(Mockito.any(MemberDto.LoginPost.class)))
                .willReturn(Optional.of(joinedMember));

        // when
        ResultActions actions = this.mockMvc.perform(
                post(COMMON_ENDPOINT + "/login")
                        .accept(ENCODING)
                        .contentType(ENCODING)
                        .content(requestJsonString)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userNumber").value(joinedMember.getId()))
                .andExpect(jsonPath("$.userId").value(joinedMember.getUsername()))
                .andExpect(jsonPath("$.userName").value(joinedMember.getName()))
                .andExpect(jsonPath("$.companyName").value(joinedMember.getCompanyName()))
                .andExpect(jsonPath("$.sectorCode").value(joinedMember.getSector().getSectorCode()))
                .andExpect(jsonPath("$.regionCode").value(joinedMember.getRegion().getRegionCode()))
                .andDo(
                        document(
                                "login-member",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                requestFields(
                                        List.of(
                                                fieldWithPath("username").type(JsonFieldType.STRING).description("유저 아이디"),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("유저 비밀번호")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("userNumber").type(JsonFieldType.NUMBER).description("유저 식별자"),
                                                fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
                                                fieldWithPath("userName").type(JsonFieldType.STRING).description("유저 이름"),
                                                fieldWithPath("gender").type(JsonFieldType.STRING).description("유저 성별"),
                                                fieldWithPath("companyName").type(JsonFieldType.STRING).description("회사명"),
                                                fieldWithPath("sectorCode").type(JsonFieldType.NUMBER).description("업종 코드"),
                                                fieldWithPath("regionCode").type(JsonFieldType.NUMBER).description("지역 코드")
                                        )
                                )
                        )
                );
    }

    @DisplayName("멤버 조회 테스트")
    @Test
    void membersTest() throws Exception {
        // given
        Member joinedMember1 = Member.builder()
                .username("testUsername1")
                .password("testPassword1")
                .name("testName1")
                .role("ROLE_USER")
                .gender(1)
                .companyName("Alphabet1")
                .sector(new Sector(7L, "백엔드"))
                .region(new Region(1L, "서울특별시"))
                .build();
        joinedMember1.setId(1L);
        Member joinedMember2 = Member.builder()
                .username("testUsername2")
                .password("testPassword2")
                .name("testName2")
                .role("ROLE_USER")
                .gender(1)
                .companyName("Alphabet2")
                .sector(new Sector(7L, "백엔드"))
                .region(new Region(1L, "서울특별시"))
                .build();
        joinedMember2.setId(2L);
        Member joinedMember3 = Member.builder()
                .username("testUsername3")
                .password("testPassword3")
                .name("testName3")
                .role("ROLE_USER")
                .gender(1)
                .companyName("Alphabet3")
                .sector(new Sector(7L, "백엔드"))
                .region(new Region(1L, "서울특별시"))
                .build();
        joinedMember3.setId(3L);

        List<Member> joinedMembers = List.of(joinedMember1, joinedMember2, joinedMember3);

        Page<Member> members = new PageImpl<>(joinedMembers);

        given(memberService.memberList(Mockito.any(Pageable.class), Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(members);

        int page = 0;
        int size = 10;
        Long sectorCode = 7L;
        Long regionCode = 1L;

        // when
        ResultActions actions = this.mockMvc.perform(
                get(COMMON_ENDPOINT + "/members")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sectorCode", String.valueOf(sectorCode))
                        .param("regionCode", String.valueOf(regionCode))
                        .accept(ENCODING)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pagedata.totalCounts").value(joinedMembers.size()))
                .andDo(
                        document(
                                "retrieve-member",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                pathParameters(
                                        parameterWithName("sectorCode").description("업종 코드").optional(),
                                        parameterWithName("regionCode").description("지역 코드").optional()
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("content").type(JsonFieldType.ARRAY).description("유저 데이터 목록"),
                                                fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("유저 식별자"),
                                                fieldWithPath("content[].username").type(JsonFieldType.STRING).description("유저 아이디"),
                                                fieldWithPath("content[].password").type(JsonFieldType.STRING).description("유저 패스워드"),
                                                fieldWithPath("content[].role").type(JsonFieldType.STRING).description("유저 역할"),
                                                fieldWithPath("content[].name").type(JsonFieldType.STRING).description("유저 이름"),
                                                fieldWithPath("content[].gender").type(JsonFieldType.NUMBER).description("성별 코드 [남자:1, 여자:2, 선택하지 않음:3]"),
                                                fieldWithPath("content[].companyName").type(JsonFieldType.STRING).description("회사명"),
                                                fieldWithPath("content[].sector.sectorCode").type(JsonFieldType.NUMBER).description("업종 코드"),
                                                fieldWithPath("content[].sector.sectorName").type(JsonFieldType.STRING).description("업종 이름"),
                                                fieldWithPath("content[].region.regionCode").type(JsonFieldType.NUMBER).description("지역 코드"),
                                                fieldWithPath("content[].region.regionName").type(JsonFieldType.STRING).description("지역 이름"),

                                                fieldWithPath("pagedata").type(JsonFieldType.OBJECT).description("조회 데이터"),
                                                fieldWithPath("pagedata.nowCount").type(JsonFieldType.NUMBER).description("조회한 요소 수"),
                                                fieldWithPath("pagedata.nowPage").type(JsonFieldType.NUMBER).description("조회된 페이지"),
                                                fieldWithPath("pagedata.totalCounts").type(JsonFieldType.NUMBER).description("총 요소 수"),
                                                fieldWithPath("pagedata.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
                                        )
                                )
                        )
                );
    }

}