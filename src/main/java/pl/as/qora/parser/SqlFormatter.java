package pl.as.qora.parser;

import static pl.as.qora.parser.Stale.DEFAULT_TAB_WIDTH;
import static pl.as.qora.parser.Stale.isIdent;
import static pl.as.qora.parser.Stale.reservedWord;
import static pl.as.qora.parser.Stale.spaces;
import static pl.as.qora.parser.Type.KEYWORD;
import static pl.as.qora.parser.Type.LIST;
import static pl.as.qora.parser.Type.STATEMENT;
import static pl.as.qora.parser.Type.TOKEN;

import java.util.List;
import java.util.ListIterator;

public class SqlFormatter {
    Settings settings = new Settings(true, false, false, false, true, true, true, 4, 60);

    String addComment(String old, String comment) {
        String ret = old;
        if ((ret != null) && (comment != null) && !ret.isEmpty() && !comment.isEmpty()) {
            ret += ("\n");
        }
        ret += comment == null ? "" : comment;
        return ret;
    }

    int currentColumn(String txt) {
        int pos = txt.lastIndexOf("\n");
        if (pos < 0) {
            pos = 0;
        } else {
            pos++;
        }

        int level = 0;
        while (pos < txt.length()) {
            char c = txt.charAt(pos);
            if (c == '\n') {
                level = 0;
            } else if (c == '\t') {
                level = ((level / DEFAULT_TAB_WIDTH) + 1) * DEFAULT_TAB_WIDTH;
            } else {
                level++;
            }
            pos++;
        }
        return level;
    }

