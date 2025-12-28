package kr.co.bnk.bnk_project.service.mobile;

import kr.co.bnk.bnk_project.dto.mobile.FundOrderDTO;
import kr.co.bnk.bnk_project.dto.mobile.FundPlanDTO;
import kr.co.bnk.bnk_project.dto.mobile.FundSubscriptionRequestDTO;
import kr.co.bnk.bnk_project.exception.DuplicateFundSubscriptionException;
import kr.co.bnk.bnk_project.mapper.mobile.FundOrderMapper;
import kr.co.bnk.bnk_project.mapper.mobile.FundPlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FundSubscriptionService {

    private final FundOrderMapper fundOrderMapper;
    private final FundPlanMapper fundPlanMapper;

    /**
     * 펀드 가입 처리
     * 자동이체인 경우 FundPlan을 생성하고, 한 번만 투자인 경우는 FundOrder만 생성합니다.
     * 실제 자동이체 실행은 배치 작업에서 처리됩니다.
     *
     * @param request 펀드 가입 요청 정보
     * @return 생성된 주문 ID
     */
    @Transactional
    public Long subscribeFund(FundSubscriptionRequestDTO request) {
        // 0. 중복 가입 체크 및 기존 가입 정보 조회
        Long custNo = 18L;  // TODO: 로그인 기능 완성 후 세션에서 가져오기
        if (fundPlanMapper.existsActiveSubscription(custNo, request.getFundCode())) {
            // 이미 가입된 펀드 정보 조회
            FundPlanDTO existingPlan = fundPlanMapper.getExistingSubscriptionInfo(custNo, request.getFundCode());
            
            // USER_FUND_PLAN에 없으면 USER_FUND_ORDER에서 조회
            if (existingPlan == null) {
                FundOrderDTO existingOrder = fundOrderMapper.getExistingOrderInfo(custNo, request.getFundCode());
                if (existingOrder != null) {
                    // FundOrderDTO를 FundPlanDTO로 변환
                    existingPlan = new FundPlanDTO();
                    existingPlan.setAmount(existingOrder.getReqAmount());
                    existingPlan.setStartAt(existingOrder.getRequestAt());
                }
            }
            
            // 기존 가입 정보를 예외에 포함하여 던짐
            throw new DuplicateFundSubscriptionException("이미 가입된 펀드입니다.", existingPlan);
        }
        
        Long planId = null;

        // 1. 자동이체인 경우에만 FundPlan 생성
        if (!"한 번만 투자하기".equals(request.getInvestmentType())) {
            planId = createFundPlan(request);
        }

        // 2. FundOrder 생성 (첫 투자 주문 생성)
        Long orderId = createFundOrder(request, planId);

        return orderId;
    }

    /**
     * 펀드 계획 생성 (자동이체 계획 정보를 DB에 저장)
     * 자동이체(매일/매주/매월)인 경우에만 호출됩니다.
     */
    private Long createFundPlan(FundSubscriptionRequestDTO request) {
        Long planId = fundPlanMapper.getNextPlanId();

        // 자동이체 (매일, 매주, 매월)
        String investmentType = "정기";  // VARCHAR2(10) 제한을 위해 짧은 값 사용
        String cycleType = convertCycleTypeToEnglish(request.getCycleType()); // "매일" -> "DAILY", "매주" -> "WEEKLY", "매월" -> "MONTHLY"
        Integer weekday = null;
        Integer dayOfMonth = null;

        if ("매주".equals(request.getCycleType()) || "WEEKLY".equals(cycleType)) {
            weekday = request.getWeekday(); // 1~7
        } else if ("매월".equals(request.getCycleType()) || "MONTHLY".equals(cycleType)) {
            dayOfMonth = request.getDayOfMonth(); // 1~31
        }
        // "매일" 또는 "DAILY"인 경우 weekday와 dayOfMonth 모두 null

        FundPlanDTO planDTO = new FundPlanDTO();
        planDTO.setPlanId(planId);
        planDTO.setCustNo(18L);  // TODO: 로그인 기능 완성 후 세션에서 가져오기
        planDTO.setAcctNo("223-638-653120");  // TODO: 로그인 기능 완성 후 세션에서 가져오기
        planDTO.setFundCode(request.getFundCode());
        planDTO.setStatus("ACTIVE");
        planDTO.setCycleType(cycleType);
        planDTO.setAmount(request.getAmount());
        planDTO.setStartAt(LocalDateTime.now());
        planDTO.setWeekday(weekday);
        planDTO.setDayOfMonth(dayOfMonth);
        planDTO.setCreatedAt(LocalDateTime.now());
        planDTO.setInvestmentType(investmentType);

        fundPlanMapper.insertFundPlan(planDTO);

        return planId;
    }

    /**
     * 펀드 주문 생성 (첫 투자 주문)
     */
    private Long createFundOrder(FundSubscriptionRequestDTO request, Long planId) {
        Long orderId = fundOrderMapper.getNextOrderId();

        FundOrderDTO orderDTO = new FundOrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setCustNo(18L);  // TODO: 로그인 기능 완성 후 세션에서 가져오기
        orderDTO.setAcctNo("223-638-653120");  // TODO: 로그인 기능 완성 후 세션에서 가져오기
        orderDTO.setFundCode(request.getFundCode());
        orderDTO.setType("BUY");
        orderDTO.setStatus("REQUESTED");
        orderDTO.setRequestAt(LocalDateTime.now());
        orderDTO.setReqAmount(request.getAmount());
        orderDTO.setPlanId(planId);

        fundOrderMapper.insertFundOrder(orderDTO);

        return orderId;
    }

    /**
     * 한글 주기 타입을 영문으로 변환
     * DB 제약조건: CYCLE_TYPE IN ('DAILY', 'WEEKLY', 'MONTHLY')
     */
    private String convertCycleTypeToEnglish(String cycleType) {
        if (cycleType == null) {
            return null;
        }
        
        switch (cycleType) {
            case "매일":
                return "DAILY";
            case "매주":
                return "WEEKLY";
            case "매월":
                return "MONTHLY";
            default:
                // 이미 영문인 경우 그대로 반환
                return cycleType;
        }
    }
}