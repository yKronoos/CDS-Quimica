package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
    int sizeQuestion = 0;
    String nameLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_create);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        nameLevel = getIntent().getStringExtra("levelFase");

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

        if(TextUtils.isEmpty(pergunta.getText().toString()) ||
                TextUtils.isEmpty(a.getText().toString()) ||
                TextUtils.isEmpty(b.getText().toString()) ||
                TextUtils.isEmpty(c.getText().toString()) ||
                TextUtils.isEmpty(d.getText().toString()) ||
                resposta.getSelectedItem().toString().equals("--Selecione--")){

            Toast.makeText(this, "A pergunta ou respotas estão em branco", Toast.LENGTH_SHORT).show();

        }else{
            String perguntaStr = pergunta.getText().toString();
            String selectA = a.getText().toString();
            String selectB = b.getText().toString();
            String selectC = c.getText().toString();
            String selectD = d.getText().toString();
            String resportaStr = resposta.getSelectedItem().toString();

            Level level = new Level(sizeQuestion,perguntaStr, selectA, selectB, selectC, selectD, resportaStr);

            db.collection("level").document(nameLevel).collection("Perguntas").add(level).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    sizeQuestion++;
                    Toast.makeText(LevelCreateActivity.this, "Pergunta cadastrada", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}