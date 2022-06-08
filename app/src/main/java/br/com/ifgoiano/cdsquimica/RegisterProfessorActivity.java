package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.ifgoiano.cdsquimica.model.Professor;

public class RegisterProfessorActivity extends AppCompatActivity {

    private EditText name,pass, email;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_professor);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.edtNameProf);
        email = findViewById(R.id.edtEmailProf);
        pass = findViewById(R.id.edtPassProf);
    }

    public void btnRegister(View view){
        Professor prof = new Professor(name.getText().toString(), email.getText().toString(), pass.getText().toString());

        if(pass.length() < 6){
            View v = findViewById(R.id.btnRegisterProf);
            Snackbar.make(v, "Senha deve ter no mínimo 6 caracteres", Snackbar.LENGTH_SHORT).show();
        }else{
            loggin(prof);
        }

    }

    private void loggin(Professor professor){
        mAuth.createUserWithEmailAndPassword(professor.getEmail(), professor.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            registerDB(professor);
                        }
                    }
                });
    }
    private void registerDB(Professor professor){
        String uid = mAuth.getUid();
        db.collection("professors").document(uid).set(professor).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                View view = findViewById(R.id.btnRegisterProf);
                Snackbar.make(view, "Cadastrado com sucesso", Snackbar.LENGTH_SHORT).show();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent it = new Intent(RegisterProfessorActivity.this, LoginProfessorActivity.class);
                        startActivity(it);
                    }
                }, 2000);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                View view = findViewById(R.id.btnRegisterProf);
                Snackbar.make(view, "Cadastrado deu errado", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}