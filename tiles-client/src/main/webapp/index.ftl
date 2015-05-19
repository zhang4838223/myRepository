<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
<html>
<body>
 <@tiles.insertDefinition name="myapp.homepage"/>
<h2>Hello World!</h2>
<@tiles.definition name=myapp.homepage template="/layouts/classic.ftl">
  <@tiles.putAttribute name=title  value="This is the title." />
  <@tiles.putAttribute name=header value="/tiles/banner.ftl" />
  <@tiles.putAttribute name=body   value="/tiles/home_body.ftl" />
</@tiles.definition>
<@tiles.insertDefinition name="templateDefinition" />
</body>
</html>
