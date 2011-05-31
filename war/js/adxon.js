// (c)adxon.com
// <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

_=function(o,s){
	var a=s?s:[],r;
	for(o=o.firstChild;o;o=o.nextSibling){
		if(o.nodeName=='#text')continue;
		if(_.$(o)){if(o.$)a.push(o)}
		else _(o,a);
	}
	return a;
}
_[0]={
	$:function(o){
		var a,f,p;
		if(o.$)return o.$;
		if(a=o.className){
			switch(a.charAt(0)){
			case 'c':
			case 'z':
				if(!(a=a.split(' ')[0].substring(1)*1))return null;
				p=_[a];
				switch(typeof(p)){
				case 'object':
					if(p.$){
						f=function(){};
						f.prototype=p;
						p=_[a]=f;
					}
					else return p;
				case 'function':
					f=new p;
					if(f.$){
						o.$=f;
						f.$(o,a);
					}
					break
				default:
					a='Control '+a+' not exist.';
					_.Msg(a);
					f=1;
				}
				return f;
			}
		}
		return null;
	},
	$$:function(d){
		if(d){
			var c,i;
			if(c=d.$){
				switch(typeof(c.$$)){
				case 'function':
					c.$$(d);
					break;
				case 'object':
					_.$$(c.$$);
				}
				d.$=null;
			}
			else{
				if(d.nodeName)for(i=d.lastChild;i;i=i.previousSibling){
					if(i.nodeName=='#text')continue;
					_.$$(i);
				}
				else for(i in d)_.$$(d[i]);
			}
		}
	},
	_d:[],
	_D:function(){
		var b=this.document.body;
		if(!top._.$(b)){
			b.$={};
			b.$.$$=top._(b);
		}
	},
	_E:function(d,e,o,p,c){
		if(!e)e=(d.tagName?d.ownerDocument:d).parentWindow.event;
		if(e.srcElement){
			e.target=e.srcElement;
			e.pageX=e.clientX;
			e.pageY=e.clientY;
			e.which=e.keyCode;
		}
		if(!c){
			if(e.stopPropagation)e.stopPropagation();
			else e.cancelBubble=true;
		}
		return o[p].call(o,d,e,c);
	},
	_L:function(e){
		var x=e.currentTarget
		if(!x)x=e.srcElement
		var b=x.contentWindow.document.body
		if(!$$(b)){
			$.A(b,'onclick',$.J)
			$.W(b)
		}
	},
	_r:{},
	_v100:null,
	_x:null,
	lang:null,
	cursor:{},
	dict:{},
	Attach:function(d,v,o,p,c){
		var f=function(e){return _._E(d,e,o,p,c)};
//		if(d.attachEvent)d.attachEvent(v,f);
//		else d[v]=f;
		d[v]=f;
	},
	D:function(d,s){
		return d.ownerDocument.getElementById(s)
	},
	Date:function(v){
		v=v.replace(/[^\d]/g,' ').split(' ');
		if(!v[3])v[3]=0;
		if(!v[4])v[4]=0;
		if(!v[5])v[5]=0;
		return new Date(v[0],v[1]-1,v[2],v[3],v[4],v[5]);
	},
	Detach:function(d,v){
		d[v]=null;
	},
	Evnt:function(e,d,c){
		return this.Event(e,d,c);
	},
	Event:function(e,d,c){
		if(!e){
			e=(d.tagName?(d.ownerDocument):d).parentWindow.event;
			e.target=e.srcElement;
			e.pageX=e.clientX;
			e.pageY=e.clientY;
			e.cancelBubble=!c;
		}
		else if(!c)e.stopPropagation();
		return e
	},
	Exe:function(x,v){
		var r=this._r;
		if(r=r[x])return r[x].call(r,v);
		else _.Msg(x,1);
		return 0;
	},
	Flash:function(){
		var i,p=navigator.plugins,q;
		if(p&&p.length){
			for(i=p.length;i;){
				q=p[--i];
				if(q.name.toLowerCase().indexOf('shockwave flash')>=0)return parseInt(q.description.toLowerCase().split('flash ')[1]);
			}
		}
		else if(window.ActiveXObject){
			for(i=11;i>1;i--){
				try{
					new ActiveXObject('ShockwaveFlash.ShockwaveFlash.'+i);
					return i;
				}
				catch(e){}
			}
		}
		return 0;
	},
	Fly:function(p,v,e,m){
		var h=document.getElementsByTagName('HEAD')[0],i=h.lastChild,k;
		do{
			if(i.tagName=='SCRIPT'&&i.p==p){
				k=i.o;
				k[p].call(k,v,e,m);
				i.o=null;
				h.removeChild(i);
				return;
			}
		}while(i);
	},
	I:function(d,c,t){
		d=d.ownerDocument
		d=d.defaultView?d.defaultView:d.parentWindow
		return d.setInterval?d.setInterval(c,t):setInterval(c,t)
	},
	G:function(u){
		var i
		if(u)location.replace(u)
		else u=location.href
		u=u.split('#')
		i=u[1]
		if(!i)i=$._p+'/home.htm'
		u[0]=u.shift()+i
		top.cont.location.replace(u.join('#'))
	},
	J:function(e){
		var x=$.E(e,this).target;
		if(x.tagName=='A'&&x.target=='_top'){
			$.G(x.href)
			return false
		}
		return true
	},
	K:function(x,v,e,p,n,s){
		return x+'='+escape(v)+(e?';expires='+e.toGMTString():'')+(p?';path='+p:'')+(n?';domain='+n:'')+(s?';secure':'')
	},
	Lang:function(x){
		x=x.toLowerCase();
		if(this.lang==x)return;

		var v=_[2];
		if(typeof(v)=='function')v=v.prototype;
		v._P=x.substr(0,2)=='zh'?v._C:v._E;
		if(typeof(v=_[3])=='function')v=v.prototype;
		this.lang=x;
		v.$=v._N;
		this.Trans=function(x){return x}
		if(x!='en-us'){
			x=_.xmlhttp.Flush('/js/dict.'+x+'.js');
			try{
				eval('_._d='+x.responseText);
				v.$=v._P;
				this.Trans=function(x){var t=this._d[x];return t?t:x}
			}
			catch(e){}
		}
	},
	M:function(s){
		var o=document.getElementById('menu')
		o.innerHTML=s
	},
	Msg:function(v){
		alert(v);
	},
	Pos:function(d){
		var x=d.offsetLeft,y=d.offsetTop;
		while(d=d.offsetParent){
			x+=d.offsetLeft;
			y+=d.offsetTop;
		}
		return{x:x,y:y};
	},
	Reg:function(o,p){
		var i,r=this._r;
		if(p)r[p]=o;
		else while(i=r.pop())i=null;
	},
	Requests:function(d){
		var c,i,k,n,s,v;
		s=[];
		c=d.elements;
		for(i=c.length;i;){
			k=c[--i];
			if(k.name&&k.className!='gray'){
				n=encodeURIComponent(k.name);
				v=encodeURIComponent(k.value);
				switch(k.type){
				case 'checkbox':
				case 'radio':
					if(k.checked)s.push(n+'='+v);
					break;
				case 'submit':
					break;
				default:
					s.push(n+'='+v);
					break;
				}
			}
		}
		return s.join('&');
	},
	T:function(s){
		var o=document.getElementById('tool')
		o.innerHTML=s
	},
	Trans:function(x){
		return x;
	},
	Try:function(c,f,x,b){
		try{c[f](x)}
		catch(e){
			setTimeout(function(){c[f](x)},100)
			_.Msg(e,1);
		}
	},
	Walk:function(o){
		if(o.getAttribute('walk')=='y')_(o);
	},
	Wnd:function(d){
		d=d.ownerDocument;
		return d.defaultView?d.defaultView:d.parentWindow
	},
	xmlhttp:{
		Abort:function(){
			this._A();
		},
		Flush:function(u,d,p,x){
			return new _._x('GET',u,d,p,null,1);
		},
		Fly:function(u,d,p,x){
			var o=document.createElement('SCRIPT');
			o.src=u;
			o.type='text/javascript';
			o.language='javascript';
			o.o=d;
			o.p=p;
			document.getElementsByTagName('HEAD')[0].appendChild(o);
    	},
		Get:function(u,d,p,x){
			return new _._x('GET',u,d,p,null,0);
		},
		Post:function(u,d,p,r,x){
			return new _._x('POST',u,d,p,r,0);
		}
	},
	zone:0
}
_[2]={
	$:function(d){
		var c=new Date(),m,t=new Date(d.getAttribute('t')*1000);
		if(Math.abs(c-t)>28800000)d.innerHTML=this._P(t);
		else{
			c=t.getHours();
			m=t.getMinutes();
			d.innerHTML=(c>12?c-12:(c<1?12:c))+':'+(m<10?'0'+m:m);
		}
	},
	_C:function(t){
		return t.getMonth()+1+'月'+t.getDate()+'日';
	},
	_E:function(t){
		return this._m[t.getMonth()]+' '+t.getDate();
	},
	_m:['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
	_P:null
}
_[3]={
	$:function(d){
	},
	_N:function(d){
		d.style.visibility='visible';
	},
	_P:function(d){
		var x;
		d.style.visibility='visible';
		if(!(x=d.getAttribute('x')))
			if(!(x=d.getAttribute('value')))x=d.innerHTML;
		if(x=_._d[x]){
			if(d.innerHTML)d.innerHTML=x;
			else if(d.value)d.value=x;
		}
	}
}
_[20]={
	$:function(d){
		var o=d.proc,w;
		if(!o)o=d.getAttribute('proc');
		if(typeof(o)=='string'){
			w=$.Wnd(d);
			this._o=function(){w.eval(o);}
		}
		else this._o=o;
		if(d.tagName=='FORM'){
			o=this;
			d.submit=function(){o._S.call(o,d)}
//			_.Attach(d,'submit',this,'_S');
			_.Attach(d,'onsubmit',this,'_S');
			_.Attach(d,'onclick',this,'_K',1);
		}
		else _.xmlhttp.Flush(d.getAttribute('action'),d);
		_(d);
	},
	_s:null,
	_A:function(){
		alert("A");
	},
	_C:function(){
		var x=this._x;
		this._q=typeof(x)=='string'?new ActiveXObject(x):new x;
	},
	_K:function(d,e){
		e=e.target;
		if(e.tagName=='INPUT'&&e.type=='submit')this._s=e.name+'='+e.value;
	},
	_M:function(d){
		var o,t;
		d.onstate=null;
		if(d.innerHTML=='Ok'&&(o=d.onok))o(d);
		if(o=d.msg){
			if(!(t=o.getAttribute('t')))t=2;
			_.Msg(o.value,t);
		}
		else _.Msg('<span class=c3>Ok</span>',2);
	},
	_P:function(d,p){
		var q=this._q;
		if(q.readyState==4){
			if(p){
				this.responseText=q.responseText;
				d[p].call(d,q,this);
			}
			else switch(q.status){
			case 200:
				d.innerHTML=q.responseText;
				if(d.getAttribute('walk')=='y')_(d);
				if(d.onstate)d.onstate(d);
				break;
			case 204:
				if(_.Exe(x.statusText.split('; ')[1]))_.Msg();
				break;
			}
		}
	},
	_Q:function(m,u,d,p,r,f){
		var q=this._q,s=d?true:false;
		q.open(m,u,s);
		if(s){
			var a=this;
			q.onreadystatechange=function(){a._P(d,p)}
		}
		if(m=='POST')q.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		if(f)q.setRequestHeader('If-Modified-Since','Thu, 1 Jun 1970 00:00:00 GMT');
		q.send(r);
		if(!s&&q.status==200)this.responseText=q.responseText;
	},
	_S:function(d){
		var p;
		if((p=d.getAttribute('msg'))==null)p='<span class=c3>Saving...</span>';
		if(p)_.Msg(p);
		p=_.Requests(d)+'&strip=y&'+this._s;
		this._s=null,
		d.onstate=this._M;
		if(d.method=='post')i=_.xmlhttp.Post(d.action,d,null,p);
		else i=_.xmlhttp.Get(d.action,d,null,p);
		return false;
	}
};

if(top._.$){
	window.onload=top._._D;
}
else{
	var i,n=_[0],s,t,x;
	for(i in n)_[i]=n[i];
	if(window.XMLHttpRequest)_[20]._x=XMLHttpRequest
	else{
		n=['Microsoft.XMLHTTP','MSXML2.XMLHTTP','MSXML2.XMLHTTP.3.0','MSXML2.XMLHTTP.4.0','MSXML2.XMLHTTP.5.0'];
		for(i=n.length-1;i>=0;i--){
			try{
				_[20]._x=n[i];
				new ActiveXObject(n[i]);
				break;
			}
			catch(e){}
		}
	}
	i=function(m,u,d,p,r,f){this._C();this._Q(m,u,d,p,r,f)}
	i.prototype=_[20];
	_._x=i;
	s=navigator.browserLanguage;
	if(!s)s=navigator.language;
	_.Lang(s);
	_[99]=[];
	s=document.getElementsByTagName('LINK');
	for(i=s.length;i>0;){
		n=s[--i];
		t=n.getAttribute('script');
		if(t){
			if(t=='y')t=n.href.replace(/.css/,'.js');
			try{eval(_.xmlhttp.Get(t).responseText)}
			catch(e){_.Msg(t+': '+e)}
		}
	}
	if(document.body)window.onload=_._D;
}