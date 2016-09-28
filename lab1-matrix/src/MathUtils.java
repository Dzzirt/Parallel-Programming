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

    public static float[][] getMinorMatrix(float[][] inputMat) throws MatrixIsNotSqaure {
        float[][] outputMat = new float[inputMat.length][inputMat.length];
        for (int row = 0; row < inputMat.length; row++) {
            for (int col = 0; col < inputMat[0].length; col++) {
                outputMat[row][col] = getDeterminant(decreaseSize(inputMat, row, col));
            }
        }
        return outputMat;
    }
    public static float getDeterminant(float[][] inputMat) throws MatrixIsNotSqaure {
        int colNum = inputMat[0].length;
        int rowNum = inputMat.length;
        if (colNum != rowNum) {
            throw new MatrixIsNotSqaure();
        }
        if (inputMat.length == 2) {
            return inputMat[0][0] * inputMat[1][1] - inputMat[0][1] * inputMat[1][0];
        }
        float[][] charMat = getCharactersMatrix(colNum);
        float determinant = 0;
        for (int col = 0; col < inputMat.length; col++) {
            float[][] subMat = decreaseSize(inputMat, 0, col);
            determinant += inputMat[0][col] * getDeterminant(subMat) * charMat[0][col];
        }
        return determinant;
    }

    private static float[][] decreaseSize(float[][] inputMat, int rmRow, int rmCol) {
        int newSize = inputMat.length - 1;
        int rowCarret = 0;
        int colCarret = 0;
        boolean moveRowCarret = false;
        float[][] outputMat = new float[newSize][newSize];
        for (int i = 0; i < inputMat.length; i++) {
            for (int j = 0; j < inputMat[0].length; j++) {
                if (i != rmRow && j != rmCol) {
                    outputMat[rowCarret][colCarret] = inputMat[i][j];
                    colCarret++;
                    moveRowCarret = true;
                }
            }
            colCarret = 0;
            if (moveRowCarret) {
                moveRowCarret = false;
                rowCarret++;
            }
        }
        return outputMat;
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
