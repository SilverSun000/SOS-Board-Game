public class App {

    public int modulus(int a, int b) {
      return a % b;
    }

    public static void main(String[] args) {
      int x = 5, y = 15;
      App finder = new App();
      System.out.println(finder.modulus(y, x));
    }
}
