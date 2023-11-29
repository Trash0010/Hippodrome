import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructorReturnIllegalArgumentExceptionIfParameterIsNull() {
        try {
            Hippodrome hippodrome = new Hippodrome(null);

        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void constructorReturnIllegalArgumentExceptionIfListIsEmpty() {

        List<Horse> horses = new ArrayList<>();
        try {
            Hippodrome hippodrome = new Hippodrome(horses);

        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorsesReturnSameListWithSameObjects() {

        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());

        List<Horse> actualHorsesList = hippodrome.getHorses();

        for (int i = 0; i < horses.size(); i++) {
            assertEquals(horses.get(i).getName(), actualHorsesList.get(i).getName());
        }
    }

    @Test
    public void moveCallsTheMoveMethodOnAllHorses() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.only()).move();
        }
    }

    @Test
    public void getWinnerReturnHorseWithMaxDistance(){

        double minDistance = 100.0;
        double Distance = 200.0;
        double maxDistance = 500.0;

        Horse horseWithMinDistance = new Horse("Name1", 100, minDistance);
        Horse horseWithDistance = new Horse("Name2", 100, Distance);
        Horse horseWithMaxDistance = new Horse("Name3", 100, maxDistance);

        List<Horse> horses = new ArrayList<>();

        horses.add(horseWithMaxDistance);
        horses.add(horseWithDistance);
        horses.add(horseWithMinDistance);

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse actualHorse = hippodrome.getWinner();

        assertEquals(horseWithMaxDistance, actualHorse);
        assertEquals(horseWithMaxDistance.getDistance(), actualHorse.getDistance());
    }
}