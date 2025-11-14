package kr.co.bnk.bnk_project.controller.admin.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class AdminMemberController {


    @GetMapping("/list")
    public String userList() {

        return "admin/member/list";
    }

}


