/**
 * Created by Andrew on 3/25/2016.
 */
import java.util.Random;

public class first {
    static boolean marker = true;
    static int[][] A;
    static int[][] B;
    static int dim = 1;
    public static void main(String[] args){
        while(true) {

            A = test_mat_generator(dim);
            B = test_mat_generator(dim);

            long starttime;
            long endtime;
            int[][] R;



            starttime = System.nanoTime();
            R = multiply(A,B);
            endtime = System.nanoTime();
            long strassens_totaltime = endtime-starttime;

            starttime = System.nanoTime();
            R = multiply_reg(A,B);
            endtime = System.nanoTime();
            long regular_totaltime = endtime-starttime;

            System.out.println("Strassens: "+strassens_totaltime+" Regular: "+regular_totaltime+" Dimension: "+dim);
            if (regular_totaltime > strassens_totaltime){

                break;
            }
            dim+=20;
            marker=true;

        }

    }

    private static int[][] multiply(int[][] A, int[][] B){
        if (marker){
            marker=false;
            int n = A.length;
            if (n == 1) {
                int[][] C = new int[1][1];
                C[0][0] = A[0][0] * B[0][0];
                return C;
            } else {
                int d = n / 2;
                int[][] A11 = new int[d][d];
                int[][] A12 = new int[d][d];
                int[][] A21 = new int[d][d];
                int[][] A22 = new int[d][d];

                int[][] B11 = new int[d][d];
                int[][] B12 = new int[d][d];
                int[][] B21 = new int[d][d];
                int[][] B22 = new int[d][d];

                for (int i = 0; i < d; i++) {
                    for (int j = 0; j < d; j++) {
                        A11[i][j] = A[i][j];
                        A12[i][j] = A[i][d + j];
                        A21[i][j] = A[i + d][j];
                        A22[i][j] = A[i + d][j + d];
                        B11[i][j] = B[i][j];
                        B12[i][j] = B[i][d + j];
                        B21[i][j] = B[i + d][j];
                        B22[i][j] = B[i + d][j + d];
                    }
                }



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

        }
        else{
            return multiply_reg(A, B);
        }
    }



    private static int[][] add ( int[][] A, int[][] B, int n){
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    };

    private static int[][] subtract ( int[][] A, int[][] B, int n){
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static int[][] multiply_reg(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                int temp = 0;
                for (int k=0; k<n; k++){
                    temp+=A[i][k]*B[k][j];
                }
                C[i][j]=temp;
            }
        }
        return C;
    }

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

    public static void printMatrix(int [][] M) {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(M[i][j]);
            }
        }
    }

}
