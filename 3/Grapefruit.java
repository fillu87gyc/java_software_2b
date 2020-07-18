public class Grapefruit extends Fruit {
    final static double vitaminC = 36.0; // mg/100gあたり
    final static int price = 127;       // 1個あたり
    final static double weight = 300;   // 1個あたり[kg]
    final static String name = "グレープフルーツ";

    public Grapefruit(int how_many) {
        super(Grapefruit.vitaminC, Grapefruit.price, Grapefruit.weight,Grapefruit.name);
        if (how_many > 0)
            SetHowMany(how_many);
        else {
            System.out.println("果物は一つ以上にしてください");
        }
    }
}
