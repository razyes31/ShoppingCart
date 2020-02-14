<%@page import="java.util.*, com.rajesh.domain.*" %>

<html>
<head>
	<title>Items list</title>
	<link  rel="stylesheet" type="text/css" href="CSS/table.css">
	
</head>
<body>

<%
Collection<Cart> items = (Collection<Cart>)request.getAttribute("items");

%>

<%
String cartSuccess = (String)request.getAttribute("cartSuccess");
if(cartSuccess!=null){
%>

<span style="color: black"><%=cartSuccess %></span>
<%
}
%>

<form name="itemForm" method="post" action="/ShoppingCart/path">
<table id = "items">
<tr>
	<th>&nbsp;</td>
	<th>ID</td>
	<th>Name</td>
	<th>Price</td>
	<th>Quantity</td>
</tr>

<%


for(Cart item:items){
%>
<tr>
	<td><input type="checkbox" name="chkItem" value="<%=item.getId()%>"   <% if (item.getQuantity()!=0) { %> checked <%} %>/></td>
	<td><%=item.getId() %></td>
	<td><%=item.getName() %></td>
	<td><%=item.getPrice() %></td>
	<td><input type="text" value="<%=item.getQuantity()%>" name="quantity<%=item.getId()%>"/></td>
</tr>

<%
}
%>

</table>
<input type="submit" name="action" value="Add to Cart"/>
<input type="submit" name="action" value="Checkout"/>
<input type="hidden" name="page" value="itemslist"/>
</form>
</body>
</html>