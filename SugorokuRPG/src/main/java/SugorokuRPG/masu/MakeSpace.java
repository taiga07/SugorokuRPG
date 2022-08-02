package SugorokuRPG.masu;

//マス生成クラス
public class MakeSpace {

	public SpaceData[] makeSpace() {
		SpaceData[] space = new SpaceData[102];

		for (int i = 0; i < space.length; i++) {
			SpaceData sd = new SpaceData();
			if (i == 0) {
				sd.setSpaceName("Start");
				space[i] = sd;
			} else if (i == 101) {
				sd.setSpaceName("Goal");
				space[i] = sd;
			} else if (i % 4 == 1 || i % 4 == 2) {
				sd.setSpaceName("normal");
				space[i] = sd;
			} else if (i % 4 == 3) {
				sd.setSpaceName("item");
				space[i] = sd;
			} else {
				sd.setSpaceName("battle");
				space[i] = sd;
			}
		}
		return space;

	}
}
