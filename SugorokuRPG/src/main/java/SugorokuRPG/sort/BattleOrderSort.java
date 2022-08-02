package SugorokuRPG.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import SugorokuRPG.character.Job;

public class BattleOrderSort {

	public void agiSort(List<Job> battleOrder) {
		//Agi(素早さ)が高い順に並び替える
		Collections.sort(battleOrder, new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				return o1.getAgi() > o2.getAgi() ? -1 : 1;
			}
		});
	}

}
