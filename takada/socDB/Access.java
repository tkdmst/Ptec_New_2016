package socDB;

/**
 * Accessはインターフェースのクラスです。<br>
 * Clientから始まるクラスを呼び出すために利用します。<br>
 * ※throw先は全て独自クラス<br>
 *
 * @see ClientDB
 * @see AccessException
 *
 *
 */
public interface Access {

	public void initialize() throws AccessException;

	public void write(Model model) throws AccessException;

	public Model read() throws AccessException;

	public void close();

}
