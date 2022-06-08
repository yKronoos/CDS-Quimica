package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginProfessorActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnLoggin;
    private EditText email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_professor);

        mAuth = FirebaseAuth.getInstance();

        btnLoggin = findViewById(R.id.btnLogProf);
        email = findViewById(R.id.edtLogEmailProf);
        pass = findViewById(R.id.edtLogPassProf);
    }

    public void register(View v){
        Intent it = new Intent(this, RegisterProfessorActivity.class);
        startActivity(it);
    }

    public void loggin(View v){
        View view = btnLoggin;

        mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent it = new Intent(LoginProfessorActivity.this, HomeProfessorActivity.class);
                            startActivity(it);
                        }else{
                            Snackbar.make(view, "Professor não cadastrado", Snackbar.LENGTH_LONG);
                        }
                    }
                });
    }
}