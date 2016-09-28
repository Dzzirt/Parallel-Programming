import java.util.Random;

/**
 * Created by Nikita on 21.09.2016.
 */
public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        float[][] mat = new float[3][3];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = random.nextInt(5);
            }

        }
        try {
            long startTime = System.currentTimeMillis();
            final float[][] minors = MathUtils.getMinorMatrix(mat);

            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.println(totalTime);
        } catch (MathUtils.MatrixIsNotSqaure matrixIsNotSqaure) {
            matrixIsNotSqaure.printStackTrace();
        }
    }
}
