import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructorReturnIllegalArgumentExceptionIfParameterIsNull(){
        try {
            Hippodrome hippodrome = new Hippodrome(null);

        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }
}