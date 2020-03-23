import java.io.*;
import java.util.*;

class Legesystem {
   static SortertLenkeliste<Lege> legeListe = new SortertLenkeliste<Lege>();
   static Lenkeliste<Resept> reseptListe = new Lenkeliste<Resept>();
   static Lenkeliste<Legemiddel> legemiddelListe = new Lenkeliste<Legemiddel>();
   static Lenkeliste<Pasient> pasientListe = new Lenkeliste<Pasient>();

   public static void lesFraFil(File fil) {
    Scanner scanner = null;
    try {
      scanner = new Scanner(fil);
    } catch (FileNotFoundException e) {
      System.out.println("Fant ikke filen");
      return;
    }


    scanner.nextLine();

  while(scanner.hasNextLine()){
    String[] biterPasient = scanner.nextLine().split(",");

    while (biterPasient[0].charAt(0) != '#'){
    String navn = biterPasient[0];
    String fnr = biterPasient[1];
    Pasient pasient = new Pasient(navn, fnr);
    pasientListe.leggTil(pasient);
    biterPasient = scanner.nextLine().split(",");
    }





  String[] biterLegemidler = scanner.nextLine().split(",");
    while (biterLegemidler[0].charAt(0) != '#') {
        String navn = biterLegemidler[0];
        String type = biterLegemidler[1];
        double pris = Double.parseDouble(biterLegemidler[2]);
        double virkestoff = Double.parseDouble(biterLegemidler[3]);

        if (type.equals("narkotisk")) {
            int styrke = Integer.parseInt(biterLegemidler[4]);
            Narkotisk nark = new Narkotisk(navn, pris, virkestoff, styrke);
            legemiddelListe.leggTil(nark);
            biterLegemidler = scanner.nextLine().split(",");
        } else if (type.equals("vanedannende")) {
            int styrke = Integer.parseInt(biterLegemidler[4]);
            Vanedannende van = new Vanedannende(navn, pris, virkestoff, styrke);
            legemiddelListe.leggTil(van);
            biterLegemidler = scanner.nextLine().split(",");
        } else if (type.equals("vanlig")) {
            Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
            legemiddelListe.leggTil(vanlig);
            biterLegemidler = scanner.nextLine().split(",");
          }
      }



      String[] biterLege = scanner.nextLine().split(",");

      while (biterLege[0].charAt(0) != '#'){
        String navn = biterLege[0];
        int kontrlID = Integer.parseInt(biterLege[1]);

        if (kontrlID == 0){
              Lege lege = new Lege(navn);
              legeListe.leggTil(lege);
              biterLege = scanner.nextLine().split(",");
        } else {
              Spesialist spesialist = new Spesialist(navn, kontrlID);
              legeListe.leggTil(spesialist);
              biterLege = scanner.nextLine().split(",");
            }
      }




    while (scanner.hasNextLine()) {
      String[] info = scanner.nextLine().split(",");

      int legemnr = Integer.parseInt(info[0]);
      String legenavn = info[1];
      int pasID = Integer.parseInt(info[2]);
      String type = info[3];

      Legemiddel legemiddelet = null;
      for (Legemiddel l : legemiddelListe) {
          if (l.hentId() == legemnr) {
              legemiddelet = l;
            }
        }

      Lege lege = null;
      for (Lege l : legeListe) {
            if (l.hentNavnLege().equals(legenavn)) {
              lege = l;
            }
          }


      Pasient pasient = null;
      for (Pasient p : pasientListe) {
        if (p.hentPasientId() == pasID) {
            pasient = p;
          }
        }



      if (type.equals("hvit")){

        try{
        int reit = Integer.parseInt(info[4]);
        HvitResept hvitResept = lege.skrivHvitResept(legemiddelet, pasient, reit);
        reseptListe.leggTil(hvitResept);
        }catch(UlovligUtskrift e){}

      } else if(type.equals("militaer")){
          try{
          int reit = Integer.parseInt(info[4]);
          MilitaerResept militaerResept = lege.skrivMilitaerResept(legemiddelet, pasient, reit);
          reseptListe.leggTil(militaerResept);
        } catch(UlovligUtskrift e){}

        } else if (type.equals("hvit")) {
          try{
          int reit = Integer.parseInt(info[4]);
          HvitResept hvitResept = lege.skrivHvitResept(legemiddelet, pasient, reit);
          reseptListe.leggTil(hvitResept);
        } catch(UlovligUtskrift e){}

      } else if (type.equals("p")) {
          try{
          PResept pResept = lege.skrivPResept(legemiddelet, pasient);
          reseptListe.leggTil(pResept);
        } catch(UlovligUtskrift e){}
    }

   }
 }
}




