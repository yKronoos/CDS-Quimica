package br.com.ifgoiano.cdsquimica.model;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String uidProf;

    public Team(){}

    public Team(String name, String uidProf) {
        this.name = name;
        this.uidProf = uidProf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUidProf() {
        return uidProf;
    }

    public void setUidProf(String uidProf) {
        this.uidProf = uidProf;
    }

    public static List<Team> getTeams(){
        List<Team> teams = new ArrayList<>();

        FirebaseFirestore db;
        FirebaseAuth mAuth;

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        db.collection("class").whereEqualTo("uidProf", mAuth.getCurrentUser().getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                Team animal = dc.getDocument().toObject(Team.class);
                                teams.add(animal);
                            }
                        }

                    }
                });

        //teams.add(new Team("Turma 46", "awdawdaw"));



        return teams;
    }
}
