package SugorokuRPG.party;

public class Party {

	//フィールド
	private int nowSpaceIndex = 0; //現在地
	private String warrStat = "normal"; //戦士の状態
	private String priStat = "normal";  //僧侶の状態
	private String magStat = "normal";  //魔法使いの状態
	private int items = 0; //パーティーの回復剤の数
	private int teaBreak = 0;
	
	
	//getメソッド
	public int getNowSpaceIndex() {
		return nowSpaceIndex;
	}
	public String getWarrStat() {
		return warrStat;
	}
	public String getPriStat() {
		return priStat;
	}
	public String getMagStat() {
		return magStat;
	}
	public int getItems() {
		return items;
	}	
	public int getTeaBreak() {
		return teaBreak;
	}
	
	
	//setメソッド
	public void setNowSpaceIndex(int nowSpaceIndex) {
		this.nowSpaceIndex += nowSpaceIndex;
	}
	public void setWarrStat(String warrStat) {
		this.warrStat = warrStat;
	}
	public void setPriStat(String priStat) {
		this.priStat = priStat;
	}
	public void setMagStat(String magStat) {
		this.magStat = magStat;
	}
	public void setItems(int items) {
		this.items += items;
	}
	public void setTeaBreak(int teaBreak) {
		this.teaBreak += teaBreak;
	}
	
}
