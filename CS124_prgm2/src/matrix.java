/**
 * Created by Andrew on 3/24/2016.
 */
public class matrix{
    int row_start;
    int row_end;
    int col_start;
    int col_end;
    // prev == 1 if the parent matrix is input 1, 2 if input 2, 0 if neither
    int prev;
    int[][] mat;


    matrix(int[][] C){
        this.row_end=C.length;
        this.col_end=C.length;
    }

    void reset(){
        this.row_start=0;
        this.row_end=mat.length-1;
        this.col_start=0;
        this.col_end=mat.length-1;
        this.prev=0;
    }

    void set11 (matrix C){
        this.row_start = C.row_start;
        this.row_end = (C.row_start + C.row_end+1) / 2 - 1;
        this.col_start = C.col_start;
        this.col_end = (C.col_start + C.col_end+1) / 2 - 1;
        this.prev=C.prev;
        if (C.prev==0){
            this.mat=C.mat;
        }

    }

    public void set12 (matrix C) {
        this.row_start = C.row_start;
        this.row_end = (C.row_start + C.row_end+1) / 2 - 1;
        this.col_start = (C.col_start + C.col_end+1) / 2;
        this.col_end = C.col_end;
        this.prev=C.prev;
        if (C.prev==0){
            this.mat=C.mat;
        }

    }

    void set21 (matrix C){
        this.row_start = (C.row_start + C.row_end+1) / 2;
        this.row_end = C.row_end;
        this.col_start = C.col_start;
        this.col_end = (C.col_start + C.col_end+1) / 2 - 1;
        this.prev=C.prev;
        if (C.prev==0){
            this.mat=C.mat;
        }
    }

    void set22 (matrix C) {
        this.row_start = (C.row_start + C.row_end+1) / 2;
        this.row_end = C.row_end;
        this.col_start = (C.col_start + C.col_end+1) / 2;
        this.col_end = C.col_end;
        this.prev=C.prev;
        if (C.prev==0){
            this.mat=C.mat;
        }
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
}
