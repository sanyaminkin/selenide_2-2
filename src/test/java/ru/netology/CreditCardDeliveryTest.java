package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SetValueOptions;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldMakeOrderTest() {
        $("[data-test-id='city']").$("[placeholder='Город']").setValue("Санкт-Петербург");
        //LocalDate localDate = LocalDate.now();
        //LocalDate newDate = localDate.plusDays(3);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        //String dateText = newDate.format(formatter);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        String planningDate = new String();
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id= 'name']").$("[name ='name']").setValue("Николай Римский-Корсаков");
        $("[data-test-id='phone']").$("[name='phone']").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);

    }
}
