/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author willi
 */
public class BD {
    static Statement stm;
    static PreparedStatement stmt;
    static ResultSet rs;
    String driver = "org.postgresql.Driver";
    String caminho = "jdbc:postgresql://localhost:5432/wsWilliam";
    String user = "postgres";
    String senha = "postgres";
    static Connection con;

    public void conexao() {

        try {
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho, user, senha);
            System.out.println("Banco de Dados conectado");
        } catch (SQLException sqex) {
            System.out.println("nao foi " + sqex.getMessage());

        }

    }

    public void depositar(String numConta, String valor) throws SQLException {
        String saldo = "";
        String novoSaldo = "";
        String sql = "SELECT saldo FROM correntista WHERE codigo = '" + numConta + "'";
        stm = (Statement) con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {
            saldo = rs.getString("saldo");
        }
        System.out.println("valor do saldo eh "+saldo);
        novoSaldo = Integer.toString(Integer.parseInt(saldo) + Integer.parseInt(valor));
        updateDados(numConta, novoSaldo);

    }
    

    

    
    public void desconecta() {
        try {
            con.close();
        } catch (SQLException esx) {
            System.out.println("Banco não desconectou");

        }

    }

    public String pegarDados(String numConta) throws SQLException {
        String valor = "";
        String sql = "SELECT * FROM correntista WHERE codigo = '" + numConta + "'";
        Statement stm = (Statement) con.createStatement();
        rs = stm.executeQuery(sql);
        while (this.rs.next()) {
            valor = rs.getString("codigo") + " / " + rs.getString("saldo") + " / " + "Nome => " + rs.getString("nome");
        }

        return valor;

    }

    public void escreverBanco(String nome, String num_conta, String saldo) {
        try {
            String sql = "insert into correntista (nome,codigo,saldo)" + "values(?,?,?)";
           stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, num_conta);
            stmt.setString(3, saldo);
           
            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("algo deu errado " + ex.getMessage());

        }

    }

    public void updateDados(String num_conta, String saldo) throws SQLException {
        String sql = "update correntista "
                + "set saldo = ? where  codigo = '" +num_conta + "'";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, saldo);
        stmt.execute();
        stmt.close();

    }
   

    public String pegarSaldo(String numConta) throws SQLException {
        String valor = "";
        String sql = "SELECT saldo FROM correntista WHERE codigo = '" +numConta + "'";
        stm = (Statement) con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {
            valor = rs.getString("saldo");
        }

        return valor;
    }

    public String pegarNome(String numConta) throws SQLException {
        String valor = "";
        String sql = "SELECT nome FROM correntista WHERE codigo = '" + numConta + "'";
        stm = (Statement) con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {
            valor = rs.getString("nome");
        }

        return valor;
    }
     public String pegarCodigo(String codigo) throws SQLException {
        String valor = "";
        String sql = "SELECT nome FROM correntista WHERE codigo = '" + codigo + "'";
        stm = (Statement) con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {
            valor =rs.getString("codigo");
        }

        return valor;
    }
    
}