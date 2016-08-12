package hash;

import java.util.ArrayList;

public class Controller {

	private final View view;
	private final HashArrayObject hash;
	private final ArrayList<String> keyList;
	private Model model;

	public Controller(View view, HashArrayObject hash) {
		this.view = view;
		this.hash = hash;
		keyList = new ArrayList<String>();
	}

	// ifで組み分け
	public void proc() throws OnemoreException {
		while (true) {
			try {
				System.out.println("***メインメニュー****\n>新規登録は1 , 検索は2 , 終了は0 を押してください");
				String menuNom = view.input();
				int nom = Integer.parseInt(menuNom);
				if (nom == 1)
					newData();
				else if (nom == 2)
					whereData();
				else if (nom == 0)
					break;
				else
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				System.out.println("エラー>0～2の数字で入力してください。");
			} catch (OnemoreException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// 新規データを受付、モデルにセットし、
	public void newData() throws OnemoreException {
		try {
			System.out.println(">登録するKeyを入力してください");
			String key = view.input();
			model = new Model(key);
			model.doubleCheck(keyList, key);
			System.out.println(">登録する性別を入力してください");
			String value = view.input();
			model.setValue(value);
			System.out.println(hash.setHash(model));

		} catch (OnemoreException e) {
			System.out.println(e.getMessage() + "\n>メインメニューに戻ります");
		}
	}

	// 検索データ
	public void whereData() throws OnemoreException {
		try {
			System.out.println(">検索するKeyを入力してください");
			String key = view.input();
			model = new Model(key);
			System.out.println(hash.whereKey(model));
		} catch (OnemoreException e) {
			System.out.println(e.getMessage() + "\n>メインメニューに戻ります");
		}
	}

}
