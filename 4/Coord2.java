import static java.lang.StrictMath.sqrt;

public class Coord2 {
    // ２次元座標を表現するクラス
    // メンバー変数：
    private double x, y;

    // メンバー関数：
    public double getX() {
        return x;
    }//x を返す

    public double getY() {
        return y;
    }//y を返す

    public void setCoord2(double x, double y) {
        this.x = x;
        this.y = y;
    }//座標値をセット

    public static double distance(Coord2 v1, Coord2 v2) {
        double diff_x = v1.x - v2.x;
        double diff_y = v1.y - v2.y;
        return sqrt(diff_x * diff_x + diff_y * diff_y);
    }//2 点のユークリッド距離
    public Coord2(double x,double y){
        setCoord2(x,y);
    }
}
