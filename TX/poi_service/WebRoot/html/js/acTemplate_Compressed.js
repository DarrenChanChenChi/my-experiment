function AcTemplate(){this.home={postalCode:"94128",county:"",state:"CA",firstLine:"SAN FRANCISCO INTERNATIONAL AIRPORT",country:"US",city:"BURLINGAME"};this.work={postalCode:"94089",county:"",state:"CA",firstLine:"Baylands Park",country:"US",city:"Sunnyvale"};this.returnJSON2={region:"US",addressType:"recentandfav",addressList:[{postalCode:"94128",index:0,source:"recent",county:"",state:"CA",firstLine:"San Francisco Hotels",country:"US",city:"BURLINGAME"},{postalCode:"94128",index:0,source:"recent",
county:"",state:"CA",firstLine:"SAN FRANCISCO INTERNATIONAL AIRPORT",country:"US",city:"BURLINGAME"},{postalCode:"94089",index:1,source:"favorite",county:"",state:"CA",firstLine:"Baylands Park",country:"US",city:"Sunnyvale"},{postalCode:"94128",index:0,source:"recent",county:"",state:"CA",firstLine:"SAN FRANCISCO INTERNATIONAL AIRPORT AIRPORT AIRPORT AIRPORT AIRPORT AIRPORT AIRPORT AIRPORT",country:"US",city:"BURLINGAME"}]};this.returnJSON3={region:"US",addressType:"addressCache",addressList:[{state:"CA",
postalCode:"",county:"",country:"US",firstLine:"",city:"SUNNYVALE SAN FRANCISCO INTERNATIONAL AIRPORT"},{state:"CA",postalCode:"",county:"",country:"US",firstLine:"",city:"SAN FRANCISCO INTERNATIONAL AIRPORT AIRPORT AIRPORT AIRPORT AIRPORT"},{state:"CA",postalCode:"",county:"",country:"US",firstLine:"",city:"SANTA CLARA, CA"},{state:"CA",postalCode:"",county:"",country:"US",firstLine:"",city:"SAN FRANCISCO INTERNATIONAL AIRPORT"},{state:"CA",postalCode:"",county:"",country:"US",firstLine:"",city:"MOUNTAIN VIEW SAN FRANCISCO INTERNATIONAL AIRPORT"}]}}
AcTemplate.prototype={constructor:AcTemplate,getAddressCacheList:function(a){return sessionStorage.getItem("SESSION_STORAGE_ADDRESS_CACHE_LIST_"+a)},setAddressCacheList:function(a,b){sessionStorage.setItem("SESSION_STORAGE_ADDRESS_CACHE_LIST_"+b,a)},clearAddressCacheList:function(){sessionStorage.removeItem("SESSION_STORAGE_ADDRESS_CACHE_LIST_addressCache");sessionStorage.removeItem("SESSION_STORAGE_ADDRESS_CACHE_LIST_recentAndFav")},getAddressObj:function(a,b){for(var d="",c="",e=0,f=a.length;e<
f;e++){d+="<"+a[e]+">="+b[e];c+=b[e];if(e!=f-1){d+=";";c+=","}}return{region:$("#region").val(),aceAddress:d,uiAddress:c}},retrieveHomeWork:function(a){var b=this;CommonUtil.debug("SDK_API_getAddress ===========start "+a);dummyData=="true"?this.fakeGetHomeWork(function(){b.getHomeWorkAddressCallback.apply(b,arguments)},a):SDK_API_getAddress(function(){b.getHomeWorkAddressCallback.apply(b,arguments)},a);CommonUtil.debug("SDK_API_getAddress ===========over "+a)},fakeGetHomeWork:function(a,b){CommonUtil.debug("fakeGetHomeWork===== start");
CommonUtil.debug("addrType====="+b);var d=this.work;if(b=="home")d=this.home;a(JSON.stringify(d));CommonUtil.debug("fakeGetHomeWork===== over")},getHomeWorkAddressCallback:function(a){CommonUtil.debug("getHomeWorkAddressCallback resultString=========="+a);if(a){CommonUtil.debug("getHomeWorkAddressCallback JSON.parse(resultString)=========="+JSON.parse(a));a=JSON.parse(a);var b=document.getElementsByName("addressInput"),d="";d=null;for(var c=0,e=b.length;c<e;c++){d=$(b[c]);if(d.length<=0)CommonUtil.debug("node==null");
else{d=d.attr("stopField");CommonUtil.debug("stopField=========="+d);d=d.split("|");for(var f="",g="",j=0,i=d.length;j<i;j++)if(f=a[this.trim(d[j])]){if(g.length>0)g+=", ";g+=f}if(g)b[c].value=g}}}},retrieveAutoCompleteData:function(){var a=$("#region").val();CommonUtil.debug("region===="+a);for(var b in regionSuggestObj){var d={region:a,fields:regionSuggestObj[b]},c=this;CommonUtil.debug("SDKAPI.getAddressAutocompleteData ===========start "+b+"||"+JSON.stringify(d));dummyData=="true"?this.fakeGetAddressAutocompleteData(b,
d,function(){c.getAddressAutoCompleteDataCallback.apply(c,arguments)},b):SDKAPI.getAddressAutocompleteData(b,d,function(){c.getAddressAutoCompleteDataCallback.apply(c,arguments)},b);CommonUtil.debug("SDKAPI.getAddressAutocompleteData ===========over "+b+"||"+JSON.stringify(d))}},fakeGetAddressAutocompleteData:function(a,b,d,c){CommonUtil.debug("fakeGetAddressAutocompleteData===== start");CommonUtil.debug("autoSuggestType====="+c);CommonUtil.debug("addressType====="+a);CommonUtil.debug("addressFilter.region====="+
b.region);var e=this.returnJSON2;if(a=="addressCache")e=this.returnJSON3;e.region=b.region;e.addressType=a;d(e,c);CommonUtil.debug("fakeGetAddressAutocompleteData===== over")},getAddressAutoCompleteDataCallback:function(a,b){CommonUtil.debug("getAddressAutoCompleteDataCallback autoSuggestType=========="+b);CommonUtil.debug("getAddressAutoCompleteDataCallback result=========="+a);CommonUtil.debug("JSON.stringify(result)=========="+JSON.stringify(a));if(a){this.setAddressCacheList(JSON.stringify(a.addressList),
a.addressType);this.loadAutoCompleteData(b)}},loadAutoCompleteData:function(a){CommonUtil.debug("loadAutoCompleteData autoSuggestType=="+a);var b=document.getElementsByName("addressInput"),d="";d=d=null;for(var c=0,e=b.length;c<e;c++){d=$(b[c]);if(d.length<=0)CommonUtil.debug("node==null");else{d=d.attr("autoSuggest");CommonUtil.debug("loadAutoCompleteData input autoSuggest=="+d);if(d==a){d=this.getAddressCacheList(d);CommonUtil.debug("loadAutoCompleteData addressCacheList=="+d);d&&this.bindInputs(d,
b[c])}}}},bindInputs:function(a,b){var d=$(b);CommonUtil.debug("bindInputs node id==="+d.attr("id"));var c=this.convertBindingData(a,d.attr("autoSuggest"),d.attr("autoSuggestFormat"));!c||c.length<1||this.bindAutoComplete(b,d.attr("triggerChar"),d.attr("optionsSize"),c)},convertBindingData:function(a,b,d){if(d&&a){a=a?JSON.parse(a):[];d=d.split("|");var c=null;c="";for(var e=null,f="",g=[],j=[],i=-1,h="",m=0,n=a.length;m<n;m++){e=a[m];f="";i=e.index;h=e.source;for(var k=0,o=d.length;k<o;k++)if(c=
e[this.trim(d[k])]){if(f.length>0)f+=", ";f+=c}if(f.length>0){if(b=="addressCache")if(j[f]==true)continue;else j[f]=true;c={msg:f,index:i,source:h,addressType:b};g.push(c)}}CommonUtil.debug("msgArray======="+JSON.stringify(g));return g}return null},bindAutoComplete:function(a,b,d,c){b={min_char:b,max_len:d,pop_css:"autoDis",li_normal:"fs_large normal suggestLiCss",li_highlight:"fs_large highlight suggestLiCss"};CommonUtil.debug("options==========="+JSON.stringify(b));CommonUtil.debug("suggestArr===========\n"+
JSON.stringify(c));(new AutoComplete(c,b,this.autoSuggestCallBack)).init().bind(a)},autoSuggestCallBack:function(a,b){CommonUtil.debug("autoSuggestCallBack result===="+JSON.stringify(a));if(a)if(a.index!=null&&a.source!=null){var d={region:$("#region").val(),index:a.index,source:a.source};addressType=a.addressType;CommonUtil.debug("SDKAPI.setValidationAddress  ===========start "+addressType+"||"+JSON.stringify(d));SDKAPI.setValidationAddress(addressType,d);CommonUtil.debug("SDKAPI.setValidationAddress  ===========over "+
addressType+"||"+JSON.stringify(d))}else b.value=a.msg},findAddress:function(){for(var a=null,b=0,d=-1,c="",e=false,f=document.getElementsByName("addressInput"),g=f.length,j=Array(g),i=Array(g),h=0;h<g;h++){j[h]="";i[h]="";a=$(f[h]);if(a.length<=0)CommonUtil.debug("node==null");else{c=a.val();CommonUtil.debug("text=="+c);i[h]=a.attr("id");CommonUtil.debug("key,id=="+i[h]);d=a.attr("maxLength");CommonUtil.debug("maxLength=="+d);b=a.attr("minLen");CommonUtil.debug("minLen=="+b);if(c){c=this.trim(c);
if(c.length>0)e=true;if(c.length<b){CommonUtil.showAlert("",a.attr("placeholder")+" inputbox should >= "+b,I18NHelper["common.button.OK"]);return}else if(d!="-1"&&d>=b&&c.length>d){CommonUtil.showAlert("",a.attr("placeholder")+" inputbox should <= "+b,I18NHelper["common.button.OK"]);return}j[h]=c}else if(b>0){CommonUtil.showAlert("",a.attr("placeholder")+" inputbox should >= "+b,I18NHelper["common.button.OK"]);return}}}if(e==false)CommonUtil.showAlert("",I18NHelper["please.enter.address"],I18NHelper["common.button.OK"]);
else{a=this.getAddressObj(i,j);CommonUtil.debug("SDKAPI.setValidationAddress  ===========start userEntered||"+JSON.stringify(a));SDKAPI.setValidationAddress("userEntered",a);CommonUtil.debug("SDKAPI.setValidationAddress  ===========over userEntered||"+JSON.stringify(a))}},trim:function(a){return a.replace(/(^\s*)|(\s*$)/g,"")}};var acTemplate=new AcTemplate;function AutoComplete(a,b,d){this.min_char=b.min_char!=null?b.min_char:3;this.max_len=b.max_len!=null?b.max_len:10;this.pop_cn=b.pop_css!=null?b.pop_css:"";this.normal=b.li_normal!=null?b.li_normal:"";this.highlight=b.li_highlight!=null?b.li_highlight:"";this.source=a;this.callback=d;this.previewHighLightItem=this.object=null}
AutoComplete.prototype={constructor:AutoComplete,replaceObjSRC:function(a,b,d){if(null!=a){var c=a.src;eval("var patt=/_"+b+"\\./ig;");b=c.replace(patt,"_"+d+".");a.src=b}},init:function(){this.setDom();return this},highlightItem:function(a){if(a){this.disHighlightItem(this.previewHighLightItem);a.className=this.highlight;this.replaceObjSRC(this.getSuggestImg(a),"unfocused","focused");this.previewHighLightItem=a}},disHighlightItem:function(a){if(a){a.className=this.normal;this.replaceObjSRC(this.getSuggestImg(a),
"focused","unfocused")}},bind:function(a){if(a.getAttribute("type")!="text"||a.nodeName!="INPUT")return null;else this.object=a;var b=this;a.onkeyup=function(d){d=d||window.event;var c=b.pop.getElementsByTagName("li"),e=b.pop.getElementsByTagName("li").length,f=e,g;if(d.keyCode==38){CommonUtil.debug("up key pressed========");if(b.pop.style.display!="none"){for(d=0;d<e;d++)if(c[d].className!=b.normal)g=d;else f--;if(f==0){c[e-1].className=b.highlight;this.value=c[e-1].innerText}else{if(c[g]==c[0]){c[e-
1].className=b.highlight;this.value=c[e-1].innerText}else{c[g-1].className=b.highlight;this.value=c[g-1].innerText}c[g].className=b.normal}}else b.insert(this,b)}else if(d.keyCode==40){CommonUtil.debug("down key pressed=========");if(b.pop.style.display!="none"){for(d=0;d<e;d++)if(c[d].className!=b.normal)g=d;else f--;if(f==0){c[0].className=b.highlight;this.value=c[0].innerText}else{if(c[g]==c[e-1]){c[0].className=b.highlight;this.value=c[0].innerText}else{c[g+1].className=b.highlight;this.value=
c[g+1].innerText}c[g].className=b.normal}}else b.insert(this,b)}else b.insert(this,b)};a.onblur=function(){CommonUtil.debug("obj.onblur");setTimeout(function(){b.pop.style.display="none"},3E3)};return this},setDom:function(){var a=this,b=document.createElement("div"),d=document.createElement("ul");document.body.appendChild(b);with(b){className=this.pop_cn;appendChild(d);if(Global_fromPC=="true"){onmouseover=function(c){CommonUtil.debug("onmouseover");c=c||window.event;c=a.getSuggestLi(c);c!=null&&
a.highlightItem(c)};onmouseout=function(c){CommonUtil.debug("onmouseout");c=c||window.event;c=a.getSuggestLi(c);c!=null&&a.disHighlightItem(c)}}ontouchstart=function(c){CommonUtil.debug("ontouchstart");c=c||window.event;c=a.getSuggestLi(c);c!=null&&a.highlightItem(c)};ontouchend=function(c){CommonUtil.debug("ontouchend");c=a.getSuggestLi(c);c!=null&&a.disHighlightItem(c)}}this.pop=b},insert:function(a,b){var d=this.callback,c=this.object,e=[],f=[],g,j=[],i=0,h=0;g=a.value;var m=[],n=this.source.length;
if(g&&g.length>=this.min_char)for(var k=0;k<n;k++){var o=this.source[k],q=o.msg,r=o.source;if(q.toLowerCase().indexOf(g.toLowerCase())==0){f.push(q);e.push(o);r?m.push(r+"_unfocused.png"):m.push("")}}if(f.length==0){this.pop.style.display="none";return false}i=a.offsetLeft;h=a.offsetTop+a.offsetHeight;with(this.pop){style.cssText="width:"+a.offsetWidth+"px;position:absolute;left:"+i+"px;top:"+h+"px;display:none;";onclick=function(l){CommonUtil.debug("onclick==========================");l=l||window.event;
l=b.getSuggestLi(l);if(l!=null){CommonUtil.debug("onclick"+l.innerText);l=l.innerText;this.style.display="none";if(d)for(var s=e.length,p=0;p<s;p++)l==e[p].msg&&d(e[p],c);else a.value=l}};onresize=function(){i=a.offsetLeft;h=a.offsetTop+a.offsetHeight;style.cssText="width:"+a.offsetWidth+"px;position:absolute;left:"+i+"px;top:"+h+"px;display:"+style.display+";"}}g=f.length>this.max_len?this.max_len:f.length;for(k=0;k<g;k++){n=(n=m[k])?'<img id="suggestImg" src="'+GLOBAL_imageCommonUrl+n+'">':"";j.push('<li id="suggestLi"><div style="display: table; float:left; height: 100%;"><div id="suggestImageDiv" class="div_cell">'+
n+"</div></div>"+f[k]+"</li>")}this.pop.getElementsByTagName("ul")[0].innerHTML=j.join("");for(f=0;f<g;f++)this.pop.getElementsByTagName("li")[f].className=this.normal;this.pop.style.display="block"},getSuggestLi:function(a){a=a.srcElement||a.target;do if(a.id=="suggestLi")return a;while(a=a.parentNode||a.parentNode);return null},getSuggestImg:function(a){a=a.getElementsByTagName("img");for(var b=0;b<a.length;b++)if(a[b].id=="suggestImg")return a[b];return null},getSuggestText:function(a){a=a.getElementsByTagName("div");
for(var b=0;b<a.length;b++)if(a[b].id=="suggestText")return a[b];return null}};