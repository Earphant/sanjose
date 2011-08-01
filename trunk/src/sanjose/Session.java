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
		if(user==null){
			utext=null;
			uid=0L;
			usite=0L;
		}
		else{
			utext=user.getNickname();
			uid=1L;
			usite=1L;
		}
	}
}
