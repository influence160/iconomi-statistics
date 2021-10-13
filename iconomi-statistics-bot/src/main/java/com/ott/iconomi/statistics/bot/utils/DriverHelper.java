package com.ott.iconomi.statistics.bot.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ott.iconomi.statistics.bot.SurtBotConfiguration.IMPLICIT_WAIT;

public abstract class DriverHelper {

    private DriverHelper() {

    }

    public static List<WebElement>  findElementsSafeBy(WebDriver driver, By by) {
        driver.manage().timeouts().implicitlyWait(Duration.of(0, ChronoUnit.SECONDS));
        List<WebElement> result = driver.findElements(by);
        driver.manage().timeouts().implicitlyWait(Duration.of(IMPLICIT_WAIT, ChronoUnit.SECONDS));
        return result;
    }
}
