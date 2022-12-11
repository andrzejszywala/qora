/* ************************************************************
 * Autorskie Prawa Majątkowe - COIG S.A.
 * 
 * Copyright 2013 COIG S.A.
 * ************************************************************
 * Utworzono 29-09-2013, Andrzej Szywała
 * ************************************************************
 */
package pl.as.qora.parser;

import static pl.as.qora.parser.Stale.reservedWord;
import static pl.as.qora.parser.Stale.spaces;
import static pl.as.qora.parser.Stale.toPop;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrzej Szywała
 */
public class SqlParser {

    public List<Statement> parse(String str) {
        return parse(new Tokenizer(str, 0, 0));
    }

    //zwraca liste stringow z poleceniami
    //tokens - dostarcza zrodlo z poleceniami
    public List<String> getStatements(Tokenizer tokens) {
        String tmp = "";
        List<String> ret = new ArrayList<String>();
        int beginStatement = 0;
        for (Statement cur = parseStatement(tokens, false, false); !cur.getSubTokens().isEmpty()
            && (cur.getSubTokens().get(0) != cur.getSubTokens().get(cur.getSubTokens().size() - 1)); cur = parseStatement(
            tokens, false, false)) {
            if (cur.type == Type.LIST) {
                throw new RuntimeException("Za dużo nawiasów ')'");
            }
            int x = tokens.getOffset();
            tmp = tokens.string.substring(beginStatement, x);

            if (!tmp.isEmpty()) {
                //usuniecie srednika z konca instrukcji
                if (cur.type == Type.STATEMENT) {
                    int i = tmp.length() - 1;
                    while ((i >= 0)) {
                        if (tmp.charAt(i) == ';') {
                            tmp = tmp.substring(0, i) + tmp.substring(i + 1);
                            break;
                        }
                        if (!spaces.contains(tmp.charAt(i))) {
                            break;
                        }
                        i--;
                    }
                }
                ret.add(tmp);
            }
            beginStatement = tokens.getOffset();
        }
        String str = tokens.remaining(false);
        if (!str.isEmpty()) {
            ret.add(str);
        }
        return ret;
    }

    //analizuje tekst
    //tokens - dostarcza dane do analizy
    //lista polecen
    public List<Statement> parse(Tokenizer tokens) {
        List<Statement> ret = new ArrayList<Statement>();
        for (Statement cur = parseStatement(tokens, false, false); !cur.getSubTokens().isEmpty()
            && (cur.getSubTokens().get(0) != cur.getSubTokens().get(cur.getSubTokens().size() - 1)); cur = parseStatement(
            tokens, false, false)) {
            if (cur.type == Type.LIST) {
                throw new RuntimeException("Za dużo nawiasów ')'");
            }
            ret.add(cur);
        }
        String str = tokens.remaining(false);
        if (!str.isEmpty()) {
            ret.add(new Statement(Type.RAW, str, tokens.getLine()));
        }
        return ret;
    }

