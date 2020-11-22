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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FunctionalTests {

    @BeforeClass
    public static void openPage() {
        timeout = 20000;
        baseUrl = "localhost:8080";
        browser = "chrome";
    }

    @Test
    public void FRT01() {
        open("/registreeri");
        $(By.id("name")).setValue("");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }

    @Test
    public void FRT02() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }

    @Test
    public void FRT03() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }

    @Test
    public void FRT04() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }

    @Test
    public void FRT05() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }

    @Test
    public void FRT06() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("pass");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
        assertTrue($(byText("*Your password must have at least 5 characters")).isDisplayed());
    }

    @Test
    public void FRT07() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        $(byText("Kasutaja on registreeritud!")).should(exist);
    }

    @Test
    public void FRT08() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("234karen");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
        assertTrue($(byText("First name should consist of letters only")).isDisplayed());
    }

    @Test
    public void FRT09() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("@$€ii}");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
        assertTrue($(byText("Last name should consist of letters only")).isDisplayed());
    }

    @Test
    public void FRT10() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("@$€ii}");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
        assertTrue($(byText("Username should consist of letters or numbers only")).isDisplayed());
    }

    @Test
    public void FRT11() {
        open("/registreeri");
        $(By.id("name")).setValue("Hanna");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
        assertTrue($(byText("*Please provide a valid Email")).isDisplayed());
    }

    @Test
    public void FRT12() {
        open("/registreeri");
        $(By.id("name")).setValue("234karen");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
        assertTrue($(byText("First name should consist of letters only")).isDisplayed());
    }

    @Test
    public void FRT13() {
        open("/registreeri");
        $(By.id("name")).setValue("@$€ii}");
        $(By.id("lastName")).setValue("Jam");
        $(By.id("username")).setValue("hanna123");
        $(By.id("email")).setValue("hanna@jam.com");
        $(By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
        assertTrue($(byText("First name should consist of letters only")).isDisplayed());
    }

    @Test
    public void FRT14() {
        open("/");
        $(By.id("email")).setValue("test@test.ee");
        $(By.id("password")).setValue("");
        $(By.className("checkbox")).click();
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).should(exist);
    }

    @Test
    public void FRT15() {
        open("/");
        $(By.id("email")).setValue("");
        $(By.id("password")).setValue("password");
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).should(exist);
    }

    @Test
    public void FRT16() {
        open("/");
        $(By.id("email")).setValue("test@test.ee");
        $(By.id("password")).setValue("password");
        $(By.className("checkbox")).click();
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).shouldNot(exist);
        $(byText("Log out")).should(exist);
    }

    @Test
    public void FRT17() {
        open("/");
        $(By.id("email")).setValue("test@test");
        $(By.id("password")).setValue("password");
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).should(exist);
    }

    @Test
    public void FRT18() {
        open("/");
        $(By.id("email")).setValue("test@test.ee");
        $(By.id("password")).setValue("pass");
        $(By.className("checkbox")).click();
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).should(exist);
    }

    @Test
    public void FRT19() {
        open("/");
        $(By.id("email")).setValue("test@test.ee");
        $(By.id("password")).setValue("password");
        $(byValue("Login")).click();
        $(byText("Invalid username/password combination")).shouldNot(exist);
        $(byText("Log out")).should(exist);
    }

    @Test
    public void FRT20() {
        open("/");
        $(By.id("email")).setValue("test@test.ee");
        $(By.id("password")).setValue("password");
        $(byValue("Login")).click();
        $(byText("Log out")).click();
        $(byValue("Login")).should(exist);
    }
}