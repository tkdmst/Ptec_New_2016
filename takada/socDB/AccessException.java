package socDB;

/**
 * AccessExceptionはExceptionを継承している独自例外クラスです。<br>
 * throw時にコメントが入っていない場合、エラーを出します。<br>
 */
public class AccessException extends Exception {

	/**
	 * throw時にコメントを忘れた場合、<u>行方不明エラー</u> と書き出します。
	 */
	public AccessException() {
		super();
		System.out.println("行方不明エラー");
	}

	/**
	 * throwの際のエラー文、内容を表示します。
	 *
	 * @param message
	 *            エラー文の導入
	 */
	public AccessException(String message) {
		super(message);
	}
}
