public class MagpieConversation
{
  public static void main(String[] args)
  {
    JustinMagpie maggie1 = new JustinMagpie();
    NovedMagpie maggie2 = new NovedMagpie();
    boolean turn = true;
    long startTime = 0;
    
    String statement = maggie1.getGreeting();
    
    while (!statement.equalsIgnoreCase("bye."))
    {
      if (turn)  System.out.print("\nNitsuj: ");
      else       System.out.print("\nNoved:  ");
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
