package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import br.com.ifgoiano.cdsquimica.model.Error;

public class LoginStudantActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnLoggin;
    private EditText email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_studant);

        mAuth = FirebaseAuth.getInstance();

        btnLoggin = findViewById(R.id.btnLogStudant);
        email = findViewById(R.id.edtLogEmailStudant);
        pass = findViewById(R.id.edtLogPassStudant);

    }

    public void registerStudant(View v){
        Intent it = new Intent(this, RegisterAlunoActivity.class);
        startActivity(it);
    }

    public void logginStudant(View v){
        View view = btnLoggin;

        if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())){
            Toast.makeText(this, "Os campos não podem estar vazios", Toast.LENGTH_SHORT).show();

        }else{
            mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent it = new Intent(LoginStudantActivity.this, HomeStudantActivity.class);
                                startActivity(it);
                            }else{
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                Error error = new Error();
                                String finalError = error.showErrorAuth(errorCode);
                                Toast.makeText(LoginStudantActivity.this, finalError, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}