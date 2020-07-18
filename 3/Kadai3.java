import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.lang.Integer.*;
import static java.lang.System.exit;

class Kadai3 {
  static void print_header(String[] args) {
    String arg_concatenate = "";
    for (int i = 0; i < args.length; i++) {
      arg_concatenate += args[i] + " ";
    }
    LocalDateTime date1 = LocalDateTime.now();
    DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒");
    System.out.println("***********************************\n" + "作成者：石山智也：203302\n" + dtformat.format(date1).toString()
        + "\n" + "入力パラメータ：" + arg_concatenate + "\n" + "クラス継承\n" + "*********************************** ");
  }

  public static void main(String args[]) {
    print_header(args);
    if (args.length != 3) {
      System.out.println("引数の数が違います\n");
      exit(1);
    } else {
      for (String s : args) {
        if (Integer.parseInt(s) <= 0) {
          System.out.println("買う量は１以上にしてください\n");
          exit(1);
        }
      }
    }
    ArrayList<Fruit> fruits_list = new ArrayList<Fruit>();
    Fruit kiwi = new Kiwi(parseInt(args[0]));
    Fruit stawberry = new Stawberry(parseInt(args[1]));
    Fruit grapefruit = new Grapefruit(parseInt(args[2]));
    fruits_list.add(kiwi);
    fruits_list.add(stawberry);
    fruits_list.add(grapefruit);

    double total_vitamin = kiwi.Get_vitaminC_summation() + stawberry.Get_vitaminC_summation()
        + grapefruit.Get_vitaminC_summation();
    System.out.println("ビタミン総量 = " + total_vitamin + "mg");

    int total_price = kiwi.Get_price_summation() + stawberry.Get_price_summation() + grapefruit.Get_price_summation();
    System.out.println("値段 = " + total_price + "円\n");
  }
}
