package com.github.mikybars.demo.spring.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.stereotype.Component;

@ShellComponent
public class SimplePromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("my-shell:>", AttributedStyle.BOLD);
    }
}
