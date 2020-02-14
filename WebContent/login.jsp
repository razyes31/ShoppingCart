<!DOCTYPE html>
<html>
<head>
<link  rel="stylesheet" type="text/css" href="CSS/Login.css">
</head>
<body>

<h2>Login Form</h2>

<%
String loginError = (String)request.getAttribute("loginError");
if(loginError!=null){
%>

<span style="color: red"><%=loginError %></span>
<%
}
%>

<form name="loginForm"  method="post" action="/ShoppingCart/path" >
  <div class="container">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>
        
   <input type="submit" name="action" value="Login"/>
    <input type="reset" value="Reset"/>
    <input type="hidden" name="page" value="login"/>
    </div>    
</form>

</body>
</html>
