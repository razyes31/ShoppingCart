<%@page import="java.util.*, com.rajesh.domain.*"%>

<html>
<head>
<title>Summary</title>
<link rel="stylesheet" type="text/css" href="CSS/table.css">

</head>
<body>

	<%
		Collection<Cart> items = (Collection<Cart>) session.getAttribute("cart");
		float sum = 0;

		if (items == null) {
	%>
	<table id="items">
		<tr>

			<th>Your cart is empty</th>
		</tr>
	</table>

	<%
		} else {
	%>

	<table id="items">
		<tr>


			<th>Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Price</th>
		</tr>

		<%
			for (Cart item : items) {
		%>
		<tr>

			<td><%=item.getName()%></td>
			<td><%=item.getPrice()%></td>
			<td><%=item.getQuantity()%></td>
			<%
				sum += item.getPrice() * item.getQuantity();
			%>
			<td><%=item.getPrice() * item.getQuantity()%>
		</tr>


		<%
			}
		%>
		<tr>
			<td>Total price</td>
			<td></td>
			<td></td>

			<td><%=sum%></td>
		</tr>
	</table>
	<%
		}
	%>
	<form name="summaryForm" method="post" action="/ShoppingCart/path">

		<input type="submit" name="action" value="Back to Cart" /> <input
			type="submit" name="action" value="Checkout" /> <input type="hidden"
			name="page" value="summary" />
	</form>
</body>
</html>