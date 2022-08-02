package SugorokuRPG;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import SugorokuRPG.character.Job;
import SugorokuRPG.masu.MakeSpace;
import SugorokuRPG.masu.SpaceData;
import SugorokuRPG.party.CpuParty;
import SugorokuRPG.party.MakeParty;
import SugorokuRPG.party.MyParty;
import SugorokuRPG.party.Party;
import SugorokuRPG.party.PartyStatus;

public class SgrkRPGMain {

	public static void main(String[] args) {

		//マス生成
		MakeSpace mk = new MakeSpace();
		SpaceData[] space = mk.makeSpace();

		//パーティー生成
		MakeParty mp = new MakeParty();
		List<Party> partyList = mp.makeParty();

		//MyParty生成
		MyParty mParty = new MyParty();
		List<Job> myParty = mParty.myParty();

		//CPUParty生成
		CpuParty cpParty = new CpuParty();
		List<Job> cpuParty = cpParty.cpuParty();

		//サイコロ振るクラス生成
		RollTheDice rtd = new RollTheDice();
		
		PartyStatus ps = new PartyStatus();

		System.out.println("すごろくRPGを開始します！");
		System.out.println("");

		while (true) {
			System.out.println("あなたのターンです。");
			System.out.println("現在位置");
			System.out.println("　あなた：" + partyList.get(0).getNowSpaceIndex() + "マス目");
			System.out.println("　ⅭＰＵ：" + partyList.get(1).getNowSpaceIndex() + "マス目");
			System.out.println("");
			
			int num;
			while (true) {
				while (true) {
					Scanner scanner = new Scanner(System.in);

					System.out.println("行動を番号で選択");
					System.out.println("　１：サイコロを振る");
					System.out.println("　２：パーティステータスを見る");

					try {
						num = scanner.nextInt();
						if (num == 1 || num == 2) {
							break;
						} else {
							System.out.println("1または2の番号で選択してください。");
						}
					} catch (InputMismatchException e) {
						System.out.println("1または2の番号で選択してください。");
					}
				}
				if (num == 1) {
					rtd.myRoll(partyList, space, myParty);
					break;
				} else {
					//パーティステータス表示
					ps.showPartyStatus(myParty,partyList);
				}
			}
			if (partyList.get(0).getNowSpaceIndex() >= 101) {
				System.out.println("おめでとうございます！");
				System.out.println("先にゴールしてゲームに勝利しました。");
				break;//プログラム終了
			} else {
				System.out.println("ターン終了です。");
				System.out.println("");
				System.out.println("-----------------------------------------------------------------");
			}
			
			System.out.println("CPUターンです。");
			rtd.cpuRoll(partyList, space, cpuParty);
			if (partyList.get(1).getNowSpaceIndex() >= 101) {
				System.out.println("CPUの勝利です。");
				break;
			}
			System.out.println("-----------------------------------------------------------------");
			System.out.println("");

		}
	}
}
