package sanjose;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Session{
	private User user=UserServiceFactory.getUserService().getCurrentUser();

	public String utext;
	public Long uid;
	public Long usite;

	public Session(){
		utext=user==null?null:"";
	}
}
