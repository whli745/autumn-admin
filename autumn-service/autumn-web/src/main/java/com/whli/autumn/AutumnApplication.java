package com.whli.autumn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p></p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/21 22:09
 */
@SpringBootApplication(scanBasePackages = {"com.whli"})
@EnableTransactionManagement
public class AutumnApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutumnApplication.class,args);
    }
}
