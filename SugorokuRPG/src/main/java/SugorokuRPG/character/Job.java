package SugorokuRPG.character;

import java.util.Random;

public class Job {
	
	//共通フィールド
	private String jobname = "";
	private int hp = 0;
	private int agi = 0;
	
	
	//getメソッド
	public String getJobname() {
		return jobname;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getAgi() {
		return agi;
	}
	
	//setメソッド
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setAgi(int agi) {
		this.agi = agi;
	}
	
	//戦闘時メソッド
	public String run(String runJudgement, Job characterName) {
		Random r = new Random();
		int i = r.nextInt(2);
		if(i == 0) {
			runJudgement = "0";
		}else {
			System.out.println("モンスターから逃げ切れなかった。");
			characterName.setHp(characterName.getHp()-5);
			System.out.println(characterName.getJobname()+"は5ダメージ受けた。");
		}
		return runJudgement;
	}
	
}
