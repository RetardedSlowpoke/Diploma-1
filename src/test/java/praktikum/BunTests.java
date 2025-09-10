package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTests {

    @Test
    @DisplayName("Проверка, что конструктор корректно инициализирует поля")
    void shouldCorrectlyInitializeFields() {
        Bun bun = new Bun("Булочка с кунжутом", 50.0f);

        assertEquals("Булочка с кунжутом", bun.getName(),
                "Название булочки должно совпадать с переданным");
        assertEquals(50.0f, bun.getPrice(),
                "Цена булочки должна совпадать с переданной");
    }

    @Test
    @DisplayName("Проверка, что булочка может иметь цену 0")
    void shouldAllowZeroPrice() {
        Bun bun = new Bun("Булочка даром", 0.0f);

        assertEquals(0.0f, bun.getPrice(),
                "Цена булочки должна быть 0");
    }

    @Test
    @DisplayName("Проверка, что булочка может иметь пустое название")
    void shouldAllowEmptyName() {
        Bun bun = new Bun("", 10.0f);

        assertEquals("", bun.getName(),
                "Название булочки может быть пустым");
    }
}
