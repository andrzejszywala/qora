/* ************************************************************
 * Autorskie Prawa Majątkowe - COIG S.A.
 * 
 * Copyright 2013 COIG S.A.
 * ************************************************************
 * Utworzono 29-09-2013, Andrzej Szywała
 * ************************************************************
 */
package pl.as.qora.parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrzej Szywała
 */
public class Statement {
    List<Statement> subTokens;
    Type type;

    //znacznik w komentarzu
    String comment;
    //biezacy znacznik
    String string;
    //nr linii od 0
    int line;

    //tworzenie polecenia
    Statement(Type ntype, String token, int cline) {
        type = ntype;
        string = token;
        line = cline;
    }

    Statement(Type ntype) {
        type = ntype;
        string = null;
        line = -1;
    }

    Statement() {
        type = Type.TOKEN;
        string = null;
        line = -1;
    }

    //konstruktor kopiujacy
    Statement(Statement stat) {
        type = stat.type;
        string = stat.string;
        comment = stat.comment;
        line = stat.line;
        if (stat.subTokens != null)
        {
            subTokens = stat.subTokens;
        } else {
            subTokens = null;
        }
    }

    //alokacja zetonow podrzednych i zwrocenie referencji do nich
    List<Statement> getSubTokens() {
        if (subTokens == null) {
            subTokens = new ArrayList<Statement>();
        }
        return subTokens;
    }
}
