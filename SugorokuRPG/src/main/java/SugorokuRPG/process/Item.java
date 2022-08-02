package SugorokuRPG.process;

import java.util.List;
import java.util.Random;

import SugorokuRPG.party.Party;

public class Item {

	Random rnd = new Random();

	public void myItemProcess(List<Party> partyList) {

		int i = rnd.nextInt(3) + 1; //回復剤1～3個
		System.out.println("回復剤を" + i + "個手に入れました。");
		partyList.get(0).setItems(i);
		System.out.println("回復剤を" + partyList.get(0).getItems() + "個持っています。");
	}

	public void cpuItemProcess(List<Party> partyList) {

		int i = rnd.nextInt(3) + 1;
		System.out.println("回復剤を" + i + "個手に入れました。");
		partyList.get(1).setItems(i);
	}
}
