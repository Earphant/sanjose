package sanjose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload {
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
		
		ServletFileUpload upload = new ServletFileUpload();
		rsp.setContentType("text/plain");

		FileItemIterator iterator = upload.getItemIterator(req);
		while (iterator.hasNext()) {	
		    FileItemStream item = iterator.next();
		    InputStream stream = item.openStream();

		    if (item.isFormField()) {
		        log.warning("Got a form field: " + item.getFieldName());
		    } 
		    else { 
		        log.warning("Got an uploaded file: " + item.getFieldName()+", name = " + item.getName());
		        
		        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		        String str = null;
		        
		        reader.readLine();
		        reader.readLine();
		        while((str= reader.readLine())!= null){

		        	int sec = 0;
		        	int year = (int) Long.parseLong(str.split(" ")[0].split("-")[0]);
		        	int month = (int) Long.parseLong(str.split(" ")[0].split("-")[1]);
		        	int date = (int) Long.parseLong(str.split(" ")[0].split("-")[2]);
		        	int hour = (int) Long.parseLong(str.split(" ")[1].split(":")[0]);
		        	int min = (int) Long.parseLong(str.split(" ")[1].split(":")[1]);	
		        	
		        	Calendar calendar = Calendar.getInstance();
		            calendar.set(year,month,date,hour,min,sec);
		            Date t = calendar.getTime();
		            
		        	Long weight = Long.parseLong(str.split(" ")[2]);
		        	Long fat = Long.parseLong(str.split(" ")[3]);
		        	Long water = Long.parseLong(str.split(" ")[4]);
		        	Long heartrate = Long.parseLong(str.split(" ")[5]);
		        	Long step = Long.parseLong(str.split(" ")[6]);
		       
                    PersistenceManager mgr=Helper.getMgr();
                        I138 i138 = new I138(3L,7L,weight,t);
                        I135 i135 = new I135(3L,7L,fat,water,t);
                        I136 i136 = new I136(3L,7L,heartrate,t);
                        I139 i139 = new I139(3L,7L,step,t);
                             try{
                                mgr.makePersistent(i138);
                                mgr.makePersistent(i135);
                                mgr.makePersistent(i136);
                                mgr.makePersistent(i139);
                             }
                             finally{
                                mgr.close();
                             }
               
           }
		        reader.close();
		        rsp.sendRedirect("/12.3");
		        
		    
		    }
		      
		}
	}
}