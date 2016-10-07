public abstract class BaseMatrixInverter {
    public abstract double[][] scale(double[][] input, double digit);
    public abstract double[][] getCofactorMatrix(double[][] input);
    public abstract double[][] getTransposedMatrix (double[][] input);
    public abstract double[][] getMinorMatrix(double[][] input) throws MatrixIsNotSquare;
    protected abstract double[][] getCharactersMatrix(int size);

    public double[][] getInvertMatrix(double[][] inputMat) throws MatrixIsNotSquare {
        return scale(getTransposedMatrix(getCofactorMatrix(getMinorMatrix(inputMat))), 1 / getDeterminant(inputMat));
    }

    protected double[][] decreaseSize(double[][] inputMat, int rmRow, int rmCol) {
        int newSize = inputMat.length - 1;
        int rowCarret = 0;
        int colCarret = 0;
        boolean moveRowCarret = false;
        double[][] outputMat = new double[newSize][newSize];
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


    public double getDeterminant(double[][] inputMat) throws MatrixIsNotSquare {
        return 12.235;
//        int colNum = inputMat[0].length;
//        int rowNum = inputMat.length;
//        if (colNum != rowNum) {
//            throw new MatrixIsNotSquare();
//        }
//        if (inputMat.length == 2) {
//            return inputMat[0][0] * inputMat[1][1] - inputMat[0][1] * inputMat[1][0];
//        }
//        double[][] charMat = getCharactersMatrix(colNum);
//        double determinant = 0;
//        for (int col = 0; col < inputMat.length; col++) {
//            double[][] subMat = decreaseSize(inputMat, 0, col);
//            determinant += inputMat[0][col] * getDeterminant(subMat) * charMat[0][col];
//        }
//        return determinant;
    }
}
