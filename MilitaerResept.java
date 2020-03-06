class MilitaerResept extends HvitResept {

  public MilitaerResept(Legemiddel l,Lege u,Pasient p,int r){
    super(l,u,p,r); //Kaller på konstruktøren til Superklassen.
   }




   @Override //Overskriver prisAaBetale til 0, da disse respetene er gratis.
   public double prisAaBetale(){
     return 0;
   }



   @Override //Overskver toString metoden.
   public String toString(){
     return (super.toString() + "\nType: " + "Miltærresept") ; //Legger til at det er en Militærresept.
   }
}
