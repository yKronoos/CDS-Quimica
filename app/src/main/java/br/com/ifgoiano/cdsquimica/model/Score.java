package br.com.ifgoiano.cdsquimica.model;

public class Score {
    private String uidAluno;
    private String score;

    public Score() {

    }

    public Score(String uidAluno, String score) {
        this.uidAluno = uidAluno;
        this.score = score;
    }

    public String getUidAluno() {
        return uidAluno;
    }

    public void setUidAluno(String uidAluno) {
        this.uidAluno = uidAluno;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
