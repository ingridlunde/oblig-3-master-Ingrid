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

        if ( p == null) throw new NullPointerException("p kan ikke være null");

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

    //hentet fra kompendie 5.1.15 Kompendiet til Ulf Uttersrud
    public void postorden(Oppgave<? super T> oppgave) {


        if (tom()) return; //treet er tomt

        Node<T> p = rot; //Starter fra roten for å jobbe meg ut til første postorden

        while (true) { //Iterere fra rot til første postorden. (Rot kommer til sist i postorden).
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
                //Bryter her fordi vi har kommet til første postorden når den er gått så langt den skal til
                // venstre og det ikke finnes en høyrenode
            else break;
        }
        oppgave.utførOppgave(p.verdi);

        //Lager en whileløkke for å kjøre nestepostorden for å få neste og neste
        while(true) {
            p = nestePostorden(p);
            //Når p er null etter nestePostorden er kjørt bryter den.
            if (p == null) break;
            oppgave.utførOppgave(p.verdi);

        }

    }

    //Hentet inspirasjon fra kompendiet til Ulf Uttersrud programkode 5.1.7
    public void postordenRecursive(Oppgave<? super T> oppgave) {
        if (rot!= null) postorden(oppgave);  // sjekker om treet er tomt

    }

    //Hentet inspirasjon fra kompendiet til Ulf Uttersrud programkode 5.1.7
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        //Hvis venstre ikke er null. Da kjøres metoden postorden og tar inn oppgave.
        if (p.venstre != null) postordenRecursive(p.venstre, oppgave);
        //Hvis høyre ikke er null. Kjøres metoden postorden og tar inn oppgave.
        if (p.høyre != null) postordenRecursive( p.høyre, oppgave);

        //Deretter skrives det til skjerm.
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
