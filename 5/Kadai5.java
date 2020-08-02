import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.System.exit;

/* 
203302 石山智也
エントリーポイントが存在するクラス
*/

public class Kadai5 {
    static void print_header(String[] args) {
        String arg_concatenate = "";
        for (int i = 0; i < args.length; i++) {
            arg_concatenate += args[i] + " ";
        }
        LocalDateTime date1 = LocalDateTime.now();
        DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒");
        System.out.println("***********************************\n" + "作成者：石山智也：203302\n" + dtformat.format(date1).toString()
                + "\n" + "入力パラメータ：" + arg_concatenate + "\n" + "カロリーを単回帰によって予測するプログラム \n" + "*********************************** ");
    }

    public static void main(String args[]) {
        print_header(args);
        if (args.length != 2) {
            System.out.println("引数の数が違います\n");
            exit(1);
        }
        else if (!(args[1].equals("C") || args[1].equals("P")|| args[1].equals("G")|| args[1].equals("F"))) {
            System.out.println("引数の2つ目はC,P,G,Fを入れてください\n");
            exit(1);
        }
        File file = new File(args[0]);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("csvファイルがありません\n");
            exit(1);
        }
        String colm = null;
        try {
            colm = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ファイルが空です\n");
            exit(1);
        }
        ArrayList<Food> foods = new ArrayList<Food>();
        //0食品,1GI,2炭水化物,3カロリー,4脂質,5タンパク質
        final int carbon = 2, protein = 5, calorie = 3, GI = 1,fat = 4;
        while (true) {
            try {
                colm = br.readLine();
            } catch (IOException e) {

            }
            if (colm == null) break;
            String[] data = colm.split(",");
            Food f = new Food(data[0], Double.valueOf(data[carbon]), Double.valueOf(data[protein]), Double.valueOf(data[calorie]));
            f.setGI(Double.valueOf(data[GI]));
            f.setFat(Double.valueOf(data[fat]));
            foods.add(f);
        }
        double[][] nutrition = new double[6][foods.size()];

        for (int i = 0; i < foods.size(); i++) {
            nutrition[carbon][i] = foods.get(i).getCarbon();
            nutrition[protein][i] =foods.get(i).getProtein();
            nutrition[calorie][i] =foods.get(i).getCalorie();
            nutrition[GI][i] =foods.get(i).getGI();
            nutrition[fat][i] = foods.get(i).getFat();
        }
        int explanatoryVariable = -1;
        if (args[1].equals("C")) explanatoryVariable = carbon;
        else if (args[1].equals("P")) explanatoryVariable = protein;
        else if (args[1].equals("G")) explanatoryVariable = GI;
        else explanatoryVariable = fat;
        FoodRegression regression = new FoodRegression(nutrition[explanatoryVariable], nutrition[calorie]);
        regression.doRegression();
        System.out.println("a(回帰係数) = " + regression.getA());
        System.out.println("b(回帰切片) = " + regression.getB());
        System.out.println("R2(寄与率) = " + regression.computeR2());
        double[] unknownDataSet =
                (explanatoryVariable == carbon) ? new double[]{19.6, 2.0, 4.9} :
                        (explanatoryVariable == GI) ? new double[]{28, 42, 28} :
                        (explanatoryVariable == protein)? new double[]{26.5, 4.0, 3.0}:new double[]{49.4,3,0.4};
        System.out.println("「落花生」のカロリー予想　= " + regression.calcPredictedValue(unknownDataSet[0]));
        System.out.println("「絹豆腐」のカロリー予想　= " + regression.calcPredictedValue(unknownDataSet[1]));
        System.out.println("「しいたけ」のカロリー予想　= " + regression.calcPredictedValue(unknownDataSet[2])+"\n");
    }
}
