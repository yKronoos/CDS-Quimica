package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.ifgoiano.cdsquimica.adapter.LevelAdapter;
import br.com.ifgoiano.cdsquimica.adapter.TeamAdapter;
import br.com.ifgoiano.cdsquimica.model.QtdLevel;
import br.com.ifgoiano.cdsquimica.model.Team;

public class HomeStudantActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected List<QtdLevel> level;
    protected LevelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_studant);
        recyclerView = findViewById(R.id.recycleListLevel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        level = QtdLevel.getLevels();

        recyclerView.setAdapter(adapter = new LevelAdapter(this, level, onClickLevel()));
        adapter.notifyDataSetChanged();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("level")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){
                                Team a = document.toObject(Team.class);
                                level.addAll(QtdLevel.getLevels());
                                //animals.add(a);
                            }
                        }
                        recyclerView = findViewById(R.id.recycleListLevel);
                        recyclerView.setLayoutManager(new LinearLayoutManager(HomeStudantActivity.this));
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter = new LevelAdapter(HomeStudantActivity.this, level, onClickLevel()));
                        adapter.notifyDataSetChanged();

                    }

                });

    }

    protected  LevelAdapter.LevelOnClickListener onClickLevel(){
        return new LevelAdapter.LevelOnClickListener() {
            @Override
            public void onClickLevel(LevelAdapter.LevelViewHolder holder, int idx) {
                QtdLevel t = level.get(idx);
                Toast.makeText(HomeStudantActivity.this, ""+t.getQtd(), Toast.LENGTH_SHORT).show();
            }
        };

    }



}