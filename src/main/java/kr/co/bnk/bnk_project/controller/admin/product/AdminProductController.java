package kr.co.bnk.bnk_project.controller.admin.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    @GetMapping
    public String productList() {
        return "admin/product/adminproduct";
    }

    @GetMapping("/register")
    public String productRegister() {
        return "admin/product/adminproduct-register";
    }

    @GetMapping("/pending")
    public String productPending() {
        return "admin/product/adminproduct-pending";
    }

    @GetMapping("/edit")
    public String productEdit(@RequestParam(required = false) Long id) {
        return "admin/product/adminproduct-edit";
    }

    @GetMapping("/documents")
    public String productDocuments() {
        return "admin/product/adminproduct-documents";
    }
}

