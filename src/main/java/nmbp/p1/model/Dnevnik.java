package nmbp.p1.model;

import javax.persistence.*;
import java.sql.Timestamp;

@NamedNativeQueries({
        @NamedNativeQuery(name = "create.temp",
                query = "create temp table dani (dan timestamp, t text)"),
        @NamedNativeQuery(name = "insert.date",
                query = "insert into dani values (to_timestamp(:s, 'DDMMYYYY'), 'd' || :s);")
})
@Entity
public class Dnevnik {
    private int id;
    private String upit;
    private Timestamp datum;

    public Dnevnik(String upit, Timestamp datum) {
        this.upit = upit;
        this.datum = datum;
    }

    public Dnevnik() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "upit", nullable = false)
    public String getUpit() {
        return upit;
    }

    public void setUpit(String upit) {
        this.upit = upit;
    }

    @Basic
    @Column(name = "datum", nullable = false)
    public Timestamp getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dnevnik dnevnik = (Dnevnik) o;

        if (id != dnevnik.id) return false;
        if (upit != null ? !upit.equals(dnevnik.upit) : dnevnik.upit != null) return false;
        return datum != null ? datum.equals(dnevnik.datum) : dnevnik.datum == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (upit != null ? upit.hashCode() : 0);
        result = 31 * result + (datum != null ? datum.hashCode() : 0);
        return result;
    }
}