    //formatuje wyglad polecenia polecenie
    //stat - polecenie do sformatowania
    //level - poziom akapitu
    //zwracany jest sformatowany tekst
    String indentStatement(Statement stat, int level) {
        String ret = "";

        switch (stat.type) {
        default:
            throw new RuntimeException("Wewnętrzny błąd parsera skontaktuj się z administratorem systemu :)");
        case BLOCK: {
            ret = indentComment(level, 0, stat.comment, false);
            int exc = 0;
            ListIterator<Statement> iterator = stat.getSubTokens().listIterator();
            while (iterator.hasNext()) {
                Statement i = iterator.next();
//                Statement j = iterator.next();
//                iterator.previous();

                int add = 0;
                if ((i != stat.getSubTokens().get(0)) && iterator.hasNext()) {
                    add = settings.indentLevel;
                } else {
                    exc = 0;
                }

                String t = "";
                if (i.getSubTokens().get(0) != i.getSubTokens().get(i.getSubTokens().size() - 1)) {
                    t = i.getSubTokens().get(0).string.toUpperCase();
                }
                if (t.equals("BEGIN") || t.equals("WHEN") || t.equals("ELSE") || t.equals("ELSIF")) {
                    add = 0;
                }
                if (i.type == Type.LIST) {
                    ret += indentString(level + add + exc);
                }
                ret += indentStatement(i, level + add + exc);
                if (i.type == Type.LIST) {
                    int idx;
                    for (idx = ret.length() - 1; (idx >= 0) && spaces.contains(ret.charAt(idx)); idx--) {
                        ;
                    }
                    ret = ret.substring(1, Math.max(idx + 1, 1));
                    ret += ("\n");
                    ret += indentString(level + exc);
                }
                if (t.equals("EXCEPTION")) {
                    exc = settings.indentLevel * 2;
                }
            }
            if (settings.endBlockNewline && (level != 0)) {
                ret += ("\n");
            }
        }
            break;
        case LIST:
        case STATEMENT:
            int maxlev = 0;
            int maxlevorig = 0;
            boolean useMaxLev = false;
            boolean any = true;
            int current;
            boolean first;
            boolean noKeyBreak = false;
            boolean lineList = false;
            String comment = "";
            if (stat.type == STATEMENT) {
                ret = indentComment(level, 0, stat.comment, false);
                useMaxLev = true;
                first = true;
                current = 0;
            } else {
                for (Statement i : stat.getSubTokens()) {
                    if (i.type != KEYWORD) {
                        noKeyBreak = true;
                    } else {
                        useMaxLev = true;
                    }
                    break;
                }
                current = level;
                first = true;
            }
            if (useMaxLev) {
                int count = 0;
                for (Statement i : stat.getSubTokens()) {
                    if (any) {
                        String upp = i.string == null ? "" : i.string.toUpperCase();
                        if ((i.type == KEYWORD) &&
                            !upp.equals("DO") &&
                            !upp.equals("THEN") &&
                            !upp.equals("AS") &&
                            !upp.equals("IS")) {
                            if ((i.string.length() + 1) > maxlev) {
                                maxlev = i.string.length() + 1;
                            }
                            count++;
                            any = false;
                        } else if (i == stat.getSubTokens().get(0)) {
                            noKeyBreak = true;
                            break;
                        }
                    } else if (i.type == TOKEN) {
                        any = true;
                    }
                    if (i.type == LIST) {
                        count++;
                    }
                }
                if ((count <= 1) && (maxlev > 0)) {
                    maxlev--;
                }
                maxlevorig = maxlev;
                any = true;
            }

            for (Statement i : stat.getSubTokens()) {
                comment = addComment(comment, i.comment);
                String upp = i.string == null ? "" : i.string.toUpperCase();

                if (i.type == LIST) {
                    if (settings.operatorSpace) {
                        ret += (" ");
                        current++;
                    }
                    String t = indentStatement(i, current);
                    if (t.indexOf("\n") > 0) {
                        current = currentColumn(t);
                    } else {
                        current += currentColumn(t);
                    }
                    ret += t;
                    any = true;
                } else if (i.string.equals(",")) {
                    if (settings.commaBefore) {
                        ret += indentComment(settings.commentColumn, current, comment, true);
                        comment = "";
                        ret += indentString((level + maxlev) - (settings.operatorSpace ? 2 : 1));
                        ret += (",");
                    } else {
                        ret += (",");
                        ret += indentComment(settings.commentColumn, current + 1, comment, true);
                        comment = "";
                        ret += indentString(level + maxlev);
                    }
                    current = level + maxlev;
                    any = false;
                    lineList = true;
                } else if ((i.type == KEYWORD) && (upp.equals("LOOP") ||
                    upp.equals("DO") ||
                    upp.equals("THEN") ||
                    upp.equals("AS") ||
                    upp.equals("IS"))) {
                    if (!settings.blockOpenLine) {
                        if (ret.length() > 0) {
                            if (isIdent(ret.charAt(ret.length() - 2)) ||
                                (ret.charAt(ret.length() - 1) == '\"') ||
                                (ret.charAt(ret.length() - 1) == '\'') ||
                                settings.operatorSpace) {
                                ret += (" ");
                                current++;
                            }
                        }
                        ret += settings.keywordUpper ? i.string.toUpperCase() : i.string;
                        current += i.string.length();
                    } else {
                        ret += indentComment(settings.commentColumn, current, comment, true);
                        comment = "";
                        ret += indentString(level);
                        ret += settings.keywordUpper ? i.string.toUpperCase() : i.string;
                        current = level + i.string.length();
                    }
                    any = false;
                } else if (any && (i.type == KEYWORD) && !noKeyBreak) {
                    if (first) {
                        first = false;
                    } else {
                        ret += indentComment(settings.commentColumn, current, comment, true);
                        current = 0;
                        comment = "";
                    }
                    if (current == 0)
                    {
                        ret += indentString(level);
                        current = level;
                    } else {
                        while (current < level) {
                            ret += (" ");
                            current++;
                        }
                    }
                    maxlev = maxlevorig;
                    String word = settings.keywordUpper ? i.string.toUpperCase() : i.string;
                    if (ret.length() > 0) {
                        if (settings.rightSeparator) {
                            for (int idx = word.length(); idx < (maxlev - 1); idx++) {
                                word = " " + word;
                            }
                        } else {
                            for (int idx = word.length(); idx < (maxlev - 1); idx++) {
                                word = word + " ";
                            }
                        }
                        ret += word;
                        current = level + Math.max(word.length(), maxlev - 1);
                    } else {
                        ret += word;
                        current = level + word.length();
                    }
                    any = false;
                    lineList = false;
                } else {
                    String t = i.string;
                    boolean add = false;
                    if (i.type == KEYWORD) {
                        if (!lineList && !any && (i.type == KEYWORD) &&
                            !noKeyBreak && upp.equals("BY")) {
                            add = true;
                        }
                    } else {
                        any = true;
                    }
                    if (reservedWord(upp) && settings.keywordUpper) {
                        t = upp;
                    }

                    if (first) {
                        first = false;
                        any = false;
                    } else {
                        if ((ret.length() > 0) &&
                            !spaces.contains(ret.charAt(ret.length() - 1)) &&
                            (settings.operatorSpace || ((isIdent(t.charAt(0)) ||
                                (t.charAt(0) == '\"') || (t.charAt(0) == '\'')) &&
                            (isIdent(ret.charAt(ret.length() - 1)) ||
                                (ret.charAt(ret.length() - 1) == '\"') ||
                            (ret.charAt(ret.length() - 1) == '\''))
                            )
                            )) {
                            if (!t.equals(";") &&
                                !t.equals(".") &&
                                (ret.charAt(ret.length() - 1) != '.') &&
                                (current != 0)) {
                                current++;
                                ret += (" ");
                            }
                        } else if ((ret.length() > 2) && (ret.charAt(ret.length() - 2) == '*') && (ret.charAt(ret.length() - 1) == '/')) {
                            current++;
                            ret += (" ");
                        }
                    }
                    if (current < (level + maxlev)) {
                        if (current == 0) {
                            ret += indentString(level + maxlev);
                        } else {
                            while (current < (level + maxlev)) {
                                ret += (" ");
                                current++;
                            }
                        }
                        current = level + maxlev;
                    }
                    ret += t;
                    current += t.length();
                    if (t.startsWith("<<")) {
                        ret += ("\n");
                        current = 0;
                    }

                    if (add) {
                        maxlev += t.length() + 1;
                    }
                }
            }
            if (stat.type == STATEMENT) {
                ret += indentComment(settings.commentColumn, current, comment, true);
                comment = "";
                if (settings.endBlockNewline &&
                    (level == 0) &&
                    (stat.getSubTokens().get(0) != stat.getSubTokens().get(stat.getSubTokens().size() - 1)) &&
                    (stat.getSubTokens().get(stat.getSubTokens().size() - 1)).string.equals(";")) {
                    ret += "\n";
                }
            } else if (!comment.isEmpty()) {
                ret += indentComment(settings.commentColumn, current, comment, true);
                comment = "";
                ret += indentString(level - (settings.operatorSpace ? 2 : 1));
            }
            break;
        }
        return ret;
    }

