package br.com.ifgoiano.cdsquimica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        Team team = new Team(name.getText().toString());
        db.collection("professors").document(uid).collection("teams").add(team)
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
                Professor prof = documentSnapshot.toObject(Professor.class);
                nameProf.setText(prof.getNome());
            }
        });
    }
}