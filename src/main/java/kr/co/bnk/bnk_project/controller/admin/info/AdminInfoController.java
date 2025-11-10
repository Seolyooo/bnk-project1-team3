package kr.co.bnk.bnk_project.controller.admin.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/info")
public class AdminInfoController {

    @GetMapping("/disclosures")
    public String disclosuresDocuments() {
        return "admin/info&disclosures/disclosures_documents";
    }

    @GetMapping("/ad-hoc")
    public String adHocDisclosure() {
        return "admin/info&disclosures/ad-hoc_disclosure";
    }

    @GetMapping("/fund-info")
    public String fundInfo() {
        return "admin/info&disclosures/fund_info";
    }

    @GetMapping("/fund-market")
    public String fundMarket() {
        return "admin/info&disclosures/fund_market";
    }

    @GetMapping("/guide")
    public String guide() {
        return "admin/info&disclosures/guide";
    }
}

