package sanjose;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload{
	private static final Logger log = Logger.getLogger(Upload.class.getName());

	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page)
		throws IOException{
		page.title="Upload";
		Id d=new Id(req.getParameter("i"));
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";		
		page.out("<form method=post action=/post/upload?i="+d.i+"."+d.j+" enctype=multipart/form-data> <input type=file name=file>");
		page.end("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
	    throws IOException{
        Session sn=new Session("/post");
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
			        log.warning("Got an uploaded file: "+t.getFieldName()+", name = "+t.getName());
			        String extension=t.getName().substring(t.getName().lastIndexOf(".")+1,t.getName().length());
			        if(extension.equals("txt"))
			        	new DataText().doPost(req,rsp,s,sn.owner);
			        else
			        	new Picture().doPost(req,rsp,s,sn.owner);
			    }
				s.close();
			}
		}
		catch (FileUploadException e){
			e.printStackTrace();
		}
	}
}