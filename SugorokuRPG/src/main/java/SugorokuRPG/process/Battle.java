package SugorokuRPG.process;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import SugorokuRPG.character.Job;
import SugorokuRPG.character.JobParameter;
import SugorokuRPG.character.Magic;
import SugorokuRPG.character.Monster;
import SugorokuRPG.character.Priest;
import SugorokuRPG.character.Warrior;
import SugorokuRPG.party.Party;
import SugorokuRPG.sort.BattleOrderSort;

public class Battle {

	BattleOrderSort bos = new BattleOrderSort();

	@SuppressWarnings("unlikely-arg-type")
	public void myBattleProcess(List<Party> partyList, List<Job> myParty) {
		//モンスターとbattleOrderの配列生成
		List<Job> monsList = new ArrayList<Job>();
		List<Job> battleOrder = new ArrayList<Job>();
		monsList.add(new Monster());
		JobParameter jp = new JobParameter();
		//モンスターのパラメーターセット
		jp.monsterParameter(monsList);

		//自パーティーに死んでるキャラがいないか確認して、
		//生きてるキャラだけbattleOrder配列に加える。
		if (partyList.get(0).getWarrStat() == "dead") {
			battleOrder.add(myParty.get(1));
			battleOrder.add(myParty.get(2));
		} else if (partyList.get(0).getPriStat() == "dead") {
			battleOrder.add(myParty.get(0));
			battleOrder.add(myParty.get(2));
		} else if (partyList.get(0).getMagStat() == "dead") {
			battleOrder.add(myParty.get(0));
			battleOrder.add(myParty.get(1));
		} else {
			for (Job party : myParty) {
				battleOrder.add(party);
			}
		}

		//モンスターもbattleOrder配列に加える
		for (Job monster : monsList) {
			battleOrder.add(monster);
		}
		
		//Agi(素早さ)が高い順に並び替える
		bos.agiSort(battleOrder);

		while (true) {
			//戦闘中に倒されて、削除するキャラのindex番号を入れていく
			List<Integer> removeIndex = new ArrayList<>();

			String runJudgement = "999";
			String healJudgement = null;

			//行動選択
			for (Job characterName : battleOrder) {
				switch (characterName.getJobname()) {

				case "戦士":
					Warrior w = new Warrior();
					int num1;
					while (true) {
						Scanner scanner = new Scanner(System.in);
						System.out.println("戦士の行動");
						System.out.println("１　攻撃");
						System.out.println("２　逃げる");
						try {
							num1 = scanner.nextInt();
							if (num1 == 1 || num1 == 2) {
								break;
							} else {
								System.out.println("1または2の番号で選択してください。");
							}
						} catch (InputMismatchException e) {
							System.out.println("1または2の番号で選択してください。");
						}
					}
					switch (num1) {

					case 1:
						int damage = w.attack(myParty);
						for (Job monster : battleOrder) {
							if (monster.getJobname() == "モンスター") {
								System.out.println("モンスターに" + damage + "ダメージ与えた。");
								monster.setHp(monster.getHp() - damage);
								//HPが0以下になったモンスターをbattleOrderから削除
								if (monster.getHp() <= 0) {
									int indexNum = battleOrder.indexOf(monster);
									battleOrder.remove(indexNum);
								}
								break;
							}
						}
						break;

					case 2:
						runJudgement = w.run(runJudgement, characterName);
						break;
					}
					break;

				case "僧侶":
					Priest p = new Priest();
					Scanner scanner;
					int num2;
					while (true) {
						scanner = new Scanner(System.in);
						System.out.println("僧侶の行動");
						System.out.println("１　攻撃");
						System.out.println("２　治癒");
						System.out.println("３　逃げる");
						try {
							num2 = scanner.nextInt();
							if (num2 == 1 || num2 == 2 || num2 == 3) {
								break;
							} else {
								System.out.println("1または2または3の番号で選択してください。");
							}
						} catch (InputMismatchException e) {
							System.out.println("1または2または3の番号で選択してください。");
						}
					}
					switch (num2) {

					case 1:
						int damage = p.attack(myParty);
						for (Job monster : battleOrder) {
							if (monster.getJobname() == "モンスター") {
								System.out.println("モンスターに" + damage + "ダメージ与えた。");
								monster.setHp(monster.getHp() - damage);
								//HPが0以下になったモンスターをbattleOrderから削除
								if (monster.getHp() <= 0) {
									int indexNum = battleOrder.indexOf(monster);
									battleOrder.remove(indexNum);
								}
								break;
							}
						}
						break;

					case 2:
						p.heal(myParty, healJudgement);
						break;
					case 3:
						runJudgement = p.run(runJudgement, characterName);
						break;
					}
					break;

				case "魔法使い":
					Magic m = new Magic();
					int num3;
					while (true) {
						scanner = new Scanner(System.in);
						System.out.println("魔法使いの行動");
						System.out.println("１　魔法攻撃");
						System.out.println("２　逃げる");
						try {
							num3 = scanner.nextInt();
							if (num3 == 1 || num3 == 2) {
								break;
							} else {
								System.out.println("1または2の番号で選択してください。");
							}
						} catch (InputMismatchException e) {
							System.out.println("1または2の番号で選択してください。");
						}
					}
					switch (num3) {

					case 1:
						int damage = m.mAtt(myParty);
						for (Job monster : battleOrder) {
							if (monster.getJobname() == "モンスター") {
								monster.setHp(monster.getHp() - damage);
								//HPが0以下になったモンスターをbattleOrderから削除
								if (monsList.get(0).getHp() <= 0) {
									removeIndex.add(battleOrder.indexOf(monster));
								}
							}
						}
						System.out.println("モンスターに一律" + damage + "ダメージ与えた。");
						break;

					case 2:
						runJudgement = m.run(runJudgement, characterName);
						break;
					}
					break;

				case "モンスター":
					Monster monster = new Monster();
					System.out.println("モンスターの行動");
					int damage = monster.attack(monsList);

					for (Job character : battleOrder) {

						if (character.getJobname() != "モンスター") {

							System.out.println(character.getJobname() + "に" + damage + "ダメージ与えた。");
							character.setHp(character.getHp() - damage);
							//HPが0以下になったキャラクターをbattleOrderから削除
							if (character.getHp() <= 0) {
								// removeするindex番号を保持する
								removeIndex.add(battleOrder.indexOf(character));
							}
							break;
						}
					}
					//				for(Job character: battleOrder) {
					//					if (character.getJobname() != "モンスター") {
					//						System.out.println(character.getJobname() + "に" + damage + "ダメージ与えた。");
					//						character.setHp(character.getHp() - damage);
					//						//HPが0以下になったキャラクターをbattleOrderから削除
					//						if (character.getHp() <= 0) {
					//							
					//							Iterator<Job> iterator = battleOrder.iterator();
					//							while (iterator.hasNext()) {
					//								Job string = iterator.next();
					//								if (string.getJobname().equals(character.getJobname())) {
					//									iterator.remove();
					//								}
					//							}
					////
					////								int indexNum = battleOrder.indexOf(character);
					////								battleOrder.remove(indexNum);
					//						}
					//						break;
					//					}
					//				}

					break;
				}

				if (runJudgement.equals("0")) {
					System.out.println("モンスターから逃げ切った。");
					break;
				}

				//パーティーが全滅しているか、敵を倒したか確認
				//倒していなければ、次のキャラクターの行動に移る。
				if (myParty.get(0).getHp() <= 0 && myParty.get(1).getHp() <= 0 && myParty.get(2).getHp() <= 0) {
					System.out.println("ゲームオーバー");
					System.exit(0);
				} else if (monsList.get(0).getHp() <= 0) {
					System.out.println("モンスターを倒しました。");
					System.out.println("戦闘に勝利しました。");
					break;
				}
			}

			// 削除するindex番号を基に
			// battleOrderからremoveする
			for (int i : removeIndex) {
				battleOrder.remove(i);

			}

			//HPが0以下のキャラクターはdeadにする。
			if (myParty.get(0).getHp() <= 0) {
				partyList.get(0).setWarrStat("dead");
			}
			if (myParty.get(1).getHp() <= 0) {
				partyList.get(0).setPriStat("dead");
			}
			if (myParty.get(2).getHp() <= 0) {
				partyList.get(0).setMagStat("dead");
			}
			//モンスターのHPが0もしくは、runJudgementが"0"だったら戦闘終了
			//0でなかったらもう一周
			if (monsList.get(0).getHp() <= 0 || runJudgement.equals("0")) {
				break;
			}
		}
	}

	public void cpuBattleProcess(List<Party> partyList, List<Job> cpuParty) {
		//戦闘時処理

		//モンスターの配列生成
		List<Job> monsList = new ArrayList<Job>();
		monsList.add(new Monster());
		JobParameter jp = new JobParameter();
		//モンスターのパラメーターセット
		jp.monsterParameter(monsList);

		//battleOrderの配列作成
		List<Job> battleOrder = new ArrayList<Job>();

		//CPUパーティーに死んでるキャラがいないか確認して、
		//生きてるキャラだけbattleOrder配列に加える。
		if (partyList.get(1).getWarrStat() == "dead") {
			battleOrder.add(cpuParty.get(1));
			battleOrder.add(cpuParty.get(2));
		} else if (partyList.get(1).getPriStat() == "dead") {
			battleOrder.add(cpuParty.get(0));
			battleOrder.add(cpuParty.get(2));
		} else if (partyList.get(1).getMagStat() == "dead") {
			battleOrder.add(cpuParty.get(0));
			battleOrder.add(cpuParty.get(1));
		} else {
			for (Job party : cpuParty) {
				battleOrder.add(party);
			}
		}

		//モンスターもbattleOrder配列に加える
		for (Job monster : monsList) {
			battleOrder.add(monster);
		}

		//Agi(素早さ)が高い順に並び替える
		bos.agiSort(battleOrder);

		//行動選択

		//挙動確認用コード
		//1　戦闘マスでCPUのHPが半分以下の時
				for (Job j : cpuParty) {
					j.setHp(1);
					System.out.println(j.getJobname() + "HP：" + j.getHp());
				}
		//ここまで

		while (true) {

			List<Integer> removeIndex = new ArrayList<>();

			String healJudgement = null;
			for (Job characterName : battleOrder) {
				switch (characterName.getJobname()) {

				case "戦士":
					Warrior w = new Warrior();
					System.out.println("");
					System.out.println("戦士の行動");
					int damage = w.attack(cpuParty);
					for (Job monster : battleOrder) {
						if (monster.getJobname() == "モンスター") {
//							System.out.println("モンスターに" + damage + "ダメージ与えた。");
//							monster.setHp(monster.getHp() - damage);

							//挙動確認用コード
														System.out.println("モンスターに" + 0 + "ダメージ与えた。");
														monster.setHp(monster.getHp() - 0);
							//ここまで

							//HPが0以下になったモンスターをbattleOrderから削除
							if (monster.getHp() <= 0) {
								int indexNum = battleOrder.indexOf(monster);
								battleOrder.remove(indexNum);
							}
							break;
						}
					}
					break;

				case "僧侶":
					Priest p = new Priest();
					System.out.println("");
					System.out.println("僧侶の行動");

					for (Job cpParty : cpuParty) {
						if (cpParty.getJobname() == "戦士") {
							Warrior warrior = (Warrior) cpParty;
							if (0 < warrior.getHp() && warrior.getHp() < warrior.getMaxhp() / 2) {
								healJudgement = p.heal(cpuParty, healJudgement);
								break;
							}
						} else if (cpParty.getJobname() == "僧侶") {
							Priest priest = (Priest) cpParty;
							if (0 < priest.getHp() && priest.getHp() < priest.getMaxhp() / 2) {
								healJudgement = p.heal(cpuParty, healJudgement);
								break;
							}
						} else {
							Magic magic = (Magic) cpParty;
							if (0 < magic.getHp() && magic.getHp() < magic.getMaxhp() / 2) {
								healJudgement = p.heal(cpuParty, healJudgement);
								break;
							}
						}
						break;
					}

					//挙動確認用コード
										for (Job j : cpuParty) {
											System.out.println(j.getJobname() + "HP：" + j.getHp());
										}
										System.out.println(partyList.get(1).getWarrStat());
										System.out.println(partyList.get(1).getPriStat());
										System.out.println(partyList.get(1).getMagStat());
										
					//ここまで

					if (healJudgement == null) {
						damage = p.attack(cpuParty);
						for (Job monster : battleOrder) {
							if (monster.getJobname() == "モンスター") {
								System.out.println("モンスターに" + damage + "ダメージ与えた。");
								monster.setHp(monster.getHp() - damage);
								//HPが0以下になったモンスターをbattleOrderから削除
								if (monster.getHp() <= 0) {
									int indexNum = battleOrder.indexOf(monster);
									battleOrder.remove(indexNum);
								}
								break;
							}
						}
					}
					break;

				case "魔法使い":
					Magic m = new Magic();
					System.out.println("");
					System.out.println("魔法使いの行動");

					damage = m.mAtt(cpuParty);
					for (Job monster : battleOrder) {
						if (monster.getJobname() == "モンスター") {
//							System.out.println("モンスターに一律" + damage + "ダメージ与えた。");
//							monster.setHp(monster.getHp() - damage);

							//挙動確認用コード
														System.out.println("モンスターに" + 0 + "ダメージ与えた。");
														monster.setHp(monster.getHp() - 0);
							//ここまで
						}
					}
					//HPが0以下になったモンスターをbattleOrderから削除
					if (monsList.get(0).getHp() <= 0) {
						int indexNum = battleOrder.indexOf(monsList.get(0));
						battleOrder.remove(indexNum);
					}

					break;

				case "モンスター":
					Monster monster = new Monster();
					System.out.println("");
					System.out.println("モンスターの行動");
					damage = monster.attack(monsList);
					for (Job character : battleOrder) {
						if (character.getJobname() != "モンスター") {
							//							System.out.println(character.getJobname() + "に" + damage + "ダメージ与えた。");
							//							character.setHp(character.getHp() - damage);

							//挙動確認用コード
							System.out.println(character.getJobname() + "に" + 300 + "ダメージ与えた。");
							character.setHp(character.getHp() - 300);
							//ここまで

							//HPが0以下になったキャラクターをbattleOrderから削除
							if (character.getHp() <= 0) {
								// removeするindex番号を保持する
								removeIndex.add(battleOrder.indexOf(character));
							}
							break;
						}
					}
					break;
				}

				//パーティーが全滅しているか、敵を倒したか確認
				//倒していなければ、次のキャラクターの行動に移る。
				if (cpuParty.get(0).getHp() <= 0 && cpuParty.get(1).getHp() <= 0 && cpuParty.get(2).getHp() <= 0) {
					System.out.println("ゲームオーバー");
					System.exit(0);
				} else if (monsList.get(0).getHp() <= 0) {
					System.out.println("モンスターを倒しました。");
					System.out.println("戦闘に勝利しました。");
					break;
				}
			}
			//HPが0以下のキャラクターはdeadにする。
			if (cpuParty.get(0).getHp() <= 0) {
				partyList.get(1).setWarrStat("dead");
			}
			if (cpuParty.get(1).getHp() <= 0) {
				partyList.get(1).setPriStat("dead");
			}
			if (cpuParty.get(2).getHp() <= 0) {
				partyList.get(1).setMagStat("dead");
			}
			
			// 削除するindex番号を基に
			// battleOrderからremoveする
			for (int i : removeIndex) {
				battleOrder.remove(i);

			}
			
			//モンスターのHPが0だったら戦闘終了
			//0でなかったらもう一周
			if (monsList.get(0).getHp() <= 0) {
				break;
			}
		}
	}

}
