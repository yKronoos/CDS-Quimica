package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.ifgoiano.cdsquimica.model.Professor;
import br.com.ifgoiano.cdsquimica.model.Team;

public class HomeProfessorActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private EditText name;
    private TextView nameProf;
    private Professor professor;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_professor);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        name = findViewById(R.id.edtNameTeam);
        nameProf = findViewById(R.id.nameProf);
        uid = mAuth.getUid();

        getData(uid);
    }

    public void addTeam(View v){
        Team team = new Team(name.getText().toString(), uid);
        db.collection("class").add(team)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        View view = findViewById(R.id.addTeam);
                        Snackbar.make(view, "Turma cadastrada com sucesso", Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    public void getData(String uid){
        db.collection("professors").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                nameProf.setText(documentSnapshot.get("nome").toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomeProfessorActivity.this, ""+e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void teamsList(View v){
        Intent intent = new Intent(this, ListTeamActivity.class);
        startActivity(intent);
    }

    public void createLevel(View v){
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }
}