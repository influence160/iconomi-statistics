package com.ott.iconomi.statistics.bot;

import javax.transaction.Transactional;

import com.ott.iconomi.statistics.bot.iconomi.IconomiPageHelper;
import com.ott.iconomi.statistics.bot.iconomi.pages.LoginPage;
import com.ott.iconomi.statistics.bot.iconomi.pages.StrategiesPage;
import com.ott.iconomi.statistics.domain.repository.SnapshotRepository;
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
public class SurtBotApplicationRunner implements ApplicationRunner {

	@Autowired
	BotDataImporter botDataImporter;

	@Autowired
	WebDriver webDriver;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String login = args.getOptionValues("login").get(0);
		String pwd = args.getOptionValues("pwd").get(0);
		botDataImporter.importData(login, pwd);
		webDriver.quit();
	}

}
