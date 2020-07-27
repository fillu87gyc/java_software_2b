public class Color {
    //メンバー変数：
    private double r, g, b;//r(赤), g (緑), b(青), 0.0<=r,g,b <=1.0

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color() {
        r = Math.random();
        g = Math.random();
        b = Math.random();
    }

    //メンバー関数：
    public double getR() {
        return r;
    } //赤色を返す関数

    public double getG() {
        return g;
    } //緑色を返す関数

    public double getB() {
        return b;
    } //青色を返す関数

    public String GetFormattedColor() {
        return String.format("%.2f", r) + String.format("%6.2f", g) + String.format("%6.2f", b) + "     ";
    }
}
