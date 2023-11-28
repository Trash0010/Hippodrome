import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void constructorThrowIllegalArgumentException() {
        try {
            Horse horse = new Horse(null, 20.0);
        } catch (IllegalArgumentException e) {

            assertThrows(IllegalArgumentException.class, () -> {
                throw e;
            });
        }
    }

    @Test
    public void constructorThrowIllegalArgumentExceptionWithMessage() {
        try {
            Horse horse = new Horse(null, 20.0, 200.0);
        } catch (IllegalArgumentException e) {

            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @MethodSource("emptyString")
    void constructorThrowIllegalArgumentExceptionIfFirstArgumentEmpty(String argument) {
        try {
            Horse horse = new Horse(argument, 20.0);
        } catch (IllegalArgumentException e) {

            assertThrows(IllegalArgumentException.class, () -> {
                throw e;
            });
        }
    }

    @ParameterizedTest
    @MethodSource("emptyString")
    void constructorThrowIllegalArgumentExceptionWithMessageIfFirstArgumentEmpty(String argument) {
        try {
            Horse horse = new Horse(argument, 20.0, 200.0);
        } catch (IllegalArgumentException e) {

            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    static Stream<String> emptyString() {
        return Stream.of("", " ",  "    ");
    }

    @Test
    public void constructorThrowIllegalArgumentExceptionIfSecondArgumentNegative() {
        try {
            Horse horse = new Horse("Name", -20.0);
        } catch (IllegalArgumentException e) {

            assertThrows(IllegalArgumentException.class, () -> {
                throw e;
            });
        }
    }

    @Test
    public void constructorThrowIllegalArgumentExceptionWithMessageIfSecondArgumentNegative() {
        try {
            Horse horse = new Horse("Name", -20.0, 200.0);
        } catch (IllegalArgumentException e) {

            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void constructorThrowIllegalArgumentExceptionWithMessageIfThirdArgumentNegative() {
        try {
            Horse horse = new Horse("Name", 20.0, -200.0);
        } catch (IllegalArgumentException e) {

            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }
}