package kr.co.bnk.bnk_project.service.admin;

import kr.co.bnk.bnk_project.dto.CsDTO;
import kr.co.bnk.bnk_project.dto.PageRequestDTO;
import kr.co.bnk.bnk_project.dto.PageResponseDTO;
import kr.co.bnk.bnk_project.dto.admin.FundListDetailDTO;
import kr.co.bnk.bnk_project.dto.admin.ProductListDTO;
import kr.co.bnk.bnk_project.mapper.admin.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/*
    날짜 : 2025/11/24
    이름 : 이종봉
    내용 : 펀드목록 돋보기
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    // 펀드 목록 페이지
    public PageResponseDTO<ProductListDTO> getProductPage(PageRequestDTO pageRequestDTO) {

        // 방어 코드
        if (pageRequestDTO.getPg() <= 0) pageRequestDTO.setPg(1);
        if (pageRequestDTO.getSize() <= 0) pageRequestDTO.setSize(10);

        // 목록
        List<ProductListDTO> list = productMapper.selectProductList(pageRequestDTO);

        // 총 개수
        int total = productMapper.selectProductTotal(pageRequestDTO);


        return PageResponseDTO.of(pageRequestDTO, list, total);

    }

    // 펀드 상세 조회(돋보기)
    public FundListDetailDTO getProductDetail(String fundCode) {
        return productMapper.selectProductDetail(fundCode);
    }

}