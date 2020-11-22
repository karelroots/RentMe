package com.rent.rent;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.springframework.test.annotation.DirtiesContext;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FunctionalAcceptanceTests {

    @BeforeClass
    public static void openPage() {
        timeout = 20000;
        baseUrl = "localhost:8080";
        browser = "chrome";
    }

    @Test
    public void AFT01() {
        open("/registreeri");
        $(By.id("name")).setValue("test");
        $(By.id("lastName")).setValue("test");
        $(By.id("username")).setValue("test1");
        $(By.id("email")).setValue("test@test.ee");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        $(byText("Selle e-mailiga on juba kasutaja registreeritud!")).should(exist);
    }

    @Test
    public void AFT02() {
        open("/registreeri");
        $(By.id("name")).setValue("test");
        $(By.id("lastName")).setValue("test");
        $(By.id("username")).setValue("test2");
        $(By.id("email")).setValue("JohnDoe12@gmail.com");
        $(By.id("password")).setValue("Ⓝ♡➳༄♛");
        $(byText("Send")).click();
        sleep(2000);
        $(byText("Kasutaja on registreeritud!")).should(exist);
    }

    @Test
    public void AFT03() {
        open("/registreeri");
        $(By.id("name")).setValue("test");
        $(By.id("lastName")).setValue("test");
        $(By.id("username")).setValue("test3");
        $(By.id("email")).setValue("машаэмамафя@гмаил.ру");
        $(By.id("password")).setValue("Test123");
        $(byText("Send")).click();
        sleep(2000);
        $(byText("Kasutaja on registreeritud!")).should(exist);
    }

    @Test
    public void AFT04() {
        open("/");
        $(By.id("email")).setValue("DROP TABLE IF EXISTS users;");
        $(By.id("password")).setValue("password");
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).should(exist);
    }

    @Test
    public void AFT05() {
        open("/");
        $(By.id("email")).setValue("JohnDoe12@gmail.com");
        $(By.id("password")).setValue("DROP TABLE IF EXISTS users;");
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).should(exist);
    }

    @Test
    public void AFT06() {
        open("/registreeri");
        $(By.id("name")).setValue("test");
        $(By.id("lastName")).setValue("test");
        $(By.id("username")).setValue("test6");
        $(By.id("email")).setValue("JohnDoe55@gmail.com");
        $(By.id("password")).setValue("DROP TABLE IF EXISTS users;");
        $(byText("Send")).click();
        sleep(2000);
        $(byText("Kasutaja on registreeritud!")).should(exist);
    }

    @Test
    public void AFT07() {
        open("/registreeri");
        $(By.id("name")).setValue("test");
        $(By.id("lastName")).setValue("test");
        $(By.id("username")).setValue("test7");
        $(By.id("email")).setValue("DROP TABLE IF EXISTS users;");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        $(byText("*Please provide a valid Email")).should(exist);
    }

    @Test
    public void AFT08() {
        open("/");
        $(By.id("email")).setValue("JohnDoe99@gmail.com");
        $(By.id("password")).setValue("BestGirl12");
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).should(exist);
    }

    @Test
    public void AFT09() {
        open("/registreeri");
        $(By.id("name")).setValue("Elon");
        $(By.id("lastName")).setValue("Musk");
        $(By.id("username")).setValue("test");
        $(By.id("email")).setValue("elon@tesla.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertTrue($(byText("Selle kasutajanimega on juba kasutaja registreeritud!")).isDisplayed());
    }

    @Test
    public void AFT10() {
        open("/registreeri");
        $(By.id("name")).setValue("test");
        $(By.id("lastName")).setValue("test");
        $(By.id("username")).setValue("test10");
        $(By.id("email")).setValue("JohnDoe10000@gmail.com");
        $(By.id("password")).setValue("BestGirl12");
        $(byText("Send")).click();
        sleep(2000);
        assertTrue($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }

    @Test
    public void AFT11() {
        open("/registreeri");
        $(By.id("name")).setValue("маша");
        $(By.id("lastName")).setValue("Mama");
        $(By.id("username")).setValue("test11");
        $(By.id("email")).setValue("masha1@gmail.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertTrue($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }

    @Test
    public void AFT12() {
        open("/registreeri");
        $(By.id("name")).setValue("Masha");
        $(By.id("lastName")).setValue("мамафя");
        $(By.id("username")).setValue("test12");
        $(By.id("email")).setValue("masha2@gmail.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertTrue($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
}
