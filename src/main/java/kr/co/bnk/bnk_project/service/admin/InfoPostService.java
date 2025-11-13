package kr.co.bnk.bnk_project.service.admin;

import jakarta.transaction.Transactional;
import kr.co.bnk.bnk_project.dto.admin.InfoAttachmentDTO;
import kr.co.bnk.bnk_project.dto.admin.InfoPostDTO;
import kr.co.bnk.bnk_project.mapper.InfoPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InfoPostService {

    private final InfoPostMapper infoPostMapper;

    // 공시자료 등록 + 파일첨부
    public void createDisclosure(InfoPostDTO dto, MultipartFile attachment) {

        if (dto.getStatus() == null || dto.getStatus().isBlank()) {
            dto.setStatus("PUBLISHED");
        }
        if (dto.getCreatedBy() == null || dto.getCreatedBy().isBlank()) {
            dto.setCreatedBy("admin");
        }

        // 글 먼저 등록
        infoPostMapper.insertInfoPost(dto);

        // 첨부파일
        String originalName = null;
        if (attachment != null && !attachment.isEmpty()) {
            originalName = attachment.getOriginalFilename();
        }

        // 파일 저장
        String uploadDir = "uploads/info";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String storedName = UUID.randomUUID() + "_" + originalName;
        File dest = new File(dir, storedName);

        try {
            attachment.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("첨부파일 저장 실패", e);
        }

        String storedPath = dest.getPath();

        InfoAttachmentDTO fileDTO = new InfoAttachmentDTO();
        fileDTO.setPostId(dto.getPostId());
        fileDTO.setFileName(originalName);
        fileDTO.setFilePath(storedPath);
        fileDTO.setSortOrder(1);

        infoPostMapper.insertInfoAttachment(fileDTO);

    }

    // 상세 확인
    public InfoPostDTO findInfoPostById(int postId) {
        return infoPostMapper.selectInfoPostById(postId);
    }

    // 목록 전체
    public List<InfoPostDTO> findAllInfoPost(){
        return infoPostMapper.selectAllInfoPost();
    }
}
