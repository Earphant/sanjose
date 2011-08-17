package sanjose;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RegList extends HttpServlet {
	public RegList(List<I> rs, Page page)throws IOException{
		if(!rs.isEmpty()){
			for(I o:rs){
				String i=o.getId()+"."+o.getSite();
				String x=o.getTitle();
				String w=o.getOwnerId()+"."+o.getOwnerSite();
			    if(o.getRefId()==1){	
				
				switch((int)o.getType()){
				case 1:
				case 2:
					page.out("<div class=post><a href=/"+i+
						"><img class=icon src=/icons/"+i+
						"></a><div class=text><a href=/"+i+
						">"+x+"</a><div class=c2 t="+
						o.getModifyTick()+"></div></div></div>");
					break;
				case 12:
					page.out("<div class=post><a href=/"+w+"/"+i+"><img class=thmb src=/thumbnails/"+i+"></a><a href=/"+w+"/><img src=/icons/"+w+" class=icon></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
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
			}}
		}  	
	private void postReply(Long d,Long h,Page page)throws IOException{
		PersistenceManager m=Helper.getMgr();
		Query q3=m.newQuery(I.class);
		q3.setOrdering("m desc");
		
			q3.setFilter("d==dParam && h==hParam" );	
			
			q3.declareParameters("Long dParam,Long hParam");
			try{			               
				@SuppressWarnings("unchecked")
				List<I> r=(List<I>)q3.execute(d,h);
				if(!r.isEmpty()){
					for(I o:r){
						String i=o.getId()+"."+o.getSite();
						String x=o.getTitle();
						String w=o.getOwnerId()+"."+o.getOwnerSite();
						if(o.getRefId()==1l&&o.getRefSite()==1l){
							switch((int)o.getType()){
							case 1:
							case 2:
								page.out("<div class=post><a href=/"+i+
									"><img class=icon src=/icons/"+i+
									"></a><div class=text><a href=/"+i+
									">"+x+"</a><div class=c2 t="+
									o.getModifyTick()+"></div></div></div>");
								break;
							case 12:
								page.out("<div class=post><a href=/"+w+"/"+i+"><img class=thmb src=/thumbnails/"+i+"></a><a href=/"+w+"/><img src=/icons/"+w+" class=icon></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
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
								page.out("»Ø¸´£º<div class=post><a href=/"+w+"><img src=/icons/"+w+" class=icon></a><div class=text>"+x+"<div class=c2 t="+o.getModifyTick()+"></div><a href=/post?re="+i+"&jmp=%2F>Re</a></div></div>");
							}
						}
				    
					
              }
		    }
			}
			finally{
			    q3.closeAll();					
			}
		}
		
		}
	
	

     	
		
		
		
		
		
		
		

		
		
		
		
		
		