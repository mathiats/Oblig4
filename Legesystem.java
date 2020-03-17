import java.io.File;
import java.util.Scanner;

class Legesystem {
    private SortertLenkeliste<Lege> legeListe;
    private Lenkeliste<Resept> reseptListe;
    private Lenkeliste<Legemiddel> legemidelListe;
  private Lenkeliste<Pasient> pasientListe;

  public void lesFraFil(String filnavn){
        File fil = new File(filnavn);
        Scanner scanner = new Scanner(fil);
        while(fil.hasNextLine()){
            String biter[]= fil.split(",");
            
            if (biter[0].contains("Pasient")){
                fil.nextLine;
                while(!(biter[0].contains("#")){
                    String navn = biter[0];
                    String fnr = biter[1];
                    Pasient pasient= new Pasient(navn,fnr);
                    pasientListe.leggTil(pasient);
                    fil.nextLine;
                }
                }
                
                if ((biter[0].contains("Lege")){
                fil.nextLine;
                while(!(biter[0].contains("#")){
                    String navn = biter[0];
                    String kontrlID = biter[1];
                if (kontrlID==0){
                    Lege lege = new Lege(navn, kontrlID);
                    legeliste.leggTil(lege);
                    fil.nextLine;
                }else{
                    Lege spesialist = new Spesialist(navn, kontrlID);
                    legeliste.leggTil(spesialist);
                    fil.nextLine;
                }
                }
                }

                if (biter[0].contains("Legemidler")){
                fil.nextLine;
                while(!(biter[0].contains("#")){
                    String navn = biter[0];
                    String type = biter[1];
                    double pris = Double.parseDouble(biter[2]);
                    double virkestoff = Double.parseDouble(biter[3]);
                if(type=="narkotisk" || type=="vanedannende"){
                  double styrke=Double.parseDouble(biter[4]);
                  
                
                if (type==narkotisk){
                    Legemidel nar = new Narkotisk(navn,pris,virkestoff,styrke);
                    legemidelListe.leggTil(nar);
                    fil.nextLine;
                }else
                    Legemidel vanedannende = new Vanedannende(navn,pris,virkestoff,styrke);
                    legemidelListe.leggTil(vanedannende);
                    fil.nextLine;
                }
                }else{
                    Legmidel vanlig = new Vanlig(navn,pris,virkestoff);
                    legemidelListe.leggTil(vanlig);
                    fil.nextLine;
                }
                }
                }
            }
    }
}
