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
  
  private String[] answer = {"Wait, what was the question?", "Yes.", "How should I know?", "Aboslutely.", "Absolutely...not!", "Like, idk.",
    "I don't know. For a computer, I am not very smart.", "Why do you want to know?", "I don't know.", "No.", "Absolutely not.", "Maybe.", "Possibly.",
    "That depends. Who's asking?.", "Affirmative.", "Negative.", "Affirmatory.", "Negatory.", "I think so.", "I don't think so.", "Probably.", "Probably not."};
  
  private String[] confirmation = {"Okay.", "On it.", "Will do!", "Yes, sir - I mean, ma'am, I mean boss, I mean puba!", "Sure.", "I will do my best."};
  
  private String[] auxVerbs = {"could", "can", "would", "will", "should", "shall", "could", "may", "may", "might", "did", "do", "did", "does", "was", "is", "were",
    "are", "was", "am", "had to", "must", "had", "have", "had", "has"};
  
  private String[] verbs = {"run", "throw", "eat", "drink", "hug", "love", "hate", "hit", "break", "work", "tally", "marry", "donate", "believe", "fill",
    "kill", "bring", "lie", "enjoy", "laugh", "play", "stand", "lay", "review", "write", "read", "live", "make", "understand", "bake", "open", "close", "let"};
  
  
  
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
    lstatement = toSecondPerson(lstatement);
    lstatement = unContract(lstatement);
    
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
    
    else if (find("your name is", lstatement)>=0 && find("your name is", lstatement)<lstatement.length()-14)
      response = nameUpdate(lstatement.substring(find("your name is", lstatement)+13, lstatement.length()-1));
    
    else if (find("you are", lstatement)>=0 && find("you are", lstatement)<lstatement.length()-9)
      response = nameUpdate(lstatement.substring(find("you are", lstatement)+8, lstatement.length()-1));
    
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
    
    else if (find("shit", lstatement)>=0 || find("damn", lstatement)>=0 || find("frick", lstatement)>=0 || find("bitch", lstatement)>=0 || find("crap", lstatement)>=0 || find("oh your god", lstatement)>=0)
      response = "Watch your language!";
    
    else if (statement.substring(statement.length()-1).equals("?")) // interrogative sentence
    {
      if (find("what", lstatement) == 0)
        response = "I don't know. What?";
      else if (find("who", lstatement) == 0)
        response = "You're mom.";
      else if (find("why", lstatement) == 0)
        response = "'cause can.";
      else if (find("when", lstatement) == 0)
        response = "Get-yourself-a-watch o'clock.";
      else if (find("where", lstatement) == 0)
        response = "A long time ago in a galaxy far far away.";
      else if (find("which", lstatement) == 0)
        response = "The blue one.";
      else if (find("how", lstatement) == 0)
      {
        int end = 4;
        while (letterCheck(lstatement.substring(end)))
          end++;
        response = lstatement.substring(4, end) + " enough.";
        
        for (String v: auxVerbs)
          if (find(v, lstatement) == 4)
            response = "How "+v+" anything?";
      }
      else
        response = answer[x%answer.length];
    }
    
    else if (imperativeCheck(lstatement))
    {
      response = confirmation[x%confirmation.length];
    }
    
    else
      response = statementConversion(lstatement); // declarative sentence
    
    if (!userName.equals("") && x%7 == 6) // addresses you by name sometimes.
      response = response.substring(0, response.length()-1) + ", " + userName + response.substring(response.length()-1, response.length());
    
    response = response.substring(0,1).toUpperCase() + response.substring(1);
    
    x += (int)(Math.random()*2+2); // increments x at a fairly random rate
    return response;
  }
  
  
  private boolean imperativeCheck(String sentence)
  {
    for (String v: verbs)
      if (find(v, sentence) == 0)
        return true;
    for (String v: auxVerbs)
      if (find(v, sentence) == 0)
        return true;
    return false;
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
  
  
  private String nameUpdate(String newName)
  {
    String response;
    newName = newName.substring(0,1).toUpperCase() + newName.substring(1); // capitalizes name
    if (!userName.equals("") && !userName.equals(newName))
      response = "I thought your name was " + newName + ".";
    else
      response = "Nice to meet you, " + newName + ". My name is Smitty Werbenjagermanjensen.";
    userName = newName;
    userName = userName.substring(0,1).toUpperCase() + userName.substring(1); // capitalizes name
    return response;
  }
  
  
  private String toSecondPerson(String statement)
  {
    String newStatement = "";
    for (int i = 0; i < statement.length();)
    {
      if (!letterCheck(statement.substring(i)))
      {
        newStatement += statement.substring(i, i+1);
        i ++;
      }
      else if (find("i", statement.substring(i)) == 0)
      {
        newStatement += "you";
        i += 1;
      }
      else if (find("me", statement.substring(i)) == 0)
      {
        newStatement += "you";
        i += 2;
      }
      else if (find("am", statement.substring(i)) == 0)
      {
        newStatement += "are";
        i += 2;
      }
      else if (find("you are", statement.substring(i)) == 0)
      {
        newStatement += "i am";
        i += 7;
      }
      else if (find("you", statement.substring(i)) == 0)
      {
        newStatement += "me";
        i += 3;
      }
      else if (find("my", statement.substring(i)) == 0)
      {
        newStatement += "your";
        i += 2;
      }
      else if (find("your", statement.substring(i)) == 0)
      {
        newStatement += "my";
        i += 4;
      }
      else if (find("mine", statement.substring(i)) == 0)
      {
        newStatement += "yours";
        i += 4;
      }
      else if (find("yours", statement.substring(i)) == 0)
      {
        newStatement += "mine";
        i += 5;
      }
      else
      {
        while (letterCheck(statement.substring(i)))
        {
          newStatement += statement.substring(i, i+1);
          i ++;
        }
      }
    }
    
    return newStatement;
  }
  
  
  private String statementConversion(String statement)
  {
    switch (x%3)
    {
      case 1:
      for (String v: auxVerbs)
        if (find(v, statement) >= 0)
        return "Why " + v + " " + statement.substring(0, find(v, statement)-1) + statement.substring(find(v, statement)+v.length(), statement.length()-1) + "?";
      for (String v: verbs)
        if (find(v, statement) >= 0)
        return "Why do " + statement.substring(0, statement.length()-1) + "?";
      for (String v: verbs)
        if (find(pastTense(v), statement) >= 0)
        return "Why did " + statement.substring(0, find(pastTense(v), statement)) + v + statement.substring(find(pastTense(v), statement)+pastTense(v).length(), statement.length()-1) + "?";
      for (String v: verbs)
        if (find(plural(v), statement) >= 0)
        return "Why does " + statement.substring(0, find(plural(v), statement)) + v + statement.substring(find(plural(v), statement)+plural(v).length(), statement.length()-1) + "?";
      break;
      case 2:
      for (String v: auxVerbs)
        if (find(v, statement) >= 0)
        return v + " " + statement.substring(0, find(v, statement)-1) + statement.substring(find(v, statement)+v.length(), statement.length()-1) + "? Really?";
      for (String v: verbs)
        if (find(v, statement) >= 0)
        return "Do " + statement.substring(0, statement.length()-1) + "? Really?";
      for (String v: verbs)
        if (find(pastTense(v), statement) >= 0)
        return "Did " + statement.substring(0, find(pastTense(v), statement)) + v + statement.substring(find(pastTense(v), statement)+pastTense(v).length(), statement.length()-1) + "? Really?";
      for (String v: verbs)
        if (find(plural(v), statement) >= 0)
        return "Does " + statement.substring(0, find(plural(v), statement)) + v + statement.substring(find(plural(v), statement)+plural(v).length(), statement.length()-1) + "? Really?";
      break;
      default:
      for (String v: auxVerbs)
        if (find(v, statement) >= 0)
        return "I did not know " + statement.substring(0, find(v, statement)-1) + " " + pastTense(v) + statement.substring(find(v, statement)+v.length(), statement.length());
      for (String v: verbs)
        if (find(v, statement) >= 0)
        return "I did not know " + statement.substring(0, find(v, statement)-1) + " " + pastTense(v) + statement.substring(find(v, statement)+v.length(), statement.length());
      for (String v: verbs)
        if (find(pastTense(v), statement) >= 0)
        return "I did not know " + statement;
      for (String v: verbs)
        if (find(plural(v), statement) >= 0)
        return "I did not know " + statement.substring(0, find(v, statement)-1) + " " + pastTense(v) + statement.substring(find(v, statement)+v.length(), statement.length());
    }    
    return reply[x%reply.length];
  }
  
  
  private String pastTense(String word)
  {
    for (int i = 0; i < auxVerbs.length; i ++)
    {
      if (word.equals(auxVerbs[i]))
        return auxVerbs[(i/2)*2];
    }
    if (word.equals("play"))
      return "played";
    if (word.equals("let"))
      return "let";
    else if (word.substring(word.length()-3).equals("eat"))
      return word.substring(0, word.length()-3) + "ate";
    else if (word.substring(word.length()-3).equals("in"))
      return word.substring(0, word.length()-3) + "an";
    else if (word.substring(word.length()-3).equals("ink"))
      return word.substring(0, word.length()-3) + "ank";
    else if (word.substring(word.length()-3).equals("ing"))
      return word.substring(0, word.length()-3) + "ang";
    else if (word.substring(word.length()-3).equals("and"))
      return word.substring(0, word.length()-3) + "ood";
    else if (word.substring(word.length()-2).equals("ow"))
      return word.substring(0, word.length()-2) + "ew";
    else if (word.substring(word.length()-3).equals("ite"))
      return word.substring(0, word.length()-3) + "ote";
    else if (word.substring(word.length()-2).equals("un"))
      return word.substring(0, word.length()-2) + "an";
    else if (word.substring(word.length()-2).equals("ay"))
      return word.substring(0, word.length()-2) + "aid";
    else if (word.substring(word.length()-2).equals("ey"))
      return word.substring(0, word.length()-2) + "eyed";
    else if (word.substring(word.length()-2).equals("oy"))
      return word.substring(0, word.length()-2) + "oyed";
    else if (word.substring(word.length()-1).equals("y"))
      return word.substring(0, word.length()-1) + "ied";
    else if (word.substring(word.length()-1).equals("e"))
      return word + "d";
    else
      return word + "ed";
  }
  
  
  private String plural(String word)
  {
    if (word.substring(word.length()-1).equals("f"))
      return word.substring(0, word.length()-1) + "ves";
    else if (word.substring(word.length()-2).equals("ay"))
      return word.substring(0, word.length()-2) + "ays";
    else if (word.substring(word.length()-2).equals("ey"))
      return word.substring(0, word.length()-2) + "eys";
    else if (word.substring(word.length()-2).equals("oy"))
      return word.substring(0, word.length()-2) + "oys";
    else if (word.substring(word.length()-1).equals("y"))
      return word.substring(0, word.length()-1) + "ies";
    else if (word.substring(word.length()-2).equals("us"))
      return word.substring(0, word.length()-2) + "i";
    else if (word.substring(word.length()-1).equals("s"))
      return word + "es";
    else
      return word + "s";
  }
  
  
  private String unContract(String statement)
  {
    while (find("can't", statement) >= 0)
      statement = statement.substring(0, find("can't", statement)) + "cannot" + statement.substring(find("can't", statement)+5);
    while (find("won't", statement) >= 0)
      statement = statement.substring(0, find("won't", statement)) + "will not" + statement.substring(find("won't", statement)+5);
    while (find("shan't", statement) >= 0)
      statement = statement.substring(0, find("shan't", statement)) + "shall not" + statement.substring(find("shan't", statement)+6);
    while (find("ain't", statement) >= 0)
      statement = statement.substring(0, find("ain't", statement)) + "are not" + statement.substring(find("ain't", statement)+5);
    while (find("let's", statement) >= 0)
      statement = statement.substring(0, find("let's", statement)) + "let us" + statement.substring(find("let's", statement)+5);
    while (find("i'm", statement) >= 0)
      statement = statement.substring(0, find("i'm", statement)) + "i am" + statement.substring(find("i'm", statement)+3);
    while (statement.indexOf("n't") >= 0)
      statement = statement.substring(0, statement.indexOf("n't")) + " not" + statement.substring(statement.indexOf("n't")+3);
    while (statement.indexOf("'re") >= 0)
      statement = statement.substring(0, statement.indexOf("'re")) + " are" + statement.substring(statement.indexOf("'re")+3);
    while (statement.indexOf("'ll") >= 0)
      statement = statement.substring(0, statement.indexOf("'ll")) + " will" + statement.substring(statement.indexOf("'ll")+3);
    while (statement.indexOf("'d") >= 0)
      statement = statement.substring(0, statement.indexOf("'d")) + " did" + statement.substring(statement.indexOf("'d")+2);
    while (statement.indexOf("'t") >= 0)
      statement = statement.substring(0, statement.indexOf("'t")) + "it " + statement.substring(statement.indexOf("'t")+2);
    while (statement.indexOf("'ve") >= 0)
      statement = statement.substring(0, statement.indexOf("'ve")) + " have" + statement.substring(statement.indexOf("'ve")+3);
    
    return statement;
  }
}