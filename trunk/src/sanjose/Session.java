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
	public Long id;
	public Long site;
	public Cookie cookie;

	public Session(Boolean signin){
		I i;
		I11 u;
		if(user!=null && signin){
			name=user.getNickname();
			email=user.getEmail();
			cookie=new Cookie("us","12.3:10&"+id+"."+site+"&"+name+"&"+email);
			PersistenceManager m=Helper.getMgr();
			try{
				u=m.getObjectById(I11.class,email);
				email=u.geteml();
			}
			catch (JDOObjectNotFoundException e) {
				i=new I(name,"",1L,1L,1L);
				m.makePersistent(i);
				if(i.geti()==0L){
					i.seti();
					m.makePersistent(i);
				}
				u=new I11(i,email);
				m.makePersistent(u);
			}
			finally{
				m.close();
			}
			id=u.geti();
			site=u.getj();
		}
		else{
			email=null;
			id=0L;
			site=0L;
			cookie=new Cookie("us",null);
		}
		cookie.setMaxAge(-1);
		cookie.setPath("/");
	}
}
