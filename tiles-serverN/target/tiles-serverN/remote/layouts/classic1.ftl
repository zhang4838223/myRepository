<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>
<html>
	<head>
		<title><@tiles.getAsString name="title"/></title>
		<@tiles.insertAttribute name="mycss"/>
	</head>
	<body>
		<table border="1px" style="width:500px;background:#ccc;margin:50px auto;">
			<tr>
				<td colspan="2"><@tiles.insertAttribute name="header"/></td>
			</tr>
			<tr>
				<td><@tiles.insertAttribute name="menu"/></td>
				<td><@tiles.insertAttribute name="body"/></td>
			</tr>
			<tr>
				<td colspan="2">the footer is null!!</td>
			</tr>
		</table>
	</body>
</html>