package SugorokuRPG;

import java.util.List;
import java.util.Random;

import SugorokuRPG.character.Job;
import SugorokuRPG.masu.SpaceData;
import SugorokuRPG.party.Party;
import SugorokuRPG.process.Battle;
import SugorokuRPG.process.Item;
import SugorokuRPG.process.Nomal;

//サイコロ振るクラス
public class RollTheDice {

	//1～6までの乱数生成準備
	Random rnd = new Random();

	Nomal n = new Nomal();
	Item i = new Item();
	Battle b = new Battle();

	public void myRoll(List<Party> partyList, SpaceData[] space, List<Job> myParty) {
		//自パーティー
		if (partyList.get(0).getTeaBreak() == 0) {
			int r = rnd.nextInt(6) + 1; //サイコロ1～6
			System.out.println("出た目は" + r + "です。 " + r + "マス移動します。");
			System.out.println("");
			partyList.get(0).setNowSpaceIndex(r); //出た値をNowSpaceIndexに足していく
			int nSI = partyList.get(0).getNowSpaceIndex(); //現在の位置を取得

			//ゴールしているか確認
			if (nSI >= 101) {
				
			} else {
				String s = space[nSI].getSpaceName(); //その位置のマスの種類を取得

				switch (s) {

				case "normal":
					System.out.println("通常マスです。");
					n.myNomalProcess(partyList, myParty);
					break;

				case "item":
					System.out.println("お宝マスです。");
					i.myItemProcess(partyList);
					break;

				case "battle":
					System.out.println("戦闘マスです。");
					b.myBattleProcess(partyList, myParty);
					break;
				}
			}
		} else {
			partyList.get(0).setTeaBreak(-1);
			System.out.println("休みのため何もしませんでした。");
			System.out.println("残り" + partyList.get(0).getTeaBreak() + "回休みです。");
		}
	}

	public void cpuRoll(List<Party> partyList, SpaceData[] space, List<Job> cpuParty) {
		//		CPUパーティー
		if (partyList.get(1).getTeaBreak() == 0) {
			int r = rnd.nextInt(6) + 1;
			System.out.println("CPUは" + r + "マス進みました。");
			System.out.println("");
			partyList.get(1).setNowSpaceIndex(r);
			int nSI = partyList.get(1).getNowSpaceIndex(); //現在の位置を取得

			//ゴールしているか確認
			if (nSI >= 101) {
				
			} else {
				String s = space[nSI].getSpaceName(); //その位置のマスの種類を取得

				switch (s) {

				case "normal":
					System.out.println("通常マスです。");
					n.cpuNomalProcess(partyList, cpuParty);
					break;

				case "item":
					System.out.println("お宝マスです。");
					i.cpuItemProcess(partyList);
					break;

				case "battle":
					System.out.println("戦闘マスです。");
					b.cpuBattleProcess(partyList, cpuParty);
					break;
				}
			}
		} else {
			partyList.get(1).setTeaBreak(-1);
			System.out.println("休みのため何もしませんでした。");
			System.out.println("残り" + partyList.get(1).getTeaBreak() + "回休みです。");
		}
	}
}
