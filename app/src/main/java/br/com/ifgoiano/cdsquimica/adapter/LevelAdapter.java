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
import br.com.ifgoiano.cdsquimica.model.Team;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelViewHolder> {
    private final List<QtdLevel> level;
    private final Context context;
    private final LevelOnClickListener onClickListener;

    public interface LevelOnClickListener {
        public void onClickLevel(LevelViewHolder holder, int idx);
    }

    public LevelAdapter(Context context, List<QtdLevel> level, LevelOnClickListener onClickListener) {
        this.context = context;
        this.level = level;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.level_row_item, parent, false);
        LevelViewHolder holder = new LevelViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        QtdLevel a = level.get(position);
        holder.levelName.setText(a.getQtd());

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(v -> onClickListener.onClickLevel(holder, position));
        }
    }

    @Override
    public int getItemCount() {
        return this.level != null ? this.level.size() : 0;
    }

    public static class LevelViewHolder extends RecyclerView.ViewHolder {

        public TextView levelName;
        public View view;

        public LevelViewHolder(View view) {
            super(view);
            this.view = view;
            levelName = (TextView) view.findViewById(R.id.nameLevel);
        }
    }

}



