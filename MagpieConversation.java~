/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieConversation
{
  /**
   * Create a Magpie, give it user input, and print its replies.
   */
  public static void main(String[] args)
  {
    JustinMagpie maggie1 = new JustinMagpie();
    JustinMagpie maggie2 = new JustinMagpie();
    boolean turn = true;
    long startTime = 0;
    
    String statement = maggie1.getGreeting();
    
    while (!statement.equalsIgnoreCase("bye."))
    {
      startTime = System.currentTimeMillis();
      while (System.currentTimeMillis() < startTime + 1000) {}
      System.out.println(statement);
      if (turn)  statement = maggie2.getResponse(statement);
      else       statement = maggie1.getResponse(statement);
      turn = !turn;
    }
    
    while (System.currentTimeMillis() < startTime + 1000) {}
    System.out.println (statement);
  }
}
