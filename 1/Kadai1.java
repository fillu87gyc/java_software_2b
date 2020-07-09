/*********************************************************
作成者：石山智也203302
日付：2020年7月4日 午後7時59分
入力ファイル名：jnews2.txt 
ファイル内の2文字以上のカタカナ語の抽出と頻度のプリント
********************************************************/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.System.exit;

public class Kadai1 {
    public static void main(String[] args) {
        //引数が正しいか確認
        if (args.length != 1) error_occered.stop_program("コマンドライン引数が違います");

        Count_text_file text_file = new Count_text_file(args[0]);
        String text = text_file.Get_text();
        HashList_counter count_list = new HashList_counter();
        while (text != null) {
            Katakana_counter katakana_counter = new Katakana_counter(count_list);
            katakana_counter.Count_katakana(text);
            //ハッシュリストを保持して引き継ぐ
            count_list = katakana_counter.Get_count_list();
            //次の文章を読み始め
            text = text_file.Get_text();
        }
        count_list.Show_count_list();
    }
}

//ハッシュリストによって出現回数を保持する
class HashList_counter {
    String[] word_list;
    int[] count_list;
    static int HASH_SIZE = 50;

    HashList_counter() {
        word_list = new String[HASH_SIZE];
        count_list = new int[HASH_SIZE];
        for (int i = 0; i < HASH_SIZE; i++) {
            count_list[i] = 0;
            word_list[i] = "EMPTY";
        }
    }

    public void Insert_word(String keyword) {
        int hash_result = Math.abs(keyword.hashCode() % HASH_SIZE);
        if (word_list[hash_result].equals(keyword)) {
            //すでに登録済みなのでカウントアップして終わり
            count_list[hash_result]++;
            return;
        }
        //ハッシュ衝突
        int loop_count = 0;
        while (!(word_list[hash_result] == "EMPTY" || word_list[hash_result].equals(keyword))) {
            hash_result++;
            loop_count++;
            if (hash_result >= HASH_SIZE)
                hash_result = 0;
            if (loop_count > HASH_SIZE)
                error_occered.stop_program("単語を挿入しようとしたがリストがいっぱいです");
        }
        //実際にカウント
        count_list[hash_result]++;
        word_list[hash_result] = keyword;
    }

    public void Show_count_list() {
        for (int i = 0; i < HASH_SIZE; i++) {
            if (word_list[i] != "EMPTY") {
                System.out.println(word_list[i] + ":  " + count_list[i]);
            }
        }
    }
}

//ファイルから読み取る
class Count_text_file {
    File file;
    BufferedReader br;

    public Count_text_file(String file_name) {
        open_text_file(file_name);
    }
    public String Get_text() {
        try {
            return br.readLine();
        } catch (Exception e) {
            error_occered.stop_program("ファイルが見つかりませんでした");
        }
        return "";
    }
    private void open_text_file(String file_name) {
        file = new File(file_name);
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            error_occered.stop_program("ファイルが見つかりませんでした");
        }
    }
}

//実際にカウントを行う
class Katakana_counter {
    Katakana_counter(HashList_counter ls) {
        katakana = ls;
    }
    HashList_counter katakana;
    public HashList_counter Get_count_list() {
        return katakana;
    }
    public void Count_katakana(String original_string) {
        String st = original_string;
        String word_temp = "";
        for (int i = 0; i < st.length(); i++) {
            char x = st.charAt(i);
            if (is_katakana(x)) {
                //1文字目
                i++;
                word_temp = String.valueOf(x);
                x = st.charAt(i);
                while (is_katakana(x) || x == 'ー') {
                    word_temp = word_temp + x;
                    i++;
                    if (st.length() <= i) break;
                    x = st.charAt(i);
                }
                //カタカナwordの終わり
                katakana.Insert_word(word_temp);
            }
        }
    }
    boolean is_katakana(char c) {
        return (c >= 'ァ' && c <= 'ヶ');
    }
}

//エラー時に使う処理
class error_occered{
    static void stop_program(String e){
        //プログラム終了
        System.out.println(e);
        exit(1);
    }
}

