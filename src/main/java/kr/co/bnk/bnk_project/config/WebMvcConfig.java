package kr.co.bnk.bnk_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
    날짜 : 2025/11/26
    이름 : 이종봉
    내용 : 약관, 투자설명서, 간이투자설명서 웹띄워서 확인. 로컬 테스트완료. 배포는 추후.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:C:/bnk_upload/");
    }
}

