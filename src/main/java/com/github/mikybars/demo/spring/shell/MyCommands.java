package com.github.mikybars.demo.spring.shell;

import jakarta.validation.constraints.Size;
import org.jline.utils.Log;
import org.springframework.shell.Availability;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Command
class MyCommands {

    private boolean adminEnableExecuted;

    @Command(alias = "hw", description = "Say hello.")
    public String helloWorld(@Option(defaultValue = "spring") String arg) {
        return "Hello World " + arg;
    }

    @Command(alias = "wg", description = "Get contents of URL.")
    public String webGet(String url) {
        return getContentsOfUrlAsString(url);
    }

    @Command(alias = "ws", description = "Save the contents of URL to file.")
    public String webSave(String url, @Option(longNames = {"out", "file"}) String file) {
        String contents = getContentsOfUrlAsString(url);
        try (PrintWriter out = new PrintWriter(file)) {
            out.write(contents);
        } catch (FileNotFoundException e) {
            return "File not found: " + file;
        }
        return "Done.";
    }

    @Command(alias = "admin", description = "Enable admin commands.")
    public String adminEnable() {
        adminEnableExecuted = true;
        return "Admin commands enabled.";
    }

    @Command(description = "Change password.")
    public String changePassword(@Size(min = 8, max = 40) String password) {
        return "Password successfully set to " + password;
    }

    public Availability webSaveAvailability() {
        return adminEnableExecuted
                ? Availability.available()
                : Availability.unavailable("you must first run admin-enable");
    }

    private String getContentsOfUrlAsString(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
            }
        } catch (MalformedURLException e) {
            return "Bad URL";
        } catch (IOException e) {
            sb.append("ERROR: ").append(e.getMessage());
            Log.error(e);
        }
        return sb.toString();
    }
}
