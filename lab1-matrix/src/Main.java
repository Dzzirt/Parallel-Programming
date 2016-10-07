import java.util.Random;

public class Main {

    public static void main(String[] args) throws MatrixIsNotSquare {
        Random random = new Random();
        double[][] mat = new double[200][200];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                mat[i][j] = random.nextInt(25);
            }

        }


        long asyncStartTime = System.currentTimeMillis();
        final double[][] async = new AsyncMatrixInverter(Integer.valueOf(args[0])).getInvertMatrix(mat);
        long asyncEndTime   = System.currentTimeMillis();
        long asyncTotalTime = asyncEndTime - asyncStartTime;
        System.out.println("Async time " + asyncTotalTime);


        long startTime = System.currentTimeMillis();
        final double[][] iterative = new MatrixInverter().getInvertMatrix(mat);
        long endTime   = System.currentTimeMillis();
        long iterativeTotalTime = endTime - startTime;
        System.out.println("Iterative time " + iterativeTotalTime);

    }
}
