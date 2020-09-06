package sqlite;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import sqlite.Conexao;

public class CriarBancoDeDados {

	
	// Adapata��o
	//private Conexao conexao = new Conexao();

	private final Conexao conexao;

    //Construtor da classe
  	//Pq est� usando uma variavel do tipo "final"
    public CriarBancoDeDados(Conexao pConexaoSQLite) {
        this.conexao = pConexaoSQLite;
    }
    
	//Metodo
	public void createTableFuncionarios() {
		//Codigo para crias tabela
		String sql = "CREATE TABLE IF NOT EXISTS T_FUNCIONARIOS"
				+ "("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome varchar2(25) NOT NULL, "
				+ "sobrenome varchar2(35) NOT NULL, "
				+ "dataNascimento varchar2(10) NOT NULL, "
				+ "email varchar2(60) NOT NULL, "
				+ "cargo varchar2(30) NOT NULL, "
				+ "salario real NOT NULL, "
				+ "data_entrada_sistema varchar2(10) NOT NULL"
				+ ");";
		// Executando o sql criar tabela
		boolean conectou = false;

		try {
			//conectou = this.conexaoSQLite.conectar(); // Chama o metodo de outra classe, para conectar-se do BD
			conectou = this.conexao.conectar();
			Statement stmt = this.conexao.criarStatement(); // Estancia o Statement para usa-lo
			stmt.execute(sql);
		} 
		catch (SQLException e) { //Erro na tabela
			JOptionPane.showMessageDialog(null, "ERRO NA CRIA��O DA TEBELA FUNCION�RIO", "ERRO", JOptionPane.ERROR_MESSAGE);
		} finally {
			if(conectou){
				this.conexao.desconectar(); // Chama o metodo de outra classe, para desconectar-se do BD
			}
		}

	}

	public void createTableCargos() {
		String sql = "CREATE TABLE IF NOT EXISTS T_CARGOS"
				+ "("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome varchar2(30) NOT NULL, "
				+ "data_entrada_sistema varchar2(10) NOT NULL"
				+ ");";
		boolean conectou = false;
		try {
			conectou = this.conexao.conectar();
			Statement stmt = this.conexao.criarStatement();
			stmt.execute(sql);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERRO NA CRIA��O DA TEBELA CARGOS", "ERRO", JOptionPane.ERROR_MESSAGE);
		} finally {
			if(conectou) {
				this.conexao.desconectar();
			}
		}
	}

}
