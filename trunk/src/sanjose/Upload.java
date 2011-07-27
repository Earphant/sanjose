package sanjose;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
import java.io.InputStream; 
import java.io.IOException; 
import java.util.logging.Logger;  
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
    

  public class Upload  {
	 
    private static final Logger log =
      	      Logger.getLogger(Upload.class.getName());

	

  	  public void doGet(HttpServletRequest req,HttpServletResponse rsp)
  		throws IOException{
  		
  		Page p=new Page(rsp);
  		p.title="Upload";
  		p.aside="<ul><li><a href=/post/upload>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a><li><a href=/12.3/upload>Upload</a></ul>";
  		}
	
	
  	public void Out(String plink,Page page)
    	throws IOException{
  		
  		page.title="Upload";
  		page.aside="<ul><li><a href=/post/upload>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a><li><a href=/12.3/upload>Upload</a></ul>";
  		page.Out("<form method=post action=/post/upload enctype=post/form-data> <input type=file name=file> <input type=submit value=Ok> </form>");
    	
  	}
  	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
  	 throws  ServletException,IOException{
  		 try {
  		      ServletFileUpload upload = new ServletFileUpload();
  		      rsp.setContentType("text/plain");

  		      FileItemIterator iterator = upload.getItemIterator(req);
  		      while (iterator.hasNext()) {
  		        FileItemStream item = iterator.next();
  		        InputStream stream = item.openStream();

  		        if (item.isFormField())
  		        {
  		          log.warning("Got a form field: " + item.getFieldName());
  		        } 
  		        else 
  		        {
  		          log.warning("Got an uploaded file: " + item.getFieldName() +
  		                      ", name = " + item.getName());

  		          // You now have the filename (item.getName() and the
  		          // contents (which you can read from stream).  Here we just
  		          // print them back out to the servlet output stream, but you
  		          // will probably want to do something more interesting (for
  		          // example, wrap them in a Blob and commit them to the
  		          // datastore).
  		          int len;
  		          byte[] buffer = new byte[8192];
  		          while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
  		            rsp.getOutputStream().write(buffer, 0, len);
  		          }
  		        }
  		      }
  		    } 
  		    catch (Exception ex) 
  		    {
  		      throw new ServletException(ex);
  		    }
  		  }
  		}
	      
	  
	
	


	
			
			
			
			
			
			
			

