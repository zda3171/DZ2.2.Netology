package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderDeliveryCardTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    String planningDate = generateDate(4);
    SelenideElement notification = $("[data-test-id=notification]");

    @Test
    void shouldOrderCardDelivery() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Барнаул");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Пупов Николай");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$(By.className("button__text")).last().click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        notification.$x(".//div[@class='notification__title']").should(text("Успешно!"));
        notification.$x(".//div[@class='notification__content']").should(text("Встреча успешно забронирована на " + planningDate));
    }
}
