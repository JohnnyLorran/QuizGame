package db.data;

import Objetcs.Jogador;
import assets.ConsoleColors;
import db.ConnectionBD.ConnectionFactory;

import java.sql.*;

public class SalvaJogo  {

    public void salvaRJogo(Jogador j) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        try(Connection connection = factory.newConnectionBD()) {
            try (PreparedStatement stm = connection.prepareStatement("Insert into jogo (nome,acertos, erros) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);){


                stm.setString(1, j.getNome());
                stm.setInt(2, j.getAcertos());
                stm.setInt(3, j.getErros());

                stm.execute();

                try (ResultSet rst = stm.getGeneratedKeys()) {
                    while (rst.next()) {
                        System.out.println(ConsoleColors.BLUE + "Seu resultado foi salvo !!" + ConsoleColors.WHITE);
                    }
                }
            }catch (Exception e){
                System.out.println("Erro ao salvar o jogo: " + e.getMessage());
                connection.rollback();
            }
        }
    }
}
