package kr.co.bnk.bnk_project.controller.admin.approval;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/approval")
public class AdminApprovalController {

    @GetMapping
    public String approvalManagement() {
        return "admin/approval_management/approval_management";
    }
}

