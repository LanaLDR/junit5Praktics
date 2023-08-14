package guru.qa;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleWebTest {

    @CsvSource({
            "https://ru.selenide.org, Selenide",
            "https://junit.org, JUnit 5"
    })
    @ParameterizedTest(name = "В поисковой выдаче google присутствует url {0} для запроса {1}")
    void successfulSearchTest(String url, String searchQuery) {
        open("https://www.google.com/");
        $("[name=q]").setValue(searchQuery).pressEnter();
        $("[id=search]").shouldHave(text(url));
    }
}
