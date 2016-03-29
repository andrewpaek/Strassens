import java.util.Random;

/**
 * Created by Andrew on 3/25/2016.
 */

/*
public class second {
    static int[][] A;
    static int[][] B;
    static int dim=1;
    public static void main(String[] args){
        A=test_mat_generator(dim);
        B=test_mat_generator(dim);
        matrix C= new matrix(A);
        matrix D = new matrix(B);

    }

    private static int[][] multiply(matrix C, matrix D){
            int n = A.length;
            int d = n / 2;

            // assume add and subtract reference the larger matrices and alters coordinates as necessary

            int[][] M1 = multiply(add(A11, A22, d), add(B11, B22, d));
            int[][] M2 = multiply(add(A21, A22, d), B11);
            int[][] M3 = multiply(A11, subtract(B12, B22, d));
            int[][] M4 = multiply(A22, subtract(B21, B11, d));
            int[][] M5 = multiply(add(A11, A12, d), B22);
            int[][] M6 = multiply(subtract(A21, A11, d), add(B11, B12, d));
            int[][] M7 = multiply(subtract(A12, A22, d), add(B21, B22, d));

            int[][] C11 = subtract(add(M7, add(M1, M4, d), d), M5, d);
            int[][] C12 = add(M3, M5, d);
            int[][] C21 = add(M2, M4, d);
            int[][] C22 = subtract(add(M1, add(M3, M6, d), d), M2, d);

            int[][] R = new int[n][n];

            for (int i = 0; i < d; i++) {
                for (int j = 0; j < d; j++) {
                    R[i][j] = C11[i][j];
                    R[i][j + d] = C12[i][j];
                    R[i + d][j] = C21[i][j];
                    R[i + d][j + d] = C22[i][j];
                }
            }

            return R;

    }

    private static int[][] add ( matrix C, matrix D, int n){
        int[][] C = new int[n][n];

        for (int i = 0; i < C.row_end; i++) {
            for (int j = 0; j <C.col_end; j++) {
                // the second part of this sum represents the D input matrix is expressie of how D is will always be of the same matrix as C
                C[i][j] = A[i][j] + A[i+D.row_start][j+D.row_end];
            }
        }
        // overwrite global variable A in preparation for the next
        A=C;
    };




    public static int[][] test_mat_generator(int n) {
        int[][] T = new int[n][n];
        Random rn = new Random();
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                int temp = rn.nextInt(3);
                T[i][j]=temp;
            }
        }
        return T;
    }
}

*/