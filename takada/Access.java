package socDB;

public interface Access {

	public void initialize() throws AccessException;

	public void write(Model model) throws AccessException;

	public Model read() throws AccessException;

	public void close();

}
