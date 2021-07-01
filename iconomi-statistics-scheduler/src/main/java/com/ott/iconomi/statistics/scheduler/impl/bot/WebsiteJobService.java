package com.ott.iconomi.statistics.scheduler.impl.bot;

import com.ott.iconomi.statistics.bot.BotDataImporter;
import com.ott.iconomi.statistics.scheduler.job.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = "iconomi.statistics.scheduler.importer.source", havingValue = "WEBSITE")
public class WebsiteJobService implements JobService {

    BotDataImporter botDataImporter;
    private String login;
    private String pwd;


    public WebsiteJobService(BotDataImporter botDataImporter, @Value("${login}") String login, @Value("${pwd}") String pwd) {
        this.botDataImporter = botDataImporter;
        this.login = login;
        this.pwd = pwd;
    }

    @Override
    public void importData() {
        log.info("Starting import data using WEBSITE implementation.");
        botDataImporter.importData(login, pwd);
        log.info("End import data using WEBSITE implementation.");
    }
}
