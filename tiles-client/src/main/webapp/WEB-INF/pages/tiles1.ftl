 <#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
 <#list list as key>
    <div>${(key)!"key is null"}</div>
    <@tiles.insertDefinition name="remotetiles1"/>
 </#list>
${(name)!"null"}