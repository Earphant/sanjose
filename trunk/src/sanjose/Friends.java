package sanjose;


import java.io.IOException;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileUploadException;


public class Friends{
	
	public void doPost(HttpServletRequest req, HttpServletResponse rsp)
	    throws IOException, FileUploadException{
		
		Session s=new Session("ow");
		String ii=req.getParameter("i");
		String[] id=ii.split("\\.");
		Long o=Long.parseLong(id[0]);
		Long w=Long.parseLong(id[1]);
		PersistenceManager m=Helper.getMgr();   
	    
	    I i=new I("","",10L,0L,s.id,s.site);
	    m.makePersistent(i);
		if(i.geti()==0L)
			i.seti();
		m.makePersistent(i);
	    Date t=new Date();
		I21 i21=new I21(i,o,w,t);
		m.makePersistent(i21);
        
		rsp.sendRedirect("/"+ii+"/");
	}
}	
	