package praktikum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class IngredientTypeTests {
    @Test
    void shouldHaveCorrectValues() {
        IngredientType[] types = IngredientType.values();


        assertEquals(2, types.length, "Должно быть 2 типа ингредиентов");


        assertEquals(IngredientType.SAUCE, types[0], "Первый тип должен быть SAUCE");
        assertEquals(IngredientType.FILLING, types[1], "Второй тип должен быть FILLING");
    }

    @Test
    void shouldReturnCorrectValueOf() {

        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"),
                "valueOf(\"SAUCE\") должен вернуть SAUCE");
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"),
                "valueOf(\"FILLING\") должен вернуть FILLING");
    }
}
