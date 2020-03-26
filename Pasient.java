class Pasient{
  private int id;
  private String navn;
  private String fodsNummer;
  private static int unikID = 0;
  private Stabel<Resept> liste = new Stabel<Resept>();

  public Pasient(String navn, String fodsNummer){
    this.navn = navn;
    this.fodsNummer = fodsNummer;
    id = unikID;
    unikID++;
  }

  public void leggTilResept(Resept resept){
    liste.leggPaa(resept);
  }

  public Stabel<Resept> hentUtResepter(){
    return liste;
  }

  public Resept hentUt(){
    return liste.taAv();
  }

  public int hentPasientId(){
    return id;
  }
  public String hentPasientNavn(){
    return navn;
  }
  public String hentPasientFnr(){
    return fodsNummer;
  }
  @Override
  public String toString(){
    return ("Navn: "+this.hentPasientNavn() + "\nID: " + this.hentPasientId() + "\nFodselsnummer: "+this.hentPasientFnr() );
  }
}
