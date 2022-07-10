package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import java.util.List;

import br.com.ifgoiano.cdsquimica.adapter.LevelAdapter;
import br.com.ifgoiano.cdsquimica.model.Level;
import br.com.ifgoiano.cdsquimica.model.QtdLevel;
import br.com.ifgoiano.cdsquimica.model.Team;

public class QuestionsActivity extends AppCompatActivity {

    LinearLayout backgorundImage;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    protected List<Level> levelArray = new ArrayList<>();
    TextView pergunta,sizeQuestion;
    Button a,b,c,d;
    String resposta = null, level;
    int question = 0, lastQuestion = 0, score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        pergunta = findViewById(R.id.txtPergunta);
        sizeQuestion =  findViewById(R.id.txtQuestion);
        a = findViewById(R.id.btnA);
        b = findViewById(R.id.btnB);
        c = findViewById(R.id.btnC);
        d = findViewById(R.id.btnD);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        backgorundImage = findViewById(R.id.background);

        String background = getIntent().getStringExtra("background");
        level = getIntent().getStringExtra("level");
        levelArray = Level.getLevel(level);

        db.collection("level").document(level).collection("Perguntas")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){
                                levelArray.addAll(Level.getLevel(level));
                                //animals.add(a);
                            }
                            lastQuestion = levelArray.size();
                            respondido(question);
                        }

                    }

                });

        if(background.equals("cenario1")){
            backgorundImage.setBackgroundResource(R.drawable.cenario1);
        }
        else if(background.equals("cenario2")){
            backgorundImage.setBackgroundResource(R.drawable.cenario2);
        }
        else{
            backgorundImage.setBackgroundResource(R.drawable.cenario3);
        }


    }

    private void disableButtons(){
        a.setTextColor(getResources().getColor(R.color.black));
        b.setTextColor(getResources().getColor(R.color.black));
        c.setTextColor(getResources().getColor(R.color.black));
        d.setTextColor(getResources().getColor(R.color.black));
    }

    public void respostaA(View v){
        String resp = "A";
        if(resposta.equals(resp)){
            a.setTextColor(getResources().getColor(R.color.green));
            score++;
        }else{
            a.setTextColor(getResources().getColor(R.color.red));
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                disableButtons();
                question++;
                respondido(question);
            }
        }, 2000);

    }
    public void respostaB(View v){
        String resp = "B";
        if(resposta.equals(resp)){
            b.setTextColor(getResources().getColor(R.color.green));
            score++;
        }else{
            b.setTextColor(getResources().getColor(R.color.red));
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                disableButtons();
                question++;
                respondido(question);
            }
        }, 2000);
    }
    public void respostaC(View v){
        String resp = "C";
        if(resposta.equals(resp)){
            c.setTextColor(getResources().getColor(R.color.green));
            score++;
        }else{
            c.setTextColor(getResources().getColor(R.color.red));
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                disableButtons();
                question++;
                respondido(question);
            }
        }, 2000);
    }
    public void respostaD(View v){
        String resp = "D";
        if(resposta.equals(resp)){
            d.setTextColor(getResources().getColor(R.color.green));
            score++;
        }else{
            d.setTextColor(getResources().getColor(R.color.red));
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                disableButtons();
                question++;
                respondido(question);
            }
        }, 2000);
    }

    private void respondido(int i){
        if(i == lastQuestion){
            Intent it = new Intent(QuestionsActivity.this, ScoreActivity.class);
            String scoreStr = String.valueOf(score);
            it.putExtra("score", scoreStr);
            it.putExtra("level", level);
            startActivity(it);
        }else{
            sizeQuestion.setText("Pergunta " + i);
            pergunta.setText(levelArray.get(i).getPergunta());
            a.setText(levelArray.get(i).getA());
            b.setText(levelArray.get(i).getB());
            c.setText(levelArray.get(i).getC());
            d.setText(levelArray.get(i).getD());
            resposta = levelArray.get(i).getResultado();
        }
    }


}