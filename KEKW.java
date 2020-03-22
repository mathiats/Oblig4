mport java.io.*;
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
            if (l.hentNavn().equals(legenavn)) {
              lege = l;
            }
          }


      Pasient pasient = null;
      for (Pasient p : pasientListe) {
        if (p.hentID() == pasID) {
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
          Presept pResept = lege.skrivPResept(legemiddelet, pasient);
          reseptListe.leggTil(pResept);
        } catch(UlovligUtskrift e){}
    }

   }


}
}













public static void brukerInput(){
  int svar = 15;
  while(svar != 5){
    Scanner input = new Scanner(System.in);
    System.out.println("Skriv ut informasjon om:\n0: Pasienter\n1: Leger\n2: Legemiddel\n3: Resept\n4: Ønsker du å legge til et objekt, tast 4\n5:T ast 5 for å avslutte");
    svar = input.nextInt();
    if(svar== 0){
      System.out.println("Skriver ut informasjon om pasienter:");
      for(Pasient p : pasientListe){
        System.out.println(p);
        System.out.println();

      }



    }
    else if (svar == 1){
      System.out.println("Skriver ut informasjon om Leger:");
      for(Lege l: legeListe){
        System.out.println(l.hentNavn());
        System.out.println();
      }
    }
    else if(svar == 2){
      System.out.println("Skriver ut informasjon om legemiddel:");
      for(Legemiddel l: legemiddelListe){
        System.out.println(l.toString());
        System.out.println();
      }
    }
    else if(svar ==3){
      System.out.println("Skriver ut informasjon om resept:");
      for(Resept r: reseptListe){
        System.out.println(r.toString());
        System.out.println();
    }
  }
  else if (svar==4){
    Scanner inputet = new Scanner(System.in);
    System.out.println("Hilken type objekt onsker du aa legge til? Pasient/Lege/Legemiddel");
    String svaret = inputet.nextLine();
    if (svaret.equals("Pasient")){
      System.out.println("Skriv navnet: ");
      String navn = inputet.nextLine();
      System.out.println("Skriv fodselsnummer: ");
      String fodselsnummer = inputet.nextLine();
      
    }
  }
}

}



}

  
