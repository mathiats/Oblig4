class Vanlig extends Legemiddel{

  public Vanlig(String n, double p, double v){
    super(n,p,v); //Kaller på superklassen sin konstruktør.
  }

  @Override
  public String toString(){ //Legger til hva salgs legemiddel det er.
    return ("\n" + super.toString() + "\n" + "Type legemiddel: Vanlig");
  }

  @Override
  public int styrke(){
    return 0;
  }

}
