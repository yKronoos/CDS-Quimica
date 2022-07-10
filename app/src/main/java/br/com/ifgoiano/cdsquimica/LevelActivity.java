package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.ifgoiano.cdsquimica.adapter.LevelAdapter;
import br.com.ifgoiano.cdsquimica.model.QtdLevel;
import br.com.ifgoiano.cdsquimica.model.Team;

public class LevelActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected List<QtdLevel> level;
    protected LevelAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recycleLevelsAdded);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        level = QtdLevel.getLevels();

        recyclerView.setAdapter(adapter = new LevelAdapter(this, level, onClickLevel()));
        adapter.notifyDataSetChanged();

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
                        recyclerView.setLayoutManager(new LinearLayoutManager(LevelActivity.this));
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter = new LevelAdapter(LevelActivity.this, level, onClickLevel()));
                        adapter.notifyDataSetChanged();

                    }

                });

    }

    public void addLevel(View v){
        db.collection("level").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int levelFase = 0;
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document: task.getResult()){
                        levelFase = task.getResult().size();
                    }
                }
                String nameLevel = "Fase "+levelFase++;
                QtdLevel qtd = new QtdLevel(nameLevel);
                db.collection("level").document(nameLevel).set(qtd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(LevelActivity.this, LevelCreateActivity.class);
                        intent.putExtra("levelFase", nameLevel);
                        startActivity(intent);
                    }
                });

            }
        });

    }

    protected  LevelAdapter.LevelOnClickListener onClickLevel(){
        return new LevelAdapter.LevelOnClickListener() {
            @Override
            public void onClickLevel(LevelAdapter.LevelViewHolder holder, int idx) {
                QtdLevel t = level.get(idx);
                Toast.makeText(LevelActivity.this, ""+t.getQtd(), Toast.LENGTH_SHORT).show();
            }
        };

    }
}