package SugorokuRPG.party;

import java.util.ArrayList;
import java.util.List;

//パーティー生成クラス
public class MakeParty {
	
	public List<Party> makeParty() {
		
		//パーティーデータの配列生成
		List<Party> partyList = new ArrayList<Party>();
		
		Party p1 = new Party();
		partyList.add(p1);  //自パーティー
		Party p2 = new Party();
		partyList.add(p2);  //CPUパーティー
		
		return partyList;
	}
}
