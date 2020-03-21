class Presept extends HvitResept {

  public Presept(Legemiddel l,Lege u,Pasient p){
    super(l,u,p,3); //Kaller paa super sin konstruktor, og sender inn 3 som reit.

  }



  @Override //Overskriver prisAaBetale metoden.
  public double prisAaBetale(){
    if (legemiddel.hentPris() > 108){ //Bruker en if-sjekk for aa faa riktig pris.
         return (legemiddel.hentPris() - 108);
        }
    return 0; // De skal ikke betale mindre enn 0.
  }





  @Override
  public String toString(){ //Legger til at dette er et Prevensjonsresept.
    return (super.toString() + "\nType: " + "Prevensjonsresept") ;
  }


}
