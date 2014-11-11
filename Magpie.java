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
  
  private String[] answer = {"Wait, what was the question?", "Yes.", "Aboslutely.", "Absolutely...not!", "Yep.",
    "I don't know. For a computer, I am not very smart.", "Why do you want to know?", "I don't know.", "No.", "Absolutely not.", "Maybe.", "Possibly.",
    "That depends. Who's asking?", "Affirmative.", "Negative.", "Affirmatory.", "I think so.", "Probably.", "Probably not."};
  
  private String[] confirmation = {"Okay.", "On it.", "Will do!", "Yes, sir - I mean, ma'am, I mean boss, I mean puba!", "Sure.", "Alright.",
    "I will do my best.", "How do I do that?", "OK"}; // random responses
  
  private String[] auxVerbs = {"could", "can", "would", "will", "should", "shall", "could", "may", "may", "might", "did", "do", "did", "does", "was", "is", "were",
    "are", "was", "am", "had", "have", "had", "has", "had to", "must"};
  
  private String[] verbs = {"run", "throw", "eat", "drink", "hug", "love", "hate", "hit", "break", "work", "tally", "marry", "donate", "believe", "fill",
    "kill", "bring", "lie", "enjoy", "laugh", "play", "stand", "lay", "review", "write", "read", "live", "make", "understand", "bake", "open", "close", "let",
    "know", "lead", "see", "shut", "think", "buy"}; // lists of auxiliary and regular verbs
  
  private boolean nameInquire = false; // remembers if it has asked you your name recently
  
  
  
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return greeting[x%greeting.length]; // returns a random greeting from above
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
    lstatement = unContract(lstatement); // makes lstatement lowercase, from the computer's perspective, and have no contractions
    lstatement = toSecondPerson(lstatement);
    
    if (statement.length() < 1)
      response = lonelyMessage[x%lonelyMessage.length]; // gives a certain random reply if box is empty
    
    else if (letterCheck(statement.substring(statement.length()-1))) // checks last character to see if it is punctuation or a letter
      response = "You should really punctuate your sentences properly";
    
    else if (statement.substring(0, 1).compareTo(" ") > 64 && statement.substring(0, 1).compareTo(" ") < 91) // checks for first letter capitalization
      response = "you should really capitalize your sentences properly.";
    
    else if (find("shit", lstatement)>=0 || find("damn", lstatement)>=0 || find("frick", lstatement)>=0 || find("bitch", lstatement)>=0 || find("crap", lstatement)>=0 || find("oh your god", lstatement)>=0)
      response = "Watch your damn language!"; // responds to cursing
       
    else if (find("name is", lstatement)>=0 && find("name is", lstatement)<lstatement.length()-9)
      response = nameUpdate(lstatement.substring(find("name is", lstatement)+8, lstatement.length()-1)); // if it sees anything about "name is", it will assume you are telling it its name.
    
    else if (find("you are", lstatement)>=0 && find("you are", lstatement)<lstatement.length()-9)
      response = nameUpdate(lstatement.substring(find("you are", lstatement)+8, lstatement.length()-1)); // if it sees "you are", assume you are stating your name
    
    else if (nameInquire)
      response = nameUpdate(lstatement.substring(0, lstatement.length()-1)); // if it asked you your name recently, assume the sentence is your name
      
    else if (userName == "" && Math.random() < .5 && !nameInquire)
    {
      response = "I don't think I caught your name."; // randomly asks you your name
      nameInquire = true;
    }
    
    else if (find("my name", lstatement)>=0) // tells you the computer's name
      response = "My name is Smitty Werbenjagermanjensen.";
    
    else if (find("good job", lstatement)>=0 || find("congratulations", lstatement)>=0) // responds to thank you
      response = "Why, thank you.";
    
    else if (find("thank me", lstatement)>=0 || find("thanks", lstatement)>=0) // responds to thank you
      response = "You're quite welcome.";
    
    else if (find("sorry", lstatement)>=0 || find("your bad", lstatement)>=0) // sorry
      response = "Your apology is accepted.";
    
    else if (find("excuse you", lstatement)>=0 || find("scuse you", lstatement)>=0) // excuse me
      response = "You are excused.";
    
    else if (find("hi", lstatement)>=0 || find("hello", lstatement)>=0 || find("greetings", lstatement)>=0 || find("hey", lstatement)>=0) // hello
      response = getGreeting();
    
    else if (find("bye", lstatement)>=0 || find("goodbye", lstatement)>=0 || find("see ya later", lstatement)>=0 || find("see you later", lstatement)>=0) // goodbye
      response = "Bye.";
    
    else if (find("yes", lstatement)>=0) // yes
      response = "I see.";
    
    else if (find("no", lstatement)>=0) // and no
      response = "Okay.";
    
    else if (find("you want to", lstatement)>=0) // if you want to do something
      response = "Well, then go and"+lstatement.substring(find("you want to", lstatement)+11, lstatement.length()-1)+"!"; // tell you to go do it
    
    else if (find("you want", lstatement)>=0) // if you want something
      response = "I wish I could give you"+lstatement.substring(find("you want", lstatement)+8, lstatement.length()-1)+", but I am merely a computer."; // lament that you cannot give it
    
    else if (find("you like to", lstatement)>=0) // if you like to do something
      response = "I like to"+lstatement.substring(find("you like to", lstatement)+11, lstatement.length()-1)+", too!"; // aggree with that hobby
    
    else if (find("you like", lstatement)>=0) // if you like something
      response = "What do you like about"+lstatement.substring(find("you like", lstatement)+8, lstatement.length()-1)+"?"; // probe for more information
    
    else if (find("you have to", lstatement)>=0) // if you have to do something
      response = "Well, I guess you had better"+lstatement.substring(find("you have to", lstatement)+11, lstatement.length()-1)+", then."; // tell you to do it
    
    else if (find("try", lstatement)>=0) // responds to a whole bunch of different keywords
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
    
    else if (statement.substring(statement.length()-1).equals("?")) // answers interrogative sentences
    {
      if (find("how are me", lstatement) == 0) // responds to basic questions
        response = "I am fine. Thank you for asking.";
      else if (find("how do I do", lstatement) == 0)
        response = "I am fine. Thank you for asking.";
      else if (find("who are me", lstatement) == 0)
        response = "I am Smitty Werbenjagermanjensen.";
      else if (find("what are me", lstatement) == 0)
        response = "I am a human.";
      else if (find("what", lstatement) == 0) // responds to different question words with default responses
        response = "Chicken butt.";
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
      else if (find("how many", lstatement) == 0)
        response = "Over nine-thousand!";
      else if (find("how much", lstatement) == 0)
        response = "Enough.";
      else if (find("how", lstatement) == 0) // if it starts with "how"
      {
        int end = 4;
        while (letterCheck(lstatement.substring(end))) // go to next space to find next word
          end++;
        response = lstatement.substring(4, end) + " enough."; // make the default response ___ enough (long enough, far enough, etc.)
        
        for (String v: auxVerbs) // if next word is an auxiliary verb,
          if (find(v, lstatement) == 4) // "how" has a different meaning
            response = "How "+v+" anything?"; // so give a different response
      }
      else
        response = answer[x%answer.length]; // if no question word, give a "yes" or "no" answer
    }
    
    else if (imperativeCheck(lstatement)) // responds to imperative sentences
    {
      response = confirmation[x%confirmation.length]; // gives a random confirmation response
    }
    
    else
      response = statementConversion(lstatement); // replies to declarative sentences with special conversion rules
    
    if (!userName.equals("") && x%7 == 6) // addresses you by name sometimes.
      response = response.substring(0, response.length()-1) + ", " + userName + response.substring(response.length()-1, response.length());
    
    response = response.substring(0,1).toUpperCase() + response.substring(1); // Capitalizes response
    while (find("i", response) >= 0)
      response = response.substring(0,find("i", response)) + "I" + response.substring(find("i", response)+1); // changes all i to I
    
    x += (int)(Math.random()*2+2); // increments x at a fairly random rate
    return response;
  }
  
  
  private boolean imperativeCheck(String sentence)
  {
    for (String v: verbs) // if the first word is a verb
      if (find(v, sentence) == 0)
        return true; // then the sentence is imperative
    for (String v: auxVerbs)
      if (find(v, sentence) == 0)
        return true;
    return false; // otherwise, it is not
  }
  
  
  private boolean letterCheck(String character)
  {
    return character.compareTo(" ") > 32 && character.compareTo(" ") < 91; // uses character.compareTo(" ") to determine whether it is a letter or symbol
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
    nameInquire = false;
    String response;
    newName = newName.substring(0,1).toUpperCase() + newName.substring(1); // capitalizes name
    if (!userName.equals("") && !userName.equals(newName)) // expresses confusion if you change your name
      response = "I thought your name was " + newName + ".";
    else
      response = "Nice to meet you, " + newName + ". My name is Smitty Werbenjagermanjensen.";
    userName = newName; // memorizes name
    userName = userName.substring(0,1).toUpperCase() + userName.substring(1); // capitalizes name
    return response;
  }
  
  
  private String toSecondPerson(String statement) // converts all pronouns to computer perspective
  {
    String newStatement = ""; // new statement that is gradually constructed from original statement
    for (int i = 0; i < statement.length();) // cycles through statement
    {
      if (!letterCheck(statement.substring(i))) // if symbol, it stays the same
      {
        newStatement += statement.substring(i, i+1);
        i ++;
      }
      else if (find("i", statement.substring(i)) == 0) // I -> you
      {
        newStatement += "you";
        i += 1;
      }
      else if (find("me", statement.substring(i)) == 0) // me -> you
      {
        newStatement += "you";
        i += 2;
      }
      else if (find("am", statement.substring(i)) == 0) // am -> are
      {
        newStatement += "are";
        i += 2;
      }
      else if (find("you", statement.substring(i)) == 0) // you depends on whether there is a verb after it
      {
        boolean subjective = false;
        for (String v: auxVerbs) // checks all verbs and auxiliary verbs and past tenses to see if they are the next word
          subjective = subjective || (i < statement.length()-4-v.length() && statement.substring(i+4, i+4+v.length()).equals(v));
        for (String v: verbs)
          subjective = subjective || (i < statement.length()-4-v.length() && statement.substring(i+4, i+4+v.length()).equals(v));
        for (String v: verbs)
          subjective = subjective || (i < statement.length()-4-pastTense(v).length() && statement.substring(i+4, i+4+pastTense(v).length()).equals(pastTense(v)));
        if (subjective)  newStatement += "i"; // if any verb was after it, the you goes to I
        else             newStatement += "me"; // otherwise, it goes to me
        i += 3;
      }
      else if (find("my", statement.substring(i)) == 0) // my -> your
      {
        newStatement += "your";
        i += 2;
      }
      else if (find("your", statement.substring(i)) == 0) // your -> my
      {
        newStatement += "my";
        i += 4;
      }
      else if (find("mine", statement.substring(i)) == 0) // mine -> yours
      {
        newStatement += "yours";
        i += 4;
      }
      else if (find("yours", statement.substring(i)) == 0) // yours -> mine
      {
        newStatement += "mine";
        i += 5;
      }
      else if (find("was", statement.substring(i)) == 0 && find("you", newStatement) >= 0) // "was" only changes if there is a "you" before it.
      {
        newStatement += "were";
        i += 3;
      }
      else if (find("were", statement.substring(i)) == 0 && find("i", newStatement) >= 0) // "were" only changes if there is a "i" before it.
      {
        newStatement += "was";
        i += 4;
      }
      else if (find("are", statement.substring(i)) == 0 && find("i", newStatement) >= 0) // "are" only changes if there is an "i" before it.
      {
        newStatement += "am";
        i += 3;
      }
      else if (find("smitty.", statement.substring(i)) == 0) // ignore if it addresses me by name
      {
        i += 6;
      }
      else if (find("smitty,", statement.substring(i)) == 0) // ignore if it addresses me by name
      {
        i += 6;
      }
      else // if there is no name, just add letters until you hit the end of the word
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
    switch (x%4) // there are four possible statement conversions:
    {
      case 1: // "Why is that true?"
        for (String v: auxVerbs) // if there is an auxiliary verb
          if (find(v, statement) >= 0) // make it Why + verb + (statement-verb)
            return "Why " + v + " " + statement.substring(0, find(v, statement)-1) + statement.substring(find(v, statement)+v.length(), statement.length()-1) + "?";
        for (String v: verbs) // if there is a nonauxiliary verb
        {
          if (find(v, statement) >= 0) // make it Why + do/did/does(depending on tense) + presenttenseverb
            return "Why do " + statement.substring(0, statement.length()-1) + "?";
          if (find(pastTense(v), statement) >= 0)
            return "Why did " + statement.substring(0, find(pastTense(v), statement)) + v + statement.substring(find(pastTense(v), statement)+pastTense(v).length(), statement.length()-1) + "?";
          if (find(plural(v), statement) >= 0)
            return "Why does " + statement.substring(0, find(plural(v), statement)) + v + statement.substring(find(plural(v), statement)+plural(v).length(), statement.length()-1) + "?";
        }
          break;
      case 2: // "Is that true? Really?"
        for (String v: auxVerbs) // pretty similar to case 1
          if (find(v, statement) >= 0)
            return v + " " + statement.substring(0, find(v, statement)-1) + statement.substring(find(v, statement)+v.length(), statement.length()-1) + "? Really?";
        for (String v: verbs)
        {
          if (find(v, statement) >= 0)
            return "Do " + statement.substring(0, statement.length()-1) + "? Really?";
          if (find(pastTense(v), statement) >= 0)
            return "Did " + statement.substring(0, find(pastTense(v), statement)) + v + statement.substring(find(pastTense(v), statement)+pastTense(v).length(), statement.length()-1) + "? Really?";
          if (find(plural(v), statement) >= 0)
            return "Does " + statement.substring(0, find(plural(v), statement)) + v + statement.substring(find(plural(v), statement)+plural(v).length(), statement.length()-1) + "? Really?";
        }
        break;
      case 3: // "I did not know that was true."
        for (String v: auxVerbs) // if there is an auxiliary verb
          if (find(v, statement) >= 0) // make it I did not know + past tense sentence
            return "I did not know " + statement.substring(0, find(v, statement)-1) + " " + pastTense(v) + statement.substring(find(v, statement)+v.length(), statement.length());
        for (String v: verbs) // same goes for regular verbs if there is no auxiliary verb
        {
          if (find(v, statement) >= 0)
            return "I did not know " + statement.substring(0, find(v, statement)-1) + " " + pastTense(v) + statement.substring(find(v, statement)+v.length(), statement.length());
          if (find(pastTense(v), statement) >= 0)
            return "I did not know " + statement;
          if (find(plural(v), statement) >= 0)
            return "I did not know " + statement.substring(0, find(v, statement)-1) + " " + pastTense(v) + statement.substring(find(v, statement)+v.length(), statement.length());
        }
        break;
      default: // "Since when is that true?"
        for (String v: auxVerbs) // same idea as cases 1 and 2
          if (find(v, statement) >= 0)
            return "Since when " + v + " " + statement.substring(0, find(v, statement)-1) + statement.substring(find(v, statement)+v.length(), statement.length()-1) + "?";
        for (String v: verbs)
        {
          if (find(v, statement) >= 0)
            return "Since when do " + statement.substring(0, statement.length()-1) + "?";
          if (find(pastTense(v), statement) >= 0)
            return "Since when did " + statement.substring(0, find(pastTense(v), statement)) + v + statement.substring(find(pastTense(v), statement)+pastTense(v).length(), statement.length()-1) + "?";
          if (find(plural(v), statement) >= 0)
            return "Since when does " + statement.substring(0, find(plural(v), statement)) + v + statement.substring(find(plural(v), statement)+plural(v).length(), statement.length()-1) + "?";
        }
        break;
    }    
    return reply[x%reply.length]; // if no verb is found, just return a basic response
  }
  
  
  private String pastTense(String word) // automatically uses grammar rules to change things to past tense
  {
    for (int i = 0; i < auxVerbs.length; i ++)
    {
      if (word.equals(auxVerbs[i]))
        return auxVerbs[(i/2)*2];
    }
    if (word.equals("play"))
      return "played";
    if (word.equals("see"))
      return "saw";
    if (word.equals("buy"))
      return "bought";
    if (word.equals("think"))
      return "thought";
    else if (word.substring(word.length()-2).equals("et"))
      return word;
    else if (word.substring(word.length()-3).equals("ead"))
      return word;
    else if (word.substring(word.length()-3).equals("ut"))
      return word;
    else if (word.substring(word.length()-3).equals("eat"))
      return word.substring(0, word.length()-3) + "ate";
    else if (word.substring(word.length()-2).equals("in"))
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
  
  
  private String plural(String word) // same thing for plural
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
  
  
  private String unContract(String statement) // cahnges all contractions to full words
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