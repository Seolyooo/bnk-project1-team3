package kr.co.bnk.bnk_project.mapper.mobile;

import kr.co.bnk.bnk_project.dto.mobile.FundOrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FundOrderMapper {

    Long getNextOrderId();

    void insertFundOrder(FundOrderDTO fundOrderDTO);

    /**
     * 이미 가입된 펀드의 주문 정보 조회 (USER_FUND_PLAN에 없을 경우)
     * @param custNo 고객 번호
     * @param fundCode 펀드 코드
     * @return 가장 최근 주문 정보 (없으면 null)
     */
    FundOrderDTO getExistingOrderInfo(Long custNo, String fundCode);

}
