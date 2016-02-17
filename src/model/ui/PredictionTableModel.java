package model.ui;

import javax.swing.table.AbstractTableModel;

/**
 * Created by pedro on 17/02/16.
 */
public class PredictionTableModel extends AbstractTableModel {

	private String[] columnNames = {"Real Value", "Predicted Value", "Prediction"};

	private Object[][] data;

	public PredictionTableModel(Object[][] data) {
	//	this.columnNames = columnNames;
		this.data = data;
	}

	@Override
	public int getRowCount() {
		return columnNames.length;
	}

	@Override
	public int getColumnCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
}
