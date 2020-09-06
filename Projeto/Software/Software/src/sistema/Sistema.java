package sistema;

import javax.swing.JFrame; // Fornece recursos b�sicos de janela
import javax.swing.JPanel; // Fornece caixa de di�logos
import javax.swing.SwingConstants; // Constantes comuns utilizadas com swing
import javax.swing.JLabel; // Exibe texto e imagens

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon; // Carrega imagens
import javax.swing.JButton;

import sistema.telas.*;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;
// I am not responsible for any damage caused by installing the software on your hardware..
/**
 * @author Eduardo Sayans
 * 
 * Vers�o do Software: V4.6
 * @version 4.6
 * 
 * 
 * </br>Para entrar em contato com o desenvolvedor pode-se usar o e-mail: 
 * devfastml@outlook.com
 * Especificar no assunto de e-mail o motivo do contato.
 * 
 * 
 */
public class Sistema{

	/* Definimos uma vari�vel do tipo JPanel, que armazenar� a tela atual do nosso sistema. 
	Ser� do tipo est�tica, pois vamos utiliz�-la em v�rios momentos. */
	public static JPanel JPtela;
	/* Definimos uma vari�vel do tipo JFrame, que ser� a janela da nossa aplica��o. 
	Ser� do tipo est�tica, pois vamos utiliz�-la em v�rios momentos */
	public static JFrame JFframe;


	public static void main(String[] args){
		criarComponentes();

		String strIconWay = "C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\system03.png";
		Image iconSystem = Toolkit.getDefaultToolkit().getImage(strIconWay);
		JFframe.setIconImage(iconSystem);

	}
	/** 
	 *Cria��o dos componentes de tela
	 */
	private static void criarComponentes(){

		/* Instanciamos o objeto JFrame e definimos algumas configura��es */
		JFframe = new JFrame("Sistema");
		JFframe.setSize(800, 600);
		JFframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFframe.setLocationRelativeTo(null);

		Conexao conexao = new Conexao();
		conexao.conectar();
		CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);
		criarBancoDeDados.createTableFuncionarios();
		criarBancoDeDados.createTableCargos();
		conexao.desconectar();

		// Abrir 01
		/** M�todo para cria��o do menu*/
		Navegador.login();
	}
}