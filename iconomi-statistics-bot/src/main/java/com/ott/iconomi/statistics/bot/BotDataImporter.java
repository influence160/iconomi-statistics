package com.ott.iconomi.statistics.bot;

import com.ott.iconomi.statistics.bot.iconomi.IconomiPageHelper;
import com.ott.iconomi.statistics.bot.iconomi.pages.LoginPage;
import com.ott.iconomi.statistics.bot.iconomi.pages.StrategiesPage;
import com.ott.iconomi.statistics.domain.events.SnapshotTakenEvent;
import com.ott.iconomi.statistics.domain.model.Snapshot;
import com.ott.iconomi.statistics.domain.repository.SnapshotRepository;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

@Slf4j
public class BotDataImporter {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    //for test
    @Autowired
    SnapshotRepository snapshotRepository;

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private LoginPage loginPage;
    @Autowired
    private StrategiesPage strategiesPage;

    public void importData(String login, String pwd) {
        try {
            log.info("Snapshots count : " + snapshotRepository.count());
            Snapshot.SnapshotBuilder snapshotBuilder = Snapshot.builder();
            snapshotBuilder.startTime(LocalDateTime.now());
            doExtraction(login, pwd, snapshotBuilder);

            saveSnapshotAfterEnd(snapshotBuilder);
        } catch (Exception e) {
            throw e;
        } finally {
            webDriver.close();
			webDriver.switchTo().window(webDriver.getWindowHandles().iterator().next());
        }
    }


    private void doExtraction(String login, String pws, Snapshot.SnapshotBuilder snapshot) {
        log.debug("login with " + login +  "/*******");
        IconomiPageHelper.waitAMoment();
        loginPage.login(login, pws);
        strategiesPage.takeASnapshot(snapshot);
    }

    public void saveSnapshotAfterEnd(Snapshot.SnapshotBuilder snapshotBuilder) {
        snapshotBuilder.failed(false);
        Snapshot snapshot = snapshotBuilder.build();
        log.info("Snapshot Taken with " + snapshot.getStrategies().size() + " strategies and " + snapshot.getCurrentStructures().size() + " structures.");
        log.debug("snapshot = " + snapshot  + "\nstrategies = " + snapshot.getStrategies() + "\nstructures = " + snapshot.getCurrentStructures());
        SnapshotTakenEvent event = new SnapshotTakenEvent(this, snapshot);
        applicationEventPublisher.publishEvent(event);
    }

}
