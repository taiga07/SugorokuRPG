package SugorokuRPG.party;

import java.util.ArrayList;
import java.util.List;

import SugorokuRPG.character.Job;
import SugorokuRPG.character.JobParameter;
import SugorokuRPG.character.Magic;
import SugorokuRPG.character.Priest;
import SugorokuRPG.character.Warrior;

public class CpuParty {
	
	public List<Job> cpuParty() {
		JobParameter jp = new JobParameter();

		//cpuPartyの配列作成
		List<Job> cpuParty = new ArrayList<Job>();

		//0番目に戦士
		cpuParty.add(new Warrior());
		jp.warriorParameter(cpuParty);

		//1番目に僧侶
		cpuParty.add(new Priest());
		jp.priestParameter(cpuParty);

		//2番目に魔法使い
		cpuParty.add(new Magic());
		jp.magicParameter(cpuParty);
		
		return cpuParty;
		
		

	}

}
