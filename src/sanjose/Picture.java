package	sanjose;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
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
	private	I12 Get(String path){		
		if(path!=null){
			StringTokenizer n=new StringTokenizer(path,"/");
			if(n.countTokens()>0)
				return Get(new Id(n.nextToken()));
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
	private	I12 Get(I i){
		PersistenceManager mgr=Helper.getMgr();
		Query q=mgr.newQuery(I12.class);
		q.setFilter("i==iParam && j==jParam");
		q.declareParameters("Long iParam,Long jParam");
		try{
			@SuppressWarnings("unchecked")
			List<I12> r=(List<I12>)q.execute(i.getId(),i.getSite());
			if(!r.isEmpty()){
				return r.get(0);
			}
		}
		finally{
			q.closeAll();
		}
		return null;
	}

	public void Icon(String	path,HttpServletResponse rsp)throws IOException{
		I12 i=Get(path);
		if(i==null){
			rsp.sendRedirect("/icon.jpg");
			return;
		}
		rsp.setContentType("image");
		rsp.getOutputStream().write(i.getIcon().getBytes());
	}
	public void Original(String path,HttpServletResponse rsp)throws	IOException{
		I12 i=Get(path);
		rsp.setContentType("image");
		rsp.getOutputStream().write(i.getOriginal().getBytes());
	}
	public void Regular(I i,HttpServletResponse rsp)throws IOException{
		I12 d=Get(i);
		rsp.setContentType("image");
		rsp.getOutputStream().write(d.getRegular().getBytes());
	}
	public void Thumbnail(String path,HttpServletResponse rsp)throws IOException{
		I12 i=Get(path);
		rsp.setContentType("image");
		rsp.getOutputStream().write(i.getThumbnail().getBytes());
	}
}