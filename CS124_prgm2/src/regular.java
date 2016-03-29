/**
 * Created by Andrew on 3/23/2016.
 */
public class regular {
    public static void main(String[] args) {
        int[][] A = new int[][]{
                {1, 2, 3, 5},
                {3, 4, 1, 1},
                {2, 3, 1, 5},
                {7, 4, 5, 1}
        };
        int[][] B = new int[][]{
                {12, -4, 3, 5},
                {3, 4, 1, 9},
                {-1, 2, 1, 5},
                {3, 7, -5, 1}
        };
        int[][] result = multiply_reg(A, B);
        printMatrix(result);
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

    public static void printMatrix(int [][] M) {
        int n = M.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
