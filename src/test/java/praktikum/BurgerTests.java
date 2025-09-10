package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BurgerTests {

    static Stream<Object[]> priceData() { //Цены на всякое
        return Stream.of(
                new Object[]{100f, new float[]{50f, 50f}, 100f * 2 + 50f + 50f},
                new Object[]{200f, new float[]{}, 200f * 2},
                new Object[]{150f, new float[]{100f, 200f, 50f}, 150f * 2 + 100f + 200f + 50f}
        );
    }

    @ParameterizedTest
    @MethodSource("priceData")
    @DisplayName("Проверка расчёта цены")
    void shouldCalculateBurgerPriceCorrectly(float bunPrice, float[] ingredientPrices, float expectedPrice) {

        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(bunMock);

        for (float price : ingredientPrices) {
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getPrice()).thenReturn(price);
            burger.addIngredient(ingredientMock);
        }

        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.01f,
                String.format("Неверная цена бургера: булка %.2f, ингредиенты %s. Ожидалось %.2f, получено %.2f",
                        bunPrice, java.util.Arrays.toString(ingredientPrices), expectedPrice, actualPrice));
    }

    @Test
    @DisplayName("Проверка перемещения ингредиентов")
    void shouldMoveIngredientsCorrectly() {

        Burger burger = new Burger();
        burger.setBuns(mock(Bun.class));

        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(0, 2);

        assertEquals(secondIngredient, burger.ingredients.get(0),
                "Ингредиенты не переместились корректно: первый элемент не тот");
        assertEquals(thirdIngredient, burger.ingredients.get(1),
                "Ингредиенты не переместились корректно: второй элемент не тот");
        assertEquals(firstIngredient, burger.ingredients.get(2),
                "Ингредиенты не переместились корректно: третий элемент не тот");
    }

    @Test
    @DisplayName("Проверка удаления ингредиента")
    void shouldRemoveIngredientCorrectly() {

        Burger burger = new Burger();
        burger.setBuns(mock(Bun.class));

        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size(),
                "Ингредиент не удалился, размер списка после удаления должен быть 0");
    }

    @Test
    @DisplayName("Проверка формирования чека")
    void shouldGenerateReceiptCorrectly() {

        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("white bun");
        when(bunMock.getPrice()).thenReturn(100f);

        Ingredient ingredientMock = mock(Ingredient.class);
        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock.getName()).thenReturn("cutlet");
        when(ingredientMock.getPrice()).thenReturn(200f);

        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String expectedReceipt = String.format(
                "(==== white bun ====)%n" +
                        "= filling cutlet =%n" +
                        "(==== white bun ====)%n" +
                        "%nPrice: %f%n",
                burger.getPrice()
        );

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt,
                "Чек сформирован некорректно. Ожидалось:\n" + expectedReceipt + "\nПолучено:\n" + actualReceipt);
    }
}
