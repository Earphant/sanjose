package sanjose;

import java.util.Date;

public class Timed {
	public Long i=0L;
	public Long j=0L;
	public Date t=null;
	public Timed(String timed){
		if(timed!=null){
			String[]s=timed.split("\\.");
			if(s.length>0){
				i=Long.parseLong(s[0]);
				j=Long.parseLong(s[1]);
                t=new Date(Long.parseLong(s[2]));

			}
		}
	}
}
