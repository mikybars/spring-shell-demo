package com.github.mikybars.demo.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MyCommands {

    @ShellMethod(value = "Say hello", key = { "hello-world", "hw" })
    public String helloWorld(@ShellOption(defaultValue = "spring") String arg) {
        return "Hello World " + arg;
    }
}
