 <#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
 <#list list as key>
 <div>${(key)!"asdfsadfdsfdsa"}</div>
 <@tiles.insertDefinition name="remotetiles2"/>
 </#list>
${(name)!"null"}