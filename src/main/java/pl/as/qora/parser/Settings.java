/* ************************************************************
 * Autorskie Prawa Majątkowe - COIG S.A.
 * 
 * Copyright 2013 COIG S.A.
 * ************************************************************
 * Utworzono 29-09-2013, Andrzej Szywała
 * ************************************************************
 */
package pl.as.qora.parser;

/**
 * ustawienia formatowania tekstu
 * @author Andrzej Szywała
 */
public class Settings {
    public boolean expandSpaces;
    public boolean commaBefore;
    public boolean blockOpenLine;
    public boolean operatorSpace;
    public boolean keywordUpper;
    public boolean rightSeparator;
    public boolean endBlockNewline;
    public int indentLevel;
    public int commentColumn;

    public Settings() {
    }

    public Settings(boolean expandSpaces, boolean commaBefore, boolean blockOpenLine, boolean operatorSpace, boolean keywordUpper, boolean rightSeparator, boolean endBlockNewline,
        int indentLevel, int commentColumn) {
        super();
        this.expandSpaces = expandSpaces;
        this.commaBefore = commaBefore;
        this.blockOpenLine = blockOpenLine;
        this.operatorSpace = operatorSpace;
        this.keywordUpper = keywordUpper;
        this.rightSeparator = rightSeparator;
        this.endBlockNewline = endBlockNewline;
        this.indentLevel = indentLevel;
        this.commentColumn = commentColumn;
    }

}
