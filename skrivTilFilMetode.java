public void skrivTilFil(){
    try{
      File nyFil = new File("nyfil.txt");
      if (nyFil.createNewFile()){
        System.out.println("Ny fil er opprettet med navn: " + nyFil.getName());
      } else{
        System.out.println("Filen finnes allerede");
      }
    } catch (IOException e){
      System.out.println("En feil har oppstaatt");
      e.printStackTrace();
    }
    try{
      FileWriter minSkriver = new FileWriter("nyfil.txt");
      for (Pasient p : pasientLise){
        minSkriver.write("# Pasienter (navn, fnr)");
        String navn = p.hentPasientNavn();
        String fnr = p.hentPasientFnr();
        minSkriver.write(navn + "," + fnr);
      }
      for (Legemiddel l : legemiddelListe){
        minSkriver.write("# Legemidler (navn,type,pris,virkestoff,[styrke])");
        String navn = l.hentNavn();
        double pris = l.hentPris();
        double virkestoff = l.hentVirkestoff();
        if (l instanceof Narkotisk){
          int styrke = l.hentNarkotiskStyrke();
          minSkriver.write(navn + ",narkotisk," + pris + "," + virkestoff + "," + styrke);
        }
        else if (l instanceof Vanedannende){
          int styrke = l.hentVanedannendeStyrke();
          minSkriver.write(navn + ",vanedannende," + pris + "," + virkestoff "," + styrke);
        }
        else{
          minSkriver.write(navn + ",vanlig," + pris + "," + virkestoff);
        }
      }
      for (Lege lege : legeListe){
        minSkriver.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
        String navn = lege.hentNavnLege();
        if (lege instanceof Spesialist){
          int kontrollId = lege.hentKontrollID();
          minSkriver.write(navn + "," + kontrollId);
        } else{
          minSkriver.write(navn + ",0");
        }
      }
      for (Resept resept : reseptListe){
        minSkriver.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
        int legemiddelNummer = resept.hentLegemiddel().hentId();
        String legeNavn = resept.hentLege().hentNavnLege();
        int pasientId = resept.hentPasient().hentPasientId();
        if (resept instanceof BlaaResept){
          int reit = resept.hentReit();
          minSkriver.write(legemiddelNummer + "," + legeNavn + "," + pasientId +
          ",blaa," + reit);
        }
        else if (resept instanceof PResept){
          minSkriver.write(legemiddelNummer + "," + legeNavn + "," + pasientId + ",p,");
        }
        else if (resept instanceof MilitaerResept){
          int reit = resept.hentReit();
          minSkriver.write(legemiddelNummer + "," + legeNavn + "," + pasientId +
          ",millitaer," + reit);
        }
        else if (resept instanceof HvitResept){
          int reit = resept.hentReit();
          minSkriver.write(legemiddelNummer + "," + legeNavn + "," + pasientId +
          ",hvit," + reit);
        }
      }
      minSkriver.close();
      System.out.println("Fulloert skriving til fil");
    } catch (IOException e){
      System.out.println("En feil inntraff");
      e.printStackTrace();
    }
  }
