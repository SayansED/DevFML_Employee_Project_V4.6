package sqlite;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexao {

	private Connection conexao;

	//Conectar
	public boolean conectar() {
		try {
			String url = "jdbc:sqlite:BancoDeDados/SQLiteSistema.db";
			this.conexao = DriverManager.getConnection(url);
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "ERRO NA CONEX�O COM O BANCO", "ERRO", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	//Desconectar
	public boolean desconectar() {
		try {
			if (this.conexao.isClosed() == false) {
				this.conexao.close();
			}
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO NA DESCONEX�O COM O BANCO", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	//Statement l� as string e concatena para codigo sql
	//Criar Statement para o sql ser execuado
	//Em outras palavras, o Statement � o respons�vel por executar os teus c�digos sql no banco de dados
	public Statement criarStatement() {
		try {
			return this.conexao.createStatement();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CRIA��O STATEMENT", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}

	// Metodo que prepara os parametros do sql e separa eles 
	public PreparedStatement criarPreparedStatement(String sql) {
		try {
			return this.conexao.prepareStatement(sql);
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO NA CRIA��O PREPAREDSTATEMENT", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public PreparedStatement criarPreparedStatement(String pSQL, int RETURN_GENERATED_KEYS) {
		try{
			return conexao.prepareStatement(pSQL, RETURN_GENERATED_KEYS);
		}
		catch (SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO NA CRIA��O PREPAREDSTATEMENT", "ERRO", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null; 	
		}
	}

	public Connection getConexao() {
		return this.conexao;
	}
}