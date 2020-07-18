public class Stawberry extends Fruit {
    final static double vitaminC = 62.0; // mg/100gあたり
    final static int price = 59;       // 1個あたり
    final static double weight = 12.5;   // 1個あたり[g]
    final static String name = "いちご";

    public Stawberry(int how_many) {
        super(Stawberry.vitaminC, Stawberry.price, Stawberry.weight,Stawberry.name);
        if (how_many > 0)
            SetHowMany(how_many);
        else {
            System.out.println("果物は一つ以上にしてください");
        }
    }
}
