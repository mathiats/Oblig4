class Vanedannende extends Legemiddel{
  protected int vStyrke; //Vanedannende er en subklasse til Legemiddel, men har også instansvariablen vStyrke.

  public Vanedannende(String n, double p, double v, int styrke){
    super(n,p,v); //Kaller på superklassen sin konstruktør, med de ulike parametrene fra konstruktøren.
    vStyrke = styrke;
  }

  @Override
  public int styrke(){ // returnerer vStyrke.
    return vStyrke;
  }
  @Override
  public String toString(){ //legger til informasjon som er spesifikt for Vanedannende.
    return ("\n" + super.toString() + "\n" + "Type legemiddel: Vanedannende" + "\n" +
    "Vanedannendestyrke: " + vStyrke);
  }



}
