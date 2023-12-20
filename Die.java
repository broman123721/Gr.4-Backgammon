import java.util.Random;

class Die {
    private int sides_int;
    public Die(int sides){
        this.sides_int = sides;
    }

    public int roll(){
        Random random = new Random();
        return random.nextInt(sides_int) + 1;
    }

    public static int[] rollDice(){
        Die die1 = new Die(6); // Six-sided die
        Die die2 = new Die(6); // Another six-sided die


        int die3 = 0;
        int die4 = 0;

        int result1 = die1.roll();
        int result2 = die2.roll();

        if(result1 == result2){
            die3 = result1;
            die4 = result1;
        }

        return new int[]{result1, result2, die3, die4};

     }
}
