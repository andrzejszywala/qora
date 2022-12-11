package pl.as.qora.tree.entity;

import java.util.Collection;

public class WykonajSQLResult {

    private Collection<ZbiorDanych> zbioryDanych;
    private Collection<String> komunikaty;

    public WykonajSQLResult() {
    }

    public WykonajSQLResult(Collection<String> komunikaty) {
        super();
        this.komunikaty = komunikaty;
    }

    public WykonajSQLResult(Collection<ZbiorDanych> zbioryDanych, Collection<String> komunikaty) {
        super();
        this.zbioryDanych = zbioryDanych;
        this.komunikaty = komunikaty;
    }

    public Collection<ZbiorDanych> getZbioryDanych() {
        return this.zbioryDanych;
    }

    public void setZbioryDanych(Collection<ZbiorDanych> zbioryDanych) {
        this.zbioryDanych = zbioryDanych;
    }

    public Collection<String> getKomunikaty() {
        return this.komunikaty;
    }

    public void setKomunikaty(Collection<String> komunikaty) {
        this.komunikaty = komunikaty;
    }

}
