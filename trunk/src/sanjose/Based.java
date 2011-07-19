package sanjose;

import java.io.IOException;

public class Based{
	public Based(String plink,Page page) throws IOException{
		String[]s=plink.split("/");
		if(s.length>2){
			String n=s[2];
			if(n.equalsIgnoreCase("profile")){
				new Profile().Out(plink,page);
				return;
			}
			if(n.equalsIgnoreCase("dashboard")){
				new Dashboard().Out(plink,page);
				return;
			}
			if(n.equalsIgnoreCase("contacts")){
				new Contacts().Out(plink,page);
				return;
			}
			if(n.equalsIgnoreCase("tags")){
				new Tags().Out(plink,page);
				return;
			}
			if(n.equalsIgnoreCase("steps")){
				new Steps().Out(plink,page);
				return;
			}
			if(n.equalsIgnoreCase("weight")){
				new Weight().Out(plink,page);
				return;
			}
			if(n.equalsIgnoreCase("heartrate")){
				new HeartRate().Out(plink,page);
				return;
			}
			if(n.equalsIgnoreCase("fat")){
				new Fat().Out(plink,page);
				return;
			}
		}
		page.title=plink;
		page.aside="<ul><li><a href=/post/>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><li><a href=/12.3/weight>Weight</a><li><a href=/12.3/heartrate>Heart Rate</a><li><a href=/12.3/steps>Steps</a><li><a href=/12.3/fat>Fat</a></ul>";
		int i;
		for(i=0;i<10;i++)
			page.Out("<h2>Based</h2>55556666.");
	}
}
