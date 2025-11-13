package kr.co.bnk.bnk_project.mapper;

import kr.co.bnk.bnk_project.dto.admin.InfoAttachmentDTO;
import kr.co.bnk.bnk_project.dto.admin.InfoPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfoPostMapper {

    public void insertInfoPost(InfoPostDTO infoPostDTO);

    // 파일 첨부
    public void insertInfoAttachment(InfoAttachmentDTO infoAttachmentDTO);

    public InfoPostDTO selectInfoPostById(int postId);

    public List<InfoPostDTO> selectAllInfoPost();

    public void updateInfoPost(InfoPostDTO infoPostDTO);

    public void deleteInfoPost();

}
