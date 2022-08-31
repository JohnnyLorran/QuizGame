package db.data;

import assets.ConsoleColors;
import db.ConnectionBD.ConnectionFactory;

import java.sql.*;

public class ListaRanking {

    public ListaRanking() throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnectionBD()) {
            try (Statement stm = connection.createStatement()) {
                stm.execute("Select nome, sum(acertos) as acertos, sum(erros) as erros from jogo where nome = nome group by nome order by acertos desc limit 5");

                ResultSet rst = stm.getResultSet();

                System.out.println("\n\n\nRanking:\n");
                int i = 0;
                while (rst.next()) {
                    i++;
                    System.out.println("Em "+i+"°lugar está o jogador: " + rst.getString("nome"));
                    System.out.println(ConsoleColors.GREEN + "Acertos: " + ConsoleColors.WHITE + rst.getInt("acertos"));
                    System.out.println(ConsoleColors.RED + "Erros: " + ConsoleColors.WHITE+ rst.getInt("erros")+"\n\n");
                }
            }
        }
    }
}
