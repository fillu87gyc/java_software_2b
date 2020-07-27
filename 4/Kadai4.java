import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.exit;

public class Kadai4 {
    static void print_header(String[] args,PrintStream cout) {
        String arg_concatenate = "";
        for (int i = 0; i < args.length; i++) {
            arg_concatenate += args[i] + " ";
        }
        LocalDateTime date1 = LocalDateTime.now();
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒");
        cout.println("%***********************************\n" + "%作成者：石山智也：203302\n%" + dtformat.format(date1).toString()
                + "\n" + "%入力パラメータ：" + arg_concatenate + "\n" + "%抽象クラスによる継承\n" + "%*********************************** ");
    }

    static void print_footer(int n, PrintStream cout, double area, double length) {
        cout.println("% 総面積 = " + area +
                "\n% 総長 = " + length +
                "\n% 図形総数 =" + n +
                "\nshowpage");
    }

    public static void main(String argv[]) {
        if (argv.length != 2) {
            System.out.println("引数はふたつです");
            exit(1);
        }
        int n = Integer.parseInt(argv[1]);
        if (n > 50 || n < 10) {
            System.out.println("図形総数が不正です");
            exit(1);
        }
        PrintStream print_out = null;
        try {
            print_out = new PrintStream(new FileOutputStream(argv[0]), true);
        } catch (Exception e) {
            System.out.println(e);
            exit(1);
        }

        print_header(argv,print_out);
        ArrayList<Shape2D> list = new ArrayList<Shape2D>();
        for (int i = 0; i < n; i++) {
            int selectType = (int) (Math.random() * 3);
            Coord2[] coord;
            switch (selectType) {
                case 0:
                    //Triangle
                    coord = new Coord2[3];
                    for (int j = 0; j < 3; j++) {
                        coord[j] = new Coord2(Math.random() * 600, Math.random() * 800);
                    }
                    list.add(new Triangle(coord, new Color()));
                    break;
                case 1:
                    //Rectangle
                    coord = new Coord2[2];
                    coord[0] = new Coord2(Math.random() * 600, Math.random() * 800);
                    coord[1] = new Coord2(Math.random() * 600, Math.random() * 800);
                    list.add(new Rectangle(coord, new Color()));
                    break;
                default:
                    //Circle
                    Coord2 c = new Coord2(Math.random() * 600, Math.random() * 800);
                    double[] distance = new double[3];
                    distance[0] = Math.min(c.getX(), c.getY());
                    distance[1] = Math.min(600 - c.getX(), 800 - c.getY());
                    double radius = Math.random() * Math.min(distance[0], distance[1]);
                    list.add(new Circle(c, radius, new Color()));
                    break;
            }
        }
        double sum_area = 0;
        double sum_length = 0;
        for (Shape2D s : list) {
            s.psPrint(print_out);
            sum_area += s.area();
            sum_length += s.perimeter();
        }
        print_footer(n, print_out, sum_area, sum_length);
    }
}
