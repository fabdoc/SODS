package com.github.miachm.sods;

import java.util.HashMap;
import java.util.Map;

/**
 * This a class which represents the formatting of a cell (background color, font size, font style, etc...)
 */

public final class Style implements Cloneable {
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private Color fontColor;
    private Color backgroundColor;
    private int fontSize = -1;
    private boolean textWrap;
    private int textRotation = 0;
    private TEXT_POSITION alignment = null;
    
    /**
     * Constructs an empty-default Style.
     */
    public Style() {

    }

    public Style(boolean bold, boolean italic, boolean underline, java.awt.Color fontColor, java.awt.Color backgroundColor, int fontSize) {
    	this(bold, italic, underline,
    		 new Color(fontColor.getRed(), fontColor.getGreen(), fontColor.getBlue()),
    		 new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue()),
    		 fontSize);
    }

    public Style(boolean bold, boolean italic, boolean underline, Color fontColor, Color backgroundColor, int fontSize) {
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
        this.fontSize = fontSize;
    }

    /**
     * Determine if this has default rules or not
     *
     * @return True if the style is not modified.
     */
    public boolean isDefault()
    {
        return this.equals(new Style());
    }

    /**
     * Determines if the font has bold style or not.
     *
     * @return True if the font has bold style
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * Determines if the font has italic style or not.
     *
     * @return True if the font has italic style
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * Returns the font color.
     *
     * @return The font color. It can be null if it doesn't have a setted font color
     */
    public Color getFontColor() {
        return fontColor;
    }

    /**
     * Returns the cell background color.
     *
     * @return The background color. It can be null if it doesn't have a setted background color
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Returns the font size
     *
     * @return The font size. It will be -1 if it doesn't have a setted font size
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * Set the background color
     *
     * @param backgroundColor The background color of the cell. It can be null.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Set the font color
     *
     * @param fontColor The font color of the cell. It can be null.
     */
    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    /**
     * Set a bold font style
     *
     * @param bold True for sets a bold style font.
     */
    public void setBold(boolean bold)
    {
        this.bold = bold;
    }

    /**
     * Set a italic font style
     *
     * @param italic True for sets a italic style font.
     */
    public void setItalic(boolean italic)
    {
        this.italic = italic;
    }

    /**
     * Returns if the font has a underline style or not
     *
     * @return Returns true if the font has an underline style
     */
    public boolean isUnderline() {
        return underline;
    }

    /**
     * Set a underline font style
     *
     * @param underline True for sets a underline style font.
     */
    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    /**
     * Sets the font size
     *
     * @param fontSize The font size to set. It must be greater of -1, a -1 value indicates no font size.
     * @throws IllegalArgumentException if the font size is less of -1
     */
    public void setFontSize(int fontSize) {
        if (fontSize < -1)
            throw new IllegalArgumentException("Error, font size can be less of -1");
        this.fontSize = fontSize;
    }

    public enum TEXT_POSITION {
    	Left, Center, Right
    }

    /**
     * set text position
     * @param p {@link TEXT_POSITION} Left, Center, Right
     */
    public void setTextPosition (TEXT_POSITION p) {
    	alignment = p;
    }

    /**
     * get text position for write file
     * @return String
     */
    String getTextAlignment() {
    	if(alignment == null) return null;
    	if(alignment == TEXT_POSITION.Left)
    		return "start";
    	if(alignment == TEXT_POSITION.Right)
    		return "end";
    	return TEXT_POSITION.Center.toString().toLowerCase();
    }

    /**
     * get text position for style
     * @return String
     */
    public String getTextPosition() {
    	if(alignment == null) return null;
		return alignment.toString().toLowerCase();
	}

    public boolean isTextWrap() {
		return textWrap;
	}

    public void setTextWrap(boolean wrap) {
    	this.textWrap = wrap;
    }
    
    public int getTextRotation() {
		return textRotation;
	}

    public void setTextRotation(int textRotation) {
    	if(textRotation < -359 || textRotation > 359)
    		throw new IllegalArgumentException("Value must be > -360 and < 360: you set "+textRotation);
		this.textRotation = textRotation;
	}

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Style style = (Style) o;

        if (bold != style.bold) return false;
        if (italic != style.italic) return false;
        if (underline != style.underline) return false;
        if (fontSize != style.fontSize) return false;
        if (textWrap != style.textWrap) return false;
        if (fontColor != null ? !fontColor.equals(style.fontColor) : style.fontColor != null) return false;
        if(alignment != null ?  !alignment.equals(style.alignment) : style.alignment != null) return false;
        if(textRotation != style.textRotation) return false;
        return backgroundColor != null ? backgroundColor.equals(style.backgroundColor) : style.backgroundColor == null;
    }

    @Override
    public int hashCode() {
        int result = (bold ? 1 : 0);
        result = 31 * result + (italic ? 1 : 0);
        result = 31 * result + (underline ? 1 : 0);
        result = 31 * result + (fontColor != null ? fontColor.hashCode() : 0);
        result = 31 * result + (backgroundColor != null ? backgroundColor.hashCode() : 0);
        result = 31 * result + fontSize;
        result = 31 * result + (textWrap ? 1 : 0);
        result = 31 * result + textRotation;
        result = result + (alignment == TEXT_POSITION.Left ? 1 : alignment == TEXT_POSITION.Center ? 2 : 3);
        return result;
    }

    /**
     * Returns a Map representing this class as css-styles.
     * For example, if you setted a bold font and a italic font. You will get a Map with 2 keys:
     *
     * font-weight = bold
     * font-style  = italic
     *
     * @return A map with the CSS representation of this class
     */
    public Map<String, String> getCssStyles()
    {
        Map<String, String> result = new HashMap<>();
        if (isBold())
            result.put("font-weight", "bold");

        if (isItalic())
            result.put("font-style", "italic");

        if (isUnderline())
            result.put("text-decoration", "underline");

        if (getFontSize() != -1)
            result.put("font-size", "" + getFontSize());

        if (getFontColor() != null)
            result.put("color", "" + getFontColor().toString() + ";");

        if (getBackgroundColor() != null)
            result.put("background-color", getBackgroundColor().toString());

        if(alignment != null)
        	result.put("text-align", getTextPosition());
        
        if (isTextWrap())
        	result.put("wrap", "wrap");

        if(textRotation != 0)
        	result.put("rotation-angle", String.valueOf(textRotation));
        return result;
    }

    /**
     * Returns a String of this class in a CSS way.
     * For example, if you setted a bold font and a italic font. You will get:
     *
     * font-weight: bold;
     * font-style: italic;
     *
     * @return A String with the CSS representation of this class
     */
    @Override
    public String toString()
    {
        return toString("");
    }

    /**
     * Overloaded method of toString(), it allows a slight customization in the output.
     *
     * <pre>
     *     style.setBold(true);
     *     style.setItalic(true);
     *     style.toString("-fx-");
     *
     *     // It returns:
     *     //
     *     // -fx-font-weight: bold;
     *     // -fx-font-style: italic;
     * </pre>
     *
     * @param prefix a prefix to add in every CSS key. For example: "-fx-"
     * @return A String with the CSS representation of this class
     */
    public String toString(String prefix)
    {
        String result = "";
        Map<String,String> styles = getCssStyles();
        for (Map.Entry<String,String> style : styles.entrySet())
            result += prefix + style.getKey() + ": " + style.getValue() + ";\n";

        return result;
    }
}
