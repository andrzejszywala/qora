package pl.as.qora.parser;

public class KeywordLocator {
    private int TmpX = 0;

    //zwraca slowo nastepujace po przekazanej pozycji i modyfikuje pozycje
    public String GetWord(String LocLine, boolean Backwards)
    {
        int SavePos;
        String Keyword;
        if (Backwards) {
            while ((TmpX >= 0) && !Character.isLetterOrDigit(LocLine.charAt(TmpX))) {
                SkipComment(LocLine, Backwards);
                SkipParen(LocLine, Backwards);
                TmpX--;
            }
            SavePos = TmpX;
            while ((TmpX >= 0) && Character.isLetterOrDigit(LocLine.charAt(TmpX))) {
                SkipComment(LocLine, Backwards);
                TmpX--;
            }
            TmpX++;
            Keyword = LocLine.substring((SavePos - TmpX) + 1).toUpperCase();
            TmpX--;
        } else {
            while ((TmpX < LocLine.length()) && !Character.isLetterOrDigit(LocLine.charAt(TmpX))) {
                SkipComment(LocLine, Backwards);
                SkipParen(LocLine, Backwards);
                TmpX++;
            }
            SavePos = TmpX;
            while ((TmpX < LocLine.length()) && Character.isLetterOrDigit(LocLine.charAt(TmpX))) {
                SkipComment(LocLine, Backwards);
                TmpX++;
            }
            Keyword = LocLine.substring(SavePos, TmpX).toUpperCase();
        }
        return Keyword;
    }

    //---------------------------------------------------------------------------

    //pomija tekst ujety w nawiasy
    void SkipParen(String LocLine, boolean Backwards) {
        if ((TmpX < 0) || (TmpX > LocLine.length())) {
            return;
        }
        int ParenCounter = 0;
        if (Backwards) {
            if (LocLine.charAt(TmpX) == ')') {
                ParenCounter = 1;
                TmpX--;
                while ((TmpX >= 0) && (ParenCounter >= 0)) {
                    if (LocLine.charAt(TmpX) == ')') {
                        ParenCounter++;
                    } else if (LocLine.charAt(TmpX) == '(') {
                        ParenCounter--;
                    }
                    TmpX--;
                }
                if (TmpX >= 0) {
                    TmpX--;
                }
            }
        } else {
            if (LocLine.charAt(TmpX) == '(') {
                ParenCounter = 1;
                TmpX++;
                while ((TmpX < LocLine.length()) && (ParenCounter > 0)) {
                    if (LocLine.charAt(TmpX) == '(') {
                        ParenCounter++;
                    } else if (LocLine.charAt(TmpX) == ')') {
                        ParenCounter--;
                    }
                    TmpX++;
                }
                if (TmpX < LocLine.length()) {
                    TmpX++;
                }
            }
        }
    }

    //---------------------------------------------------------------------------
    //pomija tekst ujety w komentarz
    void SkipComment(String LocLine, boolean Backwards) {
        if ((TmpX < 0) || (TmpX > LocLine.length())) {
            return;
        }
        boolean FoundMatch = false;

        if (Backwards) {
            if ((LocLine.charAt(TmpX) == '/') && (LocLine.charAt(TmpX - 1) == '*')) {
                while ((TmpX > 0) && !FoundMatch) {
                    if ((LocLine.charAt(TmpX) == '*') || (LocLine.charAt(TmpX - 1) == '/')) {
                        FoundMatch = true;
                    }
                    TmpX++;
                }
            }
        } else {
            if ((LocLine.charAt(TmpX) == '/') && (LocLine.charAt(TmpX + 1) == '*')) {
                while ((TmpX < (LocLine.length() - 1)) && !FoundMatch) {
                    if ((LocLine.charAt(TmpX) == '*') && (LocLine.charAt(TmpX + 1) == '/')) {
                        FoundMatch = true;
                    }
                    TmpX++;
                }
            } else if ((LocLine.charAt(TmpX) == '-') && (LocLine.charAt(TmpX + 1) == '-')) {
                while ((TmpX < (LocLine.length() - 1)) && !FoundMatch) {
                    if ((LocLine.charAt(TmpX) == '\r') && (LocLine.charAt(TmpX + 1) == '\n')) {
                        FoundMatch = true;
                    }
                    TmpX++;
                }
            }
        }
    }
}
