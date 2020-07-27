import java.io.PrintStream;

public abstract class Shape2D {
    private Color color; //Color クラスは後述

    public void setColor(Color c){
        this.color = c;
    }//Color 変数に値をセットする関数

    public Color getColor(){
        return color;
    }//戻り値として Color 変数を返す関数

    abstract double area();//面積を計算する抽象関数

    abstract double perimeter();//周囲長を計算する抽象関数

    abstract void psPrint(PrintStream cout);//プリント関数
}