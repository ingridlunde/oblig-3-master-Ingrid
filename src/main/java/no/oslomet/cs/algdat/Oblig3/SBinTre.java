package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {

    public static void main (String [] args) {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) { tre.leggInn(verdi); }
        //førstePostorden();

    }
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    //Kildekoden fra programkode 5.2.1 a) i kompendiet til Ulf Uttersrud
    public boolean leggInn(T verdi) {
            Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

            Node<T> p = rot, q = null;               // p starter i roten
            int cmp = 0;                             // hjelpevariabel

            while (p != null)       // fortsetter til p er ute av treet
            {
                q = p;                                 // q er forelder til p
                cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
                p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
            }

            // p er nå null, dvs. ute av treet, q er den siste vi passerte

            p = new Node<>(verdi, q);                   // oppretter en ny node med en foreldrereferanse til forrige.

            if (q == null) rot = p;                  // p blir rotnode
            else if (cmp < 0) q.venstre = p;         // venstre barn til q
            else q.høyre = p;                        // høyre barn til q

            antall++;                                // én verdi mer i treet
            return true;                             // vellykket innlegging
        }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //Inspirasjon til løsning fra programkode 5.2.6 b) i kompendiet til Ulf Uttersrud
    public int antall(T verdi) {
        //Kaster unntak når verdien er null
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        //Inistierer den som skal returneres. Setter den til 0.
        // Den skal returnere 0 om arrayet er tomt.
        int antallLike = 0;

        //Starter i rotnoden
        Node<T> p = rot;


        //Lager en while-løkke for å iterere gjennom nodene og sjekker om verdien er der. Den går inn i
        //løkka når p!= null. Er den lik null, er vi i enden av en av grenene

        while (p != null) {
            //bruker komparator for å iterere gjennom SBtreet.
            int cmp = comp.compare(verdi, p.verdi);

            // Er verdi > 0 itererer komparatoren videre til høyre.
            if (cmp < 0) p = p.venstre;

            //er verdi mindre enn p.verdi iterere treet til venstre.
            else {
                //Hvis cmp ikke er < eller > er den lik og verdi og p.verdi er like.
                if (cmp == 0) antallLike++;
                p = p.høyre;
            }

        }
        //Returnerer hvor mange like verdier det er i treet.
        return antallLike;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        Objects.requireNonNull(p,"Noden kan ikke ha nullverdi");

        while (true) {
            if (p.venstre != null) p = p.venstre;
            else if(p.høyre != null) p = p.høyre;
            else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        Objects.requireNonNull(p," Noden kan ikke ha nullverdi");

        //p.forelder == null sjekker om p er den siste i postorden og er med i denne if-setningen fordi det blir
        // returnert null hvis p.forelder er null.
        // Hvis P.foreldre.høyre == null betyr det at den nestePostorden er p.foreldre.

        if (p.forelder == null || p.forelder.høyre == null || p.forelder.høyre == p)
            return p.forelder;
        else {
            //Hvis p ikke går inn i if testen må den sjekke om nestePostOrden for p.foreldre.høyre.
            return førstePostorden(p.forelder.høyre);
        }
    }


    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
