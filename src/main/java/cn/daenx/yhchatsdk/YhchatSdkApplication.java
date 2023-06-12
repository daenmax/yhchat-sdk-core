package cn.daenx.yhchatsdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class YhchatSdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(YhchatSdkApplication.class, args);
    }

}
