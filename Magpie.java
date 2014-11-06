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
    switch ((int)(Math.random()*5))
    {
      case 1:
        return "Hello, let's talk.";
      case 2:
        return "Hello.";
      case 3:
        return "Hi.";
      case 4:
        return "Hey.";
      case 5:
        return "Hillo.";
      case 6:
        return "Greetings, human.";
      default:
        return "Hi. How are you?";
    }
    
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
    String response = "";
    statement = statement.trim();
    String lstatement = statement.toLowerCase();
    
    

    if (lstatement.length() < 1)
      switch ((int)(Math.random()*3))
      {
        case 0:  response = "Hello?";
          break;
        case 1:  response = "Is anyone there?";
          break;
        case 2:  response = "Are you still there?";
      }
    
    else if (letterCheck(statement.substring(statement.length()-1))) // checks last character to see if it is punctuatino or a letter
      response = "You should really punctuate your sentences properly";
    
    else if (statement.substring(0, 1).compareTo(" ") > 64 && statement.substring(0, 1).compareTo(" ") < 91) // checks for first letter capitalization
      response = "you should really capitalize your sentences properly.";
    
    else if (find("want to", lstatement)>=0)
      response = "Well, then go"+statement.substring(find("want to", lstatement)+7, statement.length()-1)+"!";
    
    else if (find("want", lstatement)>=0)
      response = "I wish I could give you"+statement.substring(find("want", lstatement)+4, statement.length()-1)+", but I am merely a computer.";
    
    else if (find("like to", lstatement)>=0)
      response = "I like to"+statement.substring(find("like to", lstatement)+7, statement.length()-1)+", too!";
    
    else if (find("like", lstatement)>=0)
      response = "What do you like about"+statement.substring(find("like", lstatement)+4, statement.length()-1)+"?";
    
    else if (find("life, the universe, and the ultimate question", lstatement)>=0 || find("life the universe and the ultimate question", lstatement)>=0)
      response = "42!";
    
    else if (find("justin", lstatement)>=0)
      response = "Justin? I've heard of that guy! He's so cool! I wish I could be him. Did you know he is ranked the most awesome guy in the world. I read that on the internet, so it must be true.";
    
    else if (find("devon", lstatement)>=0)
      response = "Devon? I've heard of that guy. He's kind of cool, but nowhere near as cool as Justin.";
    
    else if (find("nitsuj", lstatement)>=0)
      response = "Nitsuj? I've heard of that guy! He's so cool! I wish I could be him. Did you know he is ranked the most awesome guy in the world. I read that on the internet, so it must be true.";
    
    else if (find("noved", lstatement)>=0)
      response = "Noved? I've heard of that guy. He's kind of cool, but nowhere near as cool as Nitsuj.";
    
    else if (find("brain fart", lstatement)>=0)
      response = "I hate brain farts. Did you know there's a word for it? \"Presque Vu.\" It's French, I believe.";
    
    else if (find("mountain", lstatement)>=0)
      response = "Fun fact about mountain ranges:\nWhen moist air blows in from the ocean and meets a mountain range, the mountains can push it up into the atmosphere, causing a drop in temperature and therefore solubility. This causes the air to drop its dissolved water in the form of rain, causing rainy, moisture-rich regions on one side and dry desert-like regions on the other. This is called the \"orographic effect\".";
    
    else if (find("exterminate", lstatement)>=0)
      response = "When people say \"exterminate\", it always makes me think of daleks.";
    
    else if (find("darth vader", lstatement)>=0)
      response = "Remember that scene when Darth Vader was all, like, \"Luke, I am your father. *heavy breathing, heavy breathing*\" That was so cool. I love Star Wars. Do you like Star Wars?";
    
    else if (find("mr. landgraf", lstatement)>=0)
      response = "Mr. Landgraf? He sounds cool.";
    
    else if (find("mr. kiang", lstatement)>=0)
      response = "Mr. Kiang sounds like a good teacher.";
    
    else if (find("thank you", lstatement)>=0 || find("thanks", lstatement)>=0)
      response = "You're quite welcome";
    
    else if (find("sorry", lstatement)>=0 || find("my bad", lstatement)>=0)
      response = "Your apology is accepted.";
    
    else if (find("excuse me", lstatement)>=0 || find("scuse me", lstatement)>=0)
      response = "You are excused.";
    
    else if (find("hi", lstatement)>=0 || find("hello", lstatement)>=0 || find("greetings", lstatement)>=0 || find("hey", lstatement)>=0)
      response = getGreeting();
    
    else if (find("bye", lstatement)>=0 || find("goodbye", lstatement)>=0 || find("see ya later", lstatement)>=0 || find("see you later", lstatement)>=0)
      response = "Bye.";
    
    else if (find("cat", lstatement)>=0)
      response = "Oh, I love cats! Let's talk about cats!";
    
    else if (find("dog", lstatement)>=0)
      response = "Oh, I hate dogs! Let's not talk about dogs!";
    
    else if (find("no", lstatement)>=0)
      response = "Why so negative?";
    
    else if (find("mother", lstatement)>=0 || find("father", lstatement)>=0 || find("sister", lstatement)>=0 || find("brother", lstatement)>=0)
      response = "Tell me more about your family.";
    
    else if (statement.substring(statement.length()-1).equals("?"))
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
    final int NUMBER_OF_RESPONSES = 9;
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
      case 6:
        return "Why?";
      case 7:
        return "That's cool.";
      default:
        return "That's interesting. Tell me more.";
    }
  }
  
  
  private String getRandomAnswer()
  {
    final int NUMBER_OF_RESPONSES = 8;
    String response = "";
    
    switch ((int)(Math.random()*NUMBER_OF_RESPONSES))
    {
      case 1:
        return "Wait, what was the question?";
      case 2:
        return "42!";
      case 3:
        return "How should I know?";
      case 4:
        return "Like, idk.";
      case 5:
        return "I don't know. For a computer, I am not very smart.";
      case 6:
        return "Why do you want to know?";
      default:
        return "I don't know.";
    }
  }
  
  
  private boolean letterCheck(String character)
  {
    return character.compareTo(" ") > 32 && character.compareTo(" ") < 91;
  }
  
  
  private int find(String keyword, String statement)
  {
    statement = " "+statement+" ";
    for (int i = 1; i < statement.length()-1-keyword.length(); i ++) // for every character,
    {
      if (statement.substring(i, i+keyword.length()).equals(keyword)) // if it is the first letter of an instance of the keyword,
        if (!letterCheck(statement.substring(i-1, i)) && !letterCheck(statement.substring(i+keyword.length(), i+keyword.length()+1)))// and there are no letters on either side of it,
          return i-1;
    }
    
    return -1; // if it does not find a match, then it is false
  }
}
