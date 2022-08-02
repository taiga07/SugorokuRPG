package SugorokuRPG.masu;

public class SpaceData {

	//フィールド
	private String spaceName; //マスの種類
	private int spaceMonsInt = 1; //モンスター数
	private int spaceItemInt = 1; //回復剤個数
	
	//getメソッド
	public String getSpaceName() {
		return spaceName;
	}
	public int getSpaceMonsInt() {
		return spaceMonsInt;
	}
	
	public int getSpaceItemInt() {
		return spaceItemInt;
	}
	
	
	//setメソッド
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	public void setSpaceMonsInt(int spaceMonsInt) {
		this.spaceMonsInt = spaceMonsInt;
	}
	
	public void setSpaceItemInt(int spaceItemInt) {
		this.spaceItemInt = spaceItemInt;
	}

	
	
}
