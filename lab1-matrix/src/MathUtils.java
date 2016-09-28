import java.util.ArrayList;

/**
 * Created by Nikita on 28.09.2016.
 */
public class MathUtils {

    public static class MatrixIsNotSqaure extends Exception {

    }
    public static float[][] getInvertMatrix(float[][] inputMat) {
        return new float[2][2];
    }

    public static float getDeterminant(float[][] inputMat) throws MatrixIsNotSqaure {
        int colNum = inputMat[0].length;
        int rowNum = inputMat.length;
        if (colNum != rowNum) {
            throw new MatrixIsNotSqaure();
        }
        float[][] charMat = getCharactersMatrix(colNum);
        float determinant = 0;
        for (int col = 0; col < inputMat.length; col++) {
            float[][] subMat =
            //determinant += inputMat[0][col] * getDeterminant()
        }
        System.out.println(1);
        return 1;
    }

    private static float[][] decreaseSize(float[][] inputMat, int rmRow, int rmCol) {
        for (int i = 0; i < inputMat.length; i++) {
            for (int j = 0; j < inputMat[0].length; j++) {

            }
        }
    }
    private static float[][] getCharactersMatrix(int size) {
        final float[][] mat = new float[size][size];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                final float plus = (float) Math.pow(-1, j);
                mat[i][j] = (i % 2 == 0) ? plus : -plus;
            }
        }
        return mat;
    }
}
