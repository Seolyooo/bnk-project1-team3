package kr.co.bnk.bnk_project.mapper.mobile;

import kr.co.bnk.bnk_project.dto.mobile.FundPlanDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FundPlanMapper {

    Long getNextPlanId();

    void insertFundPlan(FundPlanDTO fundPlanDTO);

    /**
     * 오늘 실행할 자동이체 계획 조회
     */
    List<FundPlanDTO> selectPlansToExecuteToday();

    /**
     * 다음 실행 예정일 업데이트
     */
    void updateNextRunAt(Long planId);

    /**
     * 고객이 특정 펀드에 이미 가입했는지 확인
     * @param custNo 고객 번호
     * @param fundCode 펀드 코드
     * @return 가입 여부 (true: 이미 가입함, false: 가입 안 함)
     */
    boolean existsActiveSubscription(Long custNo, String fundCode);

    /**
     * 이미 가입된 펀드의 정보 조회 (투자금액, 투자시작일)
     * @param custNo 고객 번호
     * @param fundCode 펀드 코드
     * @return 펀드 계획 정보 (없으면 null)
     */
    FundPlanDTO getExistingSubscriptionInfo(Long custNo, String fundCode);

}
