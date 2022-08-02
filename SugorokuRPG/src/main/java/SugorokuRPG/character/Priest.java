package SugorokuRPG.character;

import java.util.List;
import java.util.Random;

public class Priest extends Job {

	private int maxhp = 0;
	private int mp = 0;
	private int maxmp = 0;
	private int str = 0;
	private int maxstr = 0;

	Random r = new Random();

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

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void setMaxmp(int maxmp) {
		this.maxmp = maxmp;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public void setMaxstr(int maxstr) {
		this.maxstr = maxstr;
	}

	//戦闘時メソッド
	public int attack(List<Job> party) {

		Priest p = (Priest) party.get(1);

		int i = r.nextInt(2) + 1;
		//0.0～1.0の乱数の取得
		double d = r.nextDouble();

		if (d > 0.5) {
			d = d - 0.5;
			p.setStr((int) (p.getStr() - d));
		} else {
			p.setStr((int) (p.getStr() - d));
		}

		int damage = (p.getMaxstr() * i);

		return damage;

	}

	public String heal(List<Job> party, String healJudgement) {
		Priest p = (Priest) party.get(1);
		//0.0～1.0の乱数の取得
		double d = r.nextDouble();

		if (d > 0.5) {
			d = d - 0.5;
			p.setMp((int) (p.getMp() - (p.getMp() * d)));
		} else {
			p.setMp((int) (p.getMp() - (p.getMp() * d)));
		}
		//全員回復
		for (Job pa : party) {
			if (pa.getHp() > 0) {
				if(pa.getJobname().equals("戦士")) {
					Warrior w = (Warrior) party.get(0);
					w.setHp(w.getMaxhp());
				}
				if(pa.getJobname().equals("僧侶")) {
					p.setHp(p.getMaxhp());
				}
				if(pa.getJobname().equals("魔法使い")) {
					Magic m = (Magic) party.get(2);
					m.setHp(m.getMaxhp());
				}
			}
		}
		System.out.println("パーティーのHPを全回復した。");
		healJudgement ="0";
		return healJudgement;
	}
}
