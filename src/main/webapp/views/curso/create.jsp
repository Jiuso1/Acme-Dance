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

<form:form action="curso/create.do" method="POST" modelAttribute="curso">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:label path="titulo">
		<spring:message code="curso.titulo" />:
	</form:label>
	<form:input path="titulo" />
	<form:errors cssClass="error" path="titulo" />
	<br />
	
	<form:label path="fechaini">
		<spring:message code="curso.fechaini" />:
	</form:label>
	<form:input path="fechaini" />
	<form:errors cssClass="error" path="fechaini" />
	<br />
	
	<form:label path="fechafin">
		<spring:message code="curso.fechafin" />:
	</form:label>
	<form:input path="fechafin" />
	<form:errors cssClass="error" path="fechafin" />
	<br />

	<form:label path="diaSemana">
		<spring:message code="curso.diaSemana" />:
	</form:label>
	<form:input path="diaSemana" />
	<form:errors cssClass="error" path="diaSemana" />
	<br />

	<form:label path="hora">
		<spring:message code="curso.hora" />:
	</form:label>
	<form:input path="hora" />
	<form:errors cssClass="error" path="hora" />
	<br />
	
	<form:label path="nivel">
		<spring:message code="curso.nivel" />:
	</form:label>
	<form:select id="niveles" path="nivel">		
		<form:options items="${niveles}"/>
	</form:select>
	<form:errors cssClass="error" path="nivel" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="curso.save" />" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="curso.cancel" />"
		onclick="javascript: relativeRedir('curso/list.do');" />
	<br /> 
</form:form>