import java.io.IOException;

/**
 * Created by Dzzirt on 15.12.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Invalid argument. Use '/?' to see list of available parameters");
        } else if (args[0].equals("/?")) {
            System.out.println("Available parameters: <points count>");
        } else {
            long start = System.currentTimeMillis();
            PiCalculator piCalculator = new PiCalculator();
            System.out.println("Main " + piCalculator.calculate(Integer.valueOf(args[0])) + " " + (System.currentTimeMillis() - start));
            System.out.println(piCalculator.getDetailedResult());
        }
    }
}
