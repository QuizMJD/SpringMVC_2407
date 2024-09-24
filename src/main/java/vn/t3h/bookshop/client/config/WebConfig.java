package vn.t3h.bookshop.client.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@ComponentScan(basePackages = "vn.t3h.bookshop.client")
@EnableWebMvc
@EnableScheduling
public class WebConfig implements WebMvcConfigurer {



    // Handle HTTP GET requests for /resources/** by efficiently serving
    // static resources under ${webappRoot}/resources/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper pathHelper = new UrlPathHelper();
        pathHelper.setRemoveSemicolonContent(false); // For @MatrixVariable's
        configurer.setUrlPathHelper(pathHelper);
    }


}