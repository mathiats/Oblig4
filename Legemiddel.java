abstract class Legemiddel {

    protected String navn;      //Skriver inn alle instansvariablene jeg trenger.
    protected double pris;
    protected double virkestoff;
    protected static int idTeller = 0;
    protected int idNummer;

    public Legemiddel(String n, double p, double v){   //Konstruktøren tar inn 3 parametre.
      navn = n;
      pris = p;
      virkestoff = v;
      idNummer = idTeller;
      idTeller++;    // Idteller oppdateres hver gang et Legemiddel  blir opprettet.
    }

    //Mange metoder nedenfor, som returnerer de ulike instansvariabeler.
    public int hentId(){
      return idNummer;
    }

    public String hentNavn(){
      return navn;
    }

    public double hentPris(){
      return pris;
    }

    public double hentVirkestoff(){
      return virkestoff;
    }

    public void settNyPris(double nyPris){ //Denne metoden setter en ny pris på legemiddelet. 
      pris = nyPris;
    }

    @Override           // Overrider fra Object-klassen.
    public String toString(){
      return ("\nNavn: "+ navn + "\n" +  "Pris: " + pris + "\n"
      + "Virkestoff: " + virkestoff + "\n" + "Idnummer: " + idNummer);
    }




}
