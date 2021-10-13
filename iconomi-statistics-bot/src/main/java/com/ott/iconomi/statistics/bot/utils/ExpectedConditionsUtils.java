package com.ott.iconomi.statistics.bot.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.ott.iconomi.statistics.bot.utils.DriverHelper.findElementsSafeBy;

@Slf4j
public class ExpectedConditionsUtils  {

    private ExpectedConditionsUtils() {};

    public static ExpectedCondition<WebElement> presenceOfAtLeastOneElementLocated(final By ... locators) {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                for (int i = 0; i < locators.length -1; i++) {
                    By locator = locators[i];
//                    try {
                        List<WebElement> elements = findElementsSafeBy(driver,locator);
                        if (!elements.isEmpty()) {
                            return elements.get(0);
                        }
//                    } catch (NoSuchElementException e) {
//                        log.trace("no element found by " + locator);
//                    }
                }
//                return driver.findElement(locators[locators.length - 1]);
                List<WebElement> elements = findElementsSafeBy(driver,locators[locators.length - 1]);
                if (!elements.isEmpty()) {
                    return elements.get(0);
                }
                return null;
            }

            @Override
            public String toString() {
                return "presence of at least one element located by: " + Arrays.toString(locators);
            }
        };
    }
}
