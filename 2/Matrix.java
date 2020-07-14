/*
********************************************************
作成者：石山智也203302
日付：2020年7月10日 午前10時29分
入力ファイル名： A.txt B.txt
行列A,Bの積を計算するプログラム
******************************************************** 
 */
import java.io.*;

import static java.lang.System.exit;

public class Matrix {
    public static void main(String args[]) {
        if (args.length != 2) {
          error_occered.stop_program("引数のファイル数が間違っています");
        }
        Matrix mat_a = new Matrix("A");
        Matrix mat_b = new Matrix("B");
        mat_a.read(args[0]);
        mat_b.read(args[1]);
        if (mat_a.is_equal_M_N_K(mat_b))
            error_occered.stop_program("行列がどちらも" + mat_a.row + "*" + mat_a.col + "の正則行列です");

        Matrix mat_c = mat_a.multiply(mat_b, "C");

        System.out.println("********************************************************\n" +
                "作成者：石山智也203302\n" +
                "日付：2020年7月10日 午前10時29分\n" +
                "入力ファイル名： A.txt B.txt\n" +
                "行列A,Bの積を計算するプログラム\n" +
                "******************************************************** \n");
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

    public String Get_name() {
        return mat_name;
    }

    //正則行列かどうか大きさを確認する 大きさはA=M*K B=K*N
    public boolean is_equal_M_N_K(Matrix mat) {
        int M, N, K;
        M = col;
        N = mat.row;
        K = row; //similar B.col

        return (M == N) && (M == K) && (N == K);
    }

    public Matrix multiply(Matrix B, String name) {
        Matrix result = new Matrix(name);
        if (this.row != B.col) {
            error_occered.stop_program("計算不能です(大きさが違います)");
        }
        result.col = this.col; //Aの行
        result.row = B.row;
        result.m = new double[result.col][result.row];
        int K = row; //行列積のそれぞれの要素は積和形でいくつの項からなるか
        for (int i = 0; i < result.col; i++) {
            for (int j = 0; j < result.row; j++) {
                for (int k = 0; k < K; k++) {
                    result.m[i][j] += m[i][k] * B.m[k][j];
                }
            }
        }
        return result;
    }

    public Matrix multiply(Matrix B) {
        //計算結果の行列に名前がいらない場合はデフォルトの名前をつける
        return multiply(B, "no-named");
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

class error_occered {
    static void stop_program(String msg) {
        //プログラム終了
        System.out.println(msg);
        exit(1);
    }

    static void stop_program(Exception e) {
        //プログラム終了
        System.out.println(e.getStackTrace());
        exit(1);
    }
}
