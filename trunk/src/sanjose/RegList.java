package sanjose;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RegList extends HttpServlet {
	public RegList(List<I> rs,Page page)throws IOException{
		if(!rs.isEmpty()){
			for(I o:rs){
				String i=o.getId()+"."+o.getSite();
				String x=o.getx();
				String w=o.getOwnerId()+"."+o.getOwnerSite();
				if(x==null || x.equals(""))
					x="<i>(Untitled)</i>";
				switch((int)o.geta()){
				case 1:
					page.Out("<a href=/"+i+"/><img src=/icons/"+i+" class=icon></a>: "+x+"<br>");
					break;
				case 12:
					page.Out("<a href=/"+w+"/><img src=/icons/"+w+" class=icon>:  <a href=/"+w+"/"+i+"><img src=/thumbnails/"+i+"></a><br>");
					break;
				default:
					page.Out("<a href=/"+w+"/><img src=/icons/"+w+" class=icon></a>: "+x+"<br>");
				}
			}
		}	
	}
}
