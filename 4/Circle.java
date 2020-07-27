import java.io.PrintStream;
import java.nio.charset.CoderResult;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.abs;

public class Circle extends Shape2D {
    // （Shape2D クラスの派生クラス）
    //  メンバー変数：
    private Coord2 v;
    private double r;

    // メンバー変数：
    public Circle(Coord2 origin, double radius, Color c) {
        v = origin;
        r = radius;
        super.setColor(c);
    }

    public Coord2[] getV() {
        return new Coord2[]{v};
    } //原点を返す

    public double perimeter() {
        return 2 * r * PI;
    }//円周

    public double area() {
        return r * r * PI;
    }//円の面積を返す関数

    public void psPrint(PrintStream cout) {
        cout.println("% %円\n" +
                super.getColor().GetFormattedColor() + "setrgbcolor\n" +
                "newpath\n" +
                String.format("%.2f", v.getX()) + " " + String.format("%6.2f", v.getY()) + " " + String.format("%6.2f", r) + " 0 360 arc\n" +
                "stroke");
    }//PostScript で出力
}
