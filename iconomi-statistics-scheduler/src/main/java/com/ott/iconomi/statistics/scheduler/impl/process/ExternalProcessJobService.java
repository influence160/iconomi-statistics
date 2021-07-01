package com.ott.iconomi.statistics.scheduler.impl.process;

import com.ott.iconomi.statistics.scheduler.job.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@ConditionalOnProperty(name = "iconomi.statistics.scheduler.importer.source", havingValue = "EXTERNAL_PROCESS")
public class ExternalProcessJobService implements JobService {

    private String login;
    private String pwd;
    private String botJarPath;

    public ExternalProcessJobService(@Value("${login}") String login, @Value("${pwd}") String pwd,
                                     @Value("${iconomi.statistics.scheduler.bot.jar.path}") String botJarPath) {
        this.login = login;
        this.pwd = pwd;
        this.botJarPath = botJarPath;
    }

//    public static void main(String[] args) {
//        Path dir = Paths.get("process");
//        Path output = dir.resolve("iconomi-bot" + DateTimeFormatter.ofPattern("y_M_d-hh_mm_ss").format(LocalDateTime.now()));
//
//        System.out.println(output);
//        //new ExternalProcessJobService("test", "test").importData();;
//    }

    boolean x = false;

    @Override
    public void importData() {
        //TODO put in application.properties
        Path pathToJar = Paths.get(botJarPath);
        Path dir = Paths.get("process");
        Path output = dir.resolve("iconomi-bot" + "_" + DateTimeFormatter.ofPattern("y_M_d-hh_mm_ss").format(LocalDateTime.now()) + ".log");

        try {
            Files.createDirectories(dir);
            Process process = new ProcessBuilder()
                    .directory(dir.toFile())
                    .command("java", "-Dspring.config.import=classpath:datasource.properties",
                            "-jar", pathToJar.toAbsolutePath().toString(),
                            "--login=" + login, "--pwd=" + pwd)
                    .redirectErrorStream(true)
                    .redirectOutput(output.toFile())
                    .start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
