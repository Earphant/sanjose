package sanjose;

import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.*;

public class Session{
	public User user=UserServiceFactory.getUserService().getCurrentUser();
	public String utext;
	public Long uid;
	public Long usite;
	public Cookie cookie;

	public Session(Boolean signin){
		if(user!=null && signin){
			utext=user.getNickname();
			uid=12L;
			usite=3L;
			cookie=new Cookie("us","12.3:10&"+uid+"."+usite+"&User1&"+utext);
		}
		else{
			utext=null;
			uid=0L;
			usite=0L;
			cookie=new Cookie("us",null);
		}
		cookie.setMaxAge(-1);
		cookie.setPath("/");
	}
}
