package kr.co.bnk.bnk_project.service.admin;

import kr.co.bnk.bnk_project.dto.PageRequestDTO;
import kr.co.bnk.bnk_project.dto.PageResponseDTO;
import kr.co.bnk.bnk_project.dto.admin.UserSearchDTO;
import kr.co.bnk.bnk_project.mapper.admin.PermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionMapper permissionMapper;

    public PageResponseDTO<UserSearchDTO> getUserSearchPage(PageRequestDTO pageRequestDTO) {

        // 방어 코드
        if (pageRequestDTO.getPg() <= 0)  pageRequestDTO.setPg(1);
        if (pageRequestDTO.getSize() <= 0) pageRequestDTO.setSize(10);

        // 목록 조회
        List<UserSearchDTO> list = permissionMapper.selectUserSearchList(pageRequestDTO);

        // 총 개수
        int total = permissionMapper.selectUserSearchTotal(pageRequestDTO);

        // 공통 페이징 DTO로 래핑
        return PageResponseDTO.of(pageRequestDTO, list, total);
    }
}
