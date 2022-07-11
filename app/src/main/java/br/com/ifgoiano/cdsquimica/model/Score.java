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

public class Score {
    private String level;
    private String uidAluno;
    private String scoreNumber;

    public Score() {

    }

    public Score(String level, String uidAluno, String score) {
        this.level = level;
        this.uidAluno = uidAluno;
        this.scoreNumber = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUidAluno() {
        return uidAluno;
    }

    public void setUidAluno(String uidAluno) {
        this.uidAluno = uidAluno;
    }

    public String getScoreNumber() {
        return scoreNumber;
    }

    public void setScore(String score) {
        this.scoreNumber = score;
    }

    public static List<Score> getScores(){
        List<Score> scoreList = new ArrayList<>();

        FirebaseAuth mAuth;
        FirebaseFirestore db;

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        db.collection("results")
                .whereEqualTo("uidAluno", mAuth.getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                Score score = dc.getDocument().toObject(Score.class);
                                scoreList.add(score);
                            }
                        }

                    }
                });

        //teams.add(new Team("Turma 46", "awdawdaw"));



        return scoreList;
    }

    @Override
    public String toString() {
        return "Score{" +
                "level='" + level + '\'' +
                ", uidAluno='" + uidAluno + '\'' +
                ", scoreNumber='" + scoreNumber + '\'' +
                '}';
    }
}
