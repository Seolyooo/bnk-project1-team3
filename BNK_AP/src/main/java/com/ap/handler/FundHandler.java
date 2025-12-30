package com.ap.handler;

import com.ap.service.FundService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FundHandler implements ApServiceHandler {

    private final ObjectMapper objectMapper;
    private final FundService fundService;

    // 각 serviceName 마다 핸들러 파일 작성해서 action 부분을 구분해줍니다

    @Override
    public String handle(String json) {
        try {
            JsonNode node = objectMapper.readTree(json);
            String action = node.get("action").asText();

            switch (action) {
                case "getFundMaster":
                    String grade = node.get("investGrade").asText();
                    return objectMapper.writeValueAsString(
                            fundService.getFundMasterByGrade(grade)
                    );

                case "getFundList":
                    return objectMapper.writeValueAsString(
                            fundService.getFundList()
                    );

                case "getProductDetail":
                    String fundCode = node.get("fundCode").asText();
                    return objectMapper.writeValueAsString(
                            fundService.getProductDetail(fundCode)
                    );

                case "getMyFunds":
                    Integer custNo = node.get("custNo").asInt();
                    try {
                        return objectMapper.writeValueAsString(
                                fundService.getMyFunds(custNo)
                        );
                    } catch (Exception e) {
                        System.err.println("getMyFunds 에러 상세:");
                        e.printStackTrace();
                        throw e; // 상위로 전달하여 상세 에러 메시지 확인
                    }

                case "getMyFundDetail":
                    Integer custNo2 = node.get("custNo").asInt();
                    String fundCode2 = node.get("fundCode").asText();
                    return objectMapper.writeValueAsString(
                            fundService.getMyFundDetail(custNo2, fundCode2)
                    );

                case "getMyFundProfitHistory":
                    Integer custNo3 = node.get("custNo").asInt();
                    String fundCode3 = node.get("fundCode").asText();
                    String startDate = node.get("startDate").asText();
                    String endDate = node.get("endDate").asText();
                    return objectMapper.writeValueAsString(
                            fundService.getMyFundProfitHistory(custNo3, fundCode3, startDate, endDate)
                    );

                case "getMyFundTransactions":
                    Integer custNo4 = node.get("custNo").asInt();
                    String fundCode4 = node.get("fundCode").asText();
                    return objectMapper.writeValueAsString(
                            fundService.getMyFundTransactions(custNo4, fundCode4)
                    );

                default:
                    return "{ \"error\": \"Unknown action: " + action + "\" }";
            }

        } catch (Exception e) {
            System.err.println("========== FundHandler 에러 상세 ==========");
            System.err.println("에러 타입: " + e.getClass().getName());
            System.err.println("에러 메시지: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==========================================");
            
            // JSON 형식으로 안전하게 에러 메시지 반환
            try {
                String errorMessage = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
                // 특수문자 제거 및 안전한 문자열로 변환
                errorMessage = errorMessage.replace("\"", "'")
                                          .replace("\n", " ")
                                          .replace("\r", " ")
                                          .replace("\\", "/");
                if (errorMessage.length() > 200) {
                    errorMessage = errorMessage.substring(0, 200) + "...";
                }
                return "{\"error\":\"" + errorMessage + "\"}";
            } catch (Exception jsonError) {
                return "{\"error\":\"FundHandler error\"}";
            }
        }
    }
}
