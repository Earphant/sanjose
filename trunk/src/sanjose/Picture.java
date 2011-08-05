package sanjose;

//import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//import java.io.InputStreamReader;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;


import com.google.appengine.api.datastore.Blob;

public class Picture{
	private I12 Get(String path){		
		if(path!=null){
			String[]s=path.split("/");
			if(s.length>1)
				return Get(new Id(s[2]));
		}
		return null;
	}
	private I12 Get(Id id){
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I12.class);
		q.setFilter("i==iParam && j==jParam");
		q.declareParameters("Long iParam,Long jParam");
		try{
			@SuppressWarnings("unchecked")
			List<I12> r=(List<I12>)q.execute(id.i,id.j);
			if(!r.isEmpty()){
				return r.get(0);
			}
		}
		finally{
			q.closeAll();
		}
		return null;
	}

	public void doPost(HttpServletRequest req,HttpServletResponse rsp,
		InputStream stream,Long id,Long site)throws IOException{
		Blob b=new Blob(IOUtils.toByteArray(stream));
		PersistenceManager m=Helper.getMgr();
		try{
			I i=new I("","",12L,0L,1L,1L);
			m.makePersistent(i);
			if(i.geti()==0L){
				i.seti();
				m.makePersistent(i);
			}
			I12 i12=new I12(i,null,b);
			m.makePersistent(i12);
		}
		finally{
			m.close();
		}
		rsp.getOutputStream().write(b.getBytes());
	}
	public void Icon(String path,HttpServletResponse rsp)throws IOException{
		I12 i=Get(path);
		rsp.getOutputStream().write(i.getorg().getBytes());
	}
	public void Original(Id id,HttpServletResponse rsp)throws IOException{
		I12 i=Get(id);
		rsp.getOutputStream().write(i.getorg().getBytes());
	}
	public void Thumbnail(Id id,HttpServletResponse rsp)throws IOException{
		I12 i=Get(id);
		rsp.getOutputStream().write(i.getorg().getBytes());
	}
}