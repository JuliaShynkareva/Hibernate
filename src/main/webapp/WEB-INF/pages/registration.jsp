<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 07.11.2016
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${sessionScope.get('entry') ne null}">
  ${pageContext.forward("/profile")}
</c:if>

<c:if test="${!incorrect.equals('undefined')}">
  <script>alert('${incorrect}');</script>
</c:if>

<html>
  <head>
    <title>Registration</title>
    <link rel="shortcut icon" href="/resources/image/image2.jpg">
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet">
  </head>
  <body>
    <div id="login-form">
      <h1>РЕГИСТРАЦИЯ ${check}</h1>
      <fieldset>
        <c:if test="${check.equals('user')}">
          <form:form modelAttribute="userNew" action="/registration/user" method="post">
            <form:input path="login" placeholder="Login"/>
            <font color="#dc143c"><form:errors path="login"/></font>
            <form:password path="password" placeholder="Password"/>
            <font color="#dc143c"><form:errors path="password"/></font>
            <form:password path="confirmPassword" placeholder="ConfirmPassword"/>
            <font color="#dc143c"><form:errors path="confirmPassword"/></font>
            <input type="submit"  value="СОЗДАНИЕ ПРОФИЛЯ"/>
          </form:form>
        </c:if>
        <c:if test="${check.equals('profile')}">
          <form:form modelAttribute="profile" action="/registration/profile" method="post">
            <form:input path="firstname" placeholder="Name"/>
            <font color="#dc143c"><form:errors path="firstname"/></font>
            <form:input path="lastname" placeholder="Surname"/>
            <font color="#dc143c"><form:errors path="lastname"/></font>
            <form:input path="email" placeholder="Email"/>
            <font color="#dc143c"><form:errors path="email"/></font>

            <form:label path="age">Количество полных лет: </form:label>
            <form:input type="number" max="80" min="12" required="true" value="20" path="age" placeholder="Age"/>
            <font color="#dc143c"><form:errors path="age" /></font>

            <form:input path="phone" placeholder="+375 29 555 55 55"/>
            <font color="#dc143c"><form:errors path="phone"/></font>

            <form:select path="sex">
              <form:option value="sex" disabled="true" label="Пол"/>
              <form:option value="male" selected="true" label="Мужчина"/>
              <form:option value="female" label="Женщина"/>
            </form:select>

            <form:input path="city" placeholder="City"/>
            <font color="#dc143c"><form:errors path="city"/></font>
            <input type="submit" value="СОЗДАТЬ ПРОФИЛЬ"/>
          </form:form>
        </c:if>
        <p>Есть аккаунт? &nbsp;<a href="/">Авторизация</a></p>
      </fieldset>
    </div>
  </body>
</html>
