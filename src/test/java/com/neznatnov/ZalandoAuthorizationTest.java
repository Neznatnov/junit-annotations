package com.neznatnov;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

@DisplayName("Авторизация существующего пользователя")
public class ZalandoAuthorizationTest {
    @BeforeEach
    void setup(){
        open("https://www.zalando.cz/");
    }
    @CsvFileSource(resources = "/authorizationData.csv")
    @ParameterizedTest(name = "Авторизация существующего пользователя {0} с паролем {1}")
    void zalandoAuthorizationTest(String login, String password){


        $$("a[href='/myaccount/']").first().click();
        $(By.id("login.email")).setValue(login);
        $(By.id("login.secret")).setValue(password).pressEnter();
        $("a[title='Přihlásit se']").click();
        $$("h1._6zR8Lt.NoIaPV.FxZV-M._4F506m").findBy(text("Váš účet")).should(exist);

    }
}
