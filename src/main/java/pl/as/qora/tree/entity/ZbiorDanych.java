package pl.as.qora.tree.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ZbiorDanych implements Serializable {

    private static final long serialVersionUID = -1483875368939925539L;

    private Collection<String> kolumny;
    private List<Wiersz> dane;

    public ZbiorDanych() {
    }

    public ZbiorDanych(Collection<String> kolumny, List<Wiersz> dane) {
        super();
        this.kolumny = kolumny;
        this.dane = dane;
    }

    public Collection<String> getKolumny() {
        if (kolumny == null) {
            kolumny = new ArrayList<String>();
        }
        return kolumny;
    }

    public List<Wiersz> getDane() {
        if (dane == null) {
            dane = new ArrayList<Wiersz>();
        }
        return dane;
    }
}
