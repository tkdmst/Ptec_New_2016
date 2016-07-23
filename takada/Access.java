package socDB;

public interface Access {

	public void initialize() throws Exception;

	public void write(Model model) throws Exception;

	public Model read() throws Exception;

	public void close();

}
