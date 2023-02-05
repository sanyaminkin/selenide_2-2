package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CreditCardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldMakeOrderTest() {
        $("[data-test-id='city']").setValue("Санкт-Петербург");
        $("[data-test-id='name']").setValue("Сергей Иванов-Петров");
        $("[data-test-id='phone']").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 3);
        Date newDate = instance.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String dateText = dateFormat.format(instance);
        $("[data-test-id='date']").setValue(dateText);
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldMakeOrderTest2() {
        $("[placeholder='Город']").setValue("Санкт-Петербург");
        $("[name='name']").setValue("Сергей Иванов-Петров");
        $("[name='phone']").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 3);
        Date newDate = instance.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String dateText = dateFormat.format(instance);
        $("[placeholder='Дата встречи']").setValue("dateText");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
