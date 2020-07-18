public class Fruit {

    private double vitaminC; /* ビタミンＣ含有量：100gあたり */
    private int price; /* 野菜の小売価格: 1kgあたり */
    private int howMany;

    private double vitaminC_per_unit; //一個あたりのビタミンC
    private double weight; //一個あたりの重さ [kg]
    private String name;


    /* コンストラクタ　*/
    public Fruit(double vitaminC, int price, double weight_per_unit,String name) {
        this.vitaminC = vitaminC / 100.0; //1gあたりの重さに直す
        this.price = price;
        this.weight = weight_per_unit; //1こあたりの重さ[g]
        this.name = name;
        this.vitaminC_per_unit = this.vitaminC * weight_per_unit;
    }
    public String GetName(){return name;}

    /* ビタミンCを返す関数 */
    public double GetVitaminC() {
        return vitaminC;
    }

    /* 価格を返す関数 */
    public int GetPrice() {
        return price;
    }

    public int GetHowMany() {
        return howMany;
    }

    public double getVitaminC_per_unit() {
        return vitaminC_per_unit;
    }

    protected void SetHowMany(int unit) {
        this.howMany = unit;
    }

    private double getWeight() {
        return weight;
    }

    public double Get_vitaminC_summation() {
        return GetHowMany() * getVitaminC_per_unit();
    }

    public int Get_price_summation() {
        return GetHowMany() * GetPrice();
    }
}