package com.github.mikybars.demo.spring.shell;

import org.jline.utils.Log;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@ShellComponent
public class MyCommands {

    private boolean adminEnableExecuted;

    @ShellMethod(value = "Say hello", key = { "hello-world", "hw" })
    public String helloWorld(@ShellOption(defaultValue = "spring") String arg) {
        return "Hello World " + arg;
    }

    @ShellMethod(value = "Get contents of URL", key = { "web-get", "wg" })
    public String webGet(String url) {
        return getContentsOfUrlAsString(url);
    }

    @ShellMethod(value = "Save the contents of URL to file", key = { "web-save", "ws" })
    public String webSave(String url, @ShellOption({ "out", "file" }) String file) {
        String contents = getContentsOfUrlAsString(url);
        try (PrintWriter out = new PrintWriter(file)) {
            out.write(contents);
        } catch (FileNotFoundException e) {
            return "File not found: " + file;
        }
        return "Done.";
    }

    @ShellMethod(value = "Enable admin commands", key = "admin-enable")
    public String adminEnable() {
        adminEnableExecuted = true;
        return "Admin commands enabled.";
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
