/* ************************************************************
 * Autorskie Prawa Majątkowe - COIG S.A.
 * 
 * Copyright 2013 COIG S.A.
 * ************************************************************
 * Utworzono 29-09-2013, Andrzej Szywała
 * ************************************************************
 */
package pl.as.qora.parser;

import static pl.as.qora.parser.Stale.isIdent;

/**
 *
 * @author Andrzej Szywała
 */
public class Tokenizer {
    enum State {
        SPACE,
        ANY,
        IDENTIFIER,
        STRING,
        COMMENT,
        LABEL,
        BIND_OPEN,
        BIND_CLOSE
    };

    int offset;
    int line;
    String string;

    public Tokenizer(String str) {
        this.line = 0;
        this.offset = 0;
        this.string = str;
    }

    public Tokenizer(String str, int offset, int line) {
        this.line = line;
        this.offset = offset;
        this.string = str;
    }

    //pobranie znacznika z tekstu
    //forward - czy szukamy do przadu czy do tylu
    //comment - czy komentarz ma byc traktowany jak znacznik
    public String getToken(boolean forward, boolean comments) {
        char c = 0;
        char nc = 0;
        char endString = 0;

        State state = State.SPACE;

        String token = "";

        int inc = forward ? 1 : -1;

        while ((forward && (offset < string.length())) || (!forward && (offset >= 1))) {
            if (!forward) {
                offset--;
            }
            c = string.charAt(offset);
            if (c == '\n') {
                line++;
            }
            if ((forward && (offset < (string.length() - 1))) || (!forward && (offset > 0))) {
                nc = string.charAt(offset + inc);
            } else {
                nc = '\n';
            }
            if (state == State.SPACE) {
                if (forward && (c == '-') && (nc == '-')) {
                    int spos = offset;
                    if (forward) {
                        for (offset++; (offset < string.length()) && (string.charAt(offset) != '\n'); offset++) {
                            ;
                        }
                    }
                    if (comments) {
                        return string.substring(spos, offset - spos);
                    }
                    continue;
                }
                if ((c == '/') && (nc == '*')) {
                    state = State.COMMENT;
                } else if ((forward && (c == '<') && (nc == '<')) ||
                    (!forward && (c == '>') && (nc == '>'))) {
                    state = State.LABEL;
                } else if (!Stale.spaces.contains(c)) {
                    state = State.ANY;
                }
            }

            if (forward) {
                offset++;
            }

            if (state != State.SPACE) {
                if (forward) {
                    token += c;
                } else {
                    token = nc + token;
                }
                switch (state) {
                case COMMENT:
                    if ((c == '*') && (nc == '/')) {
                        if (forward) {
                            token += nc;
                        } else {
                            token = nc + token;
                        }
                        offset += inc;
                        if (comments) {
                            return token;
                        } else {
                            state = State.SPACE;
                            token = "";
                        }
                    }
                    break;
                case LABEL:
                    if ((forward && (c == '>') && (nc == '>')) ||
                        (!forward && (c == '<') && (nc == '<'))) {
                        if (forward) {
                            token += nc;
                        } else {
                            token = nc + token;
                        }
                        offset += inc;
                        return token;
                    }
                    break;
                case SPACE:
                    break;
                case BIND_OPEN:
                    if (!isIdent(nc)) {
                        if (nc == '<') {
                            state = State.BIND_CLOSE;
                        } else {
                            return token;
                        }
                    }
                    break;
                case BIND_CLOSE:
                    if (c == '>') {
                        return token;
                    }
                    break;
                case ANY:
                    if ((c == ':') && isIdent(nc)) {
                        state = State.BIND_OPEN;
                    } else if (isIdent(c)) {
                        if (!isIdent(nc)) {
                            return token;
                        }
                        state = State.IDENTIFIER;
                    } else if ((c == '\'') || (c == '\"')) {
                        endString = c;
                        state = State.STRING;
                    } else {
                        for (int i = 0; Stale.operators[i] != null; i++) {
                            if ((forward && (c == Stale.operators[i].charAt(0)) && (nc == Stale.operators[i].charAt(1))) ||
                                (!forward && (nc == Stale.operators[i].charAt(0)) && (c == Stale.operators[i].charAt(1)))) {
                                if (forward) {
                                    token += nc;
                                } else {
                                    token = nc + token;
                                }
                                offset += inc;
                                break;
                            }
                        }
                        return token;
                    }
                    break;
                case IDENTIFIER:
                    if (!isIdent(nc)) {
                        return token;
                    }
                    break;
                case STRING:
                    if (c == endString) {
                        if (nc == endString) {
                            if (forward) {
                                token += nc;
                                offset++;
                            } else {
                                token = nc + token;
                                offset--;
                            }
                        } else {
                            return token;
                        }
                    }
                    break;
                }
            }
        }
        return token;
    }

    //zwraca bierzacy numer linii
    int getLine() {
        return line;
    }

    //zwraca pozycje za znacznikiem
    int getOffset() {
        return offset;
    }

    //ustawia pozycje przesuniecia
    void setOffset(int offset) {
        this.offset = offset;
    }

    //ustawia nowy numer linii
    void setLine(int line) {
        this.line = line;
    }

    //zwraca dane po bierzacej pozycji
    //eol - czy do konca linii
    public String remaining(boolean eol) {
        String ret;
        String tmp = string.substring(offset, string.length());
        if (eol) {
            int pos = tmp.indexOf("\n");
            if ((pos < 0) || (pos > (string.length() - 1))) {
                pos = offset;
            }
            ret = string.substring(offset, pos);
            offset += pos;
        } else {
            ret = string.substring(offset, string.length());
            offset = string.length();
        }
        return ret;
    }
}
