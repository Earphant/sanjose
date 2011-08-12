package sanjose;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RegList extends HttpServlet {
	public RegList(List<I> rs, Page page)throws IOException{
		if(!rs.isEmpty()){
			for(I o:rs){
				String i=o.getId()+"."+o.getSite();
				String x=o.getText();
				String w=o.getOwnerId()+"."+o.getOwnerSite();
				if(x==null || x.equals(""))
					x="<i>(Untitled)</i>";
				switch((int)o.getClassId()){
				case 1:
					page.Out("<div class=post><a href=/"+i+"><img class=icon src=/icons/"+i+"></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div></div></div>");
					break;
				case 12:
					page.Out("<div class=post><a href=/"+w+"/"+i+"><img class=thmb src=/thumbnails/"+i+"></a><a href=/"+w+"/><img src=/icons/"+w+" class=icon></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div>Re</div></div>");
					break;
				case 139:
					page.Out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text><a href=/"+w+"/steps>Steps</a><div class=graf style=height:100px>"+o.getQuotation()+"</div><div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
					break;
				default:
					page.Out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
				}
			}
		}	
	}
}
