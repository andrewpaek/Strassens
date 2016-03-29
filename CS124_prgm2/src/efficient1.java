/**
 * Created by Andrew on 3/25/2016.
 */
import java.util.Arrays;
public class efficient1 {
    public static void main(String[] args){
         int[][] input1 = new int[][]{
                 {1, 2, 3, 5},
                 {3, 4, 1, 1},
                 {2, 3, 1, 5},
                 {7, 4, 5, 1}
         };
        concatenate(input1, input1, input1, input1);
    }
    public static void flatten(int[][] A) {
        int[] R = new int[A.length*A.length];
        System.out.println("array length"+A.length);
        for (int i=0; i<A.length; i++) {
            System.arraycopy(A[i], 0, R, i*A.length, A.length);
        }
    }

    public static void concatenate(int[][] A, int[][] B, int[][] C, int[][] D){
        int[][]R = new int[2*A.length][];
        int [] temp = new int[2*A.length];
        for (int i=0; i<A.length; i++){
            System.arraycopy(A[i], 0, temp, 0, A.length);
            System.arraycopy(B[i], 0, temp, A.length, A.length);
            System.arraycopy(temp, 0, R, 0, temp.length);
            temp=new int[2*A.length];
            System.arraycopy(C[i], 0, temp, 2*A.length, A.length);
            System.arraycopy(D[i], 0, temp, 3*A.length, A.length);
            System.arraycopy(temp, 0, R, 2*A.length, temp.length);
            temp=new int[2*A.length];
        }
        printarray(temp);
    }

    public static void printarray(int[] A){
        for (int i=0; i<A.length; i++){
            System.out.println(A[i]);
        }
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
    /*
    private static int[][] concat (int[][] C11, int[][] C12, int[][] C21, int[][] C22){
        int n = C11.length;
        int[][] C = new int[2 * n][2 * n];

    } */
}
