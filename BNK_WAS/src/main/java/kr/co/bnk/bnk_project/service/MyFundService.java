package kr.co.bnk.bnk_project.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.bnk.bnk_project.dto.MyFundResponse;
import kr.co.bnk.bnk_project.socket.TcpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyFundService {
    
    private final TcpClient tcpClient;
    private final ObjectMapper objectMapper;
    
    public List<MyFundResponse> getMyFunds(Integer custNo) {
        // AP로 보낼 JSON 요청 생성
        String requestJson = String.format(
            "{ \"serviceName\": \"fund\", \"action\": \"getMyFunds\", \"custNo\": %d }",
            custNo
        );
        
        // AP 서버로 요청 전송
        String responseJson = tcpClient.sendRequest(requestJson);
        
        // 응답이 null이거나 비어있는지 확인
        if (responseJson == null || responseJson.trim().isEmpty()) {
            System.err.println("AP 서버 응답이 비어있습니다.");
            return new ArrayList<>();
        }
        
        try {
            // 응답이 에러 객체인지 확인
            JsonNode rootNode = objectMapper.readTree(responseJson);
            if (rootNode.has("error")) {
                // 에러 응답인 경우 빈 리스트 반환
                String errorMessage = rootNode.get("error").asText();
                System.err.println("AP 서버 에러: " + errorMessage);
                return new ArrayList<>(); // 빈 리스트 반환
            }
            
            // 정상 응답인 경우 List로 변환
            return objectMapper.readValue(
                responseJson,
                new TypeReference<List<MyFundResponse>>() {}
            );
        } catch (com.fasterxml.jackson.core.JsonParseException e) {
            // JSON 파싱 오류인 경우
            System.err.println("JSON 파싱 오류: " + e.getMessage());
            System.err.println("응답 내용: " + responseJson);
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("보유펀드 조회 중 오류 발생: " + e.getMessage());
            System.err.println("응답 내용: " + responseJson);
            throw new RuntimeException("보유펀드 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // 보유펀드 상세 정보 조회
    public Map<String, Object> getMyFundDetail(Integer custNo, String fundCode) {
        String requestJson = String.format(
            "{ \"serviceName\": \"fund\", \"action\": \"getMyFundDetail\", \"custNo\": %d, \"fundCode\": \"%s\" }",
            custNo, fundCode
        );
        
        String responseJson = tcpClient.sendRequest(requestJson);
        
        if (responseJson == null || responseJson.trim().isEmpty()) {
            throw new RuntimeException("AP 서버 응답이 비어있습니다.");
        }
        
        try {
            JsonNode rootNode = objectMapper.readTree(responseJson);
            if (rootNode.has("error")) {
                String errorMessage = rootNode.get("error").asText();
                throw new RuntimeException("AP 서버 에러: " + errorMessage);
            }
            
            return objectMapper.readValue(responseJson, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new RuntimeException("보유펀드 상세 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // 보유펀드 수익률 히스토리 조회
    public List<Map<String, Object>> getMyFundProfitHistory(Integer custNo, String fundCode, String period) {
        // period에 따라 시작일 계산
        LocalDate endDate = LocalDate.now();
        LocalDate startDate;
        
        switch (period) {
            case "1M":
                startDate = endDate.minusMonths(1);
                break;
            case "3M":
                startDate = endDate.minusMonths(3);
                break;
            case "6M":
                startDate = endDate.minusMonths(6);
                break;
            case "1Y":
                startDate = endDate.minusYears(1);
                break;
            case "ALL":
            default:
                // 가입일부터 조회 (실제로는 DB에서 가져와야 하지만 일단 1년 전으로 설정)
                startDate = endDate.minusYears(1);
                break;
        }
        
        String requestJson = String.format(
            "{ \"serviceName\": \"fund\", \"action\": \"getMyFundProfitHistory\", \"custNo\": %d, \"fundCode\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\" }",
            custNo, fundCode, startDate.toString(), endDate.toString()
        );
        
        String responseJson = tcpClient.sendRequest(requestJson);
        
        if (responseJson == null || responseJson.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        try {
            JsonNode rootNode = objectMapper.readTree(responseJson);
            if (rootNode.has("error")) {
                System.err.println("AP 서버 에러: " + rootNode.get("error").asText());
                return new ArrayList<>();
            }
            
            return objectMapper.readValue(responseJson, new TypeReference<List<Map<String, Object>>>() {});
        } catch (com.fasterxml.jackson.core.JsonParseException e) {
            System.err.println("JSON 파싱 오류: " + e.getMessage());
            System.err.println("응답 내용: " + responseJson);
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("수익률 히스토리 조회 중 오류: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 보유펀드 거래 내역 조회
    public List<Map<String, Object>> getMyFundTransactions(Integer custNo, String fundCode) {
        String requestJson = String.format(
            "{ \"serviceName\": \"fund\", \"action\": \"getMyFundTransactions\", \"custNo\": %d, \"fundCode\": \"%s\" }",
            custNo, fundCode
        );
        
        String responseJson = tcpClient.sendRequest(requestJson);
        
        if (responseJson == null || responseJson.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        try {
            JsonNode rootNode = objectMapper.readTree(responseJson);
            if (rootNode.has("error")) {
                System.err.println("AP 서버 에러: " + rootNode.get("error").asText());
                return new ArrayList<>();
            }
            
            return objectMapper.readValue(responseJson, new TypeReference<List<Map<String, Object>>>() {});
        } catch (com.fasterxml.jackson.core.JsonParseException e) {
            System.err.println("JSON 파싱 오류: " + e.getMessage());
            System.err.println("응답 내용: " + responseJson);
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("거래 내역 조회 중 오류: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