    //Robi akapit w tekscie
    //level - ilosc znakow wciecia
    String indentString(int level) {
        String ret = "";
        if (settings.expandSpaces) {
            for (int i = 0; i < (level / 8); i++) {
                ret += ("\t");
            }
            for (int j = 0; j < (level % 8); j++) {
                ret += (" ");
            }
        } else {
            for (int j = 0; j < level; j++) {
                ret += (" ");
            }
        }
        return ret;
    }

    //formatuje tekst (robi akapity)
    //str tekst do sformatowania
    //zwraca sformatowany tekst
    public String indent(String str) {
        Tokenizer tok = new Tokenizer(str, 0, 0);
        List<Statement> blk = new SqlParser().parse(tok);
        int pos = 1;
        int level = countIndent(str, pos);

        String ret = "";
        for (Statement i : blk) {
            ret += indentStatement(i, level);
        }
        pos = ret.length() - 1;
        while ((pos > 0) && spaces.contains(ret.charAt(pos))) {
            pos--;
        }
        return ret.substring(0, pos) + "\n";
    }

    //zlicza poziom wciecia w tekscie
    //sttxtr - tekst do sprawdzenia
    //chars - pozycja w tekscie
    //zwraca poziom wciecia
    int countIndent(String txt, int chars) {
        int level = 0;
        while (spaces.contains(txt.charAt(chars)) && (chars < txt.length())) {
            char c = txt.charAt(chars);
            if (c == '\n') {
                level = 0;
            } else if (c == ' ') {
                level++;
            } else if (c == '\t') {
                level = ((level / DEFAULT_TAB_WIDTH) + 1) * DEFAULT_TAB_WIDTH;
            }
            chars++;
        }
        return level;
    }

    String indentComment(int level, int current, String comment, boolean endNl) {
        boolean nl = true;
        String ret = "";
        if ((comment != null) && (comment.length() > 0)) {
            if ((level <= current) && ((level > 0) || (current > 0))) {
                ret += ("\n");
                current = 0;
            }
            for (int i = 0; i <= (comment.length() - 1); i++) {
                if (!nl || !spaces.contains(comment.charAt(i))) {
                    if (nl) {
                        if (current == 0) {
                            ret += indentString(level);
                        } else {
                            while (current < level) {
                                ret += (" ");
                                current++;
                            }
                        }
                        if (comment.charAt(i) == '*') {
                            ret += " ";
                            current++;
                        }
                        nl = false;
                    }
                    ret += comment.charAt(i);
                    if (comment.charAt(i) == '\n') {
                        current = 0;
                        nl = true;
                    } else {
                        nl = false;
                    }
                }
            }
            if (!nl) {
                ret += ("\n");
            }
        }
        else if (endNl) {
            ret = ("\n");
        }

        return ret;
    }
}
