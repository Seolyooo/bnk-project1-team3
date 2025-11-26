package kr.co.bnk.bnk_project.mapper;

import kr.co.bnk.bnk_project.dto.RiskTestResultDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RiskTestMapper {
    void insertRiskTestResult(RiskTestResultDTO dto);
}