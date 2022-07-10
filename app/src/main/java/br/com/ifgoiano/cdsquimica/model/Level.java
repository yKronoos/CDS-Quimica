package br.com.ifgoiano.cdsquimica.model;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int id;
    private String pergunta;
    private String a;
    private String b;
    private String c;
    private String d;
    private String resultado;

    public Level(){}

    public Level(int id,String pergunta, String a, String b, String c, String d, String resultado) {
        this.id = id;
        this.pergunta = pergunta;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public static List<Level> getLevel(String levelName){
        List<Level> levelList = new ArrayList<>();

        FirebaseFirestore db;

        db = FirebaseFirestore.getInstance();

        db.collection("level").document(levelName).collection("Perguntas")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                Level level = dc.getDocument().toObject(Level.class);
                                levelList.add(level);
                            }
                        }

                    }
                });

        //teams.add(new Team("Turma 46", "awdawdaw"));



        return levelList;
    }

    @Override
    public String toString() {
        return "Level{" +
                "pergunta='" + pergunta + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
