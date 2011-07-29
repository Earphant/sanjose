package sanjose;


import java.io.BufferedReader;
import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.InputStream;
import java.util.logging.Logger;


import    java.io.*;   
import    java.util.*;  


public class Upload1 {
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
	     throws ServletException, IOException{
		 try {
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
		          log.warning("Got an uploaded file: " + item.getFieldName() +
		                      ", name = " + item.getName());

		          // You now have the filename (item.getName() and the
		          // contents (which you can read from stream).  Here we just
		          // print them back out to the servlet output stream, but you
		          // will probably want to do something more interesting (for
		          // example, wrap them in a Blob and commit them to the
		          // datastore).
		          		         		      		        	 
					  try{
						  
						  BufferedReader    br=new    BufferedReader(new InputStreamReader(stream));
						  
						   String  s;    
				            br.readLine();
				            br.readLine();
				            while((s=br.readLine())!=null)   
				            {   
				                StringTokenizer    t=new    StringTokenizer(s," ");     //" "Îª·Ö¸ô·û  
				       	       
				                String date=t.nextToken();
				                String time=t.nextToken();
				                Long weight=Long.parseLong(t.nextToken());
				       	        Long fat=Long.parseLong(t.nextToken());
				       	        Long water=Long.parseLong(t.nextToken());
				       	        Long heartrate=Long.parseLong(t.nextToken());
				       	        Long steps=Long.parseLong(t.nextToken());
				       	        I138 i138=new I138(1L,9L,weight);
				       	        I135 i135=new I135(1L,9L,fat,water);
				       	        I136 i136=new I136(1L,9L,heartrate);
				       	        I139 i139=new I139(1L,9L,steps);
				       	        
					    		 PersistenceManager mgr=Helper.getMgr();
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
				                br.close();
					  }
						 
					
				      catch (Exception e){
				        	 
				      }	        	  
		          }
		        }		      
		    } 
		 catch (Exception ex) {
		      throw new ServletException(ex);
		 }
		 rsp.sendRedirect("/12.3");
		 
	}
    						
}
		  			


	 

