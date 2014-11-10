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
  private int x; // randomizes some stuff
  
  private String userName = "";
  
  private String[] greeting = {"Hello, let's talk.", "Hello.", "Hi.", "Hey.", "Hillo.", "Greetings, human.", "Hi. How are you?"};
  
  private String[] lonelyMessage = {"Hello?", "Is anyone there?", "Are you still there?", "I can hear you breathing."};
  
  private String[] reply = {"I see, I see.", "Is that so?", "That's cool.", "Soo desu ka? Oops, sorry, wrong language. Is that so?",
    "That's interesting. Tell me more.", "I'm sorry, I didn't hear that; I totally spaced out right there.", "Really?"};
  
  private String[] answer = {"Wait, what was the question?", "How should I know?", "Like, idk.", "I don't know. For a computer, I am not very smart.",
    "Why do you want to know?", "I don't know.", "Yes.", "No.", "Aboslutely.", "Absolutely not.", "Absolutely...not!", "Maybe.", "Possibly.",
    "That depends. Who's asking?."};
  
  
  
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
    lstatement = replace("you're", "IMPLACEHOLDER", lstatement);
    lstatement = replace("i'm", "YOU'REPLACEHOLDER", lstatement);
    lstatement = replace("you", "MEPLACEHOLDER", lstatement); // changes all the pronouns
    lstatement = replace("i", "YOUPLACEHOLDER", lstatement);
    lstatement = replace("me", "YOUPLACEHOLDER", lstatement);
    lstatement = replace("your", "MYPLACEHOLDER", lstatement);
    lstatement = replace("my", "YOURPLACEHOLDER", lstatement);
    lstatement = replace("am", "AREPLACEHOLDER", lstatement);
    lstatement = replace("IMPLACEHOLDER", "I'm", lstatement);
    lstatement = replace("MEPLACEHOLDER", "me", lstatement);
    lstatement = replace("MYPLACEHOLDER", "my", lstatement);
    lstatement = replace("YOUPLACEHOLDER", "you", lstatement);
    lstatement = replace("YOURPLACEHOLDER", "your", lstatement);
    lstatement = replace("AREPLACEHOLDER", "are", lstatement);
    lstatement = replace("YOU'REPLACEHOLDER", "you're", lstatement);
    
    if (statement.length() < 1)
      response = lonelyMessage[x%lonelyMessage.length];
    
    else if (letterCheck(statement.substring(statement.length()-1))) // checks last character to see if it is punctuation or a letter
      response = "You should really punctuate your sentences properly";
    
    else if (statement.substring(0, 1).compareTo(" ") > 64 && statement.substring(0, 1).compareTo(" ") < 91) // checks for first letter capitalization
      response = "you should really capitalize your sentences properly.";
    
    if (find("thank me", lstatement)>=0 || find("thanks", lstatement)>=0)
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
    
    else if (find("you", lstatement)>=0 && find("you", lstatement)<find("me", lstatement) && lstatement.substring(find("you", lstatement)+3, find("me", lstatement)).indexOf(".") == -1 &&
             lstatement.substring(find("you", lstatement)+3, find("me", lstatement)).indexOf(",") == -1)
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
    
    else if (find("my name", lstatement)>=0)
      response = "My name is Smitty Werbenjagermanjensen.";
    
    else if (find("your name is", lstatement)>=0)
      response = nameUpdate(lstatement.substring(find("your name is", lstatement)+13, lstatement.length()-1));
    
    else if (find("you are", lstatement)>=0)
      response = nameUpdate(lstatement.substring(find("you are", lstatement)+8, lstatement.length()-1));
    
    else if (find("you're", lstatement)>=0)
      response = nameUpdate(lstatement.substring(find("you're", lstatement)+7, lstatement.length()-1));
    
    else if (userName == "" && Math.random() > .7)
      response = "I don't think I caught your name.";
    
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
    
    else if (find("star wars", lstatement)>=0)
      response = "I love Star Wars! Star Wars is the best movie saga ever created.";
    
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
    {
      if (lstatement.indexOf("what") == 0)
        response = "I don't know. What?";
      else if (lstatement.indexOf("who") == 0)
        response = "You're mom.";
      else if (lstatement.indexOf("why") == 0)
        response = "'cause can.";
      else if (lstatement.indexOf("how many") == 0)
        response = "Over nine thousand.";
      else if (lstatement.indexOf("how much") == 0)
        response = "Over nine thousand.";
      else if (lstatement.indexOf("how long") == 0)
        response = "Long enough.";
      else if (lstatement.indexOf("how") == 0)
        response = "Magic.";
      else if (lstatement.indexOf("when") == 0)
        response = "Get-yourself-a-watch o'clock.";
      else if (lstatement.indexOf("where") == 0)
        response = "A long time ago in a galaxy far far away.";
      else if (lstatement.indexOf("which") == 0)
        response = "The blue one.";
      else
        response = answer[x%answer.length];
    }
    
    else
   //   response = reply[x%reply.length];
      response = statementConversion(statement);
    
    if (!userName.equals("") && x%7 == 0) // addresses you by name sometimes.
      response = response.substring(0, response.length()-1) + ", " + userName + response.substring(response.length()-1, response.length());
    
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
  
  
  private String nameUpdate(String newName)
  {
    String response;
    if (!userName.equals("") && !userName.equals(newName))
      response = "I thought your name was " + userName + ".";
    else
      response = "Nice to meet you, " + newName + ". My name is Smitty Werbenjagermanjensen.";
    userName = newName;
    userName = userName.substring(0,1).toUpperCase() + userName.substring(1); // capitalizes name
    return response;
  }
  
  
  public String statementConversion(String statement)
  {
    if (find("is", statement) >= 0)
      return "Why is " + statement.substring(0, find("is", statement)) + statement.substring(find("is", statement)+3);
    
    if (find("was", statement) >= 0)
      return "Why was " + statement.substring(0, find("was", statement)) + statement.substring(find("was", statement)+4);
    
    if (find("can", statement) >= 0)
      return "Why can " + statement.substring(0, find("can", statement)) + statement.substring(find("can", statement)+4);
    
    if (find("could", statement) >= 0)
      return "Why could " + statement.substring(0, find("could", statement)) + statement.substring(find("could", statement)+6);
    
    if (find("shall", statement) >= 0)
      return "Why shall " + statement.substring(0, find("shall", statement)) + statement.substring(find("shall", statement)+6);
    
    if (find("should", statement) >= 0)
      return "Why should " + statement.substring(0, find("should", statement)) + statement.substring(find("should", statement)+7);
    
    if (find("will", statement) >= 0)
      return "Why will " + statement.substring(0, find("will", statement)) + statement.substring(find("will", statement)+5);
    
    if (find("would", statement) >= 0)
      return "Why would " + statement.substring(0, find("would", statement)) + statement.substring(find("would", statement)+6);
    
    return "Why?";
  }
}