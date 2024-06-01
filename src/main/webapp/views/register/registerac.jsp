<%--
 * registeracademy.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="register/registeracademy.do" method="POST" modelAttribute="academia">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:label path="nombre">
		<spring:message code="actor.nombre" />:
	</form:label>
	<form:input path="nombre" />
	<form:errors cssClass="error" path="nombre" />
	<br />
	
	<form:label path="apellidos">
		<spring:message code="actor.apellidos" />:
	</form:label>
	<form:input path="apellidos" />
	<form:errors cssClass="error" path="apellidos" />
	<br />
	
	<form:label path="email">
		<spring:message code="actor.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="telefono">
		<spring:message code="actor.telefono" />:
	</form:label>
	<form:input path="telefono" />
	<form:errors cssClass="error" path="telefono" />
	<br />

	<form:label path="direccion">
		<spring:message code="actor.direccion" />:
	</form:label>
	<form:input path="direccion" />
	<form:errors cssClass="error" path="direccion" />
	<br />
	
	<form:hidden path="rol" />
	
	<form:label path="username">
		<spring:message code="actor.username" />:
	</form:label>
	<form:input path="username" />
	<form:errors cssClass="error" path="username" />
	<br />
	
	<form:label path="password">
		<spring:message code="actor.password" />:
	</form:label>
	<form:password path="password" />
	<form:errors cssClass="error" path="password" />
	<br />
	
	<form:label path="nombreComercial">
		<spring:message code="academia.nombreComercial" />:
	</form:label>
	<form:input path="nombreComercial" />
	<form:errors cssClass="error" path="nombreComercial" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="register.save" />" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="register.cancel" />"
		onclick="javascript: relativeRedir('register/register.do');" />
	<br /> 
</form:form>