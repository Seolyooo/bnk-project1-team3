package com.ap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyFundResponse {
    private String fundCode;        // 펀드 코드
    private String fundName;        // 펀드명
    private String viewStatus;      // "HOLDING" 또는 "PENDING"
    private Long holdUnit;          // 보유좌수
    private Long investedAmt;       // 투자 원금 (가입 시점)
    private Long pendingAmount;     // 신청중 금액
    private Long profitAmount;      // 평가손익 금액
    private Double profitRate;      // 수익률 (%)
}

