import java.io.*;
import java.util.*;

class Legesystem {
  private static SortertLenkeliste<Lege> legeListe;
  private static Lenkeliste<Resept> reseptListe;
  private static Lenkeliste<Legemiddel> legemiddelListe;
  private static Lenkeliste<Pasient> pasientListe;

  public static void lesFraFil(File fil) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(fil);
    } catch (FileNotFoundException e) {
      System.out.println("Fant ikke filen");
      return;
    }
    String linje = scanner.nextLine();

    while (scanner.hasNextLine()) {
      String[] biter = linje.split(",");

      
      
      
      if (biter[0].contains("Pasient")) {
        
        while (scanner.hasNextLine()) {
          linje = scanner.nextLine();
          if (biter[0].contains("#")) {
            break;
          }
          String[] info = linje.split(",");
          String navn = info[0];
          String fnr = info[1];
          Pasient pasient = new Pasient(navn, fnr);
          pasientListe.leggTil(pasient);
        }
      }

      else if ((biter[0].contains("Lege"))) {
        while (scanner.hasNextLine()) {
          linje = scanner.nextLine();

          if (biter[0].contains("#")) {
            break;
          }
          String[] info = linje.split(",");
          String navn = info[0];
          int kontrlID = Integer.parseInt(info[1]);
          if (kontrlID == 0) {
            Lege lege = new Lege(navn);
            legeListe.leggTil(lege);
          } else {
            Lege spesialist = new Spesialist(navn, kontrlID);
            legeListe.leggTil(spesialist);
          }
        }
      }

      else if (biter[0].contains("Legemidler")) {
        while (scanner.hasNextLine()) {
          linje = scanner.nextLine();
          if (biter[0].contains("#")) {
            break;
          }
          String[] legemidler = linje.split(",");
          String navn = legemidler[0];
          String type = legemidler[1];
          double pris = Double.parseDouble(legemidler[2]);
          double virkestoff = Double.parseDouble(legemidler[3]);
          if (type.equals("narkotisk") || type.equals("vanedannende")) {
            int styrke = Integer.parseInt(legemidler[4]);
            if (type.equals("narkotisk")) {
              Legemiddel nar = new Narkotisk(navn, pris, virkestoff, styrke);
              legemiddelListe.leggTil(nar);
            } else {
              Legemiddel van = new Vanedannende(navn, pris, virkestoff, styrke);
              legemiddelListe.leggTil(van);
            }
          } else {
            Legemiddel vanlig = new Vanlig(navn, pris, virkestoff);
            legemiddelListe.leggTil(vanlig);
          }
        }
      }

      else if (biter[0].contains("Resept")) {
        while (scanner.hasNextLine()) {
          linje = scanner.nextLine();

          if (biter[0].contains("#")) {
            break;
          }
          String[] info = linje.split(",");

          int legemnr = Integer.parseInt(info[0]);
          String legenavn = info[1];
          int pasID = Integer.parseInt(info[2]);
          String type = info[3];
          Legemiddel legemiddel = finnlegemiddel(legemnr);
          Lege lege = finnLege(legenavn);
          Pasient pasient = hentPasient(pasID);

          if (type != "p") {
            int reit = Integer.parseInt(info[4]);
            if (type == "hvit") {
              try {
                Resept resept = lege.skrivHvitResept(legemiddel, pasient, reit);
                reseptListe.leggTil(resept);

              } catch (UlovligUtskrift e) {
                System.out.println("Funker ikke");
              }
            } else if (type == "blaa") {
              try {
                Resept resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                reseptListe.leggTil(resept);
              } catch (UlovligUtskrift e) {
                System.out.println("Funker ikke");
              }
            } else if (type == "militaer") {
              try {
                Resept resept = lege.skrivMilitaerResept(legemiddel, pasient, reit);
                reseptListe.leggTil(resept);
              } catch (UlovligUtskrift e) {
                System.out.println("Funker ikke");
              }
            }
          } else {
            try {
              Resept resept = lege.skrivPResept(legemiddel, pasient);
              reseptListe.leggTil(resept);
            } catch (UlovligUtskrift e) {
              System.out.println("Funker ikke");
            }

          }
        }
      }
    }
  }

  public static Legemiddel finnlegemiddel(int legemnr) {
    Legemiddel legemiddel = null;
    int teller = 0;
    for (Legemiddel l : legemiddelListe) {
      if (teller == legemnr) {
        legemiddel = l;
      } else
        teller++;
    }
    return legemiddel;
  }

  public static Lege finnLege(String legenavn) {
    Lege lege = null;
    for (Lege l : legeListe) {
      if (l.hentNavn().compareTo(legenavn) == 0) {
        lege = l;
      }
    }
    return lege;
  }

  public static Pasient hentPasient(int pasID) {
    Pasient pasient = null;
    for (Pasient p : pasientListe) {
      if (p.hentID() == pasID) {
        pasient = p;
      }
    }
    return pasient;
  }

  public void skrivUt() {
    for (Pasient p : pasientListe) {
      System.out.println(p.hentID());
    }
  }

}
