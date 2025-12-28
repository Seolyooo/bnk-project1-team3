package kr.co.bnk.bnk_project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 배치 테스트용 컨트롤러
 * 배치를 수동으로 실행하여 테스트할 수 있는 API 제공
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/batch")
@RequiredArgsConstructor
public class BatchTestController {

    private final JobLauncher jobLauncher;
    private final Job fundAutoInvestmentJob;

    /**
     * 자동이체 배치 수동 실행 (테스트용)
     * GET 또는 POST 모두 지원
     * 
     * @return 배치 실행 결과
     */
    @RequestMapping(value = "/run-auto-investment", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> runAutoInvestmentBatch() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            log.info("[BatchTestController] 자동이체 배치 수동 실행 요청");
            
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            
            JobExecution execution = jobLauncher.run(fundAutoInvestmentJob, params);
            
            response.put("success", true);
            response.put("jobExecutionId", execution.getId());
            response.put("status", execution.getStatus().toString());
            response.put("message", "배치가 실행되었습니다. USER_FUND_ORDER 테이블과 NEXT_RUN_AT을 확인하세요.");
            
            log.info("[BatchTestController] 자동이체 배치 실행 완료: JobExecutionId={}, Status={}", 
                    execution.getId(), execution.getStatus());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("[BatchTestController] 자동이체 배치 실행 중 오류 발생", e);
            
            response.put("success", false);
            response.put("message", "배치 실행 중 오류가 발생했습니다: " + e.getMessage());
            response.put("error", e.getClass().getSimpleName());
            
            return ResponseEntity.badRequest().body(response);
        }
    }
}

