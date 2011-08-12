package sanjose;

import java.util.Date;

public class Timed {
	public long o=0L;
	public long w=0L;
	public Date t=null;
	public Timed(String timed){
		if(timed!=null){
			String[]s=timed.split("\\.");
			if(s.length>0){
				o=Long.parseLong(s[0]);
				w=Long.parseLong(s[1]);
				if(s.length>2)
					t=new Date(Long.parseLong(s[2])*1000);
			}
		}
	}
	public String toString(){
		return this.o+"."+w;
	}
}
