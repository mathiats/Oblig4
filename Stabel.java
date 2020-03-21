//Stabel er en subklasse av Lenkeliste.
class Stabel<T> extends Lenkeliste<T> {

  //bruker leggTil(x) for aa legge til slutten av lista.
  public void leggPaa(T x){
    leggTil(x);
  }

  //bruker fjern() med pos stoerrelse()-1, da dette er slutten av lista. 
  public T taAv(){
    return (fjern(stoerrelse()-1));
  }
}
