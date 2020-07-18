public class Kiwi extends Fruit {
    final static double vitaminC = 69.0; // mg/100gあたり
    final static int price = 124;       // 1個あたり
    final static double weight = 100;   // 1個あたり[g]
    final static String name = "キウイ";
    public Kiwi(int how_many) {
        super(Kiwi.vitaminC, Kiwi.price, Kiwi.weight,Kiwi.name);
        if(how_many > 0)
            SetHowMany(how_many);
        else {
            System.out.println("果物は一つ以上にしてください");
        }
    }
}
