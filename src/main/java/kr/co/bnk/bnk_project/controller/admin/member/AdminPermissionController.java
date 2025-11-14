package kr.co.bnk.bnk_project.controller.admin.member;

import kr.co.bnk.bnk_project.dto.PageRequestDTO;
import kr.co.bnk.bnk_project.dto.PageResponseDTO;
import kr.co.bnk.bnk_project.dto.admin.AdminListDTO;
import kr.co.bnk.bnk_project.dto.admin.UserSearchDTO;
import kr.co.bnk.bnk_project.service.admin.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class AdminPermissionController {

    private final PermissionService permissionService;

    @GetMapping("/permission")
    public String permissionList(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<UserSearchDTO> pageResponse = null;
        if (pageRequestDTO.getKeyword() != null &&
                !pageRequestDTO.getKeyword().isBlank()) {
            pageResponse = permissionService.getUserSearchPage(pageRequestDTO);
        }

        PageResponseDTO<AdminListDTO> adminPage =
                permissionService.getAdminList(pageRequestDTO);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("adminPage", adminPage);

        return "admin/member/permission";
    }

    @PostMapping("/permission/add")
    public String addAdmin(@RequestParam("custNo") Long custNo,
                           @RequestParam("role") String role,
                           PageRequestDTO pageRequestDTO,
                           RedirectAttributes redirectAttributes) {

        permissionService.addAdmin(custNo, role);

        redirectAttributes.addFlashAttribute("msg", "관리자가 추가되었습니다.");
        redirectAttributes.addAttribute("searchType", pageRequestDTO.getSearchType());
        redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("pg", pageRequestDTO.getPg());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/admin/member/permission";
    }

    @PostMapping("/permission/role")
    @ResponseBody
    public ResponseEntity<?> updateAdminRole(
            @RequestParam("adminNo") Long adminNo,
            @RequestParam("role") String role
    ) {
        permissionService.updateAdminRole(adminNo, role);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "adminNo", adminNo,
                "role", role
        ));
    }
}


