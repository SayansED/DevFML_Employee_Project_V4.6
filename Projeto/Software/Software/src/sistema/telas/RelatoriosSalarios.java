package sistema.telas;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sistema.Navegador;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import sqlite.Conexao;

public class RelatoriosSalarios extends JPanel{
	JLabel lblTitulo, lblDescricao;
	int contRange = 0; 
    
    public RelatoriosSalarios(){
        criarComponentes();
        criarEventos();
        Navegador.habilitaMenu();
    }

    private void criarComponentes() {
        setLayout(null);
        
        lblTitulo = new JLabel("Relat�rios", JLabel.CENTER);
        lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.PLAIN, 20));
        lblDescricao = new JLabel("Esse gr�fico representa a quantidade de funcion�rios por faixas de sal�rio", 
                JLabel.CENTER);
        
        CategoryDataset dadosGrafico = this.criarDadosGrafico();
        
        JFreeChart someChart = ChartFactory.createBarChart3D("", null, "Quantidade de funcion�rios", 
                dadosGrafico, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) someChart.getCategoryPlot();
        
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        someChart.setBackgroundPaint(null);
        
        plot.getRangeAxis().setLowerBound(0);
        plot.getRangeAxis().setRange(new Range(0, contRange+10)); 
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.getRangeAxis().setAutoRange(false);
        Font font3 = new Font(lblTitulo.getFont().getName(), Font.PLAIN, 10); 
        plot.getRangeAxis().setLabelFont(font3);
        
        plot.getDomainAxis().setVisible(false);
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(-2);
        
        ChartPanel chartPanel = new ChartPanel(someChart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(660, 340);
            }
        };
        
        lblTitulo.setBounds(20, 20, 660, 40);
        lblDescricao.setBounds(20, 50, 660, 40);
        chartPanel.setBounds(20, 100, 660, 340);
        
        add(lblTitulo);
        add(lblDescricao);
        add(chartPanel);
        
        setVisible(true);
    }

    private void criarEventos() {
        
    }

    @SuppressWarnings("empty-statement")
    private CategoryDataset criarDadosGrafico() {
        
        DefaultCategoryDataset dados = new DefaultCategoryDataset();
           
        // conex�o
        Conexao conexao = new Conexao();
        // instrucao SQL
        Statement instrucaoSQL;
        // resultados
        ResultSet resultados;
        
        try {
            // conectando ao banco de dados
        	conexao.conectar();
            
            // criando a instru��o SQL 
        	instrucaoSQL = conexao.criarStatement();
        	String query = "SELECT nome, salario, ";
            query = query + " COUNT(case when salario < 1000.00 then 1 end) AS faixa1,";
            query = query + " COUNT(case when salario >= 1000 AND salario < 2000 then 1 end) AS faixa2,";
            query = query + " COUNT(case when salario >= 2000 AND salario < 3000 then 1 end) AS faixa3,";
            query = query + " COUNT(case when salario >= 3000 AND salario < 4000 then 1 end) AS faixa4,";
            query = query + " COUNT(case when salario >= 4000 AND salario < 5000 then 1 end) AS faixa5,";
            query = query + " COUNT(case when salario >= 5000 then 1 end) AS faixa6";
            query = query + " FROM T_FUNCIONARIOS";
            resultados = instrucaoSQL.executeQuery(query);

            while (resultados.next()) {
                dados.addValue(resultados.getInt("faixa1"), "At� R$ 1.000,00", "< R$ 1.000,00");
                contRange = contRange + resultados.getInt("faixa1");
                dados.addValue(resultados.getInt("faixa2"), "De R$ 1.000,00 at� R$ 2.000,00", "R$ 1.000,00 - R$ 2.000,00");
                contRange = contRange + resultados.getInt("faixa2");
                dados.addValue(resultados.getInt("faixa3"), "De R$ 2.000,00 at� R$ 3.000,00", "R$ 2.000,00 - R$ 3.000,00");
                contRange = contRange + resultados.getInt("faixa3");
                dados.addValue(resultados.getInt("faixa4"), "De R$ 3.000,00 at� R$ 4.000,00", "R$ 3.000,00 - R$ 4.000,00");
                contRange = contRange + resultados.getInt("faixa4");
                dados.addValue(resultados.getInt("faixa5"), "De R$ 4.000,00 at� R$ 5.000,00", "R$ 4.000,00 - R$ 5.000,00");
                contRange = contRange + resultados.getInt("faixa5");
                dados.addValue(resultados.getInt("faixa6"), "A partir de R$ 5.000,00", "> R$ 5.000,00");
                contRange = contRange + resultados.getInt("faixa6");
            }
            
            return dados;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relat�rio.\n\n"+ex.getMessage());
            Navegador.inicio();
        }
        
        return null;
    }
}
