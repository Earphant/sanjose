package sanjose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;

public class Picture{
	public void doPost(HttpServletRequest req,HttpServletResponse rsp,
		InputStream stream,Long id,Long site)throws IOException{
        InputStream is=req.getInputStream();		            		            
        byte[] buffer=new byte[4096*250];
    	is.read(buffer);
    	is.close();
    	Blob img=new Blob(buffer);
    	
    	I i=new I("","",1L,0L,1L,1L);
    	I12 i12=new I12(i,img);
    	
    	PersistenceManager mgr=Helper.getMgr();
    	try{
             mgr.makePersistent(i12);

        }
        finally{
             mgr.close();
        }
    	                 
        int len;
        
        while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
            rsp.getOutputStream().write(buffer, 0, len);
        }
	}
}