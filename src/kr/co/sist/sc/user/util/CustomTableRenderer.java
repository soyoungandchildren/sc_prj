package kr.co.sist.sc.user.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class CustomTableRenderer extends JLabel implements TableCellRenderer {

	private static CustomTableRenderer ctr;
	
	public CustomTableRenderer() {
		setForeground(Color.WHITE);
		setOpaque(false);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(CustomFontList.getInstance().getFontLabel());
	}
	
	public static CustomTableRenderer applyRenderer() {
		if(ctr==null) {
			ctr = new CustomTableRenderer();
		}
		return ctr;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value.toString());
		
		
        if (isSelected) {
            setBackground(new Color(18, 52, 120));
            setOpaque(true);
        }else {
        	setOpaque(false);
        }
		return this;
	}

}
