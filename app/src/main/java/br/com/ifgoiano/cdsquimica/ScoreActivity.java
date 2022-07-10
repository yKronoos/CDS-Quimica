package br.com.ifgoiano.cdsquimica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView totalScore;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        totalScore = findViewById(R.id.txtScore);

        String score = getIntent().getStringExtra("score");
        String level = getIntent().getStringExtra("level");

        totalScore.setText(score + " acertados");
    }

    public void backListLevel(View v){
        Intent it = new Intent(ScoreActivity.this, HomeStudantActivity.class);
        startActivity(it);
    }
}