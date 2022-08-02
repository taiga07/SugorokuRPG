package SugorokuRPG.character;

import java.util.List;
import java.util.Random;

public class Warrior extends Job {

	private int maxhp = 0;
	private int str = 0;
	private int maxstr = 0;

	//getメソッド
	public int getMaxhp() {
		return maxhp;
	}

	public int getStr() {
		return str;
	}

	public int getMaxstr() {
		return maxstr;
	}

	//setメソッド
	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public void setMaxstr(int maxstr) {
		this.maxstr = maxstr;
	}

	//戦闘時メソッド
	public int attack(List<Job> party) {
		Random r = new Random();
		Warrior w = (Warrior) party.get(0);

		int i = r.nextInt(2) + 1;
		//0.0～1.0の乱数の取得
		double d = r.nextDouble();

		if (d > 0.5) {
			d = d - 0.5;
			w.setStr((int) (w.getStr() - (w.getStr()*d)));
		}else {
			w.setStr((int) (w.getStr() - (w.getStr()*d)));
		}
		int damage = (w.getMaxstr() * i);
		return damage;
	}

}
