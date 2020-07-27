import java.io.PrintStream;

import static java.lang.StrictMath.sqrt;

public class Triangle extends Shape2D {
    // （Shape2D クラスの派生クラス）
    //  メンバー変数：
    private Coord2 v1, v2, v3;//又は private Coord2 v[3];三頂点座標
    private double distance[];

    // メンバー変数：
    public Triangle(Coord2 v1, Coord2 v2, Coord2 v3, Color c) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        super.setColor(c);
        distance = new double[3];
        distance[0] = Coord2.distance(v1, v2);
        distance[1] = Coord2.distance(v2, v3);
        distance[2] = Coord2.distance(v1, v3);
    }

    public Triangle(Coord2[] v, Color c) {
        v1 = v[0];
        v2 = v[1];
        v3 = v[2];
        super.setColor(c);
        distance = new double[3];
        distance[0] = Coord2.distance(v1, v2);
        distance[1] = Coord2.distance(v2, v3);
        distance[2] = Coord2.distance(v1, v3);
    }

    //public Triangle(Coord2[] v, Color c);
    public Coord2[] getV() {
        return new Coord2[]{v1, v2, v3};
    } //３頂点を返す(v1,v2,v3 の順)

    public double perimeter() {
        double sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += distance[i];
        }
        return sum;
    }//三角形の周囲長を返す関数

    public double area() {
        //ヘロンの公式
        double s = perimeter() / 2.0;
        double a, b, c;
        a = distance[0];
        b = distance[1];
        c = distance[2];
        return sqrt(s * (s - a) * (s - b) * (s - c));
    }//三角形の面積を返す関数

    public void psPrint(PrintStream cout) {
        cout.println("% %三角形\n" +
                super.getColor().GetFormattedColor() + "setrgbcolor\n" +
                "newpath\n" +
                String.format("%.2f", v1.getX()) + " " + String.format("%6.2f", v1.getY()) + " moveto\n" +
                String.format("%.2f", v2.getX()) + " " + String.format("%6.2f", v2.getY()) + " lineto\n" +
                String.format("%.2f", v3.getX()) + " " + String.format("%6.2f", v3.getY()) + " lineto\n" +
                "closepath\n" +
                "stroke");
    }//PostScript で出力
}
