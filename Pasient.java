class Pasient{
  private String navn;
  private String fodsNummer;
  private static int unikID = 0;
  private Stabel<Resept> liste;

  public Pasient(String navn, String fodsNummer){
    this.navn = navn;
    this.fodsNummer = fodsNummer;
    unikID++;
  }

  public void leggTilResept(Resept resept){
    liste.leggPaa(resept);
  }

  public Resept hentUt(){
    return liste.taAv();
  }
}
