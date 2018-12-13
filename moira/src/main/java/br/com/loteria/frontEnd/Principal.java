package br.com.loteria.frontEnd;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.com.loteria.lotofacil.Estatisticas;

public class Principal extends JFrame {

	private static final long serialVersionUID = -3798327672358307298L;
	private JPanel contentPane;
	private JTextField textQuantidadesJogos;
	private Action gerarEstatisticasAction = new GerarEstatisticasAction();
	private JTable tableEstatisticas;
	private EstatisticasTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 694);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "name_8818180841513");
		
		JPanel panelEstatisticas = new JPanel();
		tabbedPane.addTab("Estatísticas", null, panelEstatisticas, null);
		panelEstatisticas.setLayout(null);
		
		JButton btnGerarEstatisticas = new JButton("Gerar Estatística");
		btnGerarEstatisticas.setAction(gerarEstatisticasAction);
		btnGerarEstatisticas.setBounds(11, 5, 161, 23);
		panelEstatisticas.add(btnGerarEstatisticas);
		
		textQuantidadesJogos = new JTextField();
		textQuantidadesJogos.setBounds(420, 7, 41, 20);
		textQuantidadesJogos.setText("40");
		panelEstatisticas.add(textQuantidadesJogos);
		textQuantidadesJogos.setColumns(10);
		
		JLabel lblQuantidadeDeJogos = new JLabel("Quantidade de jogos a serem analisados: ");
		lblQuantidadeDeJogos.setBounds(180, 9, 250, 14);
		panelEstatisticas.add(lblQuantidadeDeJogos);
		
		String[] colunas = {"Repetidos", "Pares", "Primos", "Fibonacci", "Quadrado", "Multiplos de Três", "Dez Mais", "Linhas", "Colunas"};
		tableEstatisticas = new JTable();
		tableEstatisticas.setSurrendersFocusOnKeystroke(true);
		tableEstatisticas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model  = new EstatisticasTableModel();
		tableEstatisticas.setModel(model);
		tableEstatisticas.setBounds(3, 33, 871, 584);
		tableEstatisticas.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
			panelEstatisticas.add(tableEstatisticas);
		
		JTabbedPane panelJogos = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Jogos", null, panelJogos, null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane}));
	}


	private class GerarEstatisticasAction extends AbstractAction {

		private static final long serialVersionUID = 306725624488076991L;
		public GerarEstatisticasAction () {
			putValue(NAME, "Gerar Estatística");
		}
		public void actionPerformed(ActionEvent e) {
			Estatisticas estatisticas = new Estatisticas();
			estatisticas.iniciarListas();
			try {
				if (textQuantidadesJogos.getText().length() > 0) {
					List<Estatisticas> listaEsatisticas = 	estatisticas.listaEstatisticasUltimosSorteio(Integer.parseInt(textQuantidadesJogos.getText()),true);
					model.addListaEstatisticas(listaEsatisticas);
					model.fireTableDataChanged();
					
				} else {
					 JOptionPane.showMessageDialog(null, "A quantidade de jogos deve ser informada.");
				}
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
