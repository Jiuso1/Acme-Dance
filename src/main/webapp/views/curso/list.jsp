<%--
 * list.jsp
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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="cursos" requestURI="${requestURI}" id="row">
	
	<spring:message code="curso.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="true" />

	<spring:message code="curso.fechaini" var="fechainiHeader" />
	<display:column property="fechaini" title="${fechainiHeader}" sortable="true" />

	<spring:message code="curso.fechafin" var="fechafinHeader" />
	<display:column property="fechafin" title="${fechafinHeader}" sortable="true" />
	
	<spring:message code="curso.diaSemana" var="diaSemanaHeader" />
	<display:column property="diaSemana" title="${diaSemanaHeader}" sortable="false" />
	
	<spring:message code="curso.hora" var="horaHeader" />
	<display:column property="hora" title="${horaHeader}" sortable="false" />
	
	<spring:message code="curso.nivel" var="nivelHeader" />
	<display:column property="nivel" title="${nivelHeader}" sortable="true" />
	
	<spring:message code="curso.estilo" var="estiloHeader" />
	<display:column property="estilo" title="${estiloHeader}" sortable="true" />
	
	<spring:message code="curso.academia" var="academiaHeader" />
	<display:column property="academia" title="${academiaHeader}" sortable="true" />
	
	<security:authorize access="hasRole('ALUMNO')">

		<display:column>
			<a href="curso/request.do?cursoId=${row.id}">
				<spring:message	code="curso.solicitar" />
			</a>
		</display:column>
				
	</security:authorize>
	
	<security:authorize access="hasRole('ACADEMIA')">

		<display:column>
			<form:form action="curso/giveEstilo.do?cursoId=${row.id}" method="POST" modelAttribute="ayuda">
			<form:hidden path="a" />
			<form:hidden path="estado" />
				<form:label path="estilo">
						<spring:message code="curso.estilo" />:
					</form:label>
					<form:select id="estilos" path="estilo">
						<form:options items="${estilos}" />
					</form:select>
				<input type="submit" name="save" value="<spring:message code="curso.save" />" />&nbsp;
			</form:form>
		</display:column>
		
		<display:column>
			<a href="curso/deleteCurso.do?cursoId=${row.id}" >
				<spring:message	code="curso.borrarCurso" />
			</a>
		</display:column>
		
		<display:column>
			<a href="curso/editCurso.do?cursoId=${row.id}" >
				<spring:message	code="curso.editCurso" />
			</a>
		</display:column>
				
	</security:authorize>

</display:table>