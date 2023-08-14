package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BrandFiltersTest extends TestBase {

    @ValueSource(
            strings = {"LADA (ВАЗ)", "Audi", "BMW", "Ford", "Hyundai"}
    )
    @DisplayName("Фильтр корректно открывается по выбранному значению")
    @ParameterizedTest(name = "Открытие фильтра по марке {0}")
    void successfulSearchTest(String brandName) {
        open("https://auto.ru/");
        $$(".IndexMarks__item-name").findBy(text(brandName)).click();
        $("[name = mark").sibling(0).shouldHave(text(brandName));
    }
}
