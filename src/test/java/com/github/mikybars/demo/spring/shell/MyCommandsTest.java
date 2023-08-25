package com.github.mikybars.demo.spring.shell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.ShellTestClient.InteractiveShellSession;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.springframework.shell.test.ShellAssertions.assertThat;

@ShellTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class MyCommandsTest {

    @Autowired
    ShellTestClient client;

    @Test
    void helloWorld() {
        InteractiveShellSession session = client
                .interactive()
                .run();

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            assertThat(session.screen()).containsText("my-shell");
        });

        session.write(session.writeSequence().text("help").carriageReturn().build());
        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            assertThat(session.screen()).containsText("AVAILABLE COMMANDS");
        });
    }
}