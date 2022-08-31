package Objetcs;

public class Quiz {
    private String pergunta;
    private int resposta;

    public Quiz(String pergunta, int resposta){
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public int getResposta() {
        return resposta;
    }
}
