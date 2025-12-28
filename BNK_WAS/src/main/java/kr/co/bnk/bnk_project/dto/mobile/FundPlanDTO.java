package kr.co.bnk.bnk_project.dto.mobile;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FundPlanDTO {

    private Long planId;
    private Long custNo;
    private String acctNo;
    private String fundCode;
    private String status;
    private String cycleType;
    private Long amount;
    private LocalDateTime startAt;
    private Integer weekday;
    private Integer dayOfMonth;
    private LocalDateTime createdAt;
    private String investmentType;   // 한번만 / 매일,매주,매월(자동이체)

}
