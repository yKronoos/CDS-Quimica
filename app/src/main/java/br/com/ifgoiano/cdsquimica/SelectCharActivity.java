package br.com.ifgoiano.cdsquimica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.ifgoiano.cdsquimica.R;

public class SelectCharActivity extends AppCompatActivity {

    Drawable backround;
    ImageButton background1, background2, background3;
    String levelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.ifgoiano.cdsquimica.R.layout.activity_select_char);

        background1 = findViewById(R.id.cenario1);
        background2 = findViewById(R.id.cenario2);
        background3 = findViewById(R.id.cenario3);

        levelName = getIntent().getStringExtra("level");


        background1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBackground1();
            }
        });
        background2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBackground2();
            }
        });
        background3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBackground3();
            }
        });
    }
    public void getBackground1(){
        Intent it = new Intent(SelectCharActivity.this, StartQuestionActivity.class);
        it.putExtra("level", levelName);
        it.putExtra("background",  "cenario1");
        startActivity(it);
    }
    public void getBackground2(){
        Intent it = new Intent(SelectCharActivity.this, StartQuestionActivity.class);
        it.putExtra("level", levelName);
        it.putExtra("background",  "cenario2");
        startActivity(it);
    }
    public void getBackground3(){
        Intent it = new Intent(SelectCharActivity.this, StartQuestionActivity.class);
        it.putExtra("level", levelName);
        it.putExtra("background",  "cenario3");
        startActivity(it);
    }

}