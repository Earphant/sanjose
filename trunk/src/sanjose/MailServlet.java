package sanjose;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
		throws IOException{
		Page page=new Page(resp);
	
		page.title="Inbox";
		page.aside="<ul><li><a href=/post/>Compose</a></ul><ul><li><a href=/mail/>Inbox</a><li><a href=/mail/sent>Sent Items</a><li><a href=/mail/spam>Spam</a><li><a href=/mail/trash>Trash</a></ul><ul><li><a href=/12.3/>Contacts</a></ul>";
		page.end(null);
	}
}
