package sanjose;

import java.io.IOException;

public class Based{
	public Based(String plink,Page page) throws IOException{
		page.title=plink;
		page.aside="<ul><li><a href=/post/>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul>";
		int i;
		for(i=0;i<10;i++)
			page.Out("<h2>Based</h2>Blah blah.");
	}
}
