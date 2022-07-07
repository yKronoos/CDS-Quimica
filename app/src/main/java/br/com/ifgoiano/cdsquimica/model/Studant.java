package br.com.ifgoiano.cdsquimica.model;

public class Studant extends Account{
    private String turma;
    private String escola;
    private Double score;

    public Studant (){}

    public Studant(String nome, String email, String senha, String escola, String turma) {
        super(nome, email, senha);
        this.escola = escola;
        this.turma = turma;
    }

    public Studant(String nome, String email, String senha, Double score) {
        super(nome, email, senha);
        this.score = score;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
