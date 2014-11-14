import java.util.Scanner;




public class MagpieRunner
{
  public static void main(String[] args)
  {
    JustinMagpie maggie = new JustinMagpie();
    
    System.out.println (maggie.getGreeting());
    Scanner in = new Scanner (System.in);
    String statement = in.nextLine();
    
    while (!statement.equals("Bye."))
    {
      System.out.println (maggie.getResponse(statement));
      statement = in.nextLine();
    }
    
    System.out.println (maggie.getResponse(statement));
  }
}
