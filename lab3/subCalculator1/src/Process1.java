/**
 * Created by Dzzirt on 07.12.2016.
 */
public class Process1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(Process1.class.getSimpleName() + " " +
                calculateInnerPoints(Integer.parseInt(args[0])) + " " +
                (System.currentTimeMillis() - start));
    }

    public static int calculateInnerPoints(int dots) {
        try {
            int innerCount = 0;//число точек, попавших в круг

            for(int i = 0; i < dots; i++)
            {
                double x = (Math.random() * 2) - 1;
                double y = (Math.random() * 2) - 1;
                if(Math.pow(x, 2) + Math.pow(y, 2) <= 1)
                    innerCount++;
            }
            return innerCount;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
