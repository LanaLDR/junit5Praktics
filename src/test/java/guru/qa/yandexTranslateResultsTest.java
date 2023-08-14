package guru.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class yandexTranslateResultsTest extends TestBase {

    @BeforeEach
    void beforeEach() {
        open("https://translate.yandex.ru");
    }

    @CsvSource(value = {
            "en | Hello, Johnny!",
            "es | ¡Hola, Johnny!"
    },
            delimiter = '|')
    @DisplayName("Корректно работает перевод на разные языки")
    @ParameterizedTest(name = "CsvSource, перевод \"Привет, Джонни\" на {0} равен {1}")
    void successfulTranslateTextTest(String langReduction, String translate) {
        $("#srcLangButton").click();
        $("[data-value = ru]").click();
        $("#dstLangButton").click();
        $("[data-value = '" + langReduction + "']").click();
        $(".fakearea-container #fakeArea").setValue("Привет, Джонни!");
        $("[data-complaint-type = fullTextTranslation]").shouldHave(text(translate));
    }

    static Stream<Arguments> yaTranslateNavigationLocaleTest() {
        return Stream.of(
                Arguments.of("English", List.of("Text", "Sites", "Documents", "Images", "For business")),
                Arguments.of("Deutsch", List.of("Text", "Websites", "Dokumente", "Bilder", "Für Unternehmen"))
        );
    }

    @MethodSource
    @DisplayName("Язык навигации страницы меняется при смене локали")
    @ParameterizedTest(name = "Навигация с локализацией {0} содержит значения {1}")
    void yaTranslateNavigationLocaleTest(String locale, List<String> navValue) {
        $("#localeRoutesExpand").click();
        $$(".locale-routes-menu-link").findBy(text(locale)).click();
        $$(".header-nav a").shouldHave(texts(navValue));
    }
}
