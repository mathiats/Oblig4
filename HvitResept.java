public class HvitResept extends Resept{

    protected String farge; //HvitResept har en instansvariabel; farge


    public HvitResept(Legemiddel l,Lege u,Pasient p,int r){
      super(l,u,p,r); //Kaller paa konstruktoren til superklassen, Resept.
      farge = "Hvit";
    }

    @Override //Overskriver metoden farge.
    public String farge(){
      return farge;
    }


    @Override //Overskriver metoden prisAaBetale.
    public double prisAaBetale(){
      return legemiddel.hentPris();
    }


    @Override //Overskriver toString
    public String toString(){
      return (super.toString() + "\nFarge: " + farge + "\n"  // Kaller paa konstruktøren, og legger til
             + "Pris: " + prisAaBetale() ) ;    // Informasjon til informasjon om HvitResept.
    }

}
