package SugorokuRPG.character;

import java.util.List;
import java.util.Random;

public class Monster extends Job {
	
	private int str = 0;
	private int maxstr = 0;

	//getメソッド
	public int getStr() {
		return str;
	}
	
	public int getMaxstr() {
		return maxstr;
	}


	//setメソッド
	public void setStr(int str) {
		this.str = str;
	}

	public void setMaxstr(int maxstr) {
		this.maxstr = maxstr;
	}

	//戦闘時メソッド
	public int attack(List<Job> monsList) {
		Random r = new Random();
		Monster monster = (Monster) monsList.get(0);

		int i = r.nextInt(2) + 1;
		//0.0～1.0の乱数の取得
		double d = r.nextDouble();

		if (d > 0.5) {
			d = d - 0.5;
			monster.setStr((int) (monster.getStr()-d));
		}else {
			monster.setStr((int) (monster.getStr()-d));
		}

		int damage = (monster.getMaxstr() * i);

		return damage;

	}

}
