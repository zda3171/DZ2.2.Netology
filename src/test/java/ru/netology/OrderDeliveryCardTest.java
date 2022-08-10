package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderDeliveryCardTest {
    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Барнаул");
        $("[data-test-id=date] input").setValue("13.08.2022");
        $("[data-test-id=name] input").setValue("Пупов Николай");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $$(By.className("button__text")).last().click();
        Condition exists;
        $("[data-test-id=notification]").shouldBe(exist, Duration.ofSeconds(10));
    }
}
