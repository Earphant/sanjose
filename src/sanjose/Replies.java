package sanjose;

import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class Replies{
	static public long list(I base,Page page,PersistenceManager mgr)
		throws IOException{
		long ret=0;
		Query q=mgr.newQuery(I.class);
		q.setFilter("b==oParam && s==wParam ");	
		q.declareParameters("Long oParam,Long wParam");
        q.setOrdering("m desc");
		try{
            ret=I.list(q.execute(base.getId(),base.getSite()),page);
		}
		finally{
			q.closeAll();
		}
		return ret;
	}
}
