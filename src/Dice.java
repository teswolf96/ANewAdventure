import java.util.Random;

public class Dice {

	public static int roll(int sides){
		Random diceRoller = new Random(System.nanoTime() * System.currentTimeMillis());
		return diceRoller.nextInt(sides)+1;
	}
}
