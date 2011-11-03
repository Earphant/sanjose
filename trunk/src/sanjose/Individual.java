package	sanjose;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Individual{
	static public String menu(I id,Session ssn,PersistenceManager mgr){
		String ret=null;
		if(id.equals(ssn.owner))
			ret="<ul><li><a href=/post>Post</a><li><a href=/system/settings?i="+id+">Settings</a></ul>";
		else{
			Query q=mgr.newQuery(I21.class);
			I o=ssn.owner;
			q.setFilter("o=="+o.getId()+" && w=="+o.getSite()+" && i=="+
				id.getId()+" && j=="+id.getSite());
			try{
				@SuppressWarnings("unchecked")
				List<I21> r=(List<I21>)q.execute();
				ret=r.isEmpty()?"<ul><li><a href=/system/follow?i="+id+">Follow</a></ul>":
					"<ul><li><a href=/system/unfollow?i="+id+">Unfollow</a></ul>";
			}
			finally{
				q.closeAll();
			}
		}
		return ret;
	}
	//@SuppressWarnings("unchecked")
	static public void out(I id,Page page,PersistenceManager mgr,Session ssn)
		throws IOException{
		page.title=id.getTitle(false);
		page.aside=menu(id,ssn,mgr)+
			"<ul><li><a href=/"+id+"/profile>Profile</a><li><a href=/"+
			id+"/contacts>Contacts</a><li><a href=/"+
			id+"/tags>Tags</a></ul><ul><li><a href=/"+
			id+"/dashboard>Dashboard</a><li><a href=/"+
			id+"/activities>Activities</a><li><a href=/"+
			id+"/historical>Historical</a></ul><ul><li><a href=/"+
			id+"/weight>Weight</a><li><a href=/"+
			id+"/heart-rate>Heart Rate</a><li><a href=/"+
			id+"/steps>Steps</a><li><a href=/"+id+"/fat>Fat</a></ul>";
		page.out("<form method=post action=/post?re="+id+">");
		page.out("<textarea name=text rows=5></textarea>");
		page.out("<input type=submit name=ok value=Reply></form>");
		Query q=mgr.newQuery(I.class);
		q.setFilter("o==oParam && w==wParam ");	
		q.declareParameters("Long oParam,Long wParam");
        q.setOrdering("m desc");
		try{
            I.list(q.execute(id.getId(),id.getSite()),page);
		}
		finally{
			q.closeAll();
		}
		page.end(null);
	}
	public void doGet(HttpServletRequest req,HttpServletResponse rsp,Page page,
		PersistenceManager mgr)throws IOException{
		I i=new I(req.getParameter("i"),0);
		page.title="Individual";
		page.aside="<ul><li><a href=/post>Message</a><li><a href=/post/documents>Document</a><li><a href=/post/picture>Picture</a><li><a href=/post/marks>Mark</a><li><a href=/post/events>Event</a><li><a href=/post/upload>Upload</a></ul><ul><li><a href=/post/books>Book</a><li><a href=/post/issues>Issue</a></ul><ul><li><a href=/post/weight>Weight</a><li><a href=/post/heart-rate>Heart Rate</a><li><a href=/post/steps>Steps</a><li><a href=/post/fat>Fat</a></ul>";
		if(i.getSite()==0)
			page.out("<form method=post action=/post/individual><input type=text name=text>");
		else{
			page.out("<form method=post action=/post/individual?i="+i+">");
			individualGet(i,page,mgr);
			page.out("<input type=hidden name=i value="+i+">");
			page.end("<br><input type=submit name=ok></form>");
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse rsp)
		throws IOException{
		I i=new I(req.getParameter("i"),0);
		PersistenceManager mgr=Helper.getMgr();
		individualPost(req,i,mgr);
		rsp.sendRedirect("/"+i);
	}
	public static void individualGet(I i,Page page,PersistenceManager mgr)
	throws IOException{
		String pwd1="";
		String pwd2="";
		I1 i1=I1.query(i,mgr);
		String fsn; if(i1.getfsn().equals("")) fsn="first-name"; else fsn="";
		String mdn; if(i1.getmdn().equals("")) mdn="middle-name";else mdn="";
		String lsn; if(i1.getfsn().equals("")) lsn="last-name";  else lsn="";
		String gnf; if(i1.getgnd().equals("female")) gnf="checked";else gnf="";
		String gnm; if(i1.getgnd().equals("male"))   gnm="checked";else gnm="";
		String yir="";
		String mth="";
		String dat="";
		String ocp=i1.getocp();
		String zip=i1.getzip()==0L?"":i1.getzip().toString();
		String tel=i1.gettel()==0L?"":i1.gettel().toString();
		String add=i1.getadd();
		if(i1.gett()!=null){
			Date t=i1.gett();
			Calendar cal = Calendar.getInstance();
			cal.setTime(t);
			yir= Integer.toString(cal.get(Calendar.YEAR));
			mth= Integer.toString(cal.get(Calendar.MONTH));
			dat= Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		}
		page.out("Password<br><input type=password name=pwd1 value="+pwd1+"><br>Ensure Password<br><input type=password name=pwd2 value="+pwd2+"><br>"
		        +"Nick Name<br><input type=text name=text value="+i.getText()+"><br>"
		        +"First Name<br><input type=text name=fsn value="+fsn+"><br>Middle Name<br><input type=text name=mdn value="+mdn+"><br>Last Name<br><input type=text name=lsn value="+lsn+"><br>"
	            +"Gender<br><input type=radio name=gnd value=female "+gnf+">Female  <input type=radio name=gnd value=male "+gnm+">Male<br>"
		        +"Birthday<br><input type=text style=width:40px; name=yir value="+yir+">-<input type=text name=mth style=width:20px; value="+mth+">-<input type=text name=dat style=width:20px; value="+dat+"><br>"
		        +"Occupation<br><input type=text name=ocp value="+ocp+"><br>"
		        +"Postal Code<br><input type=text name=zip value="+zip+"><br>Telephone Number<br><input type=text name=tel value="+tel+"><br>"
		        +"Address<br><textarea name=add rows=1>"+add+"</textarea>");
	}
	@SuppressWarnings("unchecked")
	public static void individualPost(HttpServletRequest req,I i,PersistenceManager mgr)
	throws IOException{
		String v=req.getParameter("text");
		i.setText(v);
		i.setModifyTime(null);
		Query q11=mgr.newQuery(I11.class);
        q11.setFilter("i==iParam && j==jParam");
		q11.declareParameters("Long iParam,Long jParam");
		   try{
				List<I11> r11=(List<I11>)q11.execute(i.getId(),i.getSite());
				if(!r11.isEmpty()){
					I11 i11=r11.get(0);
					String pwd1 = req.getParameter("pwd1");
					String pwd2 = req.getParameter("pwd2");
					if(pwd1==pwd2 && pwd1!=""){
						i11.setPassword(pwd1);
						mgr.makePersistent(i11);
					}
				}
			}
			finally{
				q11.closeAll();
			}
		String yir = req.getParameter("yir");
        String mth = req.getParameter("mth");
        String dat = req.getParameter("dat");
           Calendar calendar = Calendar.getInstance();
           Date t=null;
        if(yir!="" && mth!="" && dat!=""){
        	calendar.set(Integer.parseInt(yir),Integer.parseInt(mth),Integer.parseInt(dat));
        	t = calendar.getTime();
        }
        Long zip=req.getParameter("zip")==""?0L:Long.parseLong(req.getParameter("zip"));
        Long tel=req.getParameter("tel")==""?0L:Long.parseLong(req.getParameter("tel"));
        I1 i1 = I1.query(i, mgr);
		i1.setfsn(req.getParameter("fsn"));
		i1.setmdn(req.getParameter("mdn"));
		i1.setlsn(req.getParameter("lsn"));
		i1.setgnd(req.getParameter("gnd"));
		i1.sett(t);
		i1.setocp(req.getParameter("ocp"));
		i1.setzip(zip);
		i1.settel(tel);
		i1.setadd(req.getParameter("add"));
		mgr.makePersistent(i1);
		
		mgr.close();
			
	}
}