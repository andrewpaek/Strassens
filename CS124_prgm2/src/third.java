import java.util.Random;

/**
 * Created by Andrew on 3/25/2016.
 */

/*
public class third {

    static int LEAF_SIZE = 1;
    static boolean marker = true;
    public static void main(String[] args) {
        int dim = 1;
        while (true) {

            int[][] A = test_mat_generator(dim);
            int[][] B = test_mat_generator(dim);

            long starttime;
            long endtime;
            int[][] R;

            starttime = System.nanoTime();
            R = strassenR(A, B);
            endtime = System.nanoTime();
            long strassens_totaltime = endtime - starttime;

            starttime = System.nanoTime();
            R = ikjAlgorithm(A, B);
            endtime = System.nanoTime();
            long regular_totaltime = endtime - starttime;

            System.out.println("Strassens: " + strassens_totaltime + " Regular: " + regular_totaltime + " Dimension: " + dim);
            if (regular_totaltime > strassens_totaltime) {
                break;
            }
            dim *= 2;
            marker = true;
        }

    }

    public static int[][] test_mat_generator(int n) {
        int[][] T = new int[n][n];
        Random rn = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = rn.nextInt(3);
                T[i][j] = temp;
            }
        }
        return T;
    }

    public static int[][] ikjAlgorithm(int[][] A, int[][] B) {
        int n = A.length;

        // initialise C
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }




    private static int[][] strassenR(int[][] A, int[][] B) {
        int n = A.length;
        if (marker) {
            if (n <= LEAF_SIZE) {
                return ikjAlgorithm(A, B);
            } else {
                // initializing the new sub-matrices
                int newSize = n / 2;
                int[][] a11 = new int[newSize][newSize];
                int[][] a12 = new int[newSize][newSize];
                int[][] a21 = new int[newSize][newSize];
                int[][] a22 = new int[newSize][newSize];

                int[][] b11 = new int[newSize][newSize];
                int[][] b12 = new int[newSize][newSize];
                int[][] b21 = new int[newSize][newSize];
                int[][] b22 = new int[newSize][newSize];

                int[][] aResult = new int[newSize][newSize];
                int[][] bResult = new int[newSize][newSize];

                // dividing the matrices in 4 sub-matrices:
                for (int i = 0; i < newSize; i++) {
                    for (int j = 0; j < newSize; j++) {
                        a11[i][j] = A[i][j]; // top left
                        a12[i][j] = A[i][j + newSize]; // top right
                        a21[i][j] = A[i + newSize][j]; // bottom left
                        a22[i][j] = A[i + newSize][j + newSize]; // bottom right

                        b11[i][j] = B[i][j]; // top left
                        b12[i][j] = B[i][j + newSize]; // top right
                        b21[i][j] = B[i + newSize][j]; // bottom left
                        b22[i][j] = B[i + newSize][j + newSize]; // bottom right
                    }
                }

                // Calculating p1 to p7:
                aResult = add(a11, a22);
                bResult = add(b11, b22);
                int[][] p1 = strassenR(aResult, bResult);
                // p1 = (a11+a22) * (b11+b22)

                aResult = add(a21, a22); // a21 + a22
                int[][] p2 = strassenR(aResult, b11); // p2 = (a21+a22) * (b11)

                bResult = subtract(b12, b22); // b12 - b22
                int[][] p3 = strassenR(a11, bResult);
                // p3 = (a11) * (b12 - b22)

                bResult = subtract(b21, b11); // b21 - b11
                int[][] p4 = strassenR(a22, bResult);
                // p4 = (a22) * (b21 - b11)

                aResult = add(a11, a12); // a11 + a12
                int[][] p5 = strassenR(aResult, b22);
                // p5 = (a11+a12) * (b22)

                aResult = subtract(a21, a11); // a21 - a11
                bResult = add(b11, b12); // b11 + b12
                int[][] p6 = strassenR(aResult, bResult);
                // p6 = (a21-a11) * (b11+b12)

                aResult = subtract(a12, a22); // a12 - a22
                bResult = add(b21, b22); // b21 + b22
                int[][] p7 = strassenR(aResult, bResult);
                // p7 = (a12-a22) * (b21+b22)

                // calculating c21, c21, c11 e c22:
                int[][] c12 = add(p3, p5); // c12 = p3 + p5
                int[][] c21 = add(p2, p4); // c21 = p2 + p4

                aResult = add(p1, p4); // p1 + p4
                bResult = add(aResult, p7); // p1 + p4 + p7
                int[][] c11 = subtract(bResult, p5);
                // c11 = p1 + p4 - p5 + p7

                aResult = add(p1, p3); // p1 + p3
                bResult = add(aResult, p6); // p1 + p3 + p6
                int[][] c22 = subtract(bResult, p2);
                // c22 = p1 + p3 - p2 + p6

                // Grouping the results obtained in a single matrix:
                int[][] C = new int[n][n];
                for (int i = 0; i < newSize; i++) {
                    for (int j = 0; j < newSize; j++) {
                        C[i][j] = c11[i][j];
                        C[i][j + newSize] = c12[i][j];
                        C[i + newSize][j] = c21[i][j];
                        C[i + newSize][j + newSize] = c22[i][j];
                    }
                }
                return C;
            }
        } else {
            return ikjAlgorithm(A, B);
        }
    }
}
*/
