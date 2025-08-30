import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class AppTest {

    App app;

    @BeforeEach
    void setup() {
      app = new App();
    }

    @Test
    @DisplayName("Simple Modulus should work")
    void testModulus() {
        assertEquals(0, app.modulus(15, 5),
          "Regular Modulus sould work");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure that we can receive non zero answers as well")
    void testModulusNonZeroReturn() {
      assertEquals(1, app.modulus(5, 4), "Four modulus five should be 1");
      assertEquals(2, app.modulus(5, 3), "Three modulus 5 should be 2");
    }
}
