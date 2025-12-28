package kr.co.bnk.bnk_project.controller;

import kr.co.bnk.bnk_project.dto.ProductDTO;
import kr.co.bnk.bnk_project.dto.mobile.FundSubscriptionRequestDTO;
import kr.co.bnk.bnk_project.exception.DuplicateFundSubscriptionException;
import kr.co.bnk.bnk_project.service.FundService;
import kr.co.bnk.bnk_project.service.mobile.FundSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* ====================================================================
 * [2024-12-24 추가] Flutter 앱 전용 펀드 API 컨트롤러
 * ====================================================================
 * Flutter 앱에서 사용할 REST API 엔드포인트 제공
 * 경로: /api/funds
 * ==================================================================== */
/**
 * Flutter 앱 전용 펀드 API 컨트롤러
 * /api/funds 경로로 매핑
 */
@RestController
@RequestMapping("/api/funds")
@RequiredArgsConstructor
public class FlutterFundController {

    private final FundService fundService;
    private final FundSubscriptionService fundSubscriptionService;

    /**
     * 판매량 Best 펀드 목록
     * (현재는 전체 목록 상위 10개 반환, 추후 판매량 기준 정렬 로직 추가 필요)
     */
    @GetMapping("/category/sales")
    public ResponseEntity<List<ProductDTO>> getSalesFunds() {
        List<ProductDTO> funds = fundService.getProductList();
        // TODO: 판매량 기준 정렬 로직 추가 필요
        // 현재는 전체 목록 상위 10개 반환
        return ResponseEntity.ok(funds.stream().limit(10).toList());
    }

    /**
     * 수익률 Best 펀드 목록
     * (기존 getFundYieldBest() 메서드 활용)
     */
    @GetMapping("/category/yield")
    public ResponseEntity<List<ProductDTO>> getYieldFunds() {
        List<ProductDTO> funds = fundService.getFundYieldBest();
        return ResponseEntity.ok(funds);
    }

    /**
     * 모든 카테고리 통합 조회
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, List<ProductDTO>>> getAllFunds() {
        Map<String, List<ProductDTO>> allFunds = new HashMap<>();
        allFunds.put("sales", fundService.getProductList().stream().limit(10).toList());
        allFunds.put("yield", fundService.getFundYieldBest());
        
        return ResponseEntity.ok(allFunds);
    }

    /**
     * 펀드 가입
     */
    @PostMapping("/subscribe")
    public ResponseEntity<Map<String, Object>> subscribeFund(@RequestBody FundSubscriptionRequestDTO request) {
        Map<String, Object> response = new HashMap<>();

        try {
            Long orderId = fundSubscriptionService.subscribeFund(request);

            response.put("success", true);
            response.put("orderId", orderId);
            response.put("message", "펀드 가입이 완료되었습니다.");

            return ResponseEntity.ok(response);
        } catch (DuplicateFundSubscriptionException e) {
            // 중복 가입 예외 (기존 가입 정보 포함)
            response.put("success", false);
            response.put("message", e.getMessage());
            if (e.getExistingSubscription() != null) {
                Map<String, Object> existingInfo = new HashMap<>();
                existingInfo.put("amount", e.getExistingSubscription().getAmount());
                if (e.getExistingSubscription().getStartAt() != null) {
                    existingInfo.put("startAt", e.getExistingSubscription().getStartAt()
                            .format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
                }
                response.put("existingSubscription", existingInfo);
            }
            return ResponseEntity.badRequest().body(response);
        } catch (IllegalStateException e) {
            // 기타 IllegalStateException
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "펀드 가입 중 오류가 발생했습니다: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}

