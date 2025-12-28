package kr.co.bnk.bnk_project.exception;

import kr.co.bnk.bnk_project.dto.mobile.FundPlanDTO;
import lombok.Getter;

/**
 * 중복 펀드 가입 예외
 * 이미 가입된 펀드 정보를 포함합니다.
 */
@Getter
public class DuplicateFundSubscriptionException extends IllegalStateException {
    
    private final FundPlanDTO existingSubscription;
    
    public DuplicateFundSubscriptionException(String message, FundPlanDTO existingSubscription) {
        super(message);
        this.existingSubscription = existingSubscription;
    }
}

