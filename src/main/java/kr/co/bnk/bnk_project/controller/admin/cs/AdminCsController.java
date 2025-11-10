package kr.co.bnk.bnk_project.controller.admin.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/cs")
public class AdminCsController {

    @GetMapping("/faq")
    public String faqManagement() {
        return "admin/cs/faq";
    }

    @GetMapping("/qna")
    public String qnaManagement() {
        return "admin/cs/qna";
    }

    @GetMapping("/faq/register")
    public String faqRegister() {
        return "admin/cs/faq-register";
    }

    @GetMapping("/faq/edit")
    public String faqEdit(@RequestParam(required = false) String id) {
        return "admin/cs/faq-edit";
    }

    @GetMapping("/qna/detail")
    public String qnaDetail(@RequestParam(required = false) String id) {
        return "admin/cs/qna-detail";
    }

    @GetMapping("/qna/edit")
    public String qnaEdit(@RequestParam(required = false) String id) {
        return "admin/cs/qna-edit";
    }
}

