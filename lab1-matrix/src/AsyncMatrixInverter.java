public class AsyncMatrixInverter extends BaseMatrixInverter {

    private volatile int m_threadCount;

    private synchronized void decrementThreadCount() {
        m_threadCount--;
    }

    private synchronized void incrementThreadCount() {
        m_threadCount++;
    }

    private synchronized int getM_threadCount() {
        return m_threadCount;
    }

    public AsyncMatrixInverter(int threadCount) {
        m_threadCount = threadCount;
    }

    private Thread startAsync(Runnable runnable) {
        while (getM_threadCount() == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        decrementThreadCount();
        final Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    @Override
    public double[][] scale(double[][] input, double scale) {
        int start = m_threadCount;
        for (int row = 0; row < input.length; row++) {
            int finalRow = row;
            startAsync(() -> {
                for (int col = 0; col < input[0].length; col++) {
                    input[finalRow][col] *= scale;
                }
                incrementThreadCount();
            });
        }
        while (m_threadCount != start) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return input;
    }

    public double[][] getCofactorMatrix(double[][] minorsMat) {
        final int length = minorsMat.length;
        int start = m_threadCount;
        double[][] outputMat = new double[length][length];
        for (int row = 0; row < minorsMat.length; row++) {
            int finalRow = row;
            startAsync(() -> {
                for (int col = 0; col < minorsMat[0].length; col++) {
                    outputMat[finalRow][col] = Math.pow(-1, finalRow + col) * minorsMat[finalRow][col];
                }
                incrementThreadCount();
            });

        }
        while (m_threadCount != start) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return outputMat;
    }

    @Override
    public double[][] getTransposedMatrix(double[][] inputMat) {
        final int length = inputMat.length;
        int start = m_threadCount;
        double[][] outputMat = new double[length][length];
        for (int row = 0; row < length; row++) {
            int finalRow = row;
            startAsync(() -> {
                for (int col = 0; col < length; col++) {
                    outputMat[col][finalRow] = inputMat[finalRow][col];
                }
                incrementThreadCount();
            });
        }
        while (m_threadCount != start) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return outputMat;
    }

    @Override
    public double[][] getMinorMatrix(double[][] inputMat) {
        double[][] outputMat = new double[inputMat.length][inputMat.length];
        int start = m_threadCount;
        for (int row = 0; row < inputMat.length; row++) {
            int finalRow = row;
            startAsync(() -> {
                for (int col = 0; col < inputMat[0].length; col++) {
                    try {
                        outputMat[finalRow][col] = getDeterminant(decreaseSize(inputMat, finalRow, col));
                    } catch (MatrixIsNotSquare matrixIsNotSquare) {
                        matrixIsNotSquare.printStackTrace();
                    }
                }
                incrementThreadCount();
            });
        }
        while (m_threadCount != start) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return outputMat;
    }

    @Override
    protected double[][] getCharactersMatrix(int size) {
        final double[][] mat = new double[size][size];
        int start = m_threadCount;
        for (int i = 0; i < mat.length; i++) {
            int row = i;
            startAsync(() -> {
                for (int col = 0; col < mat[0].length; col++) {
                    final double plus = Math.pow(-1, col);
                    mat[row][col] = (row % 2 == 0) ? plus : -plus;
                }
                incrementThreadCount();
            });
        }
        while (m_threadCount != start) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return mat;
    }
}
