package com.neznatnov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Ввод суммы в поиск")
public class SkolkoSetCurrencyTest {
    @BeforeEach
    void setup(){
        open("https://xn----btbjevagxczuj6i.xn--p1ai/");
    }

    @ValueSource(ints = {
            5000000,
            6000000,
            7000000
    })
    @ParameterizedTest(name = "Ввод суммы {0} в поиск")
    void zillowSearchFilterTest(int setPrice){
        $(".form-control").setValue(String.valueOf(setPrice)).pressEnter();
        $("h2").shouldHave(text(String.valueOf(setPrice)));
    }
}
