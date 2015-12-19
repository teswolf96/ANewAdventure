import java.util.Random;

public class Dice {

	public static int roll(int sides){
		Random diceRoller = new Random(System.nanoTime() * System.nanoTime());
		return diceRoller.nextInt(sides)+1;
	}
}
