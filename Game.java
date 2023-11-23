import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Game {

    private String player1;
    private String player2;

    private int matchLength;

    public Game(String player1, String player2, int matchLength) {
        this.player1 = player1; //White
        this.player2 = player2; //Red
        this.matchLength = matchLength; //Length of match
    }

    /*private boolean QuitOrRoll() {
        Scanner scanner = new Scanner(System.in);
        boolean ret = false;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Do you want to play? (yes/no): ");
                String choice = scanner.nextLine().toLowerCase();

                if ("yes".equals(choice)) {
                    ret = false;
                    validInput = true;
                } else if ("no".equals(choice)) {
                    ret = true;
                    validInput = true;
                } else {
                    throw new InvalidEntryException("Invalid entry. Please enter 'yes' or 'no'.");
                }
            } catch (InvalidEntryException e) {
                System.out.println(e.getMessage());
                // Continue the loop to prompt the user again
            }
        }

        return ret;
    }*/

    public void playGame() {

            int currentplayer = -1;
            boolean dicedraw = true;
            int isdouble_int = 0;
            int dice_copy = 0;
            boolean isdouble_bool = false;


            int round = 0;
            int[] diceValues = new int[4];
            int doubleOwner = 0;
            DoublingCube doublingCube = new DoublingCube();

            System.out.println("Let's roll the dice to determine the starting player...");
            while (dicedraw == true) {
                diceValues = Die.rollDice();
                System.out.println("DICE " + player1 + ":" + "[" + diceValues[0] + "]");
                int total1 = diceValues[0];

                System.out.println("DICE " + player2 + ":" + "[" + diceValues[1] + "]");
                int total2 = diceValues[1];

                if (total1 > total2) {
                    System.out.println(player1 + " goes first!");
                    currentplayer = 1;
                    doubleOwner = 1;
                    dicedraw = false;
                }
                if (total1 < total2) {
                    System.out.println(player2 + " goes first!");
                    currentplayer = 2;
                    doubleOwner = 2;
                    dicedraw = false;
                }
                if (total1 == total2) {
                    System.out.println("Draw! Lets roll again!");
                }
            }

            //Setup Game, Create Board and necessary variables
            Board myBoard = new Board(8);

        /*testing
        Checker[] white = new Checker[5];
        Checker[] red = new Checker[5];
        white[0]=new Checker("\u001B[37m",0,0,25);
        white[1]=new Checker("\u001B[37m",0,0,25);
        myBoard.setBeamWhite_Checkerarr(white);
        myBoard.setBeamRed_Checkerarr(red);
        myBoard.setBarPointerRed_int(0);
        myBoard.setBarPointerWhite_int(2);*/

            myBoard.createBoard();
            myBoard.printBoard(currentplayer);


            List<Integer> freeCheckers = new ArrayList<>();
            List<Integer> destination = new ArrayList<>();
            boolean movesPossible = true;
            int pipp1 = 0;
            int pipp2 = 0;
            int score1 = 0;
            int score2 = 0;
            int pickedChecker = -1;
            boolean endGame = false;

            while (true) {

                if (endGame == false) {
                    Scanner scanner = new Scanner(System.in);
                    //Gives user the choice to roll or to check the pip count(IDK what we're hinting because we basically do that already with our move functions)

                   try {
                       if (currentplayer == 1) {
                           System.out.println("Player 1: " + player1);
                       }
                       if (currentplayer == 2) {
                           System.out.println("Player 2: " + player2);
                       }

                       if (doubleOwner == 1 && currentplayer == 1) {
                           System.out.println("- Type 'R' if you would like to roll the dice.");
                           System.out.println("- Type 'P' if you would like to view the pip count:");
                           System.out.println("- Type 'Q' if you would like to quit the game.");
                           System.out.println("- Type 'M' if you would like to display the match length and scores.");
                           System.out.println("- Type 'D' if you would like to use the doubling.");
                       } else if (doubleOwner == 2 && currentplayer == 2) {
                           System.out.println("- Type 'R' if you would like to roll the dice.");
                           System.out.println("- Type 'P' if you would like to view the pip count:");
                           System.out.println("- Type 'Q' if you would like to quit the game.");
                           System.out.println("- Type 'M' if you would like to display the match length and scores.");
                           System.out.println("- Type 'D' if you would like to use the doubling.");
                       } else {
                           System.out.println("- Type 'R' if you would like to roll the dice.");
                           System.out.println("- Type 'P' if you would like to view the pip count:");
                           System.out.println("- Type 'Q' if you would like to quit the game.");
                           System.out.println("- Type 'M' if you would like to display the match length and scores.");
                       }
                       String choice = scanner.nextLine().toUpperCase();


                       if ("Q".equals(choice)) //quit game
                       {
                           System.out.println("Thanks for playing! Goodbye!! :)");
                           break;
                       }

                       else if("D".equals(choice)){
                           if (currentplayer == 1 && doubleOwner == 1) {
                               System.out.println("Player 1:" + player1 + " has requested to double.");
                           }
                           else if (currentplayer == 2 && doubleOwner == 2) {
                               System.out.println("Player 2:" + player2 + " has requested to double.");
                           }
                           else{
                               System.out.println("You cannot choose the double feature as you do not have ownership of the dice.");
                               break;
                           }
                           System.out.println("If you would like to accept type 'A' if you would like to refuse type 'R'");
                           String choice2 = scanner.nextLine().toUpperCase();

                           if("A".equals(choice2)) {
                               doublingCube.doubleCube();
                               if (doubleOwner == 1) {
                                   System.out.println("Ownership has switched to player 2");
                                   doubleOwner = 2;
                               }
                               else {
                                   System.out.println("Ownership has switched to player 1");
                                   doubleOwner = 1;
                               }
                           }

                           if("R".equals(choice2)){
                               System.out.println("Double proposal has been rejected.");
                           }
                       }

                       else if("M".equals(choice)){
                           System.out.println("Match Length: " + matchLength);
                           System.out.println("Player 1: " + player1 + "'s score: " + score1);
                           System.out.println("Player 2: " + player2 + "'s score: " + score2);
                           System.out.println("");
                       }

                       else if ("P".equals(choice)) {
                           if (currentplayer == 1) {
                               pipp1 = myBoard.getPipCount(currentplayer);
                               System.out.println("Your Pip count is:" + pipp1);
                           }
                           if (currentplayer == 2) {
                               pipp2 = myBoard.getPipCount(currentplayer);
                               System.out.println("Your Pip count is:" + pipp2);
                           }
                       }

                       else if ("R".equals(choice)) {
                           if (currentplayer == 1) {
                               System.out.println(player1 + "'s Move!");
                               pipp1 = myBoard.getPipCount(currentplayer);
                               System.out.println("Pipcount:" + pipp1);
                           }
                           if (currentplayer == 2) {
                               System.out.println(player2 + "'s Move!");
                               pipp2 = myBoard.getPipCount(currentplayer);
                               System.out.println("Pipcount:" + pipp2);
                           }
                           if (round == 0) // Use dice thrown in inital round for first moves
                           {
                               System.out.println("Start with these Dice:");
                               round = round + 1;
                           } else {
                               System.out.println("Rolling the dice...");


                               diceValues = Die.rollDice();


                               if (diceValues[0] == diceValues[1]) // double was thrown
                               {
                                   System.out.println("You have a double!");
                                   System.out.println("Play your two sets of dice sequentially");
                                   isdouble_int = 0; //while loop will be ran twice
                                   isdouble_bool = true; // needed so on second iteration of loop the dice can be rewritten to the original throw
                                   dice_copy = diceValues[0]; // saves the dice double that was thrown
                               } else {
                                   isdouble_int = 1; // regular round only goes through loop once
                               }
                           }

                           int total = 0;

                           while (isdouble_int < 2) // when a double is rolled the loop is ran twice
                           {
                               if ((isdouble_bool == true) && (isdouble_int == 1)) //reset dice for second round of moves in case of double
                               {
                                   movesPossible = true;
                                   diceValues[0] = dice_copy;
                                   diceValues[1] = dice_copy;
                                   System.out.println("Second Round of Your Double");
                               }

                               while (movesPossible == true)// player has moves left to make
                               {
                                   freeCheckers.clear(); // clear calculated freeCheckers and destinations from last round
                                   destination.clear();

                                   for (int i = 0; i <= 1; i++) // Print Dice that are not 0 and total value
                                   {
                                       if (diceValues[i] != 0) {
                                           System.out.println("Die " + (i + 1) + ": " + diceValues[i]);
                                       }
                                   }
                                   total = diceValues[0] + diceValues[1];
                                   System.out.println();
                                   System.out.println("Total: " + total);
                                   System.out.println();

                                   if (myBoard.hasCheckerOnBeam(currentplayer)) // CheckeronBeam
                                   {
                                       System.out.println("You have a checker on the Beam");
                                       myBoard.printBoard(currentplayer);
                                       diceValues = myBoard.calculateMovesFromBeam(currentplayer, diceValues);
                                       if ((diceValues[0] == 0) && (diceValues[1] == 0)) {
                                           movesPossible = false; // end round -> no moves possible
                                           isdouble_int = 2; // cancel double

                                       }

                                   } else if (movesPossible == true) //free moves
                                   {
                                       freeCheckers = myBoard.findFreeCheckers(currentplayer, diceValues); // find free checkers
                                       if (myBoard.canBearOff(currentplayer)) {
                                           System.out.println("You can bear off!");
                                       }
                                       if (freeCheckers.size() == 0) {
                                           System.out.println("No Moves Possible!"); // no moves possible with this throw of dice
                                           movesPossible = false;
                                           break;
                                       }
                                       myBoard.highlightCheckersandPrint(freeCheckers, currentplayer); // highlight free checkers
                                       pickedChecker = this.promptUserPickChecker(freeCheckers, currentplayer); //ask user to pick a checker to move
                                       destination = myBoard.calculateMoves(pickedChecker, diceValues, currentplayer); // calculate the possible destinations for that checker

                                       for (int i = 0; i < destination.size(); i++) //show possible destinations of that checker to user
                                       {
                                           if (currentplayer == 1) {
                                               System.out.println("This Checker can move to: " + (destination.get(i) + 1));
                                           }
                                           if (currentplayer == 2) {
                                               System.out.println("This Checker can move to: " + (24 - destination.get(i)));
                                           }
                                       }
                                       diceValues = myBoard.promptUserPickDestination(pickedChecker, destination, currentplayer, diceValues); //ask user to pick destination and calculate new dice values


                                       freeCheckers = myBoard.findFreeCheckers(currentplayer, diceValues); //check if there are movable checkers left after the move with current dice combination
                                       if (freeCheckers.isEmpty()) //  if no moves possible -> End turn else start over
                                       {
                                           System.out.println("No Moves Possible!");
                                           movesPossible = false;
                                       }
                                       if (myBoard.checkForWin(currentplayer) == true) // End of game
                                       {
                                           System.out.println("Player: " + currentplayer + " won. End of Game!");
                                           isdouble_int = 2;
                                           movesPossible = false;
                                           endGame = true;
                                           if (currentplayer == 1){
                                               score1+=1;
                                               score1=score1*doublingCube.getValue();
                                           }
                                           else score2 += 1; score2 = score2*doublingCube.getValue();//stake increase
                                       }
                                   }

                               }
                               isdouble_int = isdouble_int + 1; //increase double loop counter
                           }
                           //At the end of the turn swap player and reset while loop variable
                           if (currentplayer == 1) {
                               currentplayer = 2;
                               movesPossible = true;

                           } else if (currentplayer == 2) {
                               currentplayer = 1;
                               movesPossible = true;
                           }
                       } else {
                           throw new InvalidEntryException("Invalid entry. Please enter 'R', 'P', 'M', 'D', 'H' or 'Q'.");
                       }

                   } catch (InvalidEntryException e) {
                       System.out.println(e.getMessage());
                   }
                }
                /*if (score1 != matchLength | score2 != matchLength){
                    endGame = false;
                }*/ //Confused on match ending if score matches match length or if match length is for how many games
                    //the users play.

            }

    }
    public int promptUserPickChecker(List<Integer> movableCheckers, int playerMoving) // asks user to pick a checker
    {
        Scanner scanner = new Scanner(System.in);
        boolean succesfullPick=false;
        System.out.println();
        int choice_int=0;
        while(succesfullPick==false)
        {
            for(int i =0;i<movableCheckers.size();i++)
            {
                if(playerMoving==1)
                {
                    System.out.println("Enter "+(i+1)+" to move Checker at position "+(movableCheckers.get(i)+1));
                }
                if(playerMoving==2)
                {
                    System.out.println("Enter "+(i+1)+" to move Checker at position "+(24-(movableCheckers.get(i))));
                }

            }
            System.out.println();
            choice_int = scanner.nextInt()-1; //remove offset from prompt

            if((choice_int>=0)&&(choice_int<movableCheckers.size()))
            {
               succesfullPick=true;

            }
            else
            {
                System.out.println("Wrong input, try again!");
                succesfullPick=false;
            }
        }

        return movableCheckers.get(choice_int);
    }
}



