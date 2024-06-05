<%--
 * create.jsp
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

<form:form action="tutorial/create.do" method="POST" modelAttribute="tutorial">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="titulo">
		<spring:message code="tutorial.titulo" />:
	</form:label>
	<form:input path="titulo" />
	<form:errors cssClass="error" path="titulo" />
	<br />
	
	<form:label path="descripcion">
		<spring:message code="tutorial.descripcion" />:
	</form:label>
	<form:input path="descripcion" />
	<form:errors cssClass="error" path="descripcion" />
	<br />
	
	<form:label path="video">
		<spring:message code="tutorial.video" />:
	</form:label>
	<form:input path="video" />
	<form:errors cssClass="error" path="video" />
	<br />
	
	<form:hidden path="academia" />
	
	<input type="submit" name="save"
		value="<spring:message code="tutorial.save" />" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="tutorial.cancel" />"
		onclick="javascript: relativeRedir('tutorial/list.do');" />
	<br /> 
</form:form>