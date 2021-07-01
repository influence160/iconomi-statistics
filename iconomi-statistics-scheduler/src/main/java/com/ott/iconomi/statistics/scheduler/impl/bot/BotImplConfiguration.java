package com.ott.iconomi.statistics.scheduler.impl.bot;

import com.ott.iconomi.statistics.bot.SurtBotApplication;
import com.ott.iconomi.statistics.bot.SurtBotConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SurtBotConfiguration.class})
@ComponentScan(basePackages = "com.ott.iconomi.statistics.bot.iconomi")
@ConditionalOnBean(WebsiteJobService.class)
public class BotImplConfiguration {

}
