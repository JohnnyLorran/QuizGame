package Objetcs;

public class Jogador {
    private String nome;
    private int acertos;
    private int erros;

    public Jogador (String nome, int acertos, int erros){
        this.nome = nome;
        this.acertos = acertos;
        this.erros = erros;
    }

    public String getNome() {
        return nome;
    }

    public int getAcertos() {
        return acertos;
    }

    public int getErros() {
        return erros;
    }
}
