package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import br.com.ifgoiano.cdsquimica.model.Level;
import br.com.ifgoiano.cdsquimica.model.QtdLevel;

public class LevelCreateActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    TextView pergunta, a,b,c,d;
    Spinner resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_create);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        pergunta = findViewById(R.id.edtPergunta);
        a =findViewById(R.id.a);
        b =findViewById(R.id.b);
        c =findViewById(R.id.c);
        d =findViewById(R.id.d);
        resposta = findViewById(R.id.spResporta);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resposta.setAdapter(arrayAdapter);

    }

    public void addPergunta(View v){
        String perguntaStr = pergunta.getText().toString();
        String selectA = a.getText().toString();
        String selectB = b.getText().toString();
        String selectC = c.getText().toString();
        String selectD = d.getText().toString();
        String resportaStr = resposta.getSelectedItem().toString();

        Level level = new Level(perguntaStr, selectA, selectB, selectC, selectD, resportaStr);



        db.collection("level").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int levelFase = 0;
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document: task.getResult()){
                        levelFase = task.getResult().size();
                    }
                }

                Toast.makeText(LevelCreateActivity.this, ""+levelFase--, Toast.LENGTH_SHORT).show();
                String nameLevel = "Fase "+levelFase--;
                db.collection("level").document(nameLevel).collection("Perguntas").add(level).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(LevelCreateActivity.this, "Pergunta cadastrada", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}