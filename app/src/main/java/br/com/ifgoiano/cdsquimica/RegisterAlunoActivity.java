package br.com.ifgoiano.cdsquimica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import br.com.ifgoiano.cdsquimica.model.Professor;
import br.com.ifgoiano.cdsquimica.model.Studant;
import br.com.ifgoiano.cdsquimica.model.Team;

public class RegisterAlunoActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private List<String> spinnerArray = new ArrayList<>();
    private Spinner sItems;
    private EditText nome,email,senha,escola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_aluno);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        sItems = findViewById(R.id.spinner);

        nome = findViewById(R.id.edtNameStudant);
        email = findViewById(R.id.edtEmailStudant);
        senha = findViewById(R.id.edtPassStudant);
        escola = findViewById(R.id.edtNameEscolaStudant);

        spinnerArray.add("--Selecione--");

        populateSpinner();
    }

    public void populateSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sItems.setAdapter(adapter);

        db.collection("class")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Team t = document.toObject(Team.class);
                                spinnerArray.add(t.getName());
                            }
                        }
                    }
                });
    }

    public void btnRegister(View view){
        Studant studant = new Studant(nome.getText().toString(), email.getText().toString(), senha.getText().toString(), escola.getText().toString(), sItems.getSelectedItem().toString());

        if(senha.length() < 6){
            Snackbar.make(view, "Senha deve ter no mínimo 6 caracteres", Snackbar.LENGTH_SHORT).show();
        }else{
            createUser(studant);
        }

    }

    private void createUser(Studant studant){
        mAuth.createUserWithEmailAndPassword(studant.getEmail(), studant.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            registerDB(studant);
                        }
                    }
                });
    }

    private void registerDB(Studant studant){
        String uid = mAuth.getUid();
        db.collection("class").whereEqualTo("name", studant.getTurma())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String id = null;
                        for(QueryDocumentSnapshot document: task.getResult()){
                            id = document.getId();
                        }
                        db.collection("class").document(id).collection("studants").document(uid)
                                .set(studant).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        View view = findViewById(R.id.btnRegisterStudant);
                                        Snackbar.make(view, "Cadastrado com sucesso", Snackbar.LENGTH_SHORT).show();

                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent it = new Intent(RegisterAlunoActivity.this, LoginStudantActivity.class);
                                                startActivity(it);
                                            }
                                        }, 2000);
                                    }
                                });
                    }
                });



    }
}