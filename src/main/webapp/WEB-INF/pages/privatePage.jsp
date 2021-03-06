<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 07.11.2016
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Profile</title>
    <link rel="shortcut icon" href="/resources/image/image2.jpg">
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet">
  </head>

  <c:if test="${!profile_edit.equals('old_data')}">
    <script>alert('${profile_edit}');</script>
  </c:if>

  <body>
    <div id="login-form">
      <h1>ПРОФИЛЬ</h1>
      <fieldset>
        <form:form modelAttribute="profile" action="/profile/edit" method="post">
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
            <form:option value="male" label="Мужчина"/>
            <form:option value="female" label="Женщина"/>
          </form:select>

          <form:input path="city" placeholder="City"/>
          <font color="#dc143c"><form:errors path="city"/></font>
          <input type="submit"  value="РЕДАКТИРОВАТЬ ПРОФИЛЬ"/>
          <p id="exit"><a href="/exit">ВЫХОД</a></p>
        </form:form>
      </fieldset>
    </div>
  </body>
</html>
