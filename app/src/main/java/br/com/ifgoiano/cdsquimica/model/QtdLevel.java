package br.com.ifgoiano.cdsquimica.model;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QtdLevel {
    private String qtd;

    public QtdLevel() {
    }

    public QtdLevel(String qtd) {
        this.qtd = qtd;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public static List<QtdLevel> getLevels(){
        List<QtdLevel> levelList = new ArrayList<>();

        FirebaseFirestore db;

        db = FirebaseFirestore.getInstance();

        db.collection("level")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                QtdLevel level = dc.getDocument().toObject(QtdLevel.class);
                                levelList.add(level);
                            }
                        }

                    }
                });

        //teams.add(new Team("Turma 46", "awdawdaw"));



        return levelList;
    }
}
