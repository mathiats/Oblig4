import java.util.NoSuchElementException;
import java.util.Iterator;

class Lenkeliste<T> implements Liste<T> { //Lenkeliste implementerer fra Liste<T>
   class Node{                          //Oppretter en klasse inne i Lenkeliste; Node.
    Node neste = null;
    T innhold;
    Node(T x){                         //Node tar inn en parameter, x.
      innhold = x;
    }
  }

  public Iterator<T> iterator(){
    return new LenkelisteIterator();
  }

  class LenkelisteIterator implements Iterator<T> {
    Node denne = start;


    public boolean hasNext(){
      return denne != null;
    }

    public T next(){
      if (denne == null){
        throw new NoSuchElementException("next");
      }
      Node n = denne;
      denne = denne.neste;
      return n.innhold;

    }
  }

  //Den lenkede listen har en instansvariabel start; som settes til null.
  Node start = null;

  @Override
  public int stoerrelse(){
    //Oppretter en teller;
    int teller = 0;
    Node forste = start;
    //En while lokke som kjorer så lenge vi ikke har kommet til slutten av lista.
    while(forste != null){
      teller++;
      //Oppdaterer forste
      forste = forste.neste;
    }
    //returnerer telleren, som er en int.
    return teller;
  }




  @Override
  public T fjern() throws UgyldigListeIndeks{
    //kaster et unntak hvis Lenkeliste er tom.
    if (start == null){
      //sender med -1 som parameter.
      throw new UgyldigListeIndeks(-1);
    }
    //ellers saa fjerner vi forste node.
    T startData = start.innhold;
    Node etterStart = start.neste;
    //endrer start til å være etterStart.
    start = etterStart;

    //returnerer innholdet til opprinngelige start-noden.
    return startData;
  }




  @Override
  public T fjern(int pos) throws UgyldigListeIndeks{
    //kaster unntak hvis lista er tom.
    if (start == null){
      throw new UgyldigListeIndeks(-1);
    }
    //kaster ogsaa unntak hvis posisjonen er mindre enn 0 eller storre enn storrelsen på lista -1.
    else if(pos < 0 || pos >(stoerrelse()-1)) {
      throw new UgyldigListeIndeks(pos);
    }
    //sjekker hvis posisjonen er lik 0.
    else if (pos == 0){
      //da oppretter vi en ny første node, og forflytter nodene bakover.
      T returverdi = start.innhold;
      Node andreNode = start.neste;
      start = andreNode;
      //returnerer opprinnelige start sitt innhold.
      return returverdi;
    }
    else{
      //ellers saa iterer vi gjennom lista, til vi kommer til noden som er for den som skal fjernes.
      Node forsteNode = start;
      for (int i = 0; i < (pos-1); i++){
        forsteNode = forsteNode.neste;
      }
      Node skalFjernes = forsteNode.neste;
      T returVerdi = skalFjernes.innhold;
      //Noden etter den som skal fjernes blir definert, saa vi kan hoppe over den som skal fjernes.
      Node etterSkalFjernes = skalFjernes.neste;
      forsteNode.neste = etterSkalFjernes;
      //returnerer innholdet til noden som fjernes.
      return returVerdi;
    }

  }

  @Override
  //Skal legge til en node sist i lista.
  public void leggTil(T x){
    //Sjekker om lista er tom.
    if (start == null){
      //hvis dette er tilfelle, opprettes en ny node, som blir start i lista.
      start = new Node(x);
    }
    else {
      //Ellers iterer vi til vi er til slutten av lista.
      Node forste = start;
      while (forste.neste != null){
        forste = forste.neste;
       }
       //dette blir nye siste noden i lista.
      forste.neste = new Node(x);
    }
  }


  @Override
  public void leggTil(int pos, T x) throws UgyldigListeIndeks{
    //hvis lista er tom;
    if (start == null){
      //hvis posisjonen er 0 og lista er tom, blir dette den nye start.
      if (pos == 0){
        start = new Node(x);
      }
      //ellers kastes et nytt unntak.
      else{
        throw new UgyldigListeIndeks(pos);
      }
    }
    // ellers hvis posisjonen er lik storrelsen på lista, vil vi kunne legge den bakerst.
    //bruker samme metode som i leggTil(T x).
    else if(pos == stoerrelse()){
      Node forste = start;
      while(forste.neste != null){
        forste = forste.neste;
      }
      forste.neste = new Node(x);
    }
    //Hvis posisjonen er under 0 eller storre en storrelsen, blir et nytt unntak kastet.
    else if((pos < 0)|| (pos > stoerrelse())){
      throw new UgyldigListeIndeks(pos);
    }
    //hvis posisjonen er 0, blir dette den nye start, og alt blir forskjovet.
    else if(pos == 0){
      Node opprinneligStart = start;
      start = new Node(x);
      start.neste = opprinneligStart;
    }
    //ellers iterer vi fram til noden for der det skal legges til en ny node.
    else {
      Node forste = start;
      for(int i = 0; i < (pos-1); i++){
        forste = forste.neste;
      }
      //Legger den nye noden til i riktig posisjon.
      Node etterForste = forste.neste;
      Node nyNode = new Node(x);
      forste.neste = nyNode;
      nyNode.neste = etterForste;
    }
  }

  @Override
  public T hent(int pos)throws UgyldigListeIndeks{
    //Hvis posisjonen er mindre enn 0 eller at posisjonen er storre en stoerrelse() -1.
    if((pos < 0) || (pos > (stoerrelse()-1))) {
      //kastes et nytt unntak.
      throw new UgyldigListeIndeks(pos);
      //Hvis posisjonen er 0, saa returneres innholdet til start.
    } else if(pos == 0){
      return start.innhold;

    } else {
    //Ellers iterer vi gjennom lista fram til posisjonen vi onsker.
    Node forste = start;
    for(int i = 0; i < pos; i++){
      forste = forste.neste;
    }
    //returnerer innholdet til noden med riktig posisjon.
    T returVerdi = forste.innhold;
    return returVerdi;
   }
  }



  @Override
  public void sett(int pos, T x) throws UgyldigListeIndeks{
    //kaster unntak hvis lista er tom.
    if (start == null){
      throw new UgyldigListeIndeks(pos);
    }
    //hvis posisjonen er 0, blir innholdet i start satt til x.
    else if(pos == 0){
      start.innhold = x;
    }
    //hvis posisjonen er under 0 eller storre enn stoerrelse()-1, blir det kastet et nytt unntak.
    else if((pos < 0)||(pos >(stoerrelse()-1))){
      throw new UgyldigListeIndeks(pos);
    }
    // eller iterer vi fram til riktig node, og setter innholdet til å vaere x.
    else {
      Node node = start;
      for (int i = 0; i < pos; i++){
        node = node.neste;
      }
      node.innhold = x;
    }
  }



}
