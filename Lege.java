class Lege implements Comparable<Lege> {

  protected String navn; // Klassen lege har instansvariabelen navn.
  protected Lenkeliste<Resept> utskrevedeResepter;

  public Lege(String navnP){
    navn = navnP;
  }

  public void hent(){
    try{
    for (int i = 0; i < utskrevedeResepter.stoerrelse();i++){
      utskrevedeResepter.hent(i);
      }
    } catch(Exception e){
    System.out.println("Ingen utskrevende resepter. ");
    }


  }


  public String hentNavn(){ //Metoden returnerer navnet til Legen.
    return navn;
  }

  @Override // Overskriver toString metoden fra Object-klassen.
  public String toString(){
    return ("\n" + "Legens navn: " + navn); //Skriver ut Legens navn, da dette er eneste infoen vi har om leger.
  }


  public int compareTo(Lege annen){
    return navn.compareTo(annen.navn);
  }

  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasientID, int reit) throws UlovligUtskrift{
    if ((legemiddel instanceof Narkotisk) && (!(this instanceof Spesialist))){
      throw new UlovligUtskrift(this,legemiddel);
    }
    HvitResept hvit = new HvitResept(legemiddel,this,pasientID,reit);
    utskrevedeResepter.leggTil(hvit);
    return hvit;
  }

  public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasientID,int reit) throws UlovligUtskrift{
    if ((legemiddel instanceof Narkotisk) && (!(this instanceof Spesialist))){
      throw new UlovligUtskrift(this,legemiddel);
    }
    MilitaerResept milla = new MilitaerResept(legemiddel,this,pasientID,reit);
    utskrevedeResepter.leggTil(milla);
    return milla;
  }

  public Presept skrivPResept(Legemiddel legemiddel,Pasient pasientID) throws UlovligUtskrift{
    if ((legemiddel instanceof Narkotisk) && (!(this instanceof Spesialist))){
      throw new UlovligUtskrift(this,legemiddel);
    }
    Presept pilla = new Presept(legemiddel,this,pasientID);
    utskrevedeResepter.leggTil(pilla);
    return pilla;
  }

  public BlaaResept skrivBlaaResept(Legemiddel legemiddel,Pasient pasientID,int reit) throws UlovligUtskrift{
    if ((legemiddel instanceof Narkotisk) && (!(this instanceof Spesialist))){
      throw new UlovligUtskrift(this,legemiddel);
    }
    BlaaResept blaa = new BlaaResept(legemiddel,this,pasientID,reit);
    utskrevedeResepter.leggTil(blaa);
    return blaa;

  }




}
