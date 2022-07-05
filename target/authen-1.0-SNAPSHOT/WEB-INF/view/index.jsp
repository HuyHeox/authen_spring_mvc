<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
  <form name='loginReqDTO' action="/user/login" method='POST'>
    		<table>
    			<tr>
    				<td>User:</td>
    				<td><input type='text' name='username'></td>
    			</tr>
    			<tr>
    				<td>Password:</td>
    				<td><input type='password' name='password' /></td>
    			</tr>
    			 <tr>
                              <td>Remember Me:</td>
                              <td><input type="checkbox" name="selected" value="remember-me" /></td>
    			<tr>
    				<td colspan='2'><input name="submit" type="submit" value="login" /></td>
    			</tr>
    		</table>

    </form>
  <br/><br/>
  <a href="signup">đăng ký</a>


</body>
</html>