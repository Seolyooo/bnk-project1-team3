package kr.co.bnk.bnk_project.controller.admin.approval;

import kr.co.bnk.bnk_project.dto.PageRequestDTO;
import kr.co.bnk.bnk_project.dto.PageResponseDTO;
import kr.co.bnk.bnk_project.dto.admin.ApprovalDTO;
import kr.co.bnk.bnk_project.security.AdminUserDetails;
import kr.co.bnk.bnk_project.service.admin.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/approval")
@RequiredArgsConstructor
public class AdminApprovalController {

    private final ApprovalService approvalService;

    @GetMapping
    public String approvalManagement(PageRequestDTO pageRequestDTO, Model model) {

        // 목록 + 페이징 정보
        PageResponseDTO<ApprovalDTO> pageResponse = approvalService.selectpendingApproval(pageRequestDTO);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("recentHistory", approvalService.selectRecentApprovalHistory());

        return "admin/approval_management/approval_management";
    }

    @PostMapping("/approve")
    public String approve(@ModelAttribute ApprovalDTO approvalDTO,
                          @AuthenticationPrincipal AdminUserDetails loginAdmin) {

        if (loginAdmin != null) {
            approvalDTO.setApprover(loginAdmin.getDisplayName());
        }

        approvalService.approvalFund(approvalDTO);

        return "redirect:/admin/approval";
    }



}

