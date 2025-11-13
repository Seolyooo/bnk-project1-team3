package kr.co.bnk.bnk_project.controller.admin;

import kr.co.bnk.bnk_project.dto.PageRequestDTO;
import kr.co.bnk.bnk_project.dto.PageResponseDTO;
import kr.co.bnk.bnk_project.dto.admin.UserSearchDTO;
import kr.co.bnk.bnk_project.service.admin.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {


    private final PermissionService permissionService;


    @GetMapping({"/", "/main"})
    public String adminMain() {
        return "admin/adminMain";
    }

    @GetMapping("/login")
    public String adminLoginPage() {
      return "admin/login";
    }





    /*-------------------------권한 변경-------------------------*/

    @GetMapping("/permission")
    public String permissionList(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<UserSearchDTO> pageResponse =
                permissionService.getUserSearchPage(pageRequestDTO);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("pageResponse", pageResponse);

        return "admin/permission/permission";
    }

}

