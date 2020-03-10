class Legesystem{
  private SortertLenkeliste<Lege> legeListe;
  private Lenkeliste<Resept> reseptListe;
  private Lenkeliste<Legemiddel> legemiddelListe;
  private Lenkeliste<Pasient> pasientListe;

  public void lesFraFil(String filnavn){
        File fil = new File(filnavn);
        Scanner scanner = new Scanner(fil);
        
        
        while(fil.hasNextLine()){
         String linje = fil.
            String[] biter= fil.split(",");
            if biter[0].contains("Pasient"){
                fil.nextLine;
                while(!(biter[0].contains("#")){
                    String navn = biter[0];
                    String fnr = biter[1];
                    Pasient pasient= new Pasient(navn,fnr);
                    pasientListe.leggTil(pasient);
                    fil.nextLine;
                }
                if biter[0].contains("Pasient"){
                fil.nextLine;

                if biter[0].contains("Pasient"){
                fil.nextLine;

 }

}
