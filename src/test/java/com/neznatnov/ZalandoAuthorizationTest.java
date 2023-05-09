package com.neznatnov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Авторизация существующего пользователя")
public class ZalandoAuthorizationTest {
    @BeforeEach
    void setup(){
        open("https://www.zalando.cz/");
    }
    @CsvFileSource(resources = "/authorizationData.csv")
    @ParameterizedTest(name = "Авторизация существующего пользователя {0} с паролем {1}")
    void zalandoAuthorizationTest(String login, String password){

        $("a[title='Přihlásit se']").click();
        $(By.id("login.email")).setValue(login);
        $(By.id("login.secret")).setValue(password).pressEnter();
    }
}
