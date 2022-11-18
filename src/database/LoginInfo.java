package database;

import java.util.HashMap;

public class LoginInfo {
	public HashMap<String,String> loginInfo = new HashMap<>();
	void add(String job, String name) {
		loginInfo.put(job, name);
	}
}
