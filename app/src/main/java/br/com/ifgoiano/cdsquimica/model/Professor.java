package br.com.ifgoiano.cdsquimica.model;

import java.util.List;

public class Professor extends Account{

    private String escola;

    public Professor(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public Professor(String nome, String email, String senha, String escola) {
        super(nome, email, senha);
        this.escola = escola;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }
}
