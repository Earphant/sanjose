package sanjose;

import java.util.Date;

public class Timed {
	public Long n=0L;
	public Long o=0L;
	public Date t=null;
	public Timed(String timed){
		if(timed!=null){
			String[]s=timed.split("\\.");
			if(s.length>0){
				n=Long.parseLong(s[0]);
				o=Long.parseLong(s[1]);
                t=new Date(Long.parseLong(s[2]));

			}
		}
	}
}