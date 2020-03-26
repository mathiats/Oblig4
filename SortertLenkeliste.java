//SortertLenkeliste er en subklasse av Lenkeliste, men tar også inn Comparable.
class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

  @Override
  public void leggTil(T x){
    //Hvis lista er tom, blir start en ny node med xx som innhold.
    if (start == null){
      start = new Node(x);

      //ellers bruker vi compareTo(x) på innholdet til opprinnelige start, og hvis dette er større enn 0,
      //blir den nye noden en ny start for lista.
    } else if (start.innhold.compareTo(x) > 0){
      Node node1 = new Node(x);
      Node opprinneligForste = start;
      start = node1;
      node1.neste = opprinneligForste;

    } else {
      //ellers iterer vi gjennom lista med en while-løkke.
      Node node = start;
      //så lenge node.neste ikke er 0 eller at compareTo(x) på node.neste.innhold er mindre eller lik 0.
      while ((node.neste != null) && (node.neste.innhold.compareTo(x) <= 0)){
        //node oppdateres for hver gang.
        node = node.neste;
      }
      //hvis vi er i slutten av lista, blir den nye noden siste noden i lista.
      if (node.neste == null) {
        Node nodeStorst = new Node(x);
        node.neste = nodeStorst;
      }
      //ellers plasserer vi den nye noden på riktig posisjon.
      else {
        Node nyNode = new Node(x);
        Node etterNode = node.neste;
        node.neste = nyNode;
        nyNode.neste = etterNode;
      }
    }
  }

  @Override
  //fjern bruker metoden fjern fra Lenkeliste, med posisjon stoerrelse()-1, da dette fjerner siste node i lista.
  public T fjern(){
    return (fjern(stoerrelse()-1));
  }



  //Disse metodene skulle kun kaste et unntak allerede liggende i java. 
  @Override
  public void sett(int pos, T x) throws UnsupportedOperationException{
      throw new UnsupportedOperationException();
    }

  @Override
    public void leggTil(int pos,T x) throws UnsupportedOperationException{
      throw new UnsupportedOperationException();
    }








}
