package sanjose;

//import java.awt.image.BufferedImage;
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.File;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.InputStream;
//import java.util.Calendar;
//import java.util.Date;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
//import javax.jdo.Query;
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.google.appengine.api.datastore.Blob;
import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.logging.Logger;

public class Upload{
	private static final Logger log = Logger.getLogger(Upload.class.getName());

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Upload";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";		
		p.Out("<form method=post action=/post/uploads enctype=multipart/form-data> <input type=file name=file>");
		p.End("<input type=submit name=ok></form>");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse rsp)
	    throws IOException, FileUploadException{
		ServletFileUpload upload=new ServletFileUpload();				//创建对象	
		FileItemIterator iterator=upload.getItemIterator(req);  //解析request请求，并返回
		while (iterator.hasNext()) {	
		    FileItemStream t=iterator.next();        //从FileItemIterator集合中获得一个文件流
		    InputStream s=t.openStream();
		    if (t.isFormField())
		        log.warning("Got a form field: "+t.getFieldName());
		    else{ 
		        log.warning("Got an uploaded file: "+t.getFieldName()+", name = "+t.getName());
		        String extension=t.getName().substring(t.getName().lastIndexOf(".")+1,t.getName().length());
		        if(extension.equals("txt"))
		        	new Datatxt().doPost(req,rsp,s,12L,3L);
		        else
		        	new Picture().doPost(req,rsp,s,12L,3L);
		    }
		}
	}
}	
	