package sanjose;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Graph{
	private static final Logger log = Logger.getLogger(Graph.class.getName());

	@SuppressWarnings("unchecked")
	public String html(Object list,String post,long end,long length,
		long interval){
		if(((List<Single>)list).isEmpty())
			return "";
		long max=-0x7fffffffffffffL;
		long min=0x7fffffffffffffL;
		long cnt=0,dlt,sum=0,u=0,y=0;
		long t0=end/interval-length;
		List<Single> dst=new ArrayList<Single>();
		for(Single i:(List<Single>)list){
			long v=i.getVal()*256;
			long t=i.getTick()/interval;
			if(t<t0){
				u=v;
				y=t;
				continue;
			}
			if(t==y){
				cnt++;
				sum+=v;
			}
			else{
				if(y>0){
					v=(sum+v)/(cnt+1);
					dlt=(v-u)/(t-y);
					if(y<t0){
						u+=dlt*(t0-y);
						if(min>u)
							min=u;
						if(max<u)
							max=u;
						y=t0;
					}
					for(y++;y<t;y++){
						u+=dlt;
						Single e=new Single();
						e.setVal(u);
						dst.add(e);
					}
				}
				u=v;
				y=t;
				cnt=sum=0;
				Single e=new Single();
				e.real=true;
				e.setTick(i.getTick());
				e.setVal(v);
				dst.add(e);
			}
			if(min>v)
				min=v;
			if(max<v)
				max=v;
		}
		log.warning("************ "+min+"/"+max);
		max-=min;
		long k;
		if(max==0 || min==0x7fffffffffffffL){
			log.warning("----------- "+min+"/"+max);
			k=1000;
			min-=50;
		}
		else{
			log.warning("============ "+min+"/"+max);
			k=80000/max;
			if(k==0)
				k=1;
			min-=10000/k;
		}
		int n=2;
		String s="";
		for(Single i:(List<Single>)dst){
			if(i.real){
				s+=post==null?"<div style=left:"+n+"%;height:"+(i.getVal()-min)*k/1000+"% class=real></div>":
					"<a href="+post+i.getTick()+"><div style=left:"+n+"%;height:"+(i.getVal()-min)*k/1000+"% class=real></div></a>";
			}
			else
				s+="<div style=left:"+n+"%;height:"+(i.getVal()-min)*k/1000+"%></div>";
			n+=3;
		}
		return s;
	}
}