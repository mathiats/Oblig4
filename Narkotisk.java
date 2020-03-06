class Narkotisk extends Legemiddel{

    protected int narkStyrke; //Narkotisk har instansvariablen narkStyrke.

    public Narkotisk(String n, double p, double v, int nStyrke){
      super(n,p,v); //Kaller på super sin konstruktør.
      narkStyrke = nStyrke;
    }

    public int hentNarkotiskStyrke(){
      return narkStyrke;
    }

    @Override //Overskriver toString metoden. 
    public String toString(){
      return ("\n" +super.toString() + "\n" + "Type legemiddel: Narkotisk" + "\n" +
      "Narkotiskstyrke: " + narkStyrke);
    }
}
