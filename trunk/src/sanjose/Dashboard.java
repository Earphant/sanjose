package sanjose;

import java.io.IOException;

public class Dashboard {
	public void out(String plink,Page page) throws IOException{
		page.title="Dashboard";
		page.aside="<div class=column1></div><ul class=column2><ul><li><a href=/post/>Post</a></ul><ul><li><a href=/system/settings>Settings</a><li><a href=/12.3/profile>Profile</a><li><a href=/12.3/contacts>Contacts</a><li><a href=/12.3/tags>Tags</a></ul><ul><li><a href=/12.3/dashboard>Dashboard</a><li><a href=/12.3/activities>Activities</a><li><a href=/12.3/historical>Historical</a></ul><ul><li><a href=/12.3/weight>Weight</a></ul></ul>";
		int i;
		for(i=0;i<10;i++)
			page.out("<h2>Based</h2>Blah blah.");
	}
}
