import java.io.*;
import java.util.*;

class Legesystem {
    private static SortertLenkeliste<Lege> legeListe;
    private static  Lenkeliste<Resept> reseptListe;
    private static Lenkeliste<Legemiddel> legemiddelListe;
    private static Lenkeliste<Pasient> pasientListe;

 public static void lesFraFil(File fil){
        Scanner scanner = null;
        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen");
            return;
        }
        String linje= scanner;
        while(scanner.hasNextLine()){
            String biter[]= linje.split(",");

            if (biter[0].contains("Pasient")){
               scanner.nextLine().split(",");;
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    String fnr = biter[1];
                    Pasient pasient= new Pasient(navn,fnr);
                    pasientListe.leggTil(pasient);
                   scanner.nextLine().split(",");
                }
                }

            else if ((biter[0].contains("Lege"))){
               scanner.nextLine().split(",");
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    int kontrlID = Integer.parseInt(biter[1]);
                if (kontrlID==0){
                    Lege lege = new Lege(navn);
                    legeListe.leggTil(lege);
                   scanner.nextLine().split(",");
                } else{
                    Lege spesialist = new Spesialist(navn, kontrlID);
                    legeListe.leggTil(spesialist);
                    scanner.nextLine().split(",");
                }
                }
                }

                else if (biter[0].contains("Legemidler")){
               scanner.nextLine().split(",");
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    String type = biter[1];
                    double pris = Double.parseDouble(biter[2]);
                    double virkestoff = Double.parseDouble(biter[3]);
                if(type=="narkotisk" || type=="vanedannende"){
                  int styrke=Integer.parseInt(biter[4]);
                    if (type=="narkotisk"){
                        Legemiddel nar = new Narkotisk(navn,pris,virkestoff,styrke);
                        legemiddelListe.leggTil(nar);
                       scanner.nextLine().split(",");
                    }else{
                        Legemiddel van= new Vanedannende(navn,pris,virkestoff,styrke);
                        legemiddelListe.leggTil(van);
                       scanner.nextLine().split(",");
                    }
                  }
                else{
                    Legemiddel vanlig = new Vanlig(navn,pris,virkestoff);
                    legemiddelListe.leggTil(vanlig);
                   scanner.nextLine().split(",");
                }
                }
              }
            else if (biter[0].contains("Resept")){
               scanner.nextLine().split(",");;
                while(!(biter[0].contains("#")))
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
                      try{
                      Resept resept = lege.skrivHvitResept(legemiddel, pasient, reit);
                      reseptListe.leggTil(resept);
                      scanner.nextLine().split(",");

                      } catch(UlovligUtskrift e){
                      System.out.println("Funker ikke");
                     }
                    }
                    else if(type=="blaa"){
                      try{
                      Resept resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                      reseptListe.leggTil(resept);
                      scanner.nextLine().split(",");
                    } catch(UlovligUtskrift e){
                    System.out.println("Funker ikke");
                   }
                    }
                    else if(type=="militaer"){
                      try{
                      Resept resept = lege.skrivMilitaerResept(legemiddel, pasient, reit);
                      reseptListe.leggTil(resept);
                      scanner.nextLine().split(",");
                     } catch(UlovligUtskrift e){
                     System.out.println("Funker ikke");
                     }
                    }
                  }else{
                    try{
                    Resept resept = lege.skrivPResept(legemiddel, pasient);
                    reseptListe.leggTil(resept);
                    scanner.nextLine().split(",");
                  } catch(UlovligUtskrift e){
                  System.out.println("Funker ikke");
                  }

                  }
                }
              }
            }
          }

        public static Legemiddel finnlegemiddel(int legemnr){
          Legemiddel legemiddel = null;
          int teller = 0;
            for(Legemiddel l: legemiddelListe){
                if (teller == legemnr){
                  legemiddel = l;
                }else teller++;
          } return legemiddel;
        }
        public static Lege finnLege(String legenavn){
          Lege lege = null;
          for(Lege l: legeListe){
            if (l.hentNavn().compareTo(legenavn)==0){
              lege = l;
            }
          } return lege;
        }
        public static Pasient hentPasient(int pasID){
          Pasient pasient = null;
          for (Pasient p: pasientListe){
            if (p.hentID()==pasID){
              pasient = p;
            }
          } return pasient;
        }







}
