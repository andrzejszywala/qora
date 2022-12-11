package pl.as.qora.parser;

public class StatementPrinter {
    //towrzy drzewo rozbioru syntaktycznego dla polecenia (do debagowania)
    public StringBuilder printStatement(Statement stat, int level, StringBuilder t) {
        for (int i = 0; i < level; i++) {
            t.append("  ");
        }

        switch (stat.type) {
        case BLOCK:
            t.append("Block:");
            break;
        case STATEMENT:
            t.append("Statement:");
            break;
        case LIST:
            t.append("List:");
            break;
        case KEYWORD:
            t.append("Keyword:");
            break;
        case TOKEN:
            t.append("Token:");
            break;
        case RAW:
            t.append("Raw:");
            break;
        }
        t.append("\"" + stat.string + "\" (" + stat.line + ")\n");

        if ((stat.comment != null) && !stat.comment.isEmpty()) {
            for (int i = 0; i < level; i++) {
                t.append(" ");
            }
            t.append("Komentarz: " + stat.comment + "\n");
        }
        for (Statement statement : stat.getSubTokens()) {
            printStatement(statement, level + 1, t);
        }
        return t;
    }
}
