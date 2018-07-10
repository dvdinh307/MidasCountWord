package exam.midas.countword.view;

import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import exam.midas.countword.R;
import exam.midas.countword.model.WordObject;

public class WordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<WordObject> mListWords = new ArrayList<>();

    public WordAdapter(ArrayList<WordObject> list) {
        mListWords = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_object_word, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            WordObject word = mListWords.get(holder.getAdapterPosition());
            ((MyViewHolder) holder).bindData(word);
        }
    }

    @Override
    public int getItemCount() {
        return mListWords.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvNumber, mTvName, mTvLine;

        public MyViewHolder(View view) {
            super(view);
            mTvNumber = view.findViewById(R.id.tv_number);
            mTvName = view.findViewById(R.id.tv_name);
            mTvLine = view.findViewById(R.id.tv_line);
        }

        public void bindData(WordObject word) {
            mTvNumber.setText(String.valueOf(word.getNumber()));
            mTvName.setText(word.getName());
            mTvLine.setText(word.getLine());
        }
    }
}
