import java.io.*;
import java.util.*;

class Legesystem {
  private static SortertLenkeliste<Lege> legeListe;
  private static Lenkeliste<Resept> reseptListe;
  private static Lenkeliste<Legemiddel> legemiddelListe;
  private static Lenkeliste<Pasient> pasientListe;

  private static void lesFraFil(File fil){
        Scanner scanner = null;
        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen");
            return;
        }
        String fil = scanner.nexLine();
        while(scanner.hasNextLine()){
            String biter[]= fil.split(",");

            if (biter[0].contains("Pasient")){
                fil.nextLine();
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    String fnr = biter[1];
                    Pasient pasient= new Pasient(navn,fnr);
                    pasientListe.leggTil(pasient);
                    fil.nextLine();
                }
                }

            else if ((biter[0].contains("Lege"))){
                fil.nextLine();
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    String kontrlID = biter[1];
                if (kontrlID==0){
                    Lege lege = new Lege(navn, kontrlID);
                    legeliste.leggTil(lege);
                    fil.nextLine();
                } else{
                    Lege spesialist = new Spesialist(navn, kontrlID);
                    legeliste.leggTil(spesialist);
                    fil.nextLine();
                }
                }
                }

                else if (biter[0].contains("Legemidler")){
                fil.nextLine();
                while(!(biter[0].contains("#"))){
                    String navn = biter[0];
                    String type = biter[1];
                    double pris = Double.parseDouble(biter[2]);
                    double virkestoff = Double.parseDouble(biter[3]);
                if(type=="narkotisk" || type=="vanedannende"){
                  double styrke=Double.parseDouble(biter[4]);


                    if (type=="narkotisk"){
                        Legemiddel nar = new Narkotisk(navn,pris,virkestoff,styrke);
                        legemidelListe.leggTil(nar);
                        fil.nextLine();
                    }else
                        Legemiddel vanedannende = new Vanedannende(navn,pris,virkestoff,styrke);
                        legemidelListe.leggTil(vanedannende);
                        fil.nextLine();
                    }
                }else{
                    Legemiddel vanlig = new Vanlig(navn,pris,virkestoff);
                    legemidelListe.leggTil(vanlig);
                    fil.nextLine();
                }
                }

            }
            else if ((biter[0].contains("Resept"))){
                fil.nextLine();
                while(scanner.hasNextLine()){
                int legemnr = Integer.parseInt(biter[0];)
                String legenavn = biter[1];
                int pasID = Integer.parseInt(biter[2]);
                String type = biter[3];
                Legemiddel legemidel = finnLegemidel(legemnr);
                Lege lege = finnLege(legenavn);
                Pasient pasient = hentPasient(pasID);
                if (type!="p"){
                    int reit = Integer.parseInt(biter[4]);
                    if(type=="hvit"){
                      Resept resept = lege.skrivHvitResept(legmidel, pasient, reit);
                      reseptListe.leggTil(resept);
                    }
                    else if(type=="blaa"){
                      Resept resept = lege.skrivBlaaResept(legmidel, pasient, reit);
                      reseptListe.leggTil(resept);
                    }
                    else if(type=="militaer"){
                      Resept resept = lege.skrivMilitaerResept(legmidel, pasient, reit);
                      reseptListe.leggTil(resept);
                    }
                  }else{
                    Resept resept = lege.skrivPResept(legmidel, pasient);
                    reseptListe.leggTil(resept);

                  }
                }
              }

        public Legemiddel finnLegemidel(int legemnr){
            int teller = 0;
            for(Legemiddel l: legemidelListe){
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
            if (p.hentID().compareTo(pasID)==0){
              return p;
            }
          }
        }

    }
}


/*public static void brukerInput(){
  String svar = null;
  while(svar != "avslutt"){
    Scanner input = new Scanner(System.in);
    System.out.println("Skriv ut informasjon om:\n0: Pasienter\n1: Leger\n2: Legemiddel\n3: Resept\n4: Avslutt");
    String svar = input.nextLine();

    if(svar== "Pasienter"){
      for(Pasient p : pasientListe){
        System.out.println(p.hentNavn(), p.hentID(), p.hentfodsNummer());
        System.out.println();
      }
    }
    else if (svar == "Leger"){
      for(Lege l: legeListe){
        System.out.println(l.hentNavn());
      }
    }
    else if(svar == "Legemiddel"){
      for(Legemiddel l: legemiddelListe){
        System.out.println(l.toString());
        System.out.println();
      }
    }
  }*/

}

