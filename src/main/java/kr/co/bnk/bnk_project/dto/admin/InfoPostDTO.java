package kr.co.bnk.bnk_project.dto.admin;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InfoPostDTO {

    private int postId;
    private String postType;
    private String title;
    private String summary;
    private String content;
    private String Status;
    private LocalDateTime publishStartAt;
    private LocalDateTime publishEndAt;
    private String channels;
    private int categoryId;    // 게시판 구분
    private int versionOn;
    private String isLatest;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;

    // 추가 컬럼
    private String disclosureType; // 공시자료 내부 분류 : 운용보고서, 안내사항 등.

}
