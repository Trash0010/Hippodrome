import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
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
        return Stream.of("", " ", "    ");
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

    @Test
    public void getNameReturnFirstArgInObjectWithTwoArg() {
        Horse horseWithTwoArg = new Horse("Name", 20.0);
        String expected = "Name";
        String actual = horseWithTwoArg.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void getNameReturnFirstArgInObjectWithThreeArg() {
        Horse horseWithThreeArg = new Horse("Name", 20.0, 200.0);
        String expected = "Name";
        String actual = horseWithThreeArg.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void getSpeedReturnSecondArgInObjectWithTwoArg() {
        Horse horseWithTwoArg = new Horse("Name", 20.0);
        double expected = 20.0;
        double actual = horseWithTwoArg.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    public void getSpeedReturnSecondArgInObjectWithThreeArg() {
        Horse horseWithThreeArg = new Horse("Name", 20.0, 200.0);
        double expected = 20.0;
        double actual = horseWithThreeArg.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    public void getDistanceReturnZeroInObjectWithTwoArg() {
        Horse horseWithTwoArg = new Horse("Name", 20.0);
        double expected = 0.0;
        double actual = horseWithTwoArg.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    public void getDistanceReturnThirdArgInObjectWithThreeArg() {
        Horse horseWithThreeArg = new Horse("Name", 20.0, 200.0);
        double expected = 200.0;
        double actual = horseWithThreeArg.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    public void moveUseMethodGetRandomDoubleWithParameters(){
        MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class);
        Horse horse = new Horse("Name", 20,200);
        horse.move();
        horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.5, 0.6, 0.7 })
    public void moveAssignsValueOfDistanceAccordingToCorrectFormula(double fakeValue){

        double speed = 20;
        double distance = 200;
        String name = "Name";
        double expected = distance + speed * fakeValue;


        Horse horse = new Horse(name, speed, distance);

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class);){
            horseMockedStatic.when(()-> Horse.getRandomDouble(0.2,0.9)).thenReturn(fakeValue);
            horse.move();
        }

        assertEquals(expected, horse.getDistance());
    }


}