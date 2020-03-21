class BlaaResept extends Resept{
      protected String farge; //BlaaResept har en instansvariabel; farge

      public BlaaResept(Legemiddel l,Lege u,Pasient p,int r){
        super(l,u,p,r); //Kaller paa konstruktoren til superklassen.
        farge = "Blaa";
      }

      @Override  // Overskriver farge-metoden fra Resept
      public String farge(){
        return farge;
      }

      @Override //Overskriver ogsaa metoden prisAaBetale, som skal returnere 25% av opprinnelig pris.
      public double prisAaBetale(){
        return (legemiddel.hentPris() * 0.25); //De skal kun betale 25 % av opprinnelig pris
      }


      @Override // Overskriver toString-metoden.
      public String toString(){
        return (super.toString() + "\n" + "Farge: " + farge + "\n" //Kaller paa superklassen sin toString
       + "Pris: " + prisAaBetale() ) ; //Legger til tilleggsinformasjon som gjelder Blaa resepter.
      }

}
