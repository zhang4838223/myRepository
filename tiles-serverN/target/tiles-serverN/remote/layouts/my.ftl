<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
<html>
		<@tiles.insertAttribute name="menu"/>
		<@tiles.insertAttribute name="body"/>
		<@tiles.insertAttribute name="footer"/>
</html>