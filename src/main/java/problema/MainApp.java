package problema;
import static problema.Json.citireJson;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.time.*;

public class MainApp
{
    public static void meniu()
    {
        System.out.println("1) afisare lista angajati");
        System.out.println("2) afisare angajati cu salariu peste 2500 lei");
        System.out.println("3) creare si afisare lista sefi/directori angajati din luna aprilie");
        System.out.println("4) afisare angajati fara functie de conducere, ordine descrescatoare a salariilor");
        System.out.println("5) extragere si afisare lista de stringuri UPPERCASE");
        System.out.println("6) afisare salarii sub 3000 lei");
        System.out.println("7) afisare primul angajat");
        System.out.println("8) afisare salariu mediu, salariu minim, salariu maxim");
        System.out.println("9) cautare angajati cu numele Ion");
        System.out.println("10) afisare numar de persoana angajate in vara anului precedent");
        System.out.println("0) iesire");
        System.out.println();
    }

    public static void main(String[] args)
    {
        List<Angajat> angajati = citireJson();

        Boolean running = true;
        Scanner scan = new Scanner(System.in);
        Integer optiune;
        while (running)
        {
            meniu();
            System.out.print("~~ optiunea ta: ");
            optiune = scan.nextInt();
            System.out.println();

            switch (optiune)
            {
                case 0:
                    angajati.clear();
                    running = false;
                    break;
                case 1:
                    System.out.println("~~ lista completa angajati:");
                    for (Angajat ent : angajati) System.out.println(ent.toString());
                    break;
                case 2:
                    System.out.println("~~ lista angajati cu salariu > 2500 lei:");
                    angajati.stream()
                            .filter((pred) -> (((Angajat)pred).getSalariu() > 2500))
                            .forEach(System.out::println);
                    break;
                case 3:
                    List<Angajat> lista_sefi = angajati.stream()
                            .filter((pred) -> (((Angajat)pred).getPost().contains("sef") || ((Angajat)pred).getPost().contains("director")))
                            .collect(Collectors.toList());
                    System.out.println("~~ lista sefi/directori angajati din luna aprilie:");
                    for (Angajat ent : lista_sefi) System.out.println(ent.toString());
                    lista_sefi.clear();
                    break;
                case 4:
                    System.out.println("~~ lista angajati fara functie de conducere:");
                    angajati.stream()
                            .filter((pred) -> (!((Angajat)pred).getPost().contains("sef") && !((Angajat)pred).getPost().contains("director")))
                            .forEach(System.out::println);
                    break;
                case 5:
                    List<String> nume_angajati = angajati.stream()
                            .map(Angajat::getNume)
                            .map(String::toUpperCase)
                            .collect(Collectors.toList());
                    System.out.println("~~ lista nume angajati in majuscule:");
                    for (String ent : nume_angajati) System.out.println(ent);
                    nume_angajati.clear();
                    break;
                case 6:
                    System.out.println("~~ salarii sub 3000 lei:");
                    angajati.stream()
                            .map(Angajat::getSalariu)
                            .filter((pred) -> (pred < 3000))
                            .forEach(System.out::println);
                    break;
                case 7:
                    Optional<LocalDate> minim = angajati.stream()
                            .map(Angajat::getData_angajare)
                            .min((pred1, pred2) -> (pred1.compareTo(pred2)));
                    if (minim.isPresent())
                    {
                        System.out.println("~~ primul angajat al firmei:");
                        System.out.println(angajati.stream()
                                .filter((pred) -> (((Angajat)pred).getData_angajare() == minim.get()))
                                .findFirst()
                                .get());
                    }
                    else System.out.println("~~ lista vida!");
                    break;
                case 8:
                    System.out.println("~~ statistici salarii angajati:");
                    System.out.println("salariu mediu: " + angajati.stream()
                            .collect(Collectors.summarizingDouble(Angajat::getSalariu)).getAverage());
                    System.out.println("salariu minim: " + angajati.stream()
                            .collect(Collectors.summarizingDouble(Angajat::getSalariu)).getMin());
                    System.out.println("salariu maxim: " + angajati.stream()
                            .collect(Collectors.summarizingDouble(Angajat::getSalariu)).getMax());
                    break;
                case 9:
                    Optional<Angajat> ion = angajati.stream()
                            .filter((pred) -> ((Angajat)pred).getNume().toUpperCase().contains("ION"))
                            .findAny();
                    ion.ifPresentOrElse((ent) -> {System.out.println("firma are cel putin un Ion angajat!");}, () -> {System.out.println("firma nu are nici un Ion angajat!");});
                    break;
                case 10:
                    Predicate<Angajat> vara = new Predicate<Angajat>()
                    {
                        @Override
                        public boolean test(Angajat ent)
                        {
                            if (ent.getData_angajare().getYear() != (LocalDate.now().getYear() - 1)) return false;
                            if (ent.getData_angajare().getMonthValue() < 6 || ent.getData_angajare().getMonthValue() > 8) return false;
                            return true;
                        }
                    };
                    System.out.println("~~ " + angajati.stream()
                            .filter(vara)
                            .count() + " persoane angajate in vara anului precedent!");
                    break;
                default:
                    System.out.println("~~ optiune invalida!");
                    break;
            }
            if (running)
            {
                try
                {
                    System.out.print("(apasati ENTER pentru a continua)");
                    System.in.read();
                    System.out.println();
                }
                catch (IOException e) { throw new RuntimeException(e); }
            }
        }
        scan.close();
    }
}
