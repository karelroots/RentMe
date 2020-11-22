package com.rent.rent;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import javax.swing.text.Highlighter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FunctionalTests {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @BeforeClass
    public static void openPage() {
        timeout = 20000;
        baseUrl = "localhost:8080";
        browser = "chrome";
    }

    private static void registerBefore() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("test@test.ee");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
    }

    @Test
    public void Test01() {
        open("/registreeri");
        $( By.id("name")).setValue("");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test02() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test03() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test04() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test05() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test06() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("pass");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test07() {
        open("/registreeri");
        $( By.id("name")).setValue("@$€ii}");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test08() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("234karen");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test09() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("@$€ii}");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test10() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("@$€ii}");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test11() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test12() {
        open("/registreeri");
        $( By.id("name")).setValue("234karen");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        sleep(2000);
        Assert.assertFalse($(byText("Kasutaja on registreeritud!")).isDisplayed());
    }
    @Test
    public void Test13() {
        open("/registreeri");
        $( By.id("name")).setValue("Hanna");
        $( By.id("lastName")).setValue("Jam");
        $( By.id("username")).setValue("hanna123");
        $( By.id("email")).setValue("hanna@jam.com");
        $( By.id("password")).setValue("password");
        $(byText("Send")).click();
        $(byText("Kasutaja on registreeritud!")).isDisplayed();
    }

    @Test
    public void Test14() {
        registerBefore();
        open("/");
        $( By.id("email")).setValue("test@test.ee");
        $( By.id("password")).setValue("");
        $( By.className("checkbox")).click();
        $(byText("Log in")).click();
        $(byText("Invalid username/password combination\n")).should(exist);
    }
    @Test
    public void Test15() {
        registerBefore();
        open("/");
        $( By.id("email")).setValue("");
        $( By.id("password")).setValue("password");
        $(byText("Log in")).click();
        $(byText("Invalid username/password combination\n")).should(exist);
    }
    @Test
    public void Test16() {
        registerBefore();
        open("/");
        $( By.id("email")).setValue("test@test.ee");
        $( By.id("password")).setValue("password");
        $( By.className("checkbox")).click();
        $(byText("Log in")).click();
        $(byText("Invalid username/password combination\n")).shouldNot(exist);
        $(byText("Log out")).should(exist);
    }
    @Test
    public void Test17() {
        registerBefore();
        open("/");
        $( By.id("email")).setValue("test@test");
        $( By.id("password")).setValue("password");
        $(byText("Log in")).click();
        $(byText("Invalid username/password combination\n")).should(exist);
    }
    @Test
    public void Test18() {
        registerBefore();
        open("/");
        $( By.id("email")).setValue("test@test.ee");
        $( By.id("password")).setValue("pass");
        $( By.className("checkbox")).click();
        $(byText("Log in")).click();
        $(byText("Invalid username/password combination\n")).should(exist);
    }

    @Test
    public void Test19() {
        registerBefore();
        open("/");
        $( By.id("email")).setValue("test@test.ee");
        $( By.id("password")).setValue("password");
        $(byText("Invalid username/password combination\n")).shouldNot(exist);
        $(byText("Log out")).should(exist);
    }

    @Test
    public void Test20() {
        registerBefore();
        open("/");
        $( By.id("email")).setValue("test@test.ee");
        $( By.id("password")).setValue("pass");
        $(byText("Log out")).click();
        $(byText("Log in")).should(exist);
    }
}