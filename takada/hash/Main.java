package hash;

/**
 * hashパッケージの内容を動かす
 * メインを切り替えることなく動かすための自分用のメイン
 */
public class Main {

	public static void main(String[] args) {
		try {
			int tableNum = Integer.parseInt(args[0]);
			View view = new View();
			view.initialize();
			HashArrayObject hash = new HashArrayObject(tableNum);
			Controller controller = new Controller(view, hash);
			controller.proc();
		} catch (OnemoreException e) {
			System.out.println(e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(">コンストラクタにテーブル数を入力してください");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println(">コンストラクタは数字で入力してください");
		}
		System.out.println(">終了");

	}

}