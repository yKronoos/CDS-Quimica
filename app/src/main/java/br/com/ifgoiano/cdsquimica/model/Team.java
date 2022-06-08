package br.com.ifgoiano.cdsquimica.model;

import java.util.List;

public class Team {
    private String name;
    private List<Studant> studants;

    public Team(){}

    public Team(String name) {
        this.name = name;
    }

    public Team(List<Studant> studants) {
        this.studants = studants;
    }

    public List<Studant> getStudants() {
        return studants;
    }

    public void setStudants(List<Studant> studants) {
        this.studants = studants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
