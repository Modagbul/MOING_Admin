package com.moing.bo.backend.domain.team.presentation;

import com.moing.bo.backend.config.CommonControllerTest;
import com.moing.bo.backend.domain.team.application.dto.response.GetNewTeamResponse;
import com.moing.bo.backend.domain.team.application.service.ApproveTeamUseCase;
import com.moing.bo.backend.domain.team.application.service.GetTeamUseCase;
import com.moing.bo.backend.domain.team.application.service.RejectTeamUseCase;
import com.moing.bo.backend.domain.team.domain.constant.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
public class TeamControllerTest extends CommonControllerTest {


    @MockBean
    private ApproveTeamUseCase approveTeamUseCase;

    @MockBean
    private RejectTeamUseCase rejectTeamUseCase;

    @MockBean
    private GetTeamUseCase getTeamUseCase;


    @Test
    public void get_team() throws Exception {

        //given
        List<GetNewTeamResponse> teamResponses = new ArrayList<>();

        GetNewTeamResponse getNewTeamResponse = new GetNewTeamResponse("소모임 이름", Category.STUDY, "한줄 다짐", "소모임 소개", "프로필 사진", LocalDateTime.now());
        teamResponses.add(getNewTeamResponse);

        Page<GetNewTeamResponse> output = new PageImpl<>(teamResponses);

        given(getTeamUseCase.getNewTeam(any(), any())).willReturn(output);

        //when
        ResultActions actions = mockMvc.perform(
                get("/api/admin/team?dateSort=asc&page=1&size=3")
                        .header("Authorization", "Bearer ACCESS_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                queryParameters(parameterWithName("dateSort").description("날짜 정렬 : asc || desc"),
                                        parameterWithName("page").description("페이지 번호"),
                                        parameterWithName("size").description("한 페이지에 있는 데이터 개수")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("요청 성공 여부"),
                                        fieldWithPath("message").description("응답 메시지"),
                                        fieldWithPath("data.content[0].teamName").description("소모임 이름"),
                                        fieldWithPath("data.content[0].category").description("카테고리"),
                                        fieldWithPath("data.content[0].promise").description("약속"),
                                        fieldWithPath("data.content[0].introduction").description("소모임 소개"),
                                        fieldWithPath("data.content[0].profileImgUrl").description("프로필 이미지 URL"),
                                        fieldWithPath("data.content[0].createdDate").description("생성 날짜"),
                                        fieldWithPath("data.pageable").description("페이지 설명"),
                                        fieldWithPath("data.last").description("마지막 페이지 여부"),
                                        fieldWithPath("data.totalPages").description("총 페이지 수"),
                                        fieldWithPath("data.totalElements").description("총 요소 수"),
                                        fieldWithPath("data.first").description("첫 페이지 여부"),
                                        fieldWithPath("data.size").description("페이지 크기"),
                                        fieldWithPath("data.number").description("페이지 번호"),
                                        fieldWithPath("data.sort.sorted").description("정렬 여부"),
                                        fieldWithPath("data.sort.unsorted").description("비정렬 여부"),
                                        fieldWithPath("data.sort.empty").description("정렬 항목 비어있는지 여부"),
                                        fieldWithPath("data.numberOfElements").description("요소 수"),
                                        fieldWithPath("data.empty").description("데이터 비어있는지 여부")
                                )

                        )
                );
    }


    @Test
    public void approve_team() throws Exception {
        //given

        //when
        ResultActions actions = mockMvc.perform(
                post("/api/admin/team/approval?teamIds=74")
                        .header("Authorization", "Bearer ACCESS_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON)

        );


        //then
        actions
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                queryParameters(parameterWithName("teamIds").description("승인할 팀 아이디 리스트")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description("소모임들이 승인되었습니다.")
                                )
                        )
                );
    }

    @Test
    public void reject_team() throws Exception {
        //given

        //when
        ResultActions actions = mockMvc.perform(
                post("/api/admin/team/rejection?teamIds=74")
                        .header("Authorization", "Bearer ACCESS_TOKEN")
                        .contentType(MediaType.APPLICATION_JSON)

        );


        //then
        actions
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestHeaders(
                                        headerWithName("Authorization").description("접근 토큰")
                                ),
                                queryParameters(parameterWithName("teamIds").description("반려할 팀 아이디 리스트")
                                ),
                                responseFields(
                                        fieldWithPath("isSuccess").description("true"),
                                        fieldWithPath("message").description("소모임들이 반려되었습니다.")
                                )
                        )
                );
    }
}