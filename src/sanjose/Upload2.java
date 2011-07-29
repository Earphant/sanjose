package sanjose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.InputStream;
import java.util.logging.Logger;


public class Upload2 {
	private static final Logger log =
	      Logger.getLogger(Upload.class.getName());

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		Page p=new Page(rsp);
		p.title="Upload";
		p.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/uploads>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heartrate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";		
		p.Out("<form method=post action=/post/uploads enctype=multipart/form-data> <input type=file name=file>");
		p.End("<input type=submit name=ok></form>");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse rsp)
	     throws ServletException, IOException, FileUploadException{
		Page p=new Page(rsp);
		
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
		        String [] series = null;
		        reader.readLine();
		        reader.readLine();
		        while((str= reader.readLine())!= null){
		        	 series=str.split(" ");
		        	 String weightvols=series[2];
		        	 Long vol=Long.parseLong(weightvols);
		        	 PersistenceManager mgr=Helper.getMgr();
				     I138 i138 = new I138(3L,7L,vol);
				          try{
								mgr.makePersistent(i138);
							}
							finally{
								mgr.close();
							}
		        	
					 
				
		        }
		      
		        new Weight().Out("weight",p);
		        
		     }
		    
		     
		      
		}
	}
}