package br.com.loteria.frontEnd;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.com.loteria.lotofacil.Estatisticas;
import java.awt.Font;

public class Principal extends JFrame {

	private static final long serialVersionUID = -3798327672358307298L;
	private JPanel contentPane;
	private Action gerarEstatisticasAction = new GerarEstatisticasAction();
	private JTable tableEstatisticas;
	private EstatisticasTableModel model;
	private JLabel lblEstatisticasUltimosJogos;

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
		setBounds(1000, 1000, 1000, 1000);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - getHeight()) / 2);
		setLocation(x, y);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "tabbedPane");

		tableEstatisticas = new JTable();
		tableEstatisticas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tableEstatisticas.setSurrendersFocusOnKeystroke(true);
		model = new EstatisticasTableModel();
		tableEstatisticas.setModel(model);
		
		 DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)tableEstatisticas.getDefaultRenderer(Object.class);
	       renderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		tableEstatisticas.setBounds(3, 33, 871, 584);
		JScrollPane jsp = new JScrollPane(tableEstatisticas);
		jsp.setPreferredSize(new Dimension(1000, 500));

		JPanel panelEstatisticas = new JPanel(new BorderLayout());

		JButton btnGerarEstatisticas = new JButton("Gerar Estatística");
		btnGerarEstatisticas.setAction(gerarEstatisticasAction);

		panelEstatisticas.add(btnGerarEstatisticas, BorderLayout.NORTH);
		panelEstatisticas.add(jsp);
	
		tabbedPane.addTab("Estatísticas", null, panelEstatisticas, null);

		JTabbedPane panelJogos = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Jogos", null, panelJogos, null);

		tabbedPane.setEnabledAt(0, true);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { contentPane }));
	}

	private class GerarEstatisticasAction extends AbstractAction {

		private static final long serialVersionUID = 306725624488076991L;

		public GerarEstatisticasAction() {
			putValue(NAME, "Gerar Estatística");
		}

		public void actionPerformed(ActionEvent e) {
			Estatisticas estatisticas = new Estatisticas();
			estatisticas.iniciarListas();
			try {
				model.limpar();
				model.addListaEstatisticas(estatisticas.listaEstatisticasUltimosSorteio(50, true));
				model.fireTableDataChanged();
			} catch (NumberFormatException | IOException e1) {
				
				JOptionPane.showMessageDialog(null,e1);
				e1.printStackTrace();
			}
		}
	}
}
