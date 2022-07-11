package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.ifgoiano.cdsquimica.model.Score;

public class ScoreActivity extends AppCompatActivity {

    TextView totalScore;
    Button ok;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    String level,score=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        totalScore = findViewById(R.id.txtScore);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        score = getIntent().getStringExtra("score");
        level = getIntent().getStringExtra("level");

        totalScore.setText(score + " acertados");


    }

    public void backListLevel(View v){
        String uid = mAuth.getUid();
        Score sc = new Score(level,uid, score);

        db.collection("level").document(level).collection("results")
                .add(sc).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task != null){
                            Intent it = new Intent(ScoreActivity.this, HomeStudantActivity.class);
                            startActivity(it);
                        }
                    }
                });


    }
}