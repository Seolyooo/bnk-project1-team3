package kr.co.bnk.bnk_project.dto.mobile;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FundOrderDTO {

    private Long orderId;               // 주문(투자/환매)고유 ID
    private Long custNo;                // 고객 번호
    private String acctNo;              // 계좌 번호
    private String fundCode;            // 펀드 코드
    private String type;                // 주문 유형 BUY/SELLER
    private String status;              // 주문 상태
    private LocalDateTime requestAt;    // 투자 신청일
    private Long reqAmount;             // 사용자가 입력한 투자/환매 금액
    private Long fixAmount;             // 확정된 금액
    private Long planId;                // 자동이체인 경우
}
