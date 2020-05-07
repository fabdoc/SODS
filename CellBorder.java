package com.github.miachm.sods;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Property border for a cell
 * @author Fabrizio
 *
 */
public class CellBorder {
	/**
	 * kind of border
	 */
	public enum BorderStyle {
		solid, dotted, dashed, ridge, groove, inset,
		_double
	}

	private Double thin;
	private boolean top;
	private boolean bottom;
	private boolean left;
	private boolean right;
	private Color color;
	private BorderStyle style;
	
	public CellBorder() {
		// default constructor
		this.thin = ColumnStyle.getValue("0.035cm");
	    color = new Color(0,0,0);
	    top = true;
	    bottom = true;
	    left = true;
	    right = true;
	    style = BorderStyle.solid;
	}
	
	@Override
	public int hashCode() {
		int result = thin != null ? thin.hashCode() : 0;
        result = 31 * result + (top ? 1 : 0);
        result = 31 * result + (bottom ? 1 : 0);
        result = 31 * result + (left ? 1 : 0);
        result = 31 * result + (right ? 1 : 0);
        result = 31 * result + color.toString().hashCode();
        result = 31 * result + style.toString().hashCode();
        return result;
	}

	/**
	 * set witch side is painted
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 */
	public void setBorder(boolean top, boolean left, boolean bottom, boolean right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	/**
	 * set style for border
	 * @param style {@link BorderStyle}
	 */
	public void setStyle(BorderStyle style) {
		this.style = style;
	}
	
	public boolean isFull() {
		return top & bottom & left & right;
	}
	
	public double getTop() {
		return top ? 1 : 0;
	}

	public double getBottom() {
		return bottom ? 1 : 0;
	}
	
	public double getLeft() {
		return left ? 1 : 0;
	}

	public double getRight() {
		return right ? 1 : 0;
	}
	
	public BorderStyle getStyle() {
		return style;
	}
	
	public void setColor(java.awt.Color color) {
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue());
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setThin(Double thin) {
		this.thin = thin;
	}

	public void setThin(String thin) {
		this.thin = ColumnStyle.getValue(thin);
	}

	@Override
	public String toString() {
		BigDecimal t = new BigDecimal(thin);
		String res = t.setScale(2, RoundingMode.HALF_EVEN).toString() + "mm " + style.toString().replaceFirst("_","") + " " + color.toString();
		return res;
	}
}
