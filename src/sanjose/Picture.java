package	sanjose;

import com.google.appengine.api.datastore.Blob;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class Picture{
	//private static final Logger log = Logger.getLogger(Picture.class.getName());

	static public String Mime(String ext){
		if (ext.equalsIgnoreCase("gif"))
			return "image/gif";
		if(ext.equalsIgnoreCase("jpeg"))
			return "image/jpeg";  
		if(ext.equalsIgnoreCase("jpg"))
			return "image/jpg";	 
		if(ext.equalsIgnoreCase("png"))
			return "image/png";	 
		if(ext.equalsIgnoreCase("swf"))
			return "application/x-shockwave-flash";  
		if(ext.equalsIgnoreCase("psd"))
			return "image/psd";  
		if(ext.equalsIgnoreCase("bmp"))
			return "image/bmp";
		if(ext.equalsIgnoreCase("tiff"))
			return "image/tiff";  
		if(ext.equalsIgnoreCase("jpg"))
			return "image/JPEG";  
		if(ext.equalsIgnoreCase("jpc"))
			return "application/octet-stream";
		if(ext.equalsIgnoreCase("jp2"))
			return "image/jp2";
		if(ext.equalsIgnoreCase("jpx"))
			return "application/octet-stream";
		if(ext.equalsIgnoreCase("jb2"))
			return "application/octet-stream";
		if(ext.equalsIgnoreCase("swc"))
			return "application/x-shockwave-flash";
		if(ext.equalsIgnoreCase("iff"))
			return "image/iff";
		if(ext.equalsIgnoreCase("wbmp"))
			return "image/vnd.wap.wbmp";
		if(ext.equalsIgnoreCase("xbm"))
			return "image/xbm";
		if(ext.equalsIgnoreCase("ico"))
			return "image/vnd.microsoft.icon";
		return null;
	}

	public void Icon(String	path,HttpServletResponse rsp)throws IOException{
		I12 i=I12.create(path);
		if(i==null)
			rsp.sendRedirect("/icon.jpg");
		else{
			rsp.setContentType("image");
			rsp.getOutputStream().write(i.getIcon().getBytes());
		}
	}
	public void Original(String path,HttpServletResponse rsp)throws	IOException{
		I12 i=I12.create(path);
		rsp.setContentType("image");
		rsp.getOutputStream().write(i.getOriginal().getBytes());
	}
	public void Regular(I i,HttpServletResponse rsp)throws IOException{
		I12 d=I12.create(i);
		rsp.setContentType("image");
		rsp.getOutputStream().write(d.getRegular().getBytes());
	}
	public void Thumbnail(String path,HttpServletResponse rsp)throws IOException{
		I12 i=I12.create(path);
		Blob b=i.getThumbnail();
		if(b==null)
			rsp.sendRedirect("/icon.jpg");
		else{
			rsp.setContentType("image");
			rsp.getOutputStream().write(b.getBytes());
		}
	}
}