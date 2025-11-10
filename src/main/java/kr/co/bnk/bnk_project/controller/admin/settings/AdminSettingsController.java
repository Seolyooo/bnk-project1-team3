package kr.co.bnk.bnk_project.controller.admin.settings;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/settings")
public class AdminSettingsController {

    @GetMapping("/category")
    public String categoryManagement() {
        return "admin/settings/category_management";
    }

    @GetMapping("/search-keyword")
    public String searchKeyword() {
        return "admin/settings/search_keyword";
    }
}

