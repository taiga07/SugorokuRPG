package SugorokuRPG.character;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class JobParameter {

	//MySQLへの接続に必要なデータ
	final String URL = "jdbc:mysql://localhost:3306/sugoroku_db";
	final String USERNAME = "sgrkuser";
	final String PASSWORD = "5678";
	
	Random rnd = new Random();
	int warriorId = rnd.nextInt(3) + 1;
	int priestId = rnd.nextInt(3) + 1;
	int magicId = rnd.nextInt(3) + 1;

	//メソッド毎に書く必要がなく、クラス内で使用できる
	static {
		//MySQLドライバを指定する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //別ファイルを開こうとするため例外処理が必要
		} catch (ClassNotFoundException e) {
			System.out.println("ドライバーのロードに失敗しました");
			e.printStackTrace();
		}
	}

	public void warriorParameter(List<Job> characterPartyList) {
		//DBサーバーとコネクションで繋がる。
		//最後、自動的に切断される。
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

			//SQL文の原型を作成する
			String query = "SELECT * FROM warrior WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, warriorId);

			//SQL文をDBMSに送信して実行する
			//戻り値：ResultSet型（１件のみ返ってくる）
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int wHp = rs.getInt("hp");
				int wMHp = rs.getInt("maxhp");
				int wStr = rs.getInt("str");
				int wMStr = rs.getInt("maxstr");
				int wAgi = rs.getInt("Agi");

				// Warrior型にキャスト可能か調べる
				if (characterPartyList.get(0) instanceof Warrior) {
					Warrior w = (Warrior) characterPartyList.get(0);
					w.setJobname("戦士");
					w.setHp(wHp);
					w.setMaxhp(wMHp);
					w.setStr(wStr);
					w.setMaxstr(wMStr);
					w.setAgi(wAgi);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void priestParameter(List<Job> characterPartyList) {

		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "SELECT * FROM priest WHERE id = ?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, priestId);

			//SQL文をDBMSに送信して実行する
			//戻り値：ResultSet型（１件のみ返ってくる）
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int pHp = rs.getInt("hp");
				int pMHp = rs.getInt("maxhp");
				int pStr = rs.getInt("str");
				int pMStr = rs.getInt("maxstr");
				int pMp = rs.getInt("mp");
				int pMMp = rs.getInt("maxmp");
				int pAgi = rs.getInt("Agi");

				if (characterPartyList.get(1) instanceof Priest) {
					Priest p = (Priest) characterPartyList.get(1);
					p.setJobname("僧侶");
					p.setHp(pHp);
					p.setMaxhp(pMHp);
					p.setStr(pStr);
					p.setMaxstr(pMStr);
					p.setMp(pMp);
					p.setMaxmp(pMMp);
					p.setAgi(pAgi);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void magicParameter(List<Job> characterPartyList) {

		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "SELECT * FROM magic WHERE id = ?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, magicId);

			//SQL文をDBMSに送信して実行する
			//戻り値：ResultSet型（１件のみ返ってくる）
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int mHp = rs.getInt("hp");
				int mMHp = rs.getInt("maxhp");
				int mMp = rs.getInt("mp");
				int mMMp = rs.getInt("maxmp");
				int mAgi = rs.getInt("Agi");

				if (characterPartyList.get(2) instanceof Magic) {
					Magic m = (Magic) characterPartyList.get(2);
					m.setJobname("魔法使い");
					m.setHp(mHp);
					m.setMaxhp(mMHp);
					m.setMp(mMp);
					m.setMaxmp(mMMp);
					m.setAgi(mAgi);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void monsterParameter(List<Job> monsList) {
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "SELECT * FROM monster WHERE id = ?";

			PreparedStatement ps = con.prepareStatement(query);
			int monsterId = rnd.nextInt(3) + 1;
			ps.setInt(1, monsterId);

			//SQL文をDBMSに送信して実行する
			//戻り値：ResultSet型（１件のみ返ってくる）
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int monsterHp = rs.getInt("hp");
				int monsterStr = rs.getInt("str");
				int monsterMStr = rs.getInt("maxstr");
				int monsterAgi = rs.getInt("Agi");

				if (monsList.get(0) instanceof Monster) {
					Monster monster = (Monster) monsList.get(0);
					monster.setJobname("モンスター");
					monster.setHp(monsterHp);
					monster.setStr(monsterStr);
					monster.setMaxstr(monsterMStr);
					monster.setAgi(monsterAgi);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//回復剤を使った時の処理
	public void useRestorative(List<Job> characterPartyList) {
		//戦士の回復処理
		
		if (characterPartyList.get(0) instanceof Warrior) {
			Warrior w = (Warrior) characterPartyList.get(0);
			w.setHp(w.getMaxhp());
			w.setStr(w.getMaxstr());
		}
//		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//			String query = "SELECT * FROM warrior WHERE id = ?";
//
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setInt(1, warriorId);
//
//			//SQL文をDBMSに送信して実行する
//			//戻り値：ResultSet型（１件のみ返ってくる）
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				int wMHp = rs.getInt("maxhp");
//				int wMStr = rs.getInt("maxstr");
//
//				if (characterPartyList.get(0) instanceof Warrior) {
//					Warrior w = (Warrior) characterPartyList.get(0);
//					w.setHp(wMHp);
//					w.setStr(wMStr);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		//僧侶の回復処理
		if (characterPartyList.get(1) instanceof Priest) {
			Priest p = (Priest) characterPartyList.get(1);
			p.setHp(p.getMaxhp());
			p.setStr(p.getMaxstr());
			p.setMp(p.getMaxmp());
		}
//		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//			String query = "SELECT * FROM priest WHERE id = ?";
//
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setInt(1, priestId);
//
//			//SQL文をDBMSに送信して実行する
//			//戻り値：ResultSet型（１件のみ返ってくる）
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				int pMHp = rs.getInt("maxhp");
//				int pMStr = rs.getInt("maxstr");
//				int pMMp = rs.getInt("maxmp");
//
//				if (characterPartyList.get(1) instanceof Priest) {
//					Priest p = (Priest) characterPartyList.get(1);
//					p.setHp(pMHp);
//					p.setStr(pMStr);
//					p.setMp(pMMp);
//				}
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		//魔法使いの回復処理
		if (characterPartyList.get(2) instanceof Magic) {
			Magic m = (Magic) characterPartyList.get(2);
			m.setHp(m.getMaxhp());
			m.setMp(m.getMaxmp());
		}
//		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//			String query = "SELECT * FROM magic WHERE id = ?";
//
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setInt(1, magicId);
//
//			//SQL文をDBMSに送信して実行する
//			//戻り値：ResultSet型（１件のみ返ってくる）
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				int mMHp = rs.getInt("maxhp");
//				int mMMp = rs.getInt("maxmp");
//				if (characterPartyList.get(2) instanceof Magic) {
//					Magic m = (Magic) characterPartyList.get(2);
//					m.setHp(mMHp);
//					m.setMp(mMMp);
//				}
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}
