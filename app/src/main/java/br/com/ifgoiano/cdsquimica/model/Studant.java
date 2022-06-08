package br.com.ifgoiano.cdsquimica.model;

public class Studant extends Account{
    private Double score;

    public Studant (){}

    public Studant(String nome, String email, String senha, Double score) {
        super(nome, email, senha);
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
