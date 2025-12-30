package com.ap.service;

import com.ap.dto.MyFundResponse;
import com.ap.dto.ProductDTO;
import com.ap.entity.FundMaster;
import com.ap.mapper.FundMapper;
import com.ap.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FundService {

    // 나머지는 기존 서비스 형식 그대로 코드 작성하시면 됩니다.

    private final FundMapper productMapper;
    private final FundRepository fundRepository;

    // 단일 펀드 조회
    public FundMaster getFundMasterByGrade(String investGrade) {
        return fundRepository.findAllByInvestGrade(investGrade).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("상품 없음"));
    }

    // 전체 리스트 조회
    public List<FundMaster> getFundList() {
        return fundRepository.findAll();
    }

    public ProductDTO getProductDetail(String fundcode) {
        return productMapper.findProductDetail(fundcode);
    }

    // 보유펀드 목록 조회
    public List<MyFundResponse> getMyFunds(Integer custNo) {
        return productMapper.findMyFundsByCustNo(custNo);
    }

    // 보유펀드 상세 정보 조회
    public Map<String, Object> getMyFundDetail(Integer custNo, String fundCode) {
        return productMapper.findMyFundDetail(custNo, fundCode);
    }

    // 보유펀드 수익률 히스토리 조회
    public List<Map<String, Object>> getMyFundProfitHistory(
        Integer custNo, String fundCode, String startDate, String endDate
    ) {
        return productMapper.findMyFundProfitHistory(custNo, fundCode, startDate, endDate);
    }

    // 보유펀드 거래 내역 조회
    public List<Map<String, Object>> getMyFundTransactions(Integer custNo, String fundCode) {
        List<Map<String, Object>> transactions = productMapper.findMyFundTransactions(custNo, fundCode);
        
        // orderNo를 역순으로 설정하고 첫 번째 거래에 isStart=true 설정
        int totalCount = transactions.size();
        for (int i = 0; i < transactions.size(); i++) {
            Map<String, Object> transaction = transactions.get(i);
            // orderNo는 역순으로 설정 (가장 최근 거래가 1번)
            transaction.put("orderNo", totalCount - i);
            // 마지막(가장 오래된) 거래가 첫 거래
            transaction.put("isStart", (i == transactions.size() - 1));
        }
        
        return transactions;
    }
}

