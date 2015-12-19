import java.util.Random;

public class Dice {

	public int roll(int sides){
		Random diceRoller = new Random(System.currentTimeMillis());
		return diceRoller.nextInt(sides)+1;
	}
}
