package exam.midas.countword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import exam.midas.countword.model.WordObject;
import exam.midas.countword.utils.AppUtils;
import exam.midas.countword.view.WordAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRcyContent;
    private ArrayList<WordObject> mListWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcyContent = findViewById(R.id.rcy_content);
        mListWords = new ArrayList<>();
        mRcyContent.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        String valuesTest = AppUtils.getDataFromFile("exam.txt", MainActivity.this);
        if (valuesTest.length() > 0)
            getDataFromString(valuesTest);
    }

    private void getDataFromString(String data) {
        // Get array line.
        String[] splitLine = data.split("[\n|\r]");
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
