package sanjose;

import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
//import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Session{
	public User user=UserServiceFactory.getUserService().getCurrentUser();
	public String name;
	public String email;
	public I owner;
	public Cookie cookie;

	public Session(String jump){
		I11 u;
		if(user!=null && jump!=null){
			name=user.getNickname();
			email=user.getEmail();
			
			PersistenceManager m=Helper.getMgr();
			try{
				u=m.getObjectById(I11.class,email);
				email=u.getEmail();
			}
			catch(JDOObjectNotFoundException e) {
				I i=new I(1,1);
				i=I.store(name,null,1,(byte)0,i,m,true);
				u=new I11(i,email);
				m.makePersistent(u);
				I1 idt=new I1(i);
				m.makePersistent(idt);
			}
			finally{
				m.close();
			}
			owner=new I(u.getId(),u.getSite());
			cookie=new Cookie("us",owner+":10&"+owner+"&"+name+"&"+email);
		}
		else{
			email=null;
			owner=null;
			cookie=new Cookie("us",null);
		}
		cookie.setMaxAge(-1);
		cookie.setPath("/");
	}
}
