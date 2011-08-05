package sanjose;

//import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;


import com.google.appengine.api.datastore.Blob;

public class Picture{
	public void doPost(HttpServletRequest req,HttpServletResponse rsp,
		InputStream stream,Long id,Long site)throws IOException{
		Blob b=new Blob(IOUtils.toByteArray(stream));
		PersistenceManager m=Helper.getMgr();
		try{
			I i=new I("","",1L,0L,1L,1L);
			m.makePersistent(i);
			if(i.geti()==0L){
				i.seti();
				m.makePersistent(i);
			}
			I12 i12=new I12(i,b);
			m.makePersistent(i12);
		}
		finally{
			m.close();
		}
		rsp.getOutputStream().write(b.getBytes());
	}
}