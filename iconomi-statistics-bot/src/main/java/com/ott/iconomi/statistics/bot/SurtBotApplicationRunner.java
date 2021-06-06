package com.ott.iconomi.statistics.bot;

import javax.transaction.Transactional;

import com.ott.iconomi.statistics.bot.iconomi.IconomiPageHelper;
import com.ott.iconomi.statistics.bot.iconomi.pages.LoginPage;
import com.ott.iconomi.statistics.bot.iconomi.pages.StrategiesPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.ott.iconomi.statistics.domain.events.SnapshotTakenEvent;
import com.ott.iconomi.statistics.domain.model.Snapshot;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Component
@Slf4j
@Transactional
public class SurtBotApplicationRunner implements ApplicationRunner {
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private WebDriver webDriver;

	@Autowired
	private LoginPage loginPage;
	@Autowired
	private StrategiesPage strategiesPage;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			String login = args.getOptionValues("login").get(0);
			String pws = args.getOptionValues("pwd").get(0);
			Snapshot.SnapshotBuilder snapshotBuilder = Snapshot.builder();
			snapshotBuilder.startTime(LocalDateTime.now());
			doExtraction(login, pws, snapshotBuilder);

			saveSnapshotAfterEnd(snapshotBuilder);
		} catch (Exception e) {
			throw e;
		} finally {
			webDriver.quit();
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
