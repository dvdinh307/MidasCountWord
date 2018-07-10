package exam.midas.countword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import exam.midas.countword.model.WordObject;
import exam.midas.countword.view.WordAdapter;


//Viết một chương trình đọc 1 file text, rồi tính số lần xuất hiện của từng từ trong file đó, kèm theo các dòng mà từ đó xuất hiện:
//        Ví dụ: input file "word.in" có nội dung:
//        Python is a cool language but OCaml
//        is even cooler since it is purely functional
//        thì in ra màn hình kết quả:
//        3 is 1 2
//        1 a 1
//        1 but 1
//        1 cool 1
//        1 cooler 2
//        1 even 2
//        1 functional 2
//        1 it 2
//        1 language 1
//        1 ocaml 1
//        1 purely 2
//        1 python 1
//        1 since 2

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRcyContent;
    private String mStringTest = "Python is a cool language but OCaml\n" +
            " is even cooler since it is purely Python functionalPython is a cool language but OCaml\n" +
            " is even cooler since it is purely Python functionalPython is a cool language but OCaml\n" +
            " is even cooler since it is purely Python functional";
    private ArrayList<WordObject> mListWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcyContent = findViewById(R.id.rcy_content);
        mListWords = new ArrayList<>();
        mRcyContent.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        getDataFromString();
    }

    private void getDataFromString() {
        // Get array line.
        String[] splitLine = mStringTest.split("[\n|\r]");
        for (int i = 0; i < splitLine.length; i++) {
            // Get array word.
            String[] datas = splitLine[i].trim().split(" ");
            for (String value : datas) {
                WordObject wordObject = new WordObject();
                // line start with index 1.
                wordObject.setLine(String.valueOf(i + 1));
                wordObject.setName(value);
                addToList(wordObject);
            }
        }
        WordAdapter adapter = new WordAdapter(mListWords);
        mRcyContent.setAdapter(adapter);
    }

    /**
     * Check object before add to list.
     * 1. If exist : count++;
     * 2. if not. Normal add.
     *
     * @param word
     */
    private void addToList(WordObject word) {
        int index = checkExistInList(word);
        if (index > -1) {
            // Contain in list.
            WordObject old = mListWords.get(index);
            old.setNumber(old.getNumber() + 1);
            old.setLine(word.getLine());
            mListWords.set(index, old);
        } else {
            mListWords.add(word);
        }
    }

    /**
     * Check object exist in arraylist.
     *
     * @param word
     * @return
     */
    private int checkExistInList(WordObject word) {
        int result = -1;
        for (int i = 0; i < mListWords.size(); i++) {
            if (mListWords.get(i).getName().equals(word.getName()))
                result = i;
        }
        return result;
    }
}
