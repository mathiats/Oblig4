import java.io.*;
import java.util.*;

class Legesystem {
    private SortertLenkeliste<Lege> legeListe;
    private Lenkeliste<Resept> reseptListe;
    private Lenkeliste<Legemiddel> legemiddelListe;
    private Lenkeliste<Pasient> pasientListe;

 private static void lesFraFil(File fil){
        Scanner scanner = null;
        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen");
            return;
        }
        String linje= scanner.nextLine();
        while(scanner.hasNextLine()){
            String biter[]= linje.split(",");

            if (biter[0].contains("Pasient")){
               scanner.nextLine();
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    String fnr = biter[1];
                    Pasient pasient= new Pasient(navn,fnr);
                    pasientListe.leggTil(pasient);
                   scanner.nextLine();
                }
                }

            else if ((biter[0].contains("Lege"))){
               scanner.nextLine();
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    int kontrlID = Integer.parseInt(biter[1]);
                if (kontrlID==0){
                    Lege lege = new Lege(navn);
                    legeListe.leggTil(lege);
                   scanner.nextLine();
                } else{
                    Lege spesialist = new Spesialist(navn, kontrlID);
                    legeListe.leggTil(spesialist);
                    scanner.nextLine();
                }
                }
                }

                else if (biter[0].contains("Legemidler")){
               scanner.nextLine();
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    String type = biter[1];
                    double pris = Double.parseDouble(biter[2]);
                    double virkestoff = Double.parseDouble(biter[3]);
                if(type=="narkotisk" || type=="vanedannende"){
                  int styrke=Integer.parseInt(biter[4]);
                    if (type=="narkotisk"){
                        Legemiddel nar = new Narkotisk(navn,pris,virkestoff,styrke);
                        legemidelListe.leggTil(nar);
                       scanner.nextLine();
                    }else{
                        Legemiddel van= new Vanedannende(navn,pris,virkestoff,styrke);
                        legemidelListe.leggTil(van);
                       scanner.nextLine();
                    }
                  }
                else{
                    Legemiddel vanlig = new Vanlig(navn,pris,virkestoff);
                    legemidelListe.leggTil(vanlig);
                   scanner.nextLine();
                }
                }
              }  
            else if (biter[0].contains("Resept")){
               scanner.nextLine();
                while(scanner.hasNextLine()){
                int legemnr = Integer.parseInt(biter[0]);
                String legenavn = biter[1];
                int pasID = Integer.parseInt(biter[2]);
                String type = biter[3];
                Legemiddel legemiddel = finnlegemiddel(legemnr);
                Lege lege = finnLege(legenavn);
                Pasient pasient = hentPasient(pasID);
                if (type!="p"){
                    int reit = Integer.parseInt(biter[4]);
                    if(type=="hvit"){
                      Resept resept = lege.skrivHvitResept(legemiddel, pasient, reit);
                      reseptListe.leggTil(resept);
                    }
                    else if(type=="blaa"){
                      Resept resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                      reseptListe.leggTil(resept);
                    }
                    else if(type=="militaer"){
                      Resept resept = lege.skrivMilitaerResept(legemiddel, pasient, reit);
                      reseptListe.leggTil(resept);
                    }
                  }else{
                    Resept resept = lege.skrivPResept(legemiddel, pasient);
                    reseptListe.leggTil(resept);

                  }
                }
              }
            }
          }

            public Legemiddel finnlegemiddel(int legemnr){
            int teller = 0;
            for(Legemiddel l: legemiddelListe){
                if (teller == legemnr){
                  return l;
                }else teller++;
          }
        }
        public Lege finnLege(String legenavn){
          for(Lege l: legeListe){
            if (l.hentNavn().compareTo(legenavn)==0){
              return l;
            }
          }
        }
        public Pasient hentPasient(int pasID){
          for(Pasient p: pasientListe){
            if (p.hentID()==pasID){
              return p;
            }
          }
        }

}
