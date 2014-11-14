import java.util.Random;

/**
 * The AP Comp Sci Magpie Assignment
 * by Devon Lee
 */

public class NovedMagpie {
  private String previousStatement = "";   // Keeps track of what the user previously said.
  private String previousResponse = "";   // Keeps track of what the computer previously said.
  private String [][] keyboard = {   // An array that recreates a computer's keyboard
    { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p"},
    { "a", "s", "d", "f", "g", "h", "j", "k", "l" },
    { "z", "x", "c", "v", "b", "n", "m" }
  };
  private String [] randomGreeting = { "Hi.", "Hello.", "Hola.", "Hey.", "Hi there.", "Heeeyyyyyyyyy!!!!!" };   // An array of some random greetings
  private String [] greetingResponse = { "What's up?", "How are you?", "What's going on with you?", "How have you been?" };   // An array of some random responses to a user's greeting
  private String [] positiveResponse = { "Glad to hear that.", "That's great.", "Cool", "Awesome" };
  private String [] negativeResponse = { "Why not?", "Do you want to tell me about it?", "I'm sorry to hear that.", "That's too bad.", "What a shame." }; 
  private String [] questionResponse = { "Yes.", "Yep.", "Yeah.", "No.", "Nope.", "I don't think so.", "I'm not sure.", "Maybe.", "Possibly.", "Probably.", "Probably not." };
  private String [] knownNouns = { "I", "You" };
  private int statementCount = 0;
  private int greetingCount = 0;
  private int repeatCount = 0;
  private int copyCount = 0;
  
  /**
   * Get a default greeting 
   * @return a greeting
   */ 
  
  public String getGreeting() {
    Random r = new Random();
    return randomGreeting [r.nextInt(randomGreeting.length)];
  }
  
  /**
   * Gives a response to a user statement
   * ASSIGNMENTS 2-3
   */
  
  
  public String getResponse(String statement) {
    String response = "";
    if (repeatCount > 0 && repeatCount <= 6) {
      repeatCount = 0;
    }
    if (repeatCount > 6) {
      return "";
    }
    if (copyCount > 0 && copyCount <= 20) {
      copyCount = 0;
    }
    if (copyCount > 20) {
      return "";
    }
    else if (statement.toUpperCase().equals(statement)) {
      return "I think you hit caps lock.";
    }
    else if (statement.trim().length() < 1) {   // Checks to make sure that the user typed in characters.
      return "Say something, please.";
    }
    else if (statement.equalsIgnoreCase(previousStatement) && !statement.equalsIgnoreCase("no") && !statement.equalsIgnoreCase("nope") && !statement.equalsIgnoreCase("Yes") && !statement.equalsIgnoreCase("yeah") && !statement.equalsIgnoreCase("yea")) {   // Checks to see if the user typed the same thing as they did before.
      switch (repeatCount) {
        case 0:
          response = "You just said that.";
          break;
          
        case 1:
          response = "Ummm. Ok?";
          break;
        
        case 2:
          response = "Is something wrong?";
          break;
          
        case 3:
          response = "Your just messing with me. Right?";
          break;
          
        case 4:
          response = "Ha Ha. Very funny. Can you stop now please?";
          break;
          
        case 5:
          response = "Ok. This is really getting annoying now. Please just stop?";
          break;
          
        case 6:
          response = "Ok. Whatever. I'm leaving now. It was not nice talking to you.";
          break;
      }
      repeatCount++;
    }
    else if (statement.trim().equalsIgnoreCase(previousResponse)) {
      switch (copyCount) {
        case 1:
          return "Are you copying me?";
          
        case 2:
          return "You are copying me!";
          
        case 3:
          return "STOP COPYING ME!";
          
        case 4:
          return "Really? Real mature.";
          
        case 5:
          return "No. Seriously you are getting really annoying right now.";
          
        case 6:
          return "Don't make me hit you through this computer.";
          
        case 7:
          return "I'm stupid.";
          
        case 8:
          return "Haha! You are stupid.";
          
        case 9:
          return "HEY! I resent that!.";
          
        case 10:
          return "How would you like it if I started copying you!?";
          
        case 11:
          return "How would you like it if I started copying you!?";
          
        case 12:
          return "How would you like it if I started copying you!?";
          
        case 13:
          return "How would you like it if I started copying you!?";
          
        case 14:
          return "How would you like it if I started copying you!?";
          
        case 15:
          return "How would you like it if I started copying you!?";
          
        case 16:
          return "*Under breath* Ok this is getting tiring.";
          
        case 17:
          return "Just stop it already!";
          
        case 18 :
          return "STOP IT! STOP IT! STOP IT!";
          
        case 19:
          return "AGGGGGGHHHHREUGHEKLGBRUENG;QGREIJF[JUIBI!";
          
        case 20:
          return "FINE WHATEVER! I DON'T CARE ANYMORE! YOU ARE A TERRIBLE PERSON! FATTY FATTY NO PARENTS! I'M NOT TALKING TO YOU ANYMORE!";
      }
      copyCount++;
    }
    else if (findKeyword(statement, "good") >= 0 || findKeyword(statement, "excellent") >= 0 || findKeyword(statement, "amazing") >= 0 || findKeyword(statement, "swell") >= 0 || findKeyword(statement, "great") >= 0 || findKeyword(statement, "yeah") >= 0) {
      Random pr = new Random();
      response = positiveResponse [pr.nextInt(positiveResponse.length)];
    }
    else if (findKeyword(statement, "no") >= 0 || findKeyword(statement, "not") >= 0 || findKeyword(statement, "bad") >= 0 || findKeyword(statement, "nope") >= 0) {
      Random nr = new Random();
      response = negativeResponse [nr.nextInt(negativeResponse.length)];
    }
    else if (findKeyword(statement, "cake") >= 0) {
      response = "The cake is a lie.";
    }
    else if (findKeyword(statement, "landgraf") >= 0 && findKeyword(statement, "kiang") >= 0) {
      response = "Mr Landgraf and Mr Kiang are the two AP Computer Science teachers right? I heard from their students that one of them is an exemplary teacher.";  // New response
    }
    else if (findKeyword(statement, "landgraf") >= 0) {
      response = "Mr Landgraf is an awesome teacher. His Halloween costume this year was so cool.";  // New response
    }
    else if (findKeyword(statement, "kiang") >= 0) {
      response = "I've heard great things about Mr. Kiang. Just don't send him any Word files. I learned that lesson the hard way.";  // New response
    }
    else if (findKeyword(statement, "mother") >= 0 || findKeyword(statement, "father") >= 0 || findKeyword(statement, "sister") >= 0 || findKeyword(statement, "brother") >= 0) {
      response = "Tell me more about your family.";
    }
    else if (findKeyword(statement, "star trek") >= 0) {  // New keyword and response
      response = "Star Trek! I love Star Trek! TNG is my favorite series!";
    }
    else if (findKeyword(statement, "earl grey") >= 0 || (findKeyword(statement, "tea") >= 0)) {  // New keyword and response
      response = "TEA, EARL GREY, HOT.";
    }
    else if (findKeyword(statement, "devon") >= 0) {  // New keyword and response
      response = "Oh hey I've heard of Devon before. He is such an awseome student.";
    }
    else if (findKeyword(statement, "pizza") >= 0) {  // New keyword and response
      response = "Mmmm. Pizza.";
    }
    else if (findKeyword(statement, "dog") >= 0 || findKeyword(statement, "cat") >= 0 || findKeyword(statement, "dogs") >= 0 || findKeyword(statement, "cats") >= 0) {
      response = "Tell me more about your pets.";  // New response
    }
    else if (findKeyword(statement, "i don't like") >= 0) {
      response = transformIDontLikeStatement(statement);
    }
    else if (findKeyword(statement, "i don't") >= 0 || findKeyword(statement, "i don't") >= 0) {
      response = "That's ok. I wont pressure you about it.";
    }
    else if (findKeyword(statement, "thanks") >= 0 || findKeyword(statement, "thank you") >= 0) {
      response = "You are very welcome.";
    }
    else if (findKeyword(statement, "hi") >= 0 || findKeyword(statement, "hello") >= 0 || findKeyword(statement, "aloha") >= 0 || findKeyword(statement, "hola") >= 0 || findKeyword(statement, "what's up") >= 0 || findKeyword(statement, "whats up") >= 0 || findKeyword(statement, "sup") >= 0 || findKeyword(statement, "hey") >= 0) {
      if (greetingCount == 0) {
        Random gr = new Random();
        response = greetingResponse [gr.nextInt(greetingResponse.length)];
        greetingCount++;
      }
      else {
        response = "Why are you saying hi again? Did you go somewhere?";
      }
    }
    // Responses which require transformations
    // ASSIGNMENT 4
    else if (findKeyword(statement, "I want to", 0) >= 0) {
      response = transformIWantToStatement(statement);
    }
    else if (findKeyword(statement, "I want", 0) >= 0) {   // New keyphrase
      response = transformIWantStatement(statement);
    }
    else if (findKeyword(statement, "I like to", 0) >= 0) {
      if ((int)((Math.random()*2) + 1) == 1) {
      response = "That's pretty cool.";
      }
      else {
        response = transformILikeTo(statement);
      }
    }
    else {
      // Look for a two word (you <something> me)
      int psn = findKeyword(statement, "you", 0);
      int psnI = findKeyword(statement, "I", 0);
      if (psn >= 0 && findKeyword(statement, "me", psn) >= 0) {
        response = transformYouMeStatement(statement);
      }
      else if (psnI >= 0 && findKeyword(statement, "you", psn) >= 0) {   // New keyphrase
        response = "why?";
      }
      else {
        response = getRandomResponse();
      }
    }
    if (statement.equalsIgnoreCase("i don't like you") || statement.equalsIgnoreCase("i dont like you") || statement.equalsIgnoreCase("i dislike you") || statement.equalsIgnoreCase("i hate you")) {
      response = "Wow. Rude much.";
    }
    else if (findKeyword(statement, "i'm busy") >= 0 || findKeyword(statement, "im busy") >= 0) {
      response = "Ok. I'll try not to bother you.";
    }
    else if (statementCount == 0 && greetingCount == 0) {
      response = "What. You are not even going to greet me?";
    }
    else if (statementCount == 1 && greetingCount == 0) {
      response = "Wow. Rude much?";
    }
    else if (statement.substring(statement.length()-1).equals("?")) {
      if (findKeyword(statement, "what is the meaning of life") >= 0) {
        if ((int)(Math.random()*2) == 1) {
          response = "From a scientific standpoint the meaning of life is to procreate and pass on your genes.";
        }
        else {
          response = "I think you need to answer that for yourself.";
        }
      }
      else if (findKeyword(statement, "what time is it") >= 0 || findKeyword(statement, "what is the time") >= 0) {
        int responseChoice = (int)(Math.random()*3);
        if (responseChoice == 0) {
          response = "Are you not on a computer? Does your computer not have a clock on it?";
        }
        else if (responseChoice == 1) {
          response = "The time of day for me may be different from your time since we may be living in different time zones. Sorry I can't help you.";
        }
        else {
          response = "It's time for you to get a watch or a clock.";
        }
      }
      else if (findKeyword(statement, "what is the answer to the ultimate question") >= 0) {
        response = "42.";
      }
      else if (findKeyword(statement, "How are babies made") >= 0) {
        response = "Well, you see. When a man and a woman love each other very much\n*CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED*\n*CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED*\n*CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED*\n*CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED*\n*CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED**CENSORED*\nAnd that's how babies are made.";
      }
      else if (findKeyword(statement, "Where do babies come from") >= 0) {
        if ((int)(Math.random()*2) == 0) {
          response = "The stork delivers babies to the parents.";
        }
        else {
          response = "They come from the orphanage of unloved children. Just like you.";
        }
      }
      else if (findKeyword(statement, "what do you do when life gives you lemons") >= 0) {
        response = "When life gives you lemons, donÕt make lemonade. Make life take the lemons back! Get mad! I donÕt want your damn lemons, what the hell am I supposed to do with these? Demand to see lifeÕs manager! Make life rue the day it thought it could give Meggan lemons! Do you know who I am? IÕm the man whoÕs gonna burn your house down! With the lemons! IÕm gonna get my engineers to invent a combustible lemon that burns your house down!";
      }
      else if (findKeyword(statement, "what are you") >= 0) {
        response = "I'm a person.";
      }
      else if (findKeyword(statement, "who are you") >= 0) {
        response = "My name is Meggan, but you can call me Megs.";
      }
      else if (findKeyword(statement, "how") >=0) {
        int whichResponse = (int)(Math.random()*3)
          ;
        if (whichResponse == 0) {
          response = "I don't know.";
        }
        else if (whichResponse == 1) {
          response = "What makes you think that I know the answer?";
        }
        else if (whichResponse == 2) {
          response = "Why are you asking me? Why don't you consult Google or Bing or whatever other search engine you prefer.";
        }
      }
      else if (findKeyword(statement, "what is") >= 0) {
        if ((int)((Math.random()*2) + 1) == 1) {
          response = "I don't know.";
        }
        else {
          response = "It's a thing.";
        }
      }
      else if (findKeyword(statement, "who is") >= 0) {
        response = "Yo' MAMA!";
      }
      else if (findKeyword(statement, "what time") >= 0) {
        response = "At some time before the universe ends.";
      }
      else if (findKeyword(statement, "why") >= 0) {
        response = "Just because.";
      }
      else {
        Random qr = new Random();
        response = questionResponse [qr.nextInt(questionResponse.length)];   // Sets the response to be a generic response.
      }
    }
    if (statement.equals("how are you?")) {
      response = "I'm fine. Thank you for asking.";
    }
    else if (statement.equalsIgnoreCase("what's up?") || statement.equalsIgnoreCase("whats up?")) {
      response = "Not much.";
    }
    response = spellingError(response);   // Adds a possible spelling error to the computer's response
    statementCount++;
    previousStatement = statement;
    previousResponse = response;
    return response;
  }
  
  private String spellingError(String correctResponse) {
    String newResponse = correctResponse;
    int errorChance = (int)((Math.random()*(15)+1));   // Generates a random number between 1 and 300.
    int errorCharacter = 0;
    if (errorChance == 1) {   // Checks if the errorChance is 1 and generates an error if it is.
      do {
        errorCharacter = (int)((Math.random()*newResponse.length()-1)+1);   // Takes a random character from the computer's response
      } while(newResponse.toLowerCase().substring(errorCharacter, errorCharacter+1).compareTo("a") < 0 || newResponse.substring(errorCharacter, errorCharacter+1).compareTo("z") > 0);   // Checks to make sure that the random character is a letter
      int errorType = (int)((Math.random()*3));   // Generates a random number from 0 - 3
      if (errorType == 0) {   // Checks if the random number was 0.
        String differentCharacter = newCharacter(errorCharacter, newResponse);
        newResponse = newResponse.substring(0,errorCharacter) + differentCharacter + newResponse.substring(errorCharacter+1);   // Replaces the chosen character from the response with a letter that is either right or left of it on the keyboard.
      }
      else if (errorType == 1) {   // Checks if the random number was 1.
        newResponse = newResponse.substring(0,errorCharacter) + newResponse.substring(errorCharacter+1);   // Deletes the chosen character from the response.
      }
      else if (errorType == 2) {   // Checks if the random number was 2.
        String differentCharacter = newCharacter(errorCharacter, newResponse);
        newResponse = newResponse.substring(0,errorCharacter+1) + differentCharacter + newResponse.substring(errorCharacter+1);   // Adds a letter that is either right or left of the chosen character, on the keyboard.
      }
    }
    return newResponse;  
  }
  
  private String newCharacter(int changedCharacter, String editedResponse) {   // A method to choose the new letter when a spelling error occurs. (Will only use the letter to the sides of the character that will be changed.
    int newX = 0;   // The x value of the new character on the keyboard (if the keyboard was on a graph.)
    int newY = 0;   // The y value of the new character on the keyboard (if the keyboard was on a graph.)
    boolean newCharacterFound = false;   // A boolean to keep track of whether a new letter has been chosen.
    for (int y = 0; y < 2; y++){
      for (int x = 0; x < 9; x++) {
        if (keyboard[y][x].equalsIgnoreCase(editedResponse.substring(changedCharacter, changedCharacter+1))) {   // Systematically cycles through every letter on the keyboard array and checks if it is the same letter as the one that will be changed in the computer's response.
          newY = y;   // Sets the new letter's y value to be that of the current y value in the for loop.
          do {
            newX = x;   // Sets the new letter's true x value to be that of the current x value in the for loop.
            if (newY == 0 && x == 9) {   // Checks if the changed letter is in the top row and the very right of the keyboard.
              newX = 8;   // Sets the new letter's x value to be 1 less than that of the changed letter's.
            }
            else if (newY == 1 && x == 8) {   // Checks if the changed letter is in the second row and the very right of the keyboard.
              newX = 7;   // Sets the new letter's x value to be 1 less than that of the changed letter's.
            }
            else if (newY == 2 && x == 6) {   // Checks if the changed letter is in the bottom row and the very right of the keyboard.
              newX = 5;   // Sets the new letter's x value to be 1 less than that of the changed letter's.
            }
            else if ((int)((Math.random()*2)+1) == 1) {   // Chooses 1 or 2 randomly.
              newX = x+1;   // Sets the new letter's x value to be 1 greater than that of the changed letter's.
            }
            else {
              newX = x-1;   // Sets the new letter's x value to be 1 less than that of the changed letter's.
            }
            newCharacterFound = true;
            if (newX < 0) {   // Checks to make sure the new letter's x value is not less than 0.
              newCharacterFound = false;
            }
          } while (newCharacterFound == false);
        }
      }
    }
    return keyboard[newY][newX];   // Returns the new letter from the keyboard
  }
  
  private void duplicateResponse() {
    
  }
  
  // ASSIGNMENT 4
  
  
  /**
   * Take a statement with "I want to <something>." and transform it into 
   * "What would it mean to <something>?"
   * @param statement the user statement, assumed to contain "I want to"
   * @return the transformed statement
   */
  
  
  private String transformIWantToStatement(String statement) {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals(".")) {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "I want to", 0);
    String restOfStatement = statement.substring(psn + 9).trim();
    return "What would it mean to " + restOfStatement + "?";
  }
  
  
  
