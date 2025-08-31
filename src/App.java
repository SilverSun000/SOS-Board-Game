import gui.MainWindow;

public class App {

    public static boolean findPalindrome(String s) {
      String r = "";

      for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        r = ch + r;
      }

      return r.equals(s);
    }

    public static double celsiusToFahrenheit(double c) {
      return c * 9 / 5 + 32;
    }

    public static double fahrenheitToCelsius(double f) {
      return (f - 32) * 5 / 9;
    }

    public static void main(String[] args) {
      MainWindow myWindow = new MainWindow();
    }
}
