/**
 * Created by Andrew on 3/21/2016.
 */
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class initial {
    static int dim=1;
    static int holder = 0;
    public static void main(String[] args) {
        /* if (args.length != 3) {
            System.out.println("usage: ./strassen flag dimension inputfile");
            System.exit(1);
        }

        int flag = Integer.parseInt(args[0]);
        int dimension = Integer.parseInt(args[1]);
        File infile = new File(args[2]);
        
        try (Scanner s = new Scanner(infile);) {
            int[][] inputA = new int[dimension][dimension];
            int[][] inputB = new int[dimension][dimension];

            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    inputA[i][j] = s.nextInt();
                }
            }

            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    inputB[i][j] = s.nextInt();
                }
            }

            int[][] output = multiply(inputA, inputB);

            for (int i = 0; i < dimension; i++) {
                System.out.println(output[i][i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        } */

        // int[][] input1 = new int[][]{
        //         {1, 2, 3, 5},
        //         {3, 4, 1, 1},
        //         {2, 3, 1, 5},
        //         {7, 4, 5, 1}
        // };
        // int[][] input2 = new int[][]{
        //         {12, -4, 3, 5},
        //         {3, 4, 1, 9},
        //         {-1, 2, 1, 5},
        //         {3, 7, -5, 1}
        // };

        // int[][] D=test_mat_generator(2);
        // printMatrix(D);

        // int[][] E=test_mat_generator(2);
        // printMatrix(E);
        // int[][] C = multiply(D, E);
        // printMatrix(C);

        // long strassens;
        // long regular;
        // do{
        //     int[][] D = test_mat_generator(dim);
        //     // printMatrix(D);
        //     int[][] newD = fix(D);

        //     int[][] E = test_mat_generator(dim);
        //     // printMatrix(E);
        //     int[][] newE = fix(E);
        //     strassens = run_time(newD, newE);
        //     regular = run_time_regular(newD, newE);
        //     dim *= 2;
        //     holder=0;
        //     System.out.println("Regular:"+regular+", Strassens:"+ strassens+", Dimension:"+dim);
        // }
        // while (regular<strassens);

        // System.out.println("Our best time is:"+strassens);
        // System.out.println("Our best n is:" + (dim));



    }

    public static long run_time(int[][] A, int[][] B){
        long starttime = System.nanoTime();

        int[][] C = multiply(A, B);
        // extractMatrix(C, D.length);

        long endtime = System.nanoTime();
        long totaltime = endtime - starttime;
        return totaltime;
    }

    public static long run_time_regular(int[][] A, int[][] B){
        long starttime = System.nanoTime();

        int[][] C = multiply_reg(A, B);
        // extractMatrix(C, D.length);

        long endtime = System.nanoTime();
        long totaltime = endtime - starttime;
        return totaltime;
    }

    private static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;

        if (holder<1) {
            holder=holder+1;
            if (n == 1) {
                int[][] C = new int[1][1];
                C[0][0] = A[0][0] * B[0][0];
                return C;
            } else {
                int[][] A11 = populate(0, n / 2, 0, n / 2, A);
                int[][] A21 = populate(n / 2, n, 0, n / 2, A);
                int[][] A12 = populate(0, n / 2, n / 2, n, A);
                int[][] A22 = populate(n / 2, n, n / 2, n, A);
                int[][] B11 = populate(0, n / 2, 0, n / 2, B);
                int[][] B21 = populate(n / 2, n, 0, n / 2, B);
                int[][] B12 = populate(0, n / 2, n / 2, n, B);
                int[][] B22 = populate(n / 2, n, n / 2, n, B);    

                int[][] P1 = multiply(A11, subtract(B12, B22));
                int[][] P2 = multiply(add(A11, A12), B22);
                int[][] P3 = multiply(add(A21, A22), B11);
                int[][] P4 = multiply(A22, subtract(B21, B11));
                int[][] P5 = multiply(add(A11, A22), add(B11, B22));
                int[][] P6 = multiply(subtract(A12, A22), add(B21, B22));
                int[][] P7 = multiply(subtract(A11, A21), add(B11, B12));
                int[][] C11 = add(P6, add(P5, subtract(P4, P2)));
                int[][] C12 = add(P1, P2);
                int[][] C21 = add(P3, P4);
                int[][] C22 = add(P5, subtract(subtract(P1, P3), P7));
                int[][] C = concat(C11, C12, C21, C22);
                return C;
            }
        }
        else{
            return multiply_reg(A, B);
        }
    }

    private static int[][] add ( int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    };

    private static int[][] subtract ( int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static int[][] populate ( int rstart, int rend, int cstart, int cend, int[][] M){
        int n = rend - rstart;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = M[i + rstart][j + cstart];
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

    private static int[][] concat (int[][] C11, int[][] C12, int[][] C21, int[][] C22){
        int n = C11.length;

        int[][] C = new int[2*n][2*n];
        // System.arraycopy(C11, 0, X, 0, C11.length);
        // System.arraycopy(C12, 0, X, C11.length, C12.length);
        // System.arraycopy(C21, 0, Y, 0, C21.length);
        // System.arraycopy(C22, 0, Y, C21.length, C22.length);
        // System.arraycopy(X, 0, F, 0, X.length);
        // System.arraycopy(Y, 0, F, X.length, Y.length);
        // return F;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = C11[i][j];
            }

        }
        for (int i = n; i < 2 * n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = C21[i - n][j];

            }

        }

        for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n; j++) {
                C[i][j] = C12[i][j - n];
            }
        }

        for (int i = n; i < 2 * n; i++) {
            for (int j = n; j < 2 * n; j++) {
                C[i][j] = C22[i - n][j - n];

            }

        }
        return C; 
    }

    public static void printMatrix(int [][] M) {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
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

    public static int [][] fix(int [][] M) {
        int n = M.length;
        // checks if n is a power of 2
        // found on stackoverflow
        if ((n & (n -1)) == 0) {
            return M;
        }

        int k = pow2larger(n);
        int[][] N = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (i >= n || j >= n) {
                    N[i][j] = 0;
                }
                else {
                    N[i][j] = M[i][j];
                }
            }
        }

        return N;
    }

    public static void extractMatrix(int[][] M, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(M[i][j] + " ");
            }
        }
    }

    // algorithm for smallest power of 2 larger than n
    // taken from "Hacker's Delight" by Henry S. Warren, Jr.
    private static int pow2larger(int n) {
        --n;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16; 
        return n + 1;
    }
}
