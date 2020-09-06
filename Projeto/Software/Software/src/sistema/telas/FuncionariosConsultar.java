package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.ControllerFuncionario;
import sqlite.Conexao;
import sistema.Navegador;
import sistema.entidades.Funcionario;

public class FuncionariosConsultar extends JPanel {

	JLabel lblTitulo, lblFuncionario;
	JTextField txtFuncionario;
	JButton btnPesquisar, btnEditar, btnExcluir;
	DefaultListModel<Funcionario> listaFuncionariosModelo = new DefaultListModel();
	// JList<Funcionario> listaFuncionarios;
	ImageIcon imgPesquisar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\search03.png");
	ImageIcon imgDeletar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgEditar = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png");

	public FuncionariosConsultar() {
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	public void criarComponentes() {
		setLayout(null);

		lblTitulo = new JLabel("Consulta de Funcion�rio", JLabel.CENTER);
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.PLAIN, 20));
		lblFuncionario = new JLabel("Nome do Funcion�rio", JLabel.LEFT);
		txtFuncionario = new JTextField();
		btnPesquisar = new JButton("Pesquisar Funcion�rio", imgPesquisar);
		btnEditar = new JButton("Editar Funcion�rio", imgEditar);
		btnEditar.setEnabled(true);
		btnExcluir = new JButton("Excluir Funcion�rio", imgDeletar);
		btnExcluir.setEnabled(true);
		listaFuncionariosModelo = new DefaultListModel();

		lblTitulo.setBounds(20, 20, 660, 40);
		lblFuncionario.setBounds(150, 120, 400, 20);
		txtFuncionario.setBounds(150, 140, 400, 40);
		btnPesquisar.setBounds(560, 140, 200, 40);
		btnEditar.setBounds(180, 210, 180, 40);
		btnExcluir.setBounds(440, 210, 180, 40);

		add(lblTitulo);
		add(lblFuncionario);
		add(txtFuncionario);
		add(btnPesquisar);
		add(btnEditar);
		add(btnExcluir);

		setVisible(true);
	}

	public void criarEventos() {

		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listPesquisarFuncionarios(txtFuncionario.getText());
			}
		});

		// Editar
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
				if(controllerFuncionario.buscaFuncionario(txtFuncionario.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado");
					Navegador.funcionariosConsultar();
				} else {
					JOptionPane.showMessageDialog(null, "Funcion�rio encontrado" + "\n" + "Nome: " + txtFuncionario.getText(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
					Navegador.funcionariosEditar(txtFuncionario.getText());
				}
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerFuncionario controllerFuncionario = new ControllerFuncionario();
				if(controllerFuncionario.buscaFuncionario(txtFuncionario.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado");
					Navegador.funcionariosConsultar();
				} else {
					controllerFuncionario.deletarFuncionario(txtFuncionario.getText());
				}
			}
		});
	}

	private void listPesquisarFuncionarios(String ptxtCampoFuncionario) {
		Navegador.FuncionariosPesquisa(ptxtCampoFuncionario);
	}
}