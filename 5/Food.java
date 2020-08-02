/*
食べ物を管理するクラス
203302 石山智也
 */

public class Food {
    private String name;
    private double carbon, protein, calorie, GI,fat;

    public Food(String name, double carbon, double protein, double calorie) {
        this.name = name;
        this.calorie = calorie;
        this.protein = protein;
        this.carbon = carbon;
    }

    public Food() {

    }

    public String getName() {
        return name;
    }

    public double getCarbon() {
        return carbon;
    }

    public double getProtein() {
        return protein;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setGI(double GI) {
        this.GI = GI;
    }
    public void setFat(double fat){
        this.fat = fat;
    }

    public double getGI() {
        return GI;
    }

    public double getFat(){
        return fat;
    }
}
