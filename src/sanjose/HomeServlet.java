package sanjose;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.jdo.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet{
	@SuppressWarnings("unchecked")
	private void Signed(Page page,Session ssn)throws IOException{
		page.title="Home";
		page.Out("<form method=post action=/post/><textarea name=text rows=5></textarea><input type=submit name=ok></form>");
		String sss="";
		PersistenceManager m=Helper.getMgr();
		long[] atr={ 0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l };
		long[] atr1={ 0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,0l,
				0l,0l,0l,0l,0l,0l,0l,0l,0l,0l };

		Query q21=m.newQuery(I21.class);
		int ii=3;
		int a1=0;
		int a2=0;
		int a3=0;
		Session s=new Session("");
		Long o=s.id;
		Long w=s.site;
		atr[0]=o;
		atr[1]=w;
		q21.setFilter("o==oParam && w==wParam");
		q21.declareParameters("Long oParam,Long wParam");
		q21.setOrdering("t desc");
		List<I21> r=(List<I21>)q21.execute(o,w);
		if(!r.isEmpty()){
			for(I21 i21 : r){
				atr[ii]=i21.geti();
				atr[ii+1]=i21.getj();
				ii=ii+3;
			}
		}
		for(int iii=0;iii<ii;iii++){
			Query q=m.newQuery(I.class);
			q.setFilter("o==oParam && w==wParam");
			q.declareParameters("Long oParam,Long wParam");
			q.setOrdering("t desc");
			List<I> r1=(List<I>)q.execute(atr[iii],atr[iii+1]);
			if(!r1.isEmpty()){
				for(I i : r1){
					atr1[a1]=atr[iii];
					atr1[a1+1]=atr[iii+1];
					atr1[a1+2]=(i.getc()).getTime();
					a1=a1+3;
				}

			}
			iii=iii+2;
		}
		for(int i=0;i<(a1/3);i++)
			for(int j=i+1;j<(a1/3);j++)
				if(atr1[i*3+2]<atr1[j*3+2]){
					Long t=atr1[i*3+2];
					atr1[i*3+2]=atr1[j*3+2];
					atr1[j*3+2]=t;
					t=atr1[i*3+1];
					atr1[i*3+1]=atr1[j*3+1];
					atr1[j*3+1]=t;
					t=atr1[i*3];
					atr1[i*3]=atr1[j*3];
					atr1[j*3]=t;
				}

		PersistenceManager ms=Helper.getMgr();
		for(int iii=0;iii<a1;iii++){
			Query q2=ms.newQuery(I.class);
			Date asd=new Date(atr1[iii+2]);
			q2.setFilter("o==oParam && w==wParam && c==cParam");
			q2.declareImports("import java.util.Date");
			q2.declareParameters("Long oParam,Long wParam,Date cParam");
			q2.setOrdering("t desc");
			try{
				new RegList((List<I>)q2.execute(atr1[iii],atr1[iii+1],asd),page);
			}
			finally{
				q2.closeAll();
			}
			iii=iii+2;
		}
		page.End(null);
	}
	private void Unsigned(Page page,Session ssn)throws IOException{
		page.title="Home";
		page.Begin();
		page.End(null);
	}

	public void doGet(HttpServletRequest req,HttpServletResponse rsp)
			throws IOException{
		String n=req.getPathInfo();
		Page p=new Page(rsp);
		Session s=new Session("");
		if(n.equals("/")){
			if(s.email==null)
				Unsigned(p,s);
			else
				Signed(p,s);
		} else
			new Based(n,rsp,req);
	}
}
