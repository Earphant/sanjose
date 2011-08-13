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
					page.out("<div class=post><a href=/"+i+"><img class=icon src=/icons/"+i+"></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div></div></div>");
					break;
				case 12:
					page.out("<div class=post><a href=/"+w+"/"+i+"><img class=thmb src=/thumbnails/"+i+"></a><a href=/"+w+"/><img src=/icons/"+w+" class=icon></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div>Re</div></div>");
					break;
				case 136:
					page.out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text><a href=/"+w+"/heart-rate>"+x+"</a><div class=grf1>"+o.getQuotation()+"</div><div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
					break;
				case 138:
					page.out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text><a href=/"+w+"/weight>"+x+"</a><div class=grf1>"+o.getQuotation()+"</div><div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
					break;
				case 139:
					page.out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text><a href=/"+w+"/steps>"+x+"</a><div class=grf1>"+o.getQuotation()+"</div><div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
					break;
				default:
					page.out("<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
				}
			}
		}	
	}
}
