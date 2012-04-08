package sanjose;

import com.google.appengine.api.datastore.Blob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class DownloadsServlet extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		
		Page p=new Page(rsp);
		p.title="Downloads";
		p.aside="<div class=column1></div><ul class=column2><ul><li><a href=/post/upload>Upload</a></ul></ul>";

		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I12.class);
		q.setOrdering("i desc");
		q.setFilter("ext==extParam");	
		q.declareParameters("String extParam");
		try{
			List<I12> r=(List<I12>)q.execute("bin");
			if(!r.isEmpty()){
				for(I12 i12:r){	
					p.out(i12.geti()+"."+i12.getj()+".bin<form method=post action=/downloads/"+i12.geti()+"."+i12.getj()+"><input type=hidden name=download><input type=submit name=ok value=download></form>");					
				}
			}
		}
		finally{
			q.closeAll();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
	throws IOException{	
		String ii =  req.getPathInfo();
		String[]s=ii.split("/");
	    Long i=Long.parseLong(s[1].split("\\.")[0]);
	    Long j=Long.parseLong(s[1].split("\\.")[1]);
	    
	    rsp.reset();
		String fileName="download.bin";  		  // 设置响应头和下载保存的文件名   
        rsp.setContentType("application/octet-stream");
		rsp.addHeader("Content-Disposition","attachment; filename=\"" + fileName + "\""); 
		
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I12.class);
		q.setOrdering("i desc");
		q.setFilter("i==iParam && j==jParam ");
		q.declareParameters("Long iParam,Long jParam");
		Blob b = null;
		try{
			@SuppressWarnings("unchecked")
			List<I12> r=(List<I12>)q.execute(i,j);
			if(!r.isEmpty()){				
			    b=r.get(0).getOriginal();
			}		
		}
		finally{
			q.closeAll();
		}			
		byte[] b1 = b.getBytes();
		ByteArrayInputStream instream = new ByteArrayInputStream(b1); 
        int len;
        try {
            while ((len = instream.read(b1, 0, b1.length)) != -1)
            	rsp.getOutputStream().write(b1, 0, len);
            instream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }		   
	}
}

	

