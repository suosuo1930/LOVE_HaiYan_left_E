package aloneExampleDemo;

import java.util.List;

interface Dao {
	public boolean add_Login(Login login);

	public boolean delete_Login(String userid);

	public boolean gai_login(String userd, Login login);

	public List findByall();

	public Login findByid(String userid);

}
