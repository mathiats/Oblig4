public class UlovligUtskrift extends Exception{
  UlovligUtskrift(Lege l, Legemiddel lm){
    super("Legen " + l.hentNavnLege() + " har ikke lov til å skrive ut " + lm.hentNavn());
  }
}
