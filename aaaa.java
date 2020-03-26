public void statistikk(){
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
        Lenkeliste<Resept> resepterSpes = l.hentUtskrevedeResepter();
        for (Resept r : resepterSpes){
          if (r.hentLegemiddel() instanceof Narkotisk){
            tellerLegerNarko++;
          }
        }
        System.out.println(l.hentNavnLege() + " har skrevet ut " + tellerLegerNarko +
        " antall narkotiske legemidler");
      }
    }
    int tellerPasientNarko = 0;
    Pasient pasient = null;
    for (Pasient p : pasientListe){
      Stabel<Resept> pasientResepter = p.hentUtResepter();
      pasient = p;
      for (Resept r : pasientResepter){
        if ((r.hentReit()) > 0 && (r.hentLegemiddel() instanceof Narkotisk))
          tellerPasientNarko++;
        }
      }
      if (tellerPasientNarko > 0){
        System.out.println(pasient.hentPasientNavn() + " har gydlig narkotisk resept" +
        " og har totalt " + tellerPasientNarko + " narkotiske resepter");
      }
    }
    
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
      for (Pasient p : pasientListe){
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
          int styrke = l.styrke();
          minSkriver.write(navn + ",narkotisk," + pris + "," + virkestoff + "," + styrke);
        }
        else if (l instanceof Vanedannende){
          int styrke = l.styrke();
          minSkriver.write(navn + ",vanedannende," + pris + "," + virkestoff + "," + styrke);
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

      Lenkeliste<Resept> pasientResepter = pas.hentUtResepter();
      int nyTeller = 0;
      for (Resept r : pasientResepter){
        int reit = r.hentReit();
        String navn = r.hentLegemiddel().hentNavn();
        System.out.println(teller + ": " + navn + '(' + reit + " reit )");
        nyTeller++;
      }
      int svarRes = input.nextInt();
      if ((svarRes <= nyTeller) && (svarRes > 0)){
        Resept res = pasientResepter.hent(svarRes);
        if (res.bruk()){
          System.out.println("Brukte resept paa " + res.hentLegemiddel().hentNavn() +
          ". Antall gjenvaerende reit: " + res.hentReit());
        } else{
          System.out.println("Kunne ikke bruke resept paa " + res.hentLegemiddel().hentNavn() +
          " (ingen gjenvaerende reit)");

        }
      }
      else{
        System.out.println("Ugyldig indeks");

      }
    }
    else{
      System.out.println("Ugyldig indeks");

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
          String navn = nyObjInput.nextLine();
          System.out.println("Skriv fodselsnummer:\n");
          String fodselsnummer = nyObjInput.nextLine();
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
              Lege lege = new Lege(navn);
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
          String navn = nyObjInput.nextLine();
          try{
            System.out.println("Skriv inn type legemiddel: narkotisk/vannedannende/vanlig\n");
            String type = nyObjInput.nextLine();
            System.out.println("Skriv inn pris\n");
            double pris = nyObjInput.nextDouble();
            System.out.println("Skriv inn antall virkestoff:\n");
            double virkestoff = nyObjInput.nextDouble();
            if (type.equals("narkotisk")){
              System.out.println("Skriv inn styrke\n");
              int styrke = nyObjInput.nextInt();
              Narkotisk nark = new Narkotisk(navn, pris, virkestoff, styrke);
              legemiddelListe.leggTil(nark);
              continue;
            }
            else if (type.equals("vanedannende")){
              System.out.println("Skriv inn styrke\n");
              int styrke = nyObjInput.nextInt();
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
          Lege lege = null;
          try{
            lege = null;
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
          Legemiddel legeM = null;
          try{
            legeM = null;
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
          String pasientNavn = nyObjInput.nextLine();
          Pasient pasient = null;
          try{
            pasient = null;
            for (Pasient p : pasientListe){
              if (p.hentPasientNavn() == pasientNavn){
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
              int reit = nyObjInput.nextInt();
              HvitResept hvitResept = lege.skrivHvitResept(legeM, pasient, reit);
              reseptListe.leggTil(hvitResept);
              continue;
            } catch (UlovligUtskrift e){
            }
          }
          else if (type.equals("militaer")){
            try{
              System.out.println("Skriv inn reit: \n");
              int reit = nyObjInput.nextInt();
              MilitaerResept mili = lege.skrivMilitaerResept(legeM, pasient, reit);
              reseptListe.leggTil(mili);
              continue;
            } catch (UlovligUtskrift e){
            }
          }
          else if (type.equals("blaa")){
            try{
              System.out.println("Skriv inn reit: \n");
              int reit = nyObjInput.nextInt();
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
