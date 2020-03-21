class Spesialist extends Lege implements Godkjenningsfritak {
//Spesialist er en subklasse av Lege, og har i tillegg implementert grensesnittet "Godkjenningsfritak"

  protected int kontrollID; //kontrollID er spesialist sin instansvariabel.


  public Spesialist(String navnP,int kontrollIDP){
    super(navnP); //Bruker lege sin konstruktor for navn.
    kontrollID = kontrollIDP;
  }

  @Override
  public int hentKontrollID() { //returnerer kontrollID.
    return kontrollID;
  }

  @Override
  public String toString(){ // Kaller paa Lege sin toString, legger til info om kontrollID. 
    return (super.toString() +"\n" +  "KontrollID til Spesialisten: " + kontrollID);
  }




}
