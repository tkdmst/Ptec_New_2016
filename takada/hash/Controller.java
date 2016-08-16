package hash;

import java.util.ArrayList;


public class Controller {

	private final View view;
	private final HashArrayObject hash;
	private final ArrayList<String> keyList;
	private Model model;
	private String key;

	public Controller(View view, HashArrayObject hash) {
		this.view = view;
		this.hash = hash;
		keyList = new ArrayList<String>();
	}

	// ifで組み分け
	public void proc() throws OnemoreException {
		while (true) {
			try {
				System.out.println("***メインメニュー****\n>新規登録は1, 検索は2, 終了は0 を押してください");
				String menuNom = view.input();
				int nom = Integer.parseInt(menuNom);
				if (nom == 1)
					newData();
				else if (nom == 2)
					where();
				else if (nom == 0)
					break;
				else
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				System.out.println("エラー>数字で入力してください。");
			} catch (OnemoreException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/* 新規データを受付、重複チェックに引っかかった場合は何回でも打ち直しをする */
	public void newData() throws OnemoreException {
		try {
			boolean flag = false;
			System.out.println(">登録するKeyを入力してください");
			while (!flag) {
				key = view.input();
				model = new Model(key);
				flag = model.doubleCheck(keyList, key);
				if (!flag)
					System.out.println(">キーが重複しています。もう一度打ち直してください");
			}
			System.out.println(">登録する性別を入力してください");
			String value = view.input();
			model.setValue(value);
			System.out.println(hash.setHash(model));
			model.listPlus(keyList, key);
		} catch (OnemoreException e) {
			System.out.println(e.getMessage() + "\n>メインメニューに戻ります");
		}
	}

	// 検索からの分岐
	public void where() throws OnemoreException {
		try {
			whereData();
			System.out.println("**検索結果を修正は1, 削除は2, メニューはその他 を押してください。");
			String menuNom = view.input();
			int nom = Integer.parseInt(menuNom);
			if (nom == 1)
				revisionData();
			else if (nom == 2)
				deleteData();
		} catch (NumberFormatException e) {
			System.out.println(">メニューへ戻ります");
		} catch (OnemoreException e) {
			throw new OnemoreException(e.getMessage() + "\n>メインメニューに戻ります");
		}
	}

	/* 検索データ */
	public void whereData() throws OnemoreException {
		System.out.println(">検索するKeyを入力してください");
		key = view.input();
		model = new Model(key);
		System.out.println(hash.whereKey(model));
	}

	/* 修正プロック */
	public void revisionData() throws OnemoreException {
		hash.listRemove(model);
		System.out.println(">新しく性別を入れてください");
		String value = view.input();
		model.setValue(value);
		System.out.println(hash.setHash(model));
	}

	/* 削除プロック */
	public void deleteData() throws OnemoreException {
		System.out.println(hash.listRemove(model));
		model.listDel(keyList, key);
	}

}
