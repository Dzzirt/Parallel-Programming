/**
 * Created by Nikita on 21.09.2016.
 */
public class Main {

    public static void main(String[] args) {
        float[][] mat = new float[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 0, 1, 2},
                {3, 4, 5, 6}
        };
        try {
            final float determinant = MathUtils.getDeterminant(mat);
        } catch (MathUtils.MatrixIsNotSqaure matrixIsNotSqaure) {
            matrixIsNotSqaure.printStackTrace();
        }
    }
}
