# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Ingrid Lunde, S338231, s338231@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 kopierte jeg programkoden fra kompendiet først. Deretter måtte jeg endre der hvor det inistieres 
en ny node med verdi og en peker til foreldrenoen. 

I oppgave 2 så brukte jeg compare til å iterere gjennom treet for å telle verdiene. Startet med å inistiere 
variabelen antallLike til 0. Den skal returneres. Deretter startet jeg i rotnoden og brukte
compare til å gå til neste i treet. Siden det er et binært søketre vil alle verdiene jeg er ute etter følge et 
mønster og derfor kan jeg bruke compare til å velge om jeg går til høyre eller venstre. Når jeg møtte en verdi hvor 
compare gir ==0 betyr det at jeg har funnet en verdi som er lik og øker antallLike. Deretter går jeg videre i treet. 

I oppgave 3 Fant jeg førstePostOrden ved å først sjekke om p.venstre ikke er null. Da går den til venstre for å finne 
postorden. Slik fortsetter den helt til Den ikke går inn i if-testene. Da er den kommet til første postorden. Tegnet 
opp for meg selv for å forstå hvordan jeg skulle gjøre det. 
Brukte litt tid, men klarte tilslutt å forstå hvordan nestePostOrden skulle fungere. Her måtte jeg også tegne for å få 
det riktig. Hvis p ikke går inn i første if test skal den finne førstePostorden for å få riktig nestePostOrden. 

I oppgave 4 brukte jeg mye tid på å skjønen hvordan oppgave skulle implementeres. Først hardkoda eg postorden med 
whileløkker og mange if-setninger for å forstå hva som skjedde. Deretter bytta jeg det til nestePostorden og whileløkke.
Her brukte jeg tid på å få inn if(p == null), men når jeg fikk til det kjørte metoden. I postordenRecursive kallet jeg 
på metoden, men hadde selvsagt kallet på postorden og da kunne jeg ikke ta inn variabelen. Det ble riktig når jeg 
faktisk kallet på riktig metode. La til oppgave.utførOppgave tilslutt siden det er postorden. 

Litteraturliste
Uttersrud, U. (2021). Delkapittel 5  –  Binære Trær   –.
https://www.cs.hioa.no/~ulfu/appolonius/kap5/1/kap51.html#5.1.1