package br.com.ifgoiano.cdsquimica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartQuestionActivity extends AppCompatActivity {

    ConstraintLayout backgroundConst;
    TextView level;
    String levelName;
    String background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_question);

        levelName = getIntent().getStringExtra("level");
        background = getIntent().getStringExtra("background");

        backgroundConst = findViewById(R.id.startQuestion);
        level = findViewById(R.id.levelNameStart);

        level.setText(levelName);
        if(background.equals("cenario1")){
            backgroundConst.setBackgroundResource(R.drawable.cenario1);
        }
        else if(background.equals("cenario2")){
            backgroundConst.setBackgroundResource(R.drawable.cenario2);
        }
        else{
            backgroundConst.setBackgroundResource(R.drawable.cenario3);
        }
    }

    public void startQuestionBtn(View v){
        Intent it = new Intent(StartQuestionActivity.this, QuestionsActivity.class);
        it.putExtra("level", levelName);
        it.putExtra("background",  background);

        startActivity(it);
    }
}