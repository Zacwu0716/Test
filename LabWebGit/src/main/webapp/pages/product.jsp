<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="" />

<title>Product</title>
<script type="text/javascript">
function clearForm() {
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<inputs.length; i++) {
		if(inputs[i].type=="text") {
			inputs[i].value="";
		}
	}
}
</script>
</head>
<body>

<h3>Welcome, </h3>

<h3>Product Table</h3>

<form action="../index.jsp" method="get">
<table>
	<tr>
		<td>ID : </td>
		<td><input type="text" name="id" value=""></td>
		<td></td>
	</tr>
	<tr>
		<td>Name : </td>
		<td><input type="text" name="name" value=""></td>
		<td></td>
	</tr>

	<tr>
		<td>Price : </td>
		<td><input type="text" name="price" value=""></td>
		<td></td>
	</tr>
	<tr>
		<td>Make : </td>
		<td><input type="text" name="make" value=""></td>
		<td></td>
	</tr>
	<tr>
		<td>Expire : </td>
		<td><input type="text" name="expire" value=""></td>
		<td></td>
	</tr>
	<tr>
		<td>
			<input type="submit" name="prodaction" value="Insert">
			<input type="submit" name="prodaction" value="Update">
		</td>
		<td>
			<input type="submit" name="prodaction" value="Delete">
			<input type="submit" name="prodaction" value="Select">
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
	</tr>
</table>

</form>
</body>
</html>