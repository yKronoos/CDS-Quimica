package br.com.ifgoiano.cdsquimica.model;

import java.util.List;

public class Professor extends Account{
    private List<Team> team;

    public Professor(){}

    public Professor(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public Professor(String nome, String email, String senha, List<Team> team) {
        super(nome, email, senha);
        this.team = team;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }
}
