package sanjose;

public class Id {
	public Long i=0L;
	public Long j=0L;
	public String ext;
	public Id(String id){
		if(id!=null){
			String[]s=id.split("\\.");
			int n=s.length;
			if(n>0){
				i=Long.parseLong(s[0]);
				j=Long.parseLong(s[1]);
				if(n>2)
					ext=s[2];
			}
		}
	}
	public boolean IsPicture(){
		if(ext==null)
			return false;
		return ext.equalsIgnoreCase("jpg");
	}
}
