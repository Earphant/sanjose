package sanjose;

import com.google.appengine.api.datastore.Blob;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet{
	private static final Logger log = Logger.getLogger(Upload.class.getName());

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		I i=new I(req.getParameter("i"),0);
		Page p=new Page(rsp);
		p.title="Upload";
		p.aside="<div class=column1></div><ul class=column2><ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul></ul>";		
		p.out("<form method=post action=/post/upload?i="+i+
			" enctype=multipart/form-data> <div class=sinput><input type=file name=file>");
		p.end("<input type=submit name=ok></div></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
	    throws IOException{
		I w=new I(req.getParameter("i"),0);
		if(w.getSite()==0){
			Session s=new Session("/post/upload");
			w=s.owner;
		}
		ServletFileUpload up=new ServletFileUpload();
		FileItemIterator ir;
		try{
			ir = up.getItemIterator(req);
			while(ir.hasNext()){
				FileItemStream t=ir.next();
				InputStream s=t.openStream();
				if (t.isFormField())
					log.warning("Got a form field: "+t.getFieldName());
				else{ 
					if(t.getFieldName().equalsIgnoreCase("file")){
						String fn=t.getName();
						byte[] a=IOUtils.toByteArray(s);
						if(fn.equalsIgnoreCase("bom.csv"))
							if(new Bom().doPost(req,rsp,a,w))
								break;
						if(fn.equalsIgnoreCase("data.txt"))
							if(new DataText().doPost(req,rsp,a,w))
								break;
						PersistenceManager m=Helper.getMgr();
						I i=new I(req.getParameter("i"),0);
						if(i.getSite()==0){
							String x=t.getName();
							x=x.substring(x.lastIndexOf(".")+1,x.length());
							i=I.store(x,null,12,(byte)0,w,m,true);
						}
						I12 o=new I12(i,new Blob(a));
						o.setPicture();
						m.makePersistent(o);
						m.close();
						rsp.sendRedirect("/");
						break;
					}
			    }
				s.close();
			}
		}
		catch (FileUploadException e){
			e.printStackTrace();
		}
	}
}