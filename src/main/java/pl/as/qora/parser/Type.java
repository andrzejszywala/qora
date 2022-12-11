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
 * typy znacznikow
 * @author Andrzej Szywała
 */
public enum Type {
    //poczatek bloku
    BLOCK,
    //poczatek informacji
    STATEMENT,
    //lista
    LIST,
    //parametr
    KEYWORD,
    //znacznik
    TOKEN,
    //dane surowe nie parsowane
    RAW
}
