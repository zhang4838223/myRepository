 <#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
 <#assign tilesx=JspTaglibs["http://tiles.apache.org/tags-tiles-extras"]>
 <#list list as key>
 <div>${(key)!"asdfsadfdsfdsa"}</div>
 <@tiles.insertDefinition name="remotetiles"/>
 </#list>
<#-- ${(name)!"null"}-->
