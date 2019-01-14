package br.com.loteria.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.com.loteria.jogo.Gerador;
import br.com.loteria.jogo.Jogo;
import br.com.loteria.lotofacil.Estatisticas;
import br.com.loteria.lotofacil.Filtro;

public class Principal extends JFrame {

	private static final long serialVersionUID = -3798327672358307298L;
	private JPanel contentPane;
	private Action gerarEstatisticasAction = new GerarEstatisticasAction();
	private Action setarValoresPadroesAction = new SetarValoresPadroesAction();
	private Action gerarJogosAction = new GerarJogosAction();
	private JTable tableEstatisticas;
	private EstatisticasTableModel model;
	private Box boxRepetidos;
	private Box boxPares;
	private Box boxPrimos;
	private Box boxFibonacci;
	private Box boxQuadrado;
	private Box boxMultiplosDeTres;
	private Box boxDezMais;
	private Box boxLinhas;
	private Box boxColunas;
	
	
	
	List<String> listaPadraoRepetidos;
	List<String> listaPadraoPares;
	List<String> listaPadraoPrimos;
	List<String> listaPadraoFibonacci;
	List<String> listaPadraoQuadrado;
	List<String> listaPadraoMultiplosDeTres;
	List<String> listaPadraoDezMais;
	List<String> listaPadraoLinhas;
	List<String> listaPadraoColunas;
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
		setBounds(600, 600, 600, 600);
		setTitle("Moira");
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

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableEstatisticas
				.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		/////////////////////////////////////////////////////////

		JButton btnSetaValoresPadrao = new JButton("Marca valores padrões");
		btnSetaValoresPadrao.addActionListener(setarValoresPadroesAction);

		JButton btnGerarJogos = new JButton("Gerar Jogos");
		btnGerarJogos.addActionListener(gerarJogosAction);

		
		JPanel panelParametros = new JPanel();
		panelParametros.setLayout(new BoxLayout(panelParametros, BoxLayout.Y_AXIS));
		panelParametros.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.setLayout(new BoxLayout(panelBotoes, BoxLayout.X_AXIS));
		panelBotoes.setBorder(new EmptyBorder(new Insets(1, 1, 1, 1)));

		boxRepetidos = criaListCheckBox("Repetidos:    ",Arrays.asList("8", "9", "10"));
		boxRepetidos.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		
		listaPadraoRepetidos = new ArrayList<String>(Arrays.asList("9"));
		
		boxPares = criaListCheckBox("Pares:            ",Arrays.asList("5","6","7","8","9"));
		listaPadraoPares = new ArrayList<String>(Arrays.asList("5","6","7","8","9"));
		
		boxPrimos = criaListCheckBox("Primos:          ",Arrays.asList("4","5","6","7"));
		listaPadraoPrimos = new ArrayList<String>(Arrays.asList("4","5","6","7"));
		
		boxFibonacci = criaListCheckBox("Fibonacci:        ",Arrays.asList("2","3","4","5","6"));
		listaPadraoFibonacci = new ArrayList<String>(Arrays.asList("2","3","4","5","6"));
		
		boxQuadrado = criaListCheckBox("Quadrado:         ",Arrays.asList("8","9","10","11"));
		listaPadraoQuadrado = new ArrayList<String>(Arrays.asList("8","9","10","11"));
		
		boxMultiplosDeTres = criaListCheckBox("Multiplos de Três: ",Arrays.asList("3","4","5","6","7"));
		listaPadraoMultiplosDeTres = new ArrayList<String>(Arrays.asList("3","4","5","6","7"));
		
		boxDezMais = criaListCheckBox("Dez Mais: ",Arrays.asList("4","5","6","7","8"));
		listaPadraoDezMais = new ArrayList<String>(Arrays.asList("4","5","6","7","8"));
		
		panelBotoes.add(btnSetaValoresPadrao);
		panelBotoes.add(btnGerarJogos);
		
		
        panelParametros.add(boxRepetidos);

		panelParametros.add(boxPares);

		panelParametros.add(boxPrimos);

		panelParametros.add(boxFibonacci);

		panelParametros.add(boxQuadrado);

		panelParametros.add(boxMultiplosDeTres);

		panelParametros.add(boxDezMais);
		

		JPanel panelJogos = new JPanel();
		//panelJogos.setLayout(new GridLayout(10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(2,2,2,2);

        
        panelJogos.add(panelBotoes, gbc);
        gbc.gridy++;
		panelJogos.add(panelParametros, gbc);

		tabbedPane.addTab("Jogos", null, panelJogos, null);

		///////////////////////////////
		tabbedPane.setEnabledAt(0, true);

		tableEstatisticas.setBounds(3, 33, 871, 584);
		JScrollPane jsp = new JScrollPane(tableEstatisticas);
		jsp.setPreferredSize(new Dimension(1000, 500));

		JPanel panelEstatisticas = new JPanel(new BorderLayout());

		JButton btnGerarEstatisticas = new JButton("Gerar Estatística");
		btnGerarEstatisticas.setAction(gerarEstatisticasAction);

		panelEstatisticas.add(btnGerarEstatisticas, BorderLayout.NORTH);
		panelEstatisticas.add(jsp);

		tabbedPane.addTab("Estatísticas", null, panelEstatisticas, null);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { contentPane }));

	}

	public Box criaListCheckBox(String nome,List<String> lista) {

		Box box = Box.createHorizontalBox();
		box.add(new JLabel(nome));
		for (String l : lista) {
			box.add(new JCheckBox(l));
			box.getComponent(box.getComponents().length - 1).setName(l);
		}
		return box;

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

				JOptionPane.showMessageDialog(null, e1);
				e1.printStackTrace();
			}
		}

	}

	private class SetarValoresPadroesAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {

			int opcao = JOptionPane.showConfirmDialog(null, "Confirmar?");

			if (opcao == 0) {
				// repetidos
				for (Component cb : boxRepetidos.getComponents()) {
					if (listaPadraoRepetidos.contains(cb.getName())) {
						((JCheckBox) cb).setSelected(true);
					} else {
						((JCheckBox) cb).setSelected(false);
					}
				}
			}
		}

	}

	private class GerarJogosAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			// repetidos
			Filtro filtro = new Filtro();
			Gerador gerador = new Gerador();
			List<Integer> listaParametrosRepetidos = new ArrayList<Integer>();
			for (Component cb : boxRepetidos.getComponents()) {
				if (((JCheckBox) cb).isSelected()) {
				listaParametrosRepetidos.add(Integer.parseInt(cb.getName()));
				}
			}
			
			
			
			filtro.setParametrosRepetidos(listaParametrosRepetidos);
			
			try {
				List<Jogo> jogosFiltrados = gerador.listaJogosGerados(5, filtro);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		}

	}

}
