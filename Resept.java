abstract class Resept{

    protected static int idTeller = 0;  //Oppretter en static idTeller som oppdaterer seg hver gang en resept blir laget.
    protected int idNummer;             // Deklarerer alle andre instansvariabler også.
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;

    public Resept(Legemiddel legemiddelP,Lege utskrivendeLegeP,Pasient pasientP,int reitP){
      //Oppretter en konstruktør som tar inn 4 parametre, Legemiddel,Lege,pasientId og reit.
      legemiddel = legemiddelP;
      utskrivendeLege = utskrivendeLegeP;
      pasient = pasientP;
      reit = reitP;
      idNummer = idTeller;
      idTeller++;


      }
      //Resept har mange metoder, som er listet nedenfor.
      public int hentId(){
        return idNummer;
      }



      public Legemiddel hentLegemiddel(){
        return legemiddel;
      }



      public Lege hentLege(){
        return utskrivendeLege;
      }


      public Pasient hentPasientId(){
        return pasient;
      }


      public int hentReit(){
        return reit;
      }



      public boolean bruk(){
        reit--;  //Reiten blir 1 mindre når man bruker en resept.
        if (reit < 0){
          return false; //returner false hvis resepten er ugyldig.
        }
        return true;
      }

      @Override //Overider fra Object, returnerer all info som er nødvendig.
      public String toString(){
        return ("\nType legemiddel: " + legemiddel.hentNavn() + "\n"
             + "Utskrivende lege: " + utskrivendeLege.hentNavn() + "\n"
             + "PasientId: " + pasient + "\n"
             + "Reit: " + reit
             + "\nID-nummer:  " + idNummer) ;
      }


      abstract public String farge(); //Oppretter 2 abstrakte metoder, som tvinger oss til å overskrive
    // i subklassene. Blir som en ekstra sjekk for meg selv.
      abstract public double prisAaBetale();







}
