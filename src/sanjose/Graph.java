package sanjose;

import java.io.IOException;
import java.util.List;

public class Graph{
	@SuppressWarnings("unchecked")
	public String Daily(Object list)throws IOException{
		if(((List<Single>)list).isEmpty())
			return "";
		long max=-0x7fffffffffffffffL,min=0x7fffffffffffffffL;
		long vol;
		String s="";
		for(Single i:(List<Single>)list){
			vol=i.getvol();
			if(vol<min)
				min=vol;
			if(vol>max)
				max=vol;
		}
		max-=min;
		long k=80000/max;
		min-=10000/k;
		int n=10;
		for(Single i:(List<Single>)list){
			s+="<div style=left:"+n+"px;height:"+(i.getvol()-min)*k/1000+"%></div>";
			n+=20;
		}
		return s;
	}
}
