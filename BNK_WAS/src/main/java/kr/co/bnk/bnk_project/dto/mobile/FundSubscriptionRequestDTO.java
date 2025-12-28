package kr.co.bnk.bnk_project.dto.mobile;

import lombok.Data;

@Data
public class FundSubscriptionRequestDTO {

    private String fundCode;        // 펀드 코드
    private Long amount;            // 투자 금액
    private String investmentType;  // 한 번만 또는 매일, 매주, 매월
    private String cycleType;       // 매일, 매주, 매월 (자동이체)
    private Integer weekday;        // 매주인 경우(1~7)
    private Integer dayOfMonth;     // 매월인 경우(1~31)

    private Long custNo;    // 임시
    private String acctNo;  // 임시


}
