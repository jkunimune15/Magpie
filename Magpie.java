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
  private int x; // cycles through greetings below
  
  private String[] greeting = {"Hello, let's talk.", "Hello.", "Hi.", "Hey.", "Hillo.", "Greetings, human.", "Hi. How are you?"};
  
  private String[] lonelyMessage = {"Hello?", "Is anyone there?", "Are you still there?", "I can hear you breathing."};
  
  private String[] reply = {"I see, I see.", "Is that so?", "That's cool.", "Soo desu ka? Oops, sorry, wrong language. Is that so?",
    "That's interesting. Tell me more.", "I'm sorry, I didn't hear that; I totally spaced out right there.", "Really?"};
  
  private String[] answer = {"Wait, what was the question?", "How should I know?", "Like, idk.", "I don't know. For a computer, I am not very smart.",
    "Why do you want to know?", "I don't know."};
  
  
  
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return greeting[x%greeting.length];
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
    String lstatement = statement.toLowerCase(); // I edit "lstatement" before processing, but I keep "statement" as the original statement verbatim
    lstatement = replace("you", "MEPLACEHOLDER", lstatement); // changes all the pronouns
    lstatement = replace("i", "you", lstatement);
    lstatement = replace("me", "you", lstatement);
    lstatement = replace("MEPLACEHOLDER", "me", lstatement);
    lstatement = replace("your", "MYPLACEHOLDER", lstatement);
    lstatement = replace("my", "your", lstatement);
    lstatement = replace("MYPLACEHOLDER", "my", lstatement);
    lstatement = replace("am", "are", lstatement);

    if (lstatement.length() < 1)
      response = lonelyMessage[x%lonelyMessage.length];
    
    else if (letterCheck(statement.substring(statement.length()-1))) // checks last character to see if it is punctuatino or a letter
      response = "You should really punctuate your sentences properly";
    
    else if (statement.substring(0, 1).compareTo(" ") > 64 && statement.substring(0, 1).compareTo(" ") < 91) // checks for first letter capitalization
      response = "you should really capitalize your sentences properly.";
    
    else if (find("thank me", lstatement)>=0 || find("thanks", lstatement)>=0)
      response = "You're quite welcome";
    
    else if (find("sorry", lstatement)>=0 || find("your bad", lstatement)>=0)
      response = "Your apology is accepted.";
    
    else if (find("excuse you", lstatement)>=0 || find("scuse you", lstatement)>=0)
      response = "You are excused.";
    
    else if (find("hi", lstatement)>=0 || find("hello", lstatement)>=0 || find("greetings", lstatement)>=0 || find("hey", lstatement)>=0)
      response = getGreeting();
    
    else if (find("bye", lstatement)>=0 || find("goodbye", lstatement)>=0 || find("see ya later", lstatement)>=0 || find("see you later", lstatement)>=0)
      response = "Bye.";
    
    else if (find("yes", lstatement)>=0)
      response = "I see.";
    
    else if (find("no", lstatement)>=0)
      response = "Okay.";
    
    else if (find("you", lstatement)>=0 && find("you", lstatement)<find("me", lstatement) && lstatement.substring(find("you", lstatement)+3, find("me", lstatement)).indexOf(".") == -1 && lstatement.substring(find("you", lstatement)+3, find("me", lstatement)).indexOf(",") == -1)
      response = "Why do you"+lstatement.substring(find("you", lstatement)+3, find("me", lstatement))+"me?";
    
    else if (find("you want to", lstatement)>=0)
      response = "Well, then go"+lstatement.substring(find("you want to", lstatement)+11, lstatement.length()-1)+"!";
    
    else if (find("you want", lstatement)>=0)
      response = "I wish I could give you"+lstatement.substring(find("you want", lstatement)+8, lstatement.length()-1)+", but I am merely a computer.";
    
    else if (find("you like to", lstatement)>=0)
      response = "I like to"+lstatement.substring(find("you like to", lstatement)+11, lstatement.length()-1)+", too!";
    
    else if (find("you like", lstatement)>=0)
      response = "What do you like about"+lstatement.substring(find("you like", lstatement)+8, lstatement.length()-1)+"?";
    
    else if (find("try", lstatement)>=0)
      response = "No! Do or do not. There is no try.";
    
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
    
    else if (find("cat", lstatement)>=0)
      response = "Oh, I love cats! Let's talk about cats!";
    
    else if (find("dog", lstatement)>=0)
      response = "Oh, I hate dogs! Let's not talk about dogs!";
    
    else if (find("mother", lstatement)>=0 || find("father", lstatement)>=0 || find("sister", lstatement)>=0 || find("brother", lstatement)>=0)
      response = "Tell me more about your family.";
    
    else if (statement.substring(statement.length()-1).equals("?"))
      response = answer[x%answer.length];
    
    else
      response = reply[x%reply.length];
    
    x += (int)(Math.random()*2+2); // increments x at a fairly random rate
    return response;
  }
  
  
  private boolean letterCheck(String character)
  {
    return character.compareTo(" ") > 32 && character.compareTo(" ") < 91;
  }
  
  
  private int find(String keyword, String statement)
  {
    statement = " "+statement+" ";
    for (int i = 1; i < statement.length()-keyword.length(); i ++) // for every character,
    {
      if (statement.substring(i, i+keyword.length()).equals(keyword)) // if it is the first letter of an instance of the keyword,
        if (!letterCheck(statement.substring(i-1, i)) && !letterCheck(statement.substring(i+keyword.length(), i+keyword.length()+1)))// and there are no letters on either side of it,
          return i-1;
    }
    
    return -1; // if it does not find a match, then it is false
  }
  
  
  private String replace(String keyword, String replacement, String statement)
  {
    for (int i = 0; i < statement.length()/keyword.length(); i ++) // runs multiple times in case of multiple instances
      if (find(keyword, statement)>=0)
      {
        statement = statement.substring(0, find(keyword, statement)) + replacement + statement.substring(find(keyword, statement)+keyword.length());
      }
    
    return statement;
  }
}