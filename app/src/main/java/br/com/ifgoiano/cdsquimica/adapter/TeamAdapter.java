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
import br.com.ifgoiano.cdsquimica.model.Team;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private final List<Team> teams;
    private final Context context;
    private final TeamOnClickListener onClickListener;

    public interface TeamOnClickListener {
        public void onClickAnimal(TeamViewHolder holder, int idx);
    }

    public TeamAdapter(Context context, List<Team> teams, TeamOnClickListener onClickListener) {
        this.context = context;
        this.teams = teams;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_row_item,parent, false);
        TeamViewHolder holder = new TeamViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team a = teams.get(position);
        holder.teamName.setText(a.getName());

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(v -> onClickListener.onClickAnimal(holder, position));
        }
    }

    @Override
    public int getItemCount() {
        return this.teams != null ? this.teams.size() : 0;
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {

        public TextView teamName;
        public View view;

        public TeamViewHolder(View view) {
            super(view);
            this.view = view;
            teamName = (TextView) view.findViewById(R.id.nameTeam);
        }
    }

}



