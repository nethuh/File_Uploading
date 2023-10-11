package lk.ijse.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"lk.ijse.spring.controller","lk.ijse.spring.advisor"})
public class WebRootConfig {
    public WebRootConfig(){
        System.out.println("WebRootConfig: Instantiated");
    }
}
