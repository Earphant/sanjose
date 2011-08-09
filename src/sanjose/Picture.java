package	sanjose;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

public class Picture{
	private	String Mime(String ext){
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
	private	I12 Get(String path){		
		if(path!=null){
			String[]s=path.split("/");
			if(s.length>1)
				return Get(new Id(s[1]));
		}
		return null;
	}
	private	I12 Get(Id id){
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I12.class);
		q.setFilter("i==iParam && j==jParam");
		q.declareParameters("Long iParam,Long jParam");
		try{
			@SuppressWarnings("unchecked")
			List<I12> r=(List<I12>)q.execute(id.i,id.j);
			if(!r.isEmpty()){
				return r.get(0);
			}
		}
		finally{
			q.closeAll();
		}
		return null;
	}

	public void doPost(HttpServletRequest req,HttpServletResponse rsp,
		InputStream stream,Long	id,Long	site)throws IOException, FileUploadException{

		Blob b=new Blob(IOUtils.toByteArray(stream));
		ServletFileUpload upload=new ServletFileUpload();				
		FileItemIterator iterator=upload.getItemIterator(req); 
		String ext = null;
		while (iterator.hasNext()) {
			FileItemStream t=iterator.next(); 
			ext=t.getName().substring(t.getName().lastIndexOf(".")+1,t.getName().length());
			rsp.setContentType(Mime(ext));
		}
  
		//String base=null;
		Id icon=new Id(req.getParameter("i"));
		PersistenceManager m=Helper.getMgr();	
		if(icon.i!=0){
			byte[] oldImageData=b.getBytes();
			ImagesService imagesService = ImagesServiceFactory.getImagesService();
		    Image oldImage = ImagesServiceFactory.makeImage(oldImageData);
		    
		    Transform resize3 =	ImagesServiceFactory.makeResize(48, 48);
		    Image newImage3 = imagesService.applyTransform(resize3, oldImage);
		    byte[] newImageData3 = newImage3.getImageData();
		    Blob ico=new Blob(newImageData3);
		    
			Query q=m.newQuery(I12.class);
			q.setFilter("i==iParam && j==jParam");
			q.declareParameters("Long iParam,Long jParam");
			try{
				@SuppressWarnings("unchecked")
				List<I12> r=(List<I12>)q.execute(icon.i,icon.j);
				if(!r.isEmpty()){
					I12 i12	= r.get(0);
				    i12.setico(ico);
				    m.makePersistent(i12);
				}
				else{
					I12 i12	= new I12(icon.i,icon.j,ext,ico);
				    m.makePersistent(i12);
				}
			}
			finally{
				q.closeAll();
				m.close();
			}
			if(icon.ext!=null)
				rsp.sendRedirect("/admins/users?i="+icon.i+"."+icon.j);
			else
				rsp.sendRedirect("/system/settings");
		}
		else{
			Session	s=new Session("");
			try{
				I i=new	I("","",12L,0L,s.id,s.site);
				m.makePersistent(i);
				i.setId(m);
				I12 i12=new I12(i,ext,b);			
				m.makePersistent(i12);
				
				byte[] oldImageData=b.getBytes();		    
				ImagesService imagesService = ImagesServiceFactory.getImagesService();
			    Image oldImage = ImagesServiceFactory.makeImage(oldImageData);
			    int	wid=oldImage.getWidth();
			    int	height=oldImage.getHeight();
			    
			    if(wid>500){
				Transform resize1 = ImagesServiceFactory.makeResize(500, 500*height/wid);
				    Image newImage1 = imagesService.applyTransform(resize1, oldImage);
				    byte[] newImageData1 = newImage1.getImageData();
				    Blob reg=new Blob(newImageData1);
				    i12.setreg(reg);							       
			    }
			    
			    Transform resize2 =	ImagesServiceFactory.makeResize(96, 96);
			    Image newImage2 = imagesService.applyTransform(resize2, oldImage);
			    byte[] newImageData2 = newImage2.getImageData();
			    Blob thm=new Blob(newImageData2);
			    i12.setthm(thm);
			    
			    Transform resize3 =	ImagesServiceFactory.makeResize(48, 48);
			    Image newImage3 = imagesService.applyTransform(resize3, oldImage);
			    byte[] newImageData3 = newImage3.getImageData();
			    Blob ico=new Blob(newImageData3);
			    i12.setico(ico);

			}
			finally{
				m.close();
			}	    
			rsp.sendRedirect("/"+s.id+"."+s.site+"/");
		}
	}
	
	
	public void Icon(String	path,HttpServletResponse rsp)throws IOException{
		I12 i=Get(path);
		if(i==null){
			rsp.sendRedirect("/icon.jpg");
			return;
		}
		rsp.setContentType("image");
		rsp.getOutputStream().write(i.getico().getBytes());
	}
	public void Original(String path,HttpServletResponse rsp)throws	IOException{
		I12 i=Get(path);
		rsp.setContentType("image");
		rsp.getOutputStream().write(i.getorg().getBytes());
	}
	public void Regular(Id id,HttpServletResponse rsp)throws IOException{
		I12 i=Get(id);
		rsp.setContentType("image");
		rsp.getOutputStream().write(i.getreg().getBytes());
	}
	public void Thumbnail(String path,HttpServletResponse rsp)throws IOException{
		I12 i=Get(path);
		rsp.setContentType("image");
		rsp.getOutputStream().write(i.getthm().getBytes());
	}
}