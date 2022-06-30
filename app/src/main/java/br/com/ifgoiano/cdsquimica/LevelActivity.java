package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import br.com.ifgoiano.cdsquimica.model.QtdLevel;
import br.com.ifgoiano.cdsquimica.model.Team;

public class LevelActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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

                    }
                });

            }
        });
        Intent intent = new Intent(this, LevelCreateActivity.class);
        startActivity(intent);
    }
}