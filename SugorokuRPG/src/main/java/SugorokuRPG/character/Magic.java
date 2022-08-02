package SugorokuRPG.character;

import java.util.List;
import java.util.Random;

public class Magic extends Job {

	private int maxhp = 0;
	private int mp = 0;
	private int maxmp = 0;

	//getメソッド
	public int getMaxhp() {
		return maxhp;
	}

	public int getMp() {
		return mp;
	}

	public int getMaxmp() {
		return maxmp;
	}

	//setメソッド
	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void setMaxmp(int maxmp) {
		this.maxmp = maxmp;
	}

	//戦闘時メソッド
	public int mAtt(List<Job> party) {
		Random r = new Random();
		Magic m = (Magic) party.get(2);

		int i = r.nextInt(2) + 1;
		//0.0～1.0の乱数の取得
		double d = r.nextDouble();

		if (d > 0.5) {
			d = d - 0.5;
			m.setMp((int) (m.getMp() - (m.getMp() * d)));
		} else {
			m.setMp((int) (m.getMp() - (m.getMp() * d)));
		}
		int damage = (m.getMaxmp() * i);
		return damage;
	}

}
