class Pasient{
  private String navn;
  private String fodsNummer;
  private static int unikID = 0;
  private Stabel<Resept> pasResept;

  public Pasient(String navn, String fodsNummer){
    this.navn = navn;
    this.fodsNummer = fodsNummer;
    unikID++;
  }

  public void leggTilResept(Resept resept){
    pasResept.leggPaa(resept);
  }

  public Stabel<Resept> hentUt(){
    return pasResept;
  }
  public int hentID(){
    return unikID;
  }
}
