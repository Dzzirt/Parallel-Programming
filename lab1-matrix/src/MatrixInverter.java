public class MatrixInverter extends BaseMatrixInverter {

    @Override
    public double[][] scale(double[][] inputMat, double scale) {
        for (int row = 0; row < inputMat.length; row++) {
            for (int col = 0; col < inputMat[0].length; col++) {
                inputMat[row][col] *= scale;
            }
        }
        return inputMat;
    }

    @Override
    public double[][] getCofactorMatrix(double[][] minorsMat) {
        final int length = minorsMat.length;
        double[][] outputMat = new double[length][length];
        for (int row = 0; row < minorsMat.length; row++) {
            for (int col = 0; col < minorsMat[0].length; col++) {
                outputMat[row][col] = Math.pow(-1, row + col) * minorsMat[row][col];
            }
        }
        return outputMat;
    }

    @Override
    public double[][] getTransposedMatrix(double[][] inputMat) {
        final int length = inputMat.length;
        double[][] outputMat = new double[length][length];
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                outputMat[col][row] = inputMat[row][col];
            }
        }
        return outputMat;
    }

    @Override
    public double[][] getMinorMatrix(double[][] inputMat) throws MatrixIsNotSquare {
        double[][] outputMat = new double[inputMat.length][inputMat.length];
        for (int row = 0; row < inputMat.length; row++) {
            for (int col = 0; col < inputMat[0].length; col++) {
                outputMat[row][col] = getDeterminant(decreaseSize(inputMat, row, col));
            }
        }
        return outputMat;
    }

    @Override
    protected double[][] getCharactersMatrix(int size) {
        final double[][] mat = new double[size][size];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                final double plus = Math.pow(-1, j);
                mat[i][j] = (i % 2 == 0) ? plus : -plus;
            }
        }
        return mat;
    }
}
