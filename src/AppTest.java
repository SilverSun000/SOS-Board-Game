import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    App app;
    String s = "racecar";
    String z = "notapalindrome";

    @BeforeEach
    void setup() {
      app = new App();
    }

    @Test
    @DisplayName("Palindrome Is True Test")
    void testPalindrome() {
        assertTrue(app.findPalindrome(s), "Palindrome test should work!");
    }

    @Test
    @DisplayName("Palindrome Is False Test")
    void secondTestPalindrome() {
      assertFalse(app.findPalindrome(z), "Palindrome test should NOT work!");
    }

    @Test
    @DisplayName("Fahrenheit To Celsius Test")
    void fahrenheitToCelsius() {
      assertEquals(0, app.fahrenheitToCelsius(32), "32 Degrees in Fahrenheit is 0 Degrees Celsius");
    }

    @Test
    @DisplayName("Celsius To Fahrenheit Test")
    void celsiusToFahrenheit() {
      assertEquals(32, app.celsiusToFahrenheit(0), "0 Degrees in Celsius is 32 Degrees in Fahrenheit");
    }
}

