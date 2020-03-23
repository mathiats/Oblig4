public void brukerInput(){
    int svar = 10;
    while (svar != 6){
      Scanner input = new Scanner(System.in);
      System.out.println("Skriv ut informasjon om:\n0: Pasienter\n1: Leger\n2:" +
      " Legemiddel\n3: Resept\n 4: Fullstendig oversikt over alt\n" +
      "5: Ønsker du å legge til et objekt, tast 5\n" +
      "6: Tast 5 for å avslutte");
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
    }
