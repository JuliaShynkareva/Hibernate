<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 07.11.2016
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${sessionScope.get('entry') ne null}">
  ${pageContext.forward("/profile")}
</c:if>

<html>
  <head>
    <title>Authorization</title>
    <link rel="shortcut icon" href="/resources/image/image2.jpg">
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet">
  </head>
  <c:if test="${!incorrect.equals('undefined')}">
    <script>alert('${incorrect}');</script>
  </c:if>
  <body>
    <div id="login-form">
      <h1>АВТОРИЗАЦИЯ ${sessionScope.get('entry')}</h1>
      <fieldset>
        <form:form modelAttribute="user" action="/authorization" method="post">
          <form:input path="login" placeholder="Login"/>
          <font color="#dc143c"><form:errors path="login"/></font>
          <form:password path="password" placeholder="Password"/>
          <font color="#dc143c"><form:errors path="password"/></font>
          <input type="submit"  value="ВОЙТИ">
        </form:form>
        <p>Нет аккаунта? &nbsp;<a href="/registration">Регистрация</a></p>
      </fieldset>
    </div>
  </body>
</html>
