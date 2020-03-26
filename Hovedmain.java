import java.io.*;

class Hovedmain {
  public static void main(String[] args) {
    Legesystem legesystem = new Legesystem();
    File fil = new File("TestFil.txt");
    legesystem.lesFraFil(fil);
    //legesystem.brukerInput();
    legesystem.brukerInput();




  }
}