    public Statement parseStatement(Tokenizer tokens, boolean declare, boolean lst) {
        Statement ret = new Statement(Type.STATEMENT);

        String first = "";
        String realfirst = "";
        boolean nokey = false;
        boolean block = false;
        for (String token = tokens.getToken(true, true); (token != null) && !token.isEmpty(); token = tokens.getToken(true, true)) {
            String upp = token.toUpperCase();

            if (first.isEmpty() && !token.startsWith("/*") && !token.startsWith("--") && !token.startsWith("//")) {
                realfirst = first = upp;
            }

            if (upp.equals("PROCEDURE") ||
                upp.equals("FUNCTION") ||
                upp.equals("PACKAGE")) {
                block = true;
            }

            if (!first.equals("END") && ((first.equals("IF") && upp.equals("THEN")) ||
                upp.equals("LOOP") ||
                upp.equals("DO") ||
                upp.equals("DECLARE") ||
                (block && upp.equals("AS")) ||
                (block && upp.equals("IS")) ||
                ((!declare || block) && upp.equals("BEGIN")))) {
                block = false;
                Statement blk = new Statement(Type.BLOCK);
                ret.getSubTokens().add(new Statement(Type.KEYWORD, token, tokens.getLine()));
                blk.getSubTokens().add(ret);
                Statement cur = new Statement(Type.STATEMENT);
                boolean dcl = (upp.equals("DECLARE") || upp.equals("IS") || upp.equals("AS"));
                do {
                    cur = parseStatement(tokens, dcl, false);
                    if (cur.type == Type.LIST) {
                        throw new RuntimeException("Za dużo nawiasów ')'");
                    }
                    blk.getSubTokens().add(cur);
                    if (!cur.getSubTokens().isEmpty() && "BEGIN".equalsIgnoreCase(cur.getSubTokens().get(0).string)) {
                        dcl = false;
                    }
                } while (!cur.getSubTokens().isEmpty() && !"END".equalsIgnoreCase(cur.getSubTokens().get(0).string));
                return blk;
            } else if (((first.equals("IF") && upp.equals("THEN")) ||
                (first.equals("WHEN") && upp.equals("THEN")) ||
                (first.equals("ELSIF") && upp.equals("THEN")) ||
                upp.equals("BEGIN") ||
                upp.equals("EXCEPTION") ||
                first.equals("ELSE")) && !lst) {
                ret.getSubTokens().add(new Statement(Type.KEYWORD, token, tokens.getLine()));
                return ret;
            } else if (first.equals("ASSIGN") ||
                first.equals("SET") ||
                first.equals("PROMPT") ||
                first.equals("COLUMN") ||
                first.equals("SPOOL") ||
                first.equals("STORE") ||
                first.equals("REMARK") ||
                first.equals("REM")) {
                ret.getSubTokens().add(new Statement(Type.KEYWORD, token, tokens.getLine()));
                int line = tokens.getLine();
                int offset = tokens.getOffset();
                for (String tmp = tokens.getToken(true, true); line == tokens.getLine(); tmp = tokens.getToken(true, true)) {
                    ret.getSubTokens().add(new Statement(Type.TOKEN, tmp, line));
                }
                tokens.setLine(line);
                tokens.setOffset(offset);
                tokens.remaining(true);
                return ret;
            } else if (upp.equals(",") || ((reservedWord(upp) &&
                !upp.equals("NOT") &&
                !upp.equals("IS") &&
                !upp.equals("LIKE") &&
                !upp.equals("IN") &&
                !upp.equals("ELSE") &&
                !upp.equals("ELSIF") &&
                !upp.equals("END") &&
                !upp.equals("BETWEEN") &&
                !upp.equals("ASC") &&
                !upp.equals("DESC") &&
                !upp.equals("NULL")) && !nokey)) {
                ret.getSubTokens().add(new Statement(Type.KEYWORD, token, tokens.getLine()));
                nokey = false;
            } else if (upp.equals("(")) {
                ret.getSubTokens().add(new Statement(Type.TOKEN, token, tokens.getLine()));
                Statement st = parseStatement(tokens, false, true);
                Statement t = toPop(st.getSubTokens());
                if (st.type != Type.LIST) {
                    throw new RuntimeException("Za dużo nawiasów '('");
                }
                nokey = false;
                if (first.equals("CREATE") && /*!*/block) {
                    Statement end = parseStatement(tokens, false, true);
                    Statement blk = new Statement(Type.BLOCK);
                    blk.getSubTokens().add(ret);
                    blk.getSubTokens().add(st);
                    end.getSubTokens().add(0, t);
                    blk.getSubTokens().add(end);
                    return blk;
                } else {
                    ret.getSubTokens().add(st);
                    ret.getSubTokens().add(t);
                }
            } else if (upp.equals(")")) {
                ret.type = Type.LIST;
                ret.getSubTokens().add(new Statement(Type.TOKEN, token, tokens.getLine()));
                return ret;
            } else if (upp.equals(";")) {
                ret.getSubTokens().add(new Statement(Type.TOKEN, token, tokens.getLine()));
                return ret;
            } else if (upp.startsWith("/*+") || upp.startsWith("--+")) {
                String com = token;
                if (com.startsWith("--+")) {
                    com = ("/*+ ") + com.substring(4, com.length()) + (" */");
                }
                ret.getSubTokens().add(new Statement(Type.TOKEN,
                    com, tokens.getLine()));
            } else if (upp.startsWith("/*") || upp.startsWith("--") || upp.startsWith("//")) {
                if (ret.getSubTokens().isEmpty()) {
                    if ((ret.comment == null) || ret.comment.isEmpty()) {
                        ret.comment = token;
                    } else {
                        ret.comment += ("\n") + token;
                    }
                } else {
                    String com = ret.getSubTokens().get(ret.getSubTokens().size() - 1).comment;
                    if ((com == null) || com.isEmpty()) {
                        com = token;
                    } else {
                        com += ("\n") + token;
                    }
                }
            } else {
                ret.getSubTokens().add(new Statement(Type.TOKEN, token, tokens.getLine()));
                nokey = (token.equals("."));
            }
            if (upp.equals("AS") || upp.equals("IS")) {
                first = upp;
            } else if (first.equals("IS") && upp.equals("NULL")) {
                first = realfirst;
            }
        }
        return ret;
    }

}
