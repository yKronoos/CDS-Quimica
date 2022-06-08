package br.com.ifgoiano.cdsquimica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void studant(View view){
        Intent it = new Intent(this, LoginStudantActivity.class);
        startActivity(it);
    }

    public void professor(View view){
        Intent it = new Intent(this, LoginProfessorActivity.class);
        startActivity(it);
    }
}