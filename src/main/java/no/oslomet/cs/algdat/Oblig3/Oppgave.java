package no.oslomet.cs.algdat.Oblig3;

@FunctionalInterface
public interface Oppgave<T>  // legges under hjelpeklasser
{
    void utførOppgave(T t);    // f.eks. utskrift til konsollet

    public static <T> Oppgave<T> konsollutskrift()  // en konstruksjonsmetode
    {
        return t -> System.out.print(t + " ");        // et lambda-uttrykk
    }

    public static <T> Oppgave<T> konsollutskrift(String format)
    {
        return t -> System.out.printf(format, t);
    }

}
