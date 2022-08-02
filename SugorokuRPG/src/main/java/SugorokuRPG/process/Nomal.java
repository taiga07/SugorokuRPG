package SugorokuRPG.process;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import SugorokuRPG.character.Job;
import SugorokuRPG.character.JobParameter;
import SugorokuRPG.character.Magic;
import SugorokuRPG.character.Priest;
import SugorokuRPG.character.Warrior;
import SugorokuRPG.party.Party;
import SugorokuRPG.party.PartyStatus;

public class Nomal {

	//キーボード入力準備
	Scanner scanner = new Scanner(System.in);
	JobParameter jp = new JobParameter();
	PartyStatus ps = new PartyStatus();

	public void myNomalProcess(List<Party> partyList, List<Job> myParty) {

		System.out.println("1～5の番号で入力してください");
		System.out.println("");

		int num;
		while (true) {
			System.out.println("1　なにもしない");
			System.out.println("2　回復剤を使ってパーティー全員のHP・MP全回復");
			System.out.println("3　1回休んで(次自ターン何もできない)パーティー全員のHP・MP全回復");
			System.out.println("4　2回休んで(次と次々自ターン何もできない)パーティー内死者蘇生とパーティー全員のHP・MP全回復");
			System.out.println("5　パーティステータスを見る");
			try {
				num = scanner.nextInt();
				if (num == 1 || num == 3 || num == 4 || num == 5) {
					break;
				} else if (num == 2) {
					if (partyList.get(0).getItems() > 0) {
						break;
					} else {
						System.out.println("回復剤を持っていません。");
						System.out.println();
					}
				} else {
					System.out.println("1～5の番号で入力してください");
				}

			} catch (InputMismatchException e) {
				System.out.println("1～5の番号で入力してください");
			}
		}

		switch (num) {
		case 1:
			System.out.println("なにもしませんでした。");
			break;

		case 2:
			//mypartyの回復処理
			jp.useRestorative(myParty);
			partyList.get(0).setItems(-1); //回復剤を1つ減らす
			System.out.println("回復剤を使って、HPとMPを全回復しました。");
			System.out.println("残りの回復剤は" + partyList.get(0).getItems() + "個です。");
			break;

		case 3:
			jp.useRestorative(myParty);
			partyList.get(0).setTeaBreak(1);
			System.out.println("HPとMPを全回復しました。");
			System.out.println("1ターンは休みです。");
			break;

		case 4:
			jp.useRestorative(myParty);
			partyList.get(0).setWarrStat("normal");
			partyList.get(0).setPriStat("normal");
			partyList.get(0).setMagStat("normal");
			partyList.get(0).setTeaBreak(2);
			System.out.println("死んでいるメンバーが生き返り、HPとMPを全回復しました。");
			System.out.println("2ターン休みです。");
			break;
			
		case 5:
			ps.showPartyStatus(myParty,partyList);
		}

	}

	public void cpuNomalProcess(List<Party> partyList, List<Job> cpuParty) {

		//挙動確認用コード
		//1　通常マスでCPUのHPが半分以下の時
		//		for (Job j : cpuParty) {
		//			j.setHp(1);
		//			System.out.println(j.getJobname() + "HP：" + j.getHp());
		//		}
		//1-1　アイテムが1この時　→２の行動
		//		partyList.get(1).setItems(1);
		//		//1-2　アイテムが0この時　→３の行動
		//		partyList.get(1).setItems(0);
		//		//1-3　死んでいるキャラがいたら　→４の行動
		//		partyList.get(1).setWarrStat("dead");
		//		System.out.println("");
		//		System.out.println("戦士の状態：" + partyList.get(1).getWarrStat());
		//		System.out.println("僧侶の状態：" + partyList.get(1).getPriStat());
		//		System.out.println("魔法使いの状態：" + partyList.get(1).getMagStat());
		//ここまで

		if (partyList.get(1).getWarrStat() == "dead" ||
				partyList.get(1).getPriStat() == "dead" ||
				partyList.get(1).getMagStat() == "dead") {
			jp.useRestorative(cpuParty);
			partyList.get(1).setWarrStat("normal");
			partyList.get(1).setPriStat("normal");
			partyList.get(1).setMagStat("normal");
			partyList.get(1).setTeaBreak(2);
			System.out.println("死んでいるメンバーが生き返り、HPとMPを全回復しました。");
			System.out.println("2ターン休みです。");

			//確認用コード
			//			System.out.println("");
			//			System.out.println("戦士の状態："+partyList.get(1).getWarrStat());
			//			System.out.println("僧侶の状態："+partyList.get(1).getPriStat());
			//			System.out.println("魔法使いの状態："+partyList.get(1).getMagStat());
			//			for(Job j : cpuParty) {
			//				System.out.println(j.getJobname()+"HP："+j.getHp());
			//			}
			//ここまで

			return;
		}

		if (cpuParty.get(0) instanceof Warrior && cpuParty.get(1) instanceof Priest
				&& cpuParty.get(2) instanceof Magic) {
			Warrior w = (Warrior) cpuParty.get(0);
			Priest p = (Priest) cpuParty.get(1);
			Magic m = (Magic) cpuParty.get(2);

			if (0 < w.getHp() && w.getHp() < w.getMaxhp() / 2 ||
					0 < p.getHp() && p.getHp() < p.getMaxhp() / 2 ||
					0 < m.getHp() && m.getHp() < m.getMaxhp() / 2) {

				if (partyList.get(1).getItems() > 0) {
					jp.useRestorative(cpuParty);
					partyList.get(1).setItems(-1); //回復剤を1つ減らす
					System.out.println("回復剤を使って、HPとMPを全回復。");

					//確認用コード
					for (Job j : cpuParty) {
						System.out.println(j.getJobname() + "HP：" + j.getHp());
					}
					//ここまで

				} else {
					jp.useRestorative(cpuParty);
					partyList.get(1).setTeaBreak(1);
					System.out.println("HPとMPを全回復しました。");
					System.out.println("1ターン休みです。");
				}

			} else {
				System.out.println("なにもしませんでした");
			}
		}
	}
}
