package SugorokuRPG.party;

import java.util.List;

import SugorokuRPG.character.Job;
import SugorokuRPG.character.Magic;
import SugorokuRPG.character.Priest;
import SugorokuRPG.character.Warrior;

public class PartyStatus {

	public void showPartyStatus(List<Job> myParty, List<Party> partyList) {
		if (myParty.get(0) instanceof Warrior && myParty.get(1) instanceof Priest
				&& myParty.get(2) instanceof Magic) {
			Warrior w = (Warrior) myParty.get(0);
			Priest p = (Priest) myParty.get(1);
			Magic m = (Magic) myParty.get(2);
			
			System.out.println("");
			System.out.printf("%-9s%s%3d%s%-5d%s%d%s%-6d%s%d%n", w.getJobname(), "HP：", w.getHp(), "/",
					w.getMaxhp(), "力：", w.getStr(), "/", w.getMaxstr(), "素早さ：", w.getAgi());

			System.out.printf("%-9s%s%3d%s%-5d%s%d%s%-6d%s%d%s%-6d%s%d%n", p.getJobname(), "HP：", p.getHp(),
					"/", p.getMaxhp(), "力：", p.getStr(), "/", p.getMaxstr(), "MP：", p.getMp(), "/",
					p.getMaxmp(), "素早さ：", p.getAgi());

			System.out.printf("%-7s%s%3d%s%-5d%s%d%s%-6d%s%d%n", m.getJobname(), "HP：", m.getHp(), "/",
					m.getMaxhp(), "MP：", m.getMp(), "/", m.getMaxmp(), "素早さ：", m.getAgi());
		}
		System.out.println("回復剤："+partyList.get(0).getItems()+"個");
		System.out.println("-----------------------------------------------------------------");

	}

}
