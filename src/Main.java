import Objetcs.Jogador;
import Objetcs.Quiz;
import assets.ConsoleColors;
import db.data.ListaRanking;
import db.data.SalvaJogo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main (String args[]) throws SQLException {
        ArrayList<Quiz> quiz = new ArrayList<>();
        Random random = new Random();
        int acertos = 0;

        String continuajogo;
        do {
            //Recebe o nome do jogador
            System.out.print("Digite o nome do jogador: ");
            String nome = inputString();

            //Cria o jogo de forma aleatoria toda vez que o código é inicializado
            //O jogo pode ter de 1 a 11 perguntas
            //As  perguntas e respostas são geradas automaticamente
            int numero = random.nextInt(10) + 1;
            if (numero == 1) {
                System.out.println("\nO Quiz terá " + ConsoleColors.BLUE + numero + ConsoleColors.WHITE + " pergunta.");
            } else {
                System.out.println("\nO Quiz terá " + ConsoleColors.BLUE + numero + ConsoleColors.WHITE + " perguntas.");
            }

            for (int i = 1; i <= numero; i++) {
                int n1 = random.nextInt(100) + 1;
                int n2 = random.nextInt(100) + 1;
                String pergunta;
                int resultado;
                if (i % 2 == 0) {
                    pergunta = "Qual o valor da soma entre " + n1 + " e " + n2 + " ?";
                    resultado = n1 + n2;
                } else {
                    pergunta = "Qual o valor da subtração entre " + n1 + " e " + n2 + " ?";
                    resultado = n1 - n2;
                }

                Quiz q = new Quiz(pergunta, resultado);
                quiz.add(q);
            }

            //Exibe a pergunta, pega a resposta e verifica se acertou ou errou
            int k = 1;
            for (Quiz p : quiz) {
                System.out.println("\n" + k + "° Pergunta: " + p.getPergunta());

                int resposta;
                do {
                    System.out.print("Resposta: ");
                    resposta = inputNumberInt();
                    if (resposta == -999) {
                        System.out.println(ConsoleColors.RED + "\nAs respostas devem ser números inteiros, positivos ou negativos !\n\n" + ConsoleColors.WHITE);
                        System.out.println("\nTente responder novamente.");
                    }
                } while (resposta == -999);

                if (resposta == p.getResposta()) {
                    System.out.print(ConsoleColors.GREEN + "\nMuito bem, você acertou!\n\n" + ConsoleColors.WHITE);
                    acertos++;
                } else {
                    System.out.print(ConsoleColors.RED + "\nQue pena, você errou.\n\n" + ConsoleColors.WHITE);
                }
                k++;
            }

            int erros = numero - acertos;
            System.out.println("\n\nJogo Finalizado, resultado:\n");
            System.out.println("Jogador: " + nome);
            System.out.println(ConsoleColors.GREEN + "Acertos: " + ConsoleColors.WHITE + acertos);
            System.out.println(ConsoleColors.RED + "Erros: " + ConsoleColors.WHITE + erros);
            if (erros == 0) {
                System.out.println(ConsoleColors.GREEN + "\nPARABÉNS, VOCÊ ACERTOU TODAS AS PERGUNTAS !! " + ConsoleColors.WHITE);
            }

            if (acertos == 0) {
                System.out.println(ConsoleColors.RED + "\nVOCÊ ERROU TODAS AS PERGUNTAS, PROCURE SE ESFORÇAR MAIS." + ConsoleColors.WHITE);
            }


            Jogador j = new Jogador(nome.toLowerCase(Locale.ROOT), acertos, erros);
            SalvaJogo sj = new SalvaJogo();
            sj.salvaRJogo(j);

            System.out.println("Para iniciar um novo jogo, digite 'SIM', caso não queira continuar, aperte 'ENTER'");
            continuajogo = inputString().toLowerCase(Locale.ROOT);
        } while (continuajogo.equals("sim"));


        ListaRanking listaR = new ListaRanking();


    }

    public static String inputString(){
        Scanner inputUsuario = new Scanner(System.in);
        String texto = inputUsuario.nextLine();
        return texto;
    }

    public static Integer inputNumberInt(){
        Scanner inputUsuario = new Scanner(System.in);
        try {
            return inputUsuario.nextInt();
        } catch (Exception e){
            return -999;
        }

    }

}
