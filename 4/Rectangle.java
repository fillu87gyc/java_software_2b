import java.io.PrintStream;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.sqrt;

public class Rectangle extends Shape2D {
    // （Shape2D クラスの派生クラス）
    //  メンバー変数：
    private Coord2 v1, v2;
    private double diff_x, diff_y;

    // メンバー変数：
    public Rectangle(Coord2 v1, Coord2 v2, Color c) {
        this.v1 = v1;
        this.v2 = v2;
        super.setColor(c);
        diff_x = abs(v1.getX() - v2.getX());
        diff_y = abs(v1.getY() - v2.getY());
    }

    public Rectangle(Coord2[] v, Color c) {
        this.v1 = v[0];
        this.v2 = v[1];
        super.setColor(c);
        diff_x = abs(v1.getX() - v2.getX());
        diff_y = abs(v1.getY() - v2.getY());
    }

    public Coord2[] getV() {
        return new Coord2[]{v1, v2};
    } //2頂点を返す(v1,v2 の順)

    public double perimeter() {
        return diff_y * 2 + diff_x * 2;
    }

    public double area() {
        return diff_y * diff_x;
    }//四角形の面積を返す関数

    public void psPrint(PrintStream cout) {
        cout.println("% %四角形\n" +
                super.getColor().GetFormattedColor() + "setrgbcolor\n" +
                "newpath\n" +
                String.format("%.2f", v1.getX()).toString() + " " + String.format("%6.2f", v1.getY()).toString() + " moveto\n" +
                String.format("%.2f", v2.getX()).toString() + " " + String.format("%6.2f", v1.getY()).toString() + " lineto\n" +
                String.format("%.2f", v2.getX()).toString() + " " + String.format("%6.2f", v2.getY()).toString() + " lineto\n" +
                String.format("%.2f", v1.getX()).toString() + " " + String.format("%6.2f", v2.getY()).toString() + " lineto\n" +
                "closepath\n" +
                "stroke");
    }//PostScript で出力
}