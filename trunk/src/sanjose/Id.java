package sanjose;

public class Id {
	public Long i=0L;
	public Long j=0L;
	public Id(String id){
		if(id!=null){
			String[]s=id.split("\\.");
			if(s.length>0){
				i=Long.parseLong(s[0]);
				j=Long.parseLong(s[1]);
			}
		}
	}
}