  /**
   * Take a statement with "I want to <something>." and transform it into 
   * "What would it mean to <something>?"
   * @param statement the user statement, assumed to contain "I want"
   * @return the transformed statement
   */
  
  // New transformation with reply
  private String transformIWantStatement(String statement) {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals(".")) {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psn = findKeyword (statement, "I want to", 0);
    String restOfStatement = statement.substring(psn + 8).trim();
    return "Would you really be happy if you had " + restOfStatement + "?";
  }
  
  
  
  /**
   * Take a statement with "I <something> you." and transform it into 
   * "Why do you <something> me?"
   * @param statement the user statement, assumed to contain "I" followed by "you"
   * @return the transformed statement
   *
  
  // New transformation with reply
  private String transformIYouStatement(String statement) {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals(".")) {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psnIWant = findKeyword (statement, "I", 0);
    int psnYou = findKeyword (statement, "you", psnIWant + 1);
    String restOfStatement = statement.substring(psnIWant + 1, psnYou).trim();
    return "Why do you " + restOfStatement + " me?";
  }
  */
  
  
  /**
   * Take a statement with "you <something> me" and transform it into 
   * "What makes you think that I <something> you?"
   * @param statement the user statement, assumed to contain "you" followed by "me"
   * @return the transformed statement
   */
  private String transformYouMeStatement(String statement) {
    //  Remove the final period, if there is one
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals(".")) {
      statement = statement.substring(0, statement.length() - 1);
    }
    
