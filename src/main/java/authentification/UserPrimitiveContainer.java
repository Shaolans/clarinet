package authentification;

public class UserPrimitiveContainer {
	private int id;
	private String fname;
	private String lname;
	private String login;
	
	public UserPrimitiveContainer(int id, String login, String fname, String lname) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String toString() {
		return "id: "+id+" fname: "+fname+" lname: "+lname;
	}
}
