 <#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
 <#--<@tiles.insertDefinition name="homepage"/> -->
 <#list list as key>
 <div>${(key)!"asdfsadfdsfdsa"}</div>
 <@tiles.insertDefinition name="remotetiles"/>
 </#list>
${(name)!"null"}