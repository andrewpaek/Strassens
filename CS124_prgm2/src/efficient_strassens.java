/**
 * Created by Andrew on 3/23/2016.
 */
/*
import java.util.Random; 

public class efficient_strassens {
    static int[][] input1;
    static int[][] input2;
    static int holder = 0;

    public static void main(String[] args){
        
        //matrix C = rec(A, B);
        //printMatrix(C.mat);
 
        int dim = 1;
        while (true) {
            matrix A = new matrix();
            matrix B = new matrix();
            int length = dim;
            A.row_start=0;
            A.row_end=length-1;
            A.col_start=0;
            A.col_end=length-1;
            A.prev=1;
            B.row_start=0;
            B.row_end=length-1;
            B.col_start=0;
            B.col_end=length-1;
            B.prev=2;
            input1 = test_mat_generator(dim);
            input2 = test_mat_generator(dim);
            long t1 = run_time(A,B);
            long t2 = run_time_reg(input1, input2);
            long t = t2 - t1;
            System.out.println(t);
            if (t > 0) {
                System.out.println("n = " + dim);
                break;
            }
            dim *= 2;
        }

    }

    private static long run_time(matrix C, matrix D) {
        long starttime = System.nanoTime();
        matrix E = rec(C,D);
        long endtime=System.nanoTime();
        long totaltime = endtime-starttime;
        return totaltime;
    }

    private static long run_time_reg(int[][] C, int[][] D){
        long starttime = System.nanoTime();
        int[][] E = multiply_reg(C, D);
        long endtime = System.nanoTime();
        long totaltime = endtime-starttime;
        return totaltime;
    }

    private static matrix rec(matrix C, matrix D) {

        if ((C.row_end - C.row_start)==0) {
            int temp1;
            int temp2;
            if (C.prev==1) {
                temp1 = input1[C.row_start][C.col_start];
            }
            else if (C.prev==2){
                temp1 = input2[C.row_start][C.col_start];
            }
            else {
                temp1 = C.mat[C.row_start][C.col_start];
            }

            if (D.prev==1) {
                temp2 = input1[D.row_start][D.col_start];
            }
            else if (D.prev==2){
                temp2 = input2[D.row_start][D.col_start];
            }
            else {
                temp2 = D.mat[D.row_start][D.col_start];
            }

            int[][] R = new int[1][1];
            R[0][0] = temp1 * temp2;
            matrix T = new matrix();
            T.mat = R;
            return T;

        } else {

            matrix C11 = new matrix();
            matrix C12 = new matrix();
            matrix C21 = new matrix();
            matrix C22 = new matrix();
            matrix D11 = new matrix();
            matrix D12 = new matrix();
            matrix D21 = new matrix();
            matrix D22 = new matrix();

            C11.set11(C);
            C12.set12(C);
            C21.set21(C);
            C22.set22(C);

            D11.set11(D);
            D12.set12(D);
            D21.set21(D);
            D22.set22(D);

            matrix S1 = subtract_mat(D12, D22, D);
            matrix S2 = add_mat(C11, C12, C);
            matrix S3 = add_mat(C21, C22, C);
            matrix S4 = subtract_mat(D21, D11, D);
            matrix S5 = add_mat(C11, C22, C);
            matrix S6 = add_mat(D11, D22, D);
            matrix S7 = subtract_mat(C12, C22, C);
            matrix S8 = add_mat(D21, D22, D);
            matrix S9 = subtract_mat(C11, C21, C);
            matrix S10 = add_mat(D11, D12, D);

            matrix P1 = rec(C11, S1);
            matrix P2 = rec(S2, D22);
            matrix P3 = rec(S3, D11);
            matrix P4 = rec(C22, S4);
            matrix P5 = rec(S5, S6);
            matrix P6 = rec(S7, S8);
            matrix P7 = rec(S9, S10);

            matrix E11 = add_two(P6, add_two(P5, subtract_two(P4, P2)));
            matrix E12 = add_two(P1, P2);
            matrix E21 = add_two(P3, P4);
            matrix E22 = add_two(P5, subtract_two(subtract_two(P1, P3), P7));
            matrix E = concat_mat(E11, E12, E21, E22);
            return E;
        }
    }

    private static matrix add_two( matrix C, matrix D){
        matrix R = new matrix();
        R.mat = add(C.mat, D.mat);
        return R;
    }

    private static matrix subtract_two ( matrix C, matrix D){
        matrix R = new matrix();
        R.mat=subtract(C.mat, D.mat);
        return R;
    }

    private static matrix add_mat ( matrix C, matrix D, matrix P ){
        int[][] tempA;
        int[][] tempB;
        if (P.prev==0) {
            tempA =populate(C.row_start, C.row_end, C.col_start, C.col_end, C.mat);
            tempB = populate(D.row_start, D.row_end, D.col_start, D.col_end, D.mat);
        }
        else if (P.prev==1) {
            tempA = populate(C.row_start, C.row_end, C.col_start, C.col_end, input1);
            tempB = populate(D.row_start, D.row_end, D.col_start, D.col_end, input1);
        }
        else{
            tempA = populate(C.row_start, C.row_end, C.col_start, C.col_end, input2);
            tempB=populate(D.row_start, D.row_end, D.col_start, D.col_end, input2);
        }
        int[][] temp = add(tempA, tempB);
        matrix R = new matrix();
        R.mat = temp;
        R.reset();
        return R;
    }

    private static matrix subtract_mat ( matrix C, matrix D, matrix P){
        int[][] tempA;
        int[][] tempB;
        if (P.prev==0) {
            tempA =populate(C.row_start, C.row_end, C.col_start, C.col_end, C.mat);
            tempB = populate(D.row_start, D.row_end, D.col_start, D.col_end, D.mat);
        }
        else if (P.prev==1) {
            tempA = populate(C.row_start, C.row_end, C.col_start, C.col_end, input1);
            tempB = populate(D.row_start, D.row_end, D.col_start, D.col_end, input1);
        }
        else{
            tempA = populate(C.row_start, C.row_end, C.col_start, C.col_end, input2);
            tempB=populate(D.row_start, D.row_end, D.col_start, D.col_end, input2);
        }
        int[][] temp = subtract(tempA, tempB);
        matrix R = new matrix();
        R.mat = temp;
        R.reset();
        return R;
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
        int[][] C = new int[n+1][n+1];
        for (int i = 0; i <n+1; i++) {
            for (int j = 0; j <n+1; j++) {
                C[i][j] = M[i + rstart][j + cstart];
            }
        }
        return C;
    }

    private static matrix concat_mat(matrix C11, matrix C12, matrix C21, matrix C22){
        int[][] temp = concat(C11.mat, C12.mat, C21.mat, C22.mat);
        matrix R = new matrix();
        R.mat = temp;
        return R;
    }

    private static int[][] concat (int[][] C11, int[][] C12, int[][] C21, int[][] C22){
        int n = C11.length;
        int[][] C = new int[2 * n][2 * n];
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

}
*/