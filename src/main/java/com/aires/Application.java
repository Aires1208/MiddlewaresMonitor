package com.aires;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 10172605 on 2016/8/8.
 */
@SpringBootApplication
//@EnableAutoConfiguration
//@EnableCaching
@EnableSwagger2
//@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);

        springApplication.run(args);
        //has probloms, need to check connect
//        MockCometdClient.connect();
//        HBaseCheck.checkHTable();
    }
}

