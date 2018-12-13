package br.com.loteria.frontEnd;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.loteria.lotofacil.Estatisticas;

public class EstatisticasTableModel  extends AbstractTableModel {
   
	private static final long serialVersionUID = -1804256298978611846L;
	String[] columnNames = {"Repetidos", "Pares", "Primos", "Fibonacci", "Quadrado", "Multiplos de Três", "Dez Mais", "Linhas", "Colunas"};
    private List<Estatisticas> data;

    public EstatisticasTableModel(List<Estatisticas> data) {
        this.data = data;
    }
     
    public EstatisticasTableModel(){
     this.data = new ArrayList<Estatisticas>();
     addTableModelListener(new EstatisticasTableModelListener());
    }
  
    public int getRowCount() {
        return data.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
     
    @Override
    public String getColumnName(int columnIndex){
      return columnNames[columnIndex];
    }    
     
     @Override  
    public Class<?> getColumnClass(int columnIndex) {  
        return String.class;  
    }
   
     
    public void setData(List<Estatisticas> data) {  
    	this.data = data;  
   
    }
     
    @Override  
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
    	Estatisticas estatisticas = data.get(rowIndex);
   
    	fireTableCellUpdated(rowIndex, columnIndex);  
     }      
     
  
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Estatisticas estatisticas = data.get(rowIndex);
        String valueObject = null;
        switch(columnIndex){
            case 0: valueObject = estatisticas.getRepetidos(); break;
            case 1: valueObject = estatisticas.getPares(); break;
            case 2 : valueObject = estatisticas.getPrimos(); break;
            case 3 : valueObject = estatisticas.getFibonacci(); break;
            case 4 : valueObject = estatisticas.getQuadrado(); break;
            case 5 : valueObject = estatisticas.getMultiplosDeTres(); break;
            case 6 : valueObject = estatisticas.getDezMais(); break;
            case 7 : valueObject = estatisticas.getLinhas(); break;
            case 8 : valueObject = estatisticas.getColunas(); break;

            default: System.err.println("Índice inválido para propriedade do bean Estatistica.class");
        }
         
        return valueObject;
    }
     
    @Override  
    public boolean isCellEditable(int rowIndex, int columnIndex) {  
        return false;  
    }  
   
   
    public Estatisticas getEstatistica(int indiceLinha) {  
        return data.get(indiceLinha);  
    }  
     
    public void addEstatistica(Estatisticas u) {      
    
    	data.add(u);  
   
   
        int ultimoIndice = getRowCount() - 1;  
   
        fireTableRowsInserted(ultimoIndice, ultimoIndice);  
    }  
   
     
    public void removeEstatistica(int indiceLinha) {  
    	data.remove(indiceLinha);  
   
        fireTableRowsDeleted(indiceLinha, indiceLinha);  
    }  
     
     
    public void addListaEstatisticas(List<Estatisticas> data) {  
         
        int tamanhoAntigo = getRowCount();      
        this.data.addAll(data);    
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);  
    }  
     
    public void limpar() {  
    	data.clear();    
        fireTableDataChanged();  
    }  
   
    public boolean isEmpty() {  
        return data.isEmpty();  
    }  


}