  public int statistikk(){
    int tellerVanedannende = 0;
    int tellerNarko = 0;
    for (Legemiddel l : legemiddelListe){
      if (l instanceof Vanedannende){
        tellerVanedannende ++;
      } else if (l instanceof Narkotisk){
        tellerNarko ++;
      }
    }
    for (Lege l : legeListe){
      if (l instanceof Spesialist){
        int tellerLegerNarko = 0;
        Resept resepterSpes = l.hentUtskrevedeResepter();
        for (Resept r : resepterSpes){
          if (r instanceof Narkotisk){
            tellerLegerNarko++;
          }
        }
        System.out.println(l.hentNavnLege() + " har skrevet ut " + tellerLegerNarko +
        " antall narkotiske legemidler");
      }
    }
    for (Pasient p : pasientListe){
      Resept pasientResepter = p.hentUtResepter();
      int tellerPasientNarko = 0;
      for (Resept r : pasientResepter){
        if ((r.hentReit()) > 0 && (r instanceof Narkotisk))
          tellerPasientNarko++;
        }
      }
      if (tellerPasientNarko > 0){
        System.out.println(p.hentPasientNavn() + " har gydlig narkotisk resept" +
        " og har totalt " + tellerPasientNarko + " narkotiske resepter");
      }
    }
  }


















  public void brukResept(){
    System.out.println("Hvilken pasient vil du se resept for?\n");
    int teller = 0;
    for (Pasient p : pasientListe){
      System.out.println(teller + ": " + p.hentPasientNavn() + "(fnr " +
      p.hentPasientFnr() +")");
      teller++;
    }
    Scanner input = new Scanner(System.in);
    int svarPas = input.nextInt();
    if ((svarPas <= teller) && (svarPas > 0)){
      Pasient pas = pasientListe.hent(svarPas);
      System.out.println("Valgt pasient: " + pas.hentPasientNavn() + "(fnr " +
      pas.hentPasientFnr() +")");
      System.out.println("Hvilken resept vil du bruke?");

      Resept pasientResepter = pas.hentUtResepter();
      int teller = 0;
      for (Resept r : pasientResepter){
        int reit = r.hentReit();
        String navn = r.hentLegemiddel().hentNavn();
        System.out.println(teller + ": " + navn + "(" + reit + " reit )");
        teller++;
      }
      int svarRes = input.nextInt();
      if ((svarRess <= teller) && (svarRes > 0)){
        Resept res = pasientResepter.hent(svarRes);
        if (res.bruk()){
          System.out.println("Brukte resept paa " + res.hentLegemiddel().hentNavn() +
          ". Antall gjenvaerende reit: " + res.hentReit());
        } else{
          System.out.println("Kunne ikke bruke resept paa " + res.hentLegemiddel().hentNavn() +
          " (ingen gjenvaerende reit)");
          break;
        }
      }
      else{
        System.out.println("Ugyldig indeks");
        break;
      }
    }
    else{
      System.out.println("Ugyldig indeks");
      break;
    }
  }




  public void brukerInput(){
    int svar = 10;
    while (svar != 8){
      Scanner input = new Scanner(System.in);
      System.out.println("Skriv ut informasjon om:\n0: Pasienter\n1: Leger\n2:" +
      " Legemiddel\n3: Resept\n 4: Fullstendig oversikt over alt\n" +
      "5: Ønsker du å legge til et objekt, tast 5\n6: Bruk resept\n" +
      "7: Statistikk\n" +
      "8: Tast 8 for å avslutte");
      svar = input.nextInt();
      if (svar == 0){
        System.out.println("Skriver ut informasjon om pasienter:");
        for (Pasient p : pasientListe){
          System.out.println(p);
        }
        continue;
      }
      else if (svar == 1){
        System.out.println("Skriver ut informasjon om leger:");
        for (Lege l : legeListe){
          System.out.println(l.hentNavnLege());
        }
        continue;
      }
      else if (svar == 2){
        System.out.println("Skriver ut informasjon om legemidler:");
        for (Legemiddel lm : legemiddelListe){
          System.out.println(lm.toString());
        }
        continue;
      }
      else if (svar == 3){
        System.out.println("Skriver ut informasjon om resepter:");
        for (Resept r : reseptListe){
          System.out.println(r.toString());
        }
        continue;
      }
      else if (svar == 4){
        System.out.println("Pasienter:\n");
        for (Pasient p : pasientListe){
          System.out.println(p);
        }
        System.out.println("\nLeger:\n");
        for (Lege l : legeListe){
          System.out.println(l.hentNavnLege());
        }
        System.out.println("\nLegemidler:\n");
        for (Legemiddel lm : legemiddelListe){
          System.out.println(lm.toString());
        }
        System.out.println("\nResepter:\n");
        for (Resept r : reseptListe){
          System.out.println(r.toString());
        }
        continue;
      }
      else if (svar == 5){
        Scanner nyObjInput = new Scanner(System.in);
        System.out.println("Hilken type objekt onsker du aa legge til?"
        + " Pasient/Lege/Legemiddel/Resept\n");
        String nyttSvar = nyObjInput.nextLine();

        if (nyttSvar.equals("Pasient")){
          System.out.println("Skriv inn navnet:\n");
          String navn = inputet.nextLine();
          System.out.println("Skriv fodselsnummer:\n");
          String fodselsnummer = inputet.nextLine();
          Pasient pasient = new Pasient(navn,fodselsnummer);
          pasientListe.leggTil(pasient);
          continue;
        }
        else if (nyttSvar.equals("Lege")){
          System.out.println("Skriv inn navnet på legen du vil legge til:\n");
          String navn = nyObjInput.nextLine();
          System.out.println("Skriv inn kontroll ID:\n");
          try{
            int kontrollId = nyObjInput.nextInt();
            if (kontrollId == 0){
              Lege lege = new Lege(navn, kontrollId);
              legeListe.leggTil(lege);
              continue;
            } else{
              Spesialist spes = new Spesialist(navn, kontrollId);
              legeListe.leggTil(spes);
              continue;
            }
          } catch (NumberFormatException e){
            System.out.println("Ikke gylding input");
            continue;
          }
        }
        else if (nyttSvar.equals("Legemiddel")){
          System.out.println("Skriv navnet:\n");
          String navn = inputet.nextLine();
          try{
            System.out.println("Skriv inn type legemiddel: narkotisk/vannedannende/vanlig\n");
            String type = inputet.nextLine();
            System.out.println("Skriv inn pris\n");
            double pris = inputet.nextDouble();
            System.out.println("Skriv inn antall virkestoff:\n");
            double virkestoff = inputet.nextDouble();
            if (type.equals("narkotisk")){
              System.out.println("Skriv inn styrke\n");
              int styrke = inputet.nextInt();
              Narkotisk nark = new Narkotisk(navn, pris, virkestoff, styrke);
              legemiddelListe.leggTil(nark);
              continue;
            }
            else if (type.equals("vanedannende")){
              System.out.println("Skriv inn styrke\n");
              int styrke = inputet.nextInt();
              Vanedannende van = new Vanedannende(navn, pris, virkestoff, styrke);
              legemiddelListe.leggTil(van);
              continue;
            }
            else if (type.equals("vanlig")){
              Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
              legemiddelListe.leggTil(vanlig);
              continue;
            }
          } catch (NumberFormatException e) {
            System.out.println("Ikke gylding input");
            continue;
          }
        }
        else if (nyttSvar.equals("Resept")){
          System.out.println("Skriv navn paa oensket lege:\n");
          String legenavn = nyObjInput.nextLine();
          try{
            Lege lege = null;
            for (Lege l : legeListe){
              if (l.hentNavnLege().equals(legenavn)){
                lege = l;
              }
            }
          } catch (Exception e){
            System.out.println("Legen du skrev inn finnes ikke i listene vaare");
            continue;
          }
          System.out.println("Skriv inn navn paa oensket legemiddel");
          String legemiddelnavn = nyObjInput.nextLine();
          try{
            Legemiddel legeM = null;
            for (Legemiddel lm : legemiddelListe){
              if (lm.hentNavn() == legemiddelnavn){
                legeM = lm;
              }
            }
          } catch (Exception e){
            System.out.println("Legemiddelet du skrev inn finnes ikke i listene vaare");
            continue;
          }
          System.out.println("Skriv inn navn onsket pasient;\n");
          String pasientNavn = inputet.nextLine()
          try{
            Pasient pasient = null;
            for (Pasient p : pasientListe){
              if (p.hentNavn() == pasientNavn){
                pasient = p;
              }
            }
          } catch (Exception e){
            System.out.println("Pasienten du skrev inn finnes ikke i listene vaare");
            continue;
          }
          System.out.println("Velg type resept:\n");
          String type = nyObjInput.nextLine();
          if (type.equals("hvit")){
            try{
              System.out.println("Skriv inn reit: \n");
              int reit = inputet.nextInt();
              HvitResept hvitResept = lege.skrivHvitResept(legeM, pasient, reit);
              reseptListe.leggTil(hvitResept);
              continue;
            } catch (UlovligUtskrift e){
            }
          }
          else if (type.equals("militaer")){
            try{
              System.out.println("Skriv inn reit: \n");
              int reit = inputet.nextInt();
              MilitaerResept mili = lege.skrivMilitaerResept(legeM, pasient, reit);
              reseptListe.leggTil(mili);
              continue;
            } catch (UlovligUtskrift e){
            }
          }
          else if (type.equals("blaa")){
            try{
              System.out.println("Skriv inn reit: \n");
              int reit = inputet.nextInt();
              BlaaResept blaaResept = lege.skrivBlaaResept(legeM, pasient, reit);
              reseptListe.leggTil(blaaResept);
              continue;
            } catch (UlovligUtskrift e){
            }
          }
          else if (type.equals("p")){
            try{
              PResept pRes = lege.skrivPResept(legeM, pasient);
              reseptListe.leggTil(pRes);
              continue;
            } catch (UlovligUtskrift e){
            }
          }
        }
      }
      else if (svar == 6){
        brukerInput();
        continue;
      }
      else if (svar == 7){
        statistikk();
        continue;
      }
    }
  }
}
