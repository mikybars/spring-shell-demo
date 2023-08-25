package com.github.mikybars.demo.spring.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan
public class MyCliApp {

    public static void main(String[] args) {
        SpringApplication.run(MyCliApp.class, args);
    }

}
