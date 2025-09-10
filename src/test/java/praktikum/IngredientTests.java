package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class IngredientTests {


    static Stream<Ingredient> ingredientData() {
        return Stream.of(
                new Ingredient(IngredientType.SAUCE, "BBQ Sauce", 150.0f),
                new Ingredient(IngredientType.SAUCE, "Hot Sauce", 100.0f),
                new Ingredient(IngredientType.FILLING, "Beef Patty", 250.0f),
                new Ingredient(IngredientType.FILLING, "Chicken", 180.0f)
        );
    }

    @ParameterizedTest
    @MethodSource("ingredientData")
    @DisplayName("Проверка корректного возврата имени ингредиента")
    void shouldReturnCorrectIngredientName(Ingredient ingredient) {
        assertEquals(ingredient.name, ingredient.getName(),
                "Метод getName должен возвращать имя, переданное в конструктор");
    }

    @ParameterizedTest
    @MethodSource("ingredientData")
    @DisplayName("Проверка корректного возврата цены ингредиента")
    void shouldReturnCorrectIngredientPrice(Ingredient ingredient) {
        assertEquals(ingredient.price, ingredient.getPrice(), 0.01f,
                "Метод getPrice должен возвращать цену, переданную в конструктор");
    }

    @ParameterizedTest
    @MethodSource("ingredientData")
    @DisplayName("Проверка корректного возврата типа ингредиента")
    void shouldReturnCorrectIngredientType(Ingredient ingredient) {
        assertEquals(ingredient.type, ingredient.getType(),
                "Метод getType должен возвращать тип, переданный в конструктор");
    }
}
