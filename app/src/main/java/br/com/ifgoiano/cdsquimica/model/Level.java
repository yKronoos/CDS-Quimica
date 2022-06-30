package br.com.ifgoiano.cdsquimica.model;

public class Level {
    private String pergunta;
    private String a;
    private String b;
    private String c;
    private String d;
    private String resultado;

    public Level(){}

    public Level(String pergunta, String a, String b, String c, String d, String resultado) {
        this.pergunta = pergunta;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.resultado = resultado;
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
}
