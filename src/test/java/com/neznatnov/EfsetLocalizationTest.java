package com.neznatnov;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.CollectionCondition;

import java.util.List;
import java.util.stream.Stream;

import domain.Locale;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Web-тест локализации вверхнего меню efset.com")
public class EfsetLocalizationTest {

    @BeforeEach
    void setup(){
    open("https://www.efset.org/");
  }

    static Stream<Arguments> efsetLocaleTest() {
        return Stream.of(
                Arguments.of(Locale.ENGLISH, List.of("Our Tests", "Certification", "CEFR", "About Us")),
                Arguments.of(Locale.DEUTSCH, List.of("Unsere Tests", "Zertifizierung", "GER", "Über uns")),
                Arguments.of(Locale.ITALIANO, List.of("I nostri test", "Certificazione", "QCER", "Chi siamo"))
        );
    }
    @MethodSource("efsetLocaleTest")
    @ParameterizedTest
    void efsetLocaleTest(Locale siteLocale, List<String> expectedButtons){
        $("div[data-test='language-selector'] li span").click();
        $$("div.jss163 a[data-test^='language-anchor']").findBy(text(String.valueOf(siteLocale))).click();
        
        $$("ul.MuiList-root div[data-test='menu-item'] p.MuiTypography-body1").filter(visible)
                .shouldHave(CollectionCondition.texts(expectedButtons));

    }

}
