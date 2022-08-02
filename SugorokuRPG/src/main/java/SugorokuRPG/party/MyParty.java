package SugorokuRPG.party;

import java.util.ArrayList;
import java.util.List;

import SugorokuRPG.character.Job;
import SugorokuRPG.character.JobParameter;
import SugorokuRPG.character.Magic;
import SugorokuRPG.character.Priest;
import SugorokuRPG.character.Warrior;

public class MyParty {

	public List<Job> myParty() {
		JobParameter jp = new JobParameter();

		//myPartyの配列作成
		List<Job> myParty = new ArrayList<Job>();

		//0番目に戦士
//		Job warrior = new Warrior();//↓省略
		myParty.add(new Warrior());
		jp.warriorParameter(myParty);

		//1番目に僧侶
//		Job priest = new Priest();
		myParty.add(new Priest());
		jp.priestParameter(myParty);

		//2番目に魔法使い
//		Job magic = new Magic();
		myParty.add(new Magic());
		jp.magicParameter(myParty);
		
		return myParty;

	}
}
