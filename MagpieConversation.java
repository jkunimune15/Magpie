public class MagpieConversation
{
  public static void main(String[] args)
  {
    NovedMagpie maggie1 = new NovedMagpie();
    JustinMagpie maggie2 = new JustinMagpie();
    boolean turn = true;
    long startTime = 0;
    
    String statement = maggie1.getGreeting();
    
    while (!statement.equalsIgnoreCase("bye."))
    {
      if (turn)  System.out.print("\nNoved: ");
      else       System.out.print("\nNitsuj: ");
      System.out.println(statement);
      startTime = System.currentTimeMillis();
      while (System.currentTimeMillis() < startTime + 2000) {}
      if (turn)  statement = maggie2.getResponse(statement);
      else       statement = maggie1.getResponse(statement);
      turn = !turn;
    }
    
    while (System.currentTimeMillis() < startTime + 2000) {}
    System.out.println (statement);
  }
}
