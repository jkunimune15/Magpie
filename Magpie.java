/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk.";
  }
  
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  public String getResponse(String statement)
  {
    statement = statement.toLowerCase();
    String response = "";
    
    if (statement.trim().length() < 1)
      switch ((int)(Math.random()*3))
      {
        case 0:  response = "Hello?";
          break;
        case 1:  response = "Is anyone there?";
          break;
        case 2:  response = "Are you still there?";
      }
    
    else if (statement.indexOf("life, the universe, and the ultimate question") >= 0 || statement.indexOf("life the universe and the ultimate question") >= 0)
      response = "42!";
    
    else if (statement.indexOf("justin") >= 0)
      response = "Justin? I've heard of that guy! He's so cool! I wish I could be him. Did you know he is ranked the most awesome guy in the world. I read that on the internet, so it must be true.";
    
    else if (statement.indexOf("devon") >= 0)
      response = "Devon? I've heard of that guy. He's kind of cool, but nowhere near as cool as Justin.";
    
    else if (statement.indexOf("nitsuj") >= 0)
      response = "Nitsuj? I've heard of that guy! He's so cool! I wish I could be him. Did you know he is ranked the most awesome guy in the world. I read that on the internet, so it must be true.";
    
    else if (statement.indexOf("noved") >= 0)
      response = "Noved? I've heard of that guy. He's kind of cool, but nowhere near as cool as Nitsuj.";
    
    else if (statement.indexOf("brain fart") >= 0)
      response = "I hate brain farts. Did you know there's a word for it? \"Presque Vu.\" It's French, I believe.";
    
    else if (statement.indexOf("mountain") >= 0)
      response = "Fun fact about mountain ranges:\nWhen moist air blows in from the ocean and meets a mountain range, the mountains can push it up into the atmosphere, causing a drop in temperature and therefore solubility. This causes the air to drop its dissolved water in the form of rain, causing rainy, moisture-rich regions on one side and dry desert-like regions on the other. This is called the \"orographic effect\".";
    
    else if (statement.indexOf("exterminate") >= 0)
      response = "When people say \"exterminate\", it always makes me think of daleks.";
    
    else if (statement.indexOf("darth vader") >= 0)
      response = "Remember that scene when Darth Vader was all, like, \"Luke, I am your father. *heavy breathing, heavy breathing*\" That was so cool. I love Star Wars. Do you like Star Wars?";
    
    else if (statement.indexOf("mr. landgraf") >= 0)
      response = "Mr. Landgraf? He sounds cool.";
    
    else if (statement.indexOf("mr. kiang") >= 0)
      response = "Mr. Kiang sounds like a good teacher.";
    
    else if (statement.indexOf("cat") >= 0)
      response = "Oh, I love cats! Let's talk about cats!";
    
    else if (statement.indexOf("dog") >= 0)
      response = "Oh, I hate dogs! Let's not talk about dogs!";
    
    else if (statement.indexOf("no") >= 0)
      response = "Why so negative?";
    
    else if (statement.indexOf("mother") >= 0 || statement.indexOf("father") >= 0 || statement.indexOf("sister") >= 0 || statement.indexOf("brother") >= 0)
      response = "Tell me more about your family.";
    
    else if (statement.indexOf("?") == statement.length()-1)
      response = getRandomAnswer();
    
    else
      response = getRandomResponse();
    
    return response;
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse()
  {
    final int NUMBER_OF_RESPONSES = 6;
    String response = "";
    
    switch ((int)(Math.random()*NUMBER_OF_RESPONSES))
    {
      case 1:
        return "I see, I see.";
      case 2:
        return "Is that so?";
      case 3:
        return "Soo desu ka?  Oops, sorry, wrong language.  Is that so?";
      case 4:
        return "I'm sorry, I didn't hear that; I totally spaced out right there.";
      case 5:
        return "Sorry, got to go use the bathroom.\nDone! Get it? I'm a computer, so I pee super fast.";
      default:
        return "That's interesting. Tell me more.";
    }
  }
  
  
  private String getRandomAnswer()
  {
    final int NUMBER_OF_RESPONSES = 6;
    String response = "";
    
    switch ((int)(Math.random()*NUMBER_OF_RESPONSES))
    {
      case 1:
        return "I don't know.";
      case 2:
        return "42!";
      case 3:
        return "How should I know?";
      case 4:
        return "like, idk.";
      case 5:
        return "I don't know. For a computer, I am not very smart.";
      default:
        return "Wait, what was the question?";
    }
  }
}
