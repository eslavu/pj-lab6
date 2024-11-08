package problema;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Float.valueOf;

public class Angajat
{
    private String nume, post;
    private LocalDate data_angajare;
    private Float salariu;

    public Angajat()
    {
        this.nume = "N/A";
        this.post = "N/A";
        this.data_angajare = LocalDate.parse("0000-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
        this.salariu = valueOf(0);
    }
    public Angajat(String nume, String post, LocalDate data_angajare, Float salariu)
    {
        this.nume = nume;
        this.post = post;
        this.data_angajare = data_angajare;
        this.salariu = salariu;
    }

    @Override
    public String toString()
    { return (this.nume + ", " + this.post + ", " + this.data_angajare + ", " + this.salariu); }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }

    public LocalDate getData_angajare() { return data_angajare; }
    public void setData_angajare(LocalDate data_angajare) { this.data_angajare = data_angajare; }

    public Float getSalariu() { return salariu; }
    public void setSalariu(Float salariu) { this.salariu = salariu; }
}