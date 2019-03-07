package kr.co.sist.sc.user.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class CustomTableRenderer extends JLabel implements TableCellRenderer {

	public CustomTableRenderer() {
		setForeground(Color.WHITE);
		setOpaque(false);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value.toString());
		
        if (isSelected) {
            // this cell is the anchor and the table has the focus
            setBackground(new Color(18, 52, 120));
            setOpaque(true);
        }else {
        	setOpaque(false);
        }
		return this;
	}

}