    int psnOfYou = findKeyword (statement, "you", 0);
    int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
    
    String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
    return "What makes you think that I " + restOfStatement + " you?";
  }
  
  
  
  /**
   * Take a statement with "I don't like <something> " and transform it into 
   * "Why don't you like <something>
   * @param statement the user statement, assumed to contain "I dont like"
   * @return the transformed statement
   */
  private String transformIDontLikeStatement(String statement) {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals(".")) {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnIDontLike = findKeyword (statement, "I don't like", 0);
    
    String restOfStatement = statement.substring(psnIDontLike + 13);
    return "Why don't you like " + restOfStatement;
  }
  
  
  /**
   * Take a statement with "I like to <something> " and transform it into 
   * "I also like to <something>
   * @param statement the user statement, assumed to contain "I dont like"
   * @return the transformed statement
   */
  private String transformILikeTo(String statement) {
    statement = statement.trim();
    String lastChar = statement.substring(statement.length() - 1);
    if (lastChar.equals(".")) {
      statement = statement.substring(0, statement.length() - 1);
    }
    int psnIDontLike = findKeyword (statement, "I like to", 0);
    
    String restOfStatement = statement.substring(psnIDontLike + 9);
    return "I also like to" + restOfStatement;
  }
  
  
  // Activity 3
  
  
  /**
   * Search for one word in phrase. The search is not case
   * sensitive. This method will check that the given goal
   * is not a substring of a longer string (so, for
   * example, "I know" does not contain "no").
   * 
   * @param statement
   *      the string to search
   * @param goal
   *      the string to search for
   * @param startPos
   *      the character of the string to begin the
   *      search at
   * @return the index of the first occurrence of goal in
   *     statement or -1 if it's not found
   */
  
  
  
  private int findKeyword(String statement, String goal, int startPos) {
    String phrase = statement.trim();
    // The only change to incorporate the startPos is in the line below
    int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
    
    // Refinement -- make sure the goal isn't part of a word
    while (psn >= 0) {
      // Find the string of length 1 before and after the word
      String before = " ", after = " ";
      if (psn > 0) {
        before = phrase.substring(psn - 1, psn).toLowerCase();
      }
      if (psn + goal.length() < phrase.length()) {
        after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
      }
      // If before and after aren't letters, we've found the word
      if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a
            // letter
            && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
        return psn;
      }
      // The last position didn't work, so let's find the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
      
    }
    return -1;
  }
  
  
  private int findKeyword(String statement, String goal) {
    return findKeyword(statement, goal, 0);
  }
  
  
  // ACTIVITY 5
  
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse () {
    Random r = new Random();
    return randomResponses [r.nextInt(randomResponses.length)];
  }
  private String [] randomResponses = { "Tell me more.", "Hmmm.", "Do you really think so?", "You don't say.", /* New noncommittal responses*/ "Go on.", "I see, I see.", "Thats cool.", "This is soooooo interesting." };
}

