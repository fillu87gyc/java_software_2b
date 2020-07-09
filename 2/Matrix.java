import java.io.*;

public class Matrix {
    //3<=M,N,K<=10
    public static void main(String args[]) {
        Matrix mat_a = new Matrix("A");
        try {
            File mat_a_data = null, mat_b_data = null;
            if (args.length == 2) {
                mat_a.read(args[0]);
            } else {
                error_occered.stop_program("引数のファイル数が間違っています");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Matrix mat_b = new Matrix("B");
        mat_b.read(args[1]);
        if (mat_a.is_equal_M_N_K(mat_b))
            error_occered.stop_program("行列がどちらも" + mat_a.row + "*" + mat_a.col + "の正則行列です");

        Matrix mat_c = mat_a.multiply(mat_b);
        mat_c.Set_name("C");

        System.out.println(mat_a.Get_name() + ": " + mat_a.col + "*" + mat_a.row);
        mat_a.print();
        System.out.println("------------");

        System.out.println(mat_b.Get_name() + ": " + mat_b.col + "*" + mat_b.row);
        mat_b.print();
        System.out.println("------------");

        System.out.println(mat_c.Get_name() + ": " + mat_c.col + "*" + mat_c.row);
        mat_c.print();
        System.out.println("------------");
    }

    private int row, col;
    private double[][] m;
    private BufferedReader br;
    private String mat_name;

    public Matrix(String matrix_name) {
        this.mat_name = matrix_name;
    }

    public void Set_name(String matrix_name) {
        this.mat_name = matrix_name;
    }

    public String Get_name() {
        return mat_name;
    }

    public boolean is_equal_M_N_K(Matrix mat) {
        int M, N, K;
        M = col;
        N = mat.row;
        K = row; //or B.col

        return (M == N) && (M == K) && (N == K);
    }

    public Matrix multiply(Matrix B) {
        Matrix result = new Matrix("result");
        if (this.row != B.col) {
            error_occered.stop_program("計算不能です(大きさが違います)");
        }
        result.col = this.col;
        result.row = B.row;
        result.m = new double[result.col][result.row];
        int M = row;
        for (int i = 0; i < result.col; i++) {
            for (int j = 0; j < result.row; j++) {
                for (int k = 0; k < M; k++) {
                    result.m[i][j] += m[i][k] * B.m[k][j];
                }
            }
        }
        return result;
    }

    public void print() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                String formatted = String.format("%.6f  ", m[i][j]);
                System.out.print(formatted);
            }
            System.out.println();
        }
    }

    public void read(String file_name) {
        try {
            br = new BufferedReader(new FileReader(new File(file_name)));
        } catch (FileNotFoundException e) {
            error_occered.stop_program(mat_name + "のデータファイルを開けません");
        }
        String line = read_line();
        col = Integer.parseInt(line.split(" ")[0]);
        row = Integer.parseInt(line.split(" ")[1]);
        if (!(row >= 3 && row <= 10 && col >= 3 && col <= 10)) {
            error_occered.stop_program(mat_name + "の行列の大きさが仕様を満たしていません");
        }
        m = new double[col][row];
        for (int i = 0; i < col; i++) {
            line = read_line();
            if (line == null) {
                error_occered.stop_program(mat_name + "の読み込み中に行が足りませんでした");
            }
            if (line.split(" ").length != row) {
                error_occered.stop_program(mat_name + "の読み込み中に列数に過不足がありました");
            }
            for (int j = 0; j < row; j++) {
                m[i][j] = Double.parseDouble(line.split(" ")[j]);
            }
        }
        if (read_line() != null) {
            error_occered.stop_program(mat_name + "の行が多すぎます");
        }
    }

    private String read_line() {
        try {
            return br.readLine();
        } catch (IOException e) {
        }
        return "";
    }
}

