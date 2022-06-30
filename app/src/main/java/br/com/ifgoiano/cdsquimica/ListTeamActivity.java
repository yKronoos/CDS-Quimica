package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.ifgoiano.cdsquimica.adapter.TeamAdapter;
import br.com.ifgoiano.cdsquimica.model.Team;

public class ListTeamActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected List<Team> teams;
    protected TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_team);

        recyclerView = findViewById(R.id.recycleTeam);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        teams = Team.getTeams();

        recyclerView.setAdapter(adapter = new TeamAdapter(this, teams, onCliclTeams()));

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("class").whereEqualTo("uidProf", mAuth.getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){
                                Team a = document.toObject(Team.class);
                                teams.addAll(Team.getTeams());
                                //animals.add(a);
                            }
                        }
                        recyclerView = findViewById(R.id.recycleTeam);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ListTeamActivity.this));
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter = new TeamAdapter(ListTeamActivity.this, teams, onCliclTeams()));
                        adapter.notifyDataSetChanged();

                    }

                });

    }

    protected  TeamAdapter.TeamOnClickListener onCliclTeams(){
        return new TeamAdapter.TeamOnClickListener() {
            @Override
            public void onClickAnimal(TeamAdapter.TeamViewHolder holder, int idx) {
                Team t = teams.get(idx);
                Toast.makeText(ListTeamActivity.this, ""+t.getName(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    //protected TeamAdapter.Te
}