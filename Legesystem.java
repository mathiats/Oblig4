import java.io.*;
import java.util.*;

class Legesystem {
  private static SortertLenkeliste<Lege> legeListe;
  private static Lenkeliste<Resept> reseptListe;
  private static Lenkeliste<Legemiddel> legemiddelListe;
  private static Lenkeliste<Pasient> pasientListe;

  public static void lesFraFil(String filnavn) {
    File fil = new File(filnavn);
    Scanner scanner = null;
    try {
      scanner = new Scanner(fil);
    } catch (FileNotFoundException e) {
      System.out.println("Fant ikke filen");
    }
    String linje = "";

    while (scanner.hasNextLine()) {
      linje = scanner.nextLine();
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
              Narkotisk nar = new Narkotisk(navn, pris, virkestoff, styrke);
              legemiddelListe.leggTil(nar);
            } else {
              Vanedannende van = new Vanedannende(navn, pris, virkestoff, styrke);
              legemiddelListe.leggTil(van);
            }
          } else {
            Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
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
        Legemiddel legemiddelet = null;
            for (Legemiddel l : legemiddelListe) {
              if (legemnr==l.hentId()) {
                legemiddelet = l;
                  }
        }
        Lege lege = null;
          for (Lege l : legeListe) {
            if (legenavn.equals(l.hentNavn())) {
              lege = l;
      }
    }
    Pasient pasient = null;
    for (Pasient p : pasientListe) {
      if (pasID == p.hentID()) {
        pasient = p;
      }
    }
    if (info[3].equals("hvit")){
      int reit = Integer.parseInt(info[4]);
      HvitResept hvitResept = lege.skrivHvitResept(legemiddelet, pasient, reit);
      reseptListe.leggTil(hvitResept);
    }
    if (info[3].equals("Militaer")) {
      int reit = Integer.parseInt(info[4]);
      MilitaerResept militaerResept = lege.skrivMilitaerResept(legemiddelet, pasient, reit);
      reseptListe.leggTil(militaerResept);
    }
    if (info[3].equals("hvit")) {
      int reit = Integer.parseInt(info[4]);
      HvitResept hvitResept = lege.skrivHvitResept(legemiddelet, pasient, reit);
      reseptListe.leggTil(hvitResept);
    }
    if (info[3].equals("p")) {
      Presept pResept = lege.skrivPResept(legemiddelet, pasient);
      reseptListe.leggTil(pResept);
    }

      }
    }
  } 

}
}
