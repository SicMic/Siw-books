package it.uniroma3.theboys.siw_books.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.common.lang.NonNull;

import java.nio.file.Paths;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         // Mappa la URL /uploads/** alla directory "uploads/" nel filesystem
//         registry.addResourceHandler("/uploads/**")
//                 .addResourceLocations("file:" + Paths.get("..", "uploads").toAbsolutePath().toUri());    }
// }


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/uploads/**")
            .addResourceLocations("file:uploads/");
    }
}