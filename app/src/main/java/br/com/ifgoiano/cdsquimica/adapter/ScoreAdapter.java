package br.com.ifgoiano.cdsquimica.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ifgoiano.cdsquimica.R;
import br.com.ifgoiano.cdsquimica.model.Level;
import br.com.ifgoiano.cdsquimica.model.QtdLevel;
import br.com.ifgoiano.cdsquimica.model.Score;
import br.com.ifgoiano.cdsquimica.model.Team;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private final List<Score> score;
    private final Context context;
    private final ScoreOnClickListener onClickListener;

    public interface ScoreOnClickListener {
        public void onClickLevel(ScoreViewHolder holder, int idx);
    }

    public ScoreAdapter(Context context, List<Score> score, ScoreOnClickListener onClickListener) {
        this.context = context;
        this.score = score;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.score_row_item, parent, false);
        ScoreViewHolder holder = new ScoreViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score a = score.get(position);
        holder.levelName.setText(a.getLevel());
        holder.score.setText(a.getScoreNumber());

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(v -> onClickListener.onClickLevel(holder, position));
        }
    }

    @Override
    public int getItemCount() {
        return this.score != null ? this.score.size() : 0;
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {

        public TextView levelName;
        public TextView score;
        public View view;

        public ScoreViewHolder(View view) {
            super(view);
            this.view = view;
            levelName = (TextView) view.findViewById(R.id.viewLevel);
            score = (TextView) view.findViewById(R.id.viewScore);
        }
    }

}



