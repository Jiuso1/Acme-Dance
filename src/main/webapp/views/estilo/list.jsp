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
	name="estilos" requestURI="${requestURI}" id="row">
	
	<spring:message code="estilo.nombre" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="true" />

	<spring:message code="estilo.descripcion" var="descripcionHeader" />
	<display:column property="descripcion" title="${descripcionHeader}" sortable="true" />
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column>
			<a href="estilo/deleteEstilo.do?estiloId=${row.id}" method="GET">
				<spring:message	code="estilo.borrarEstilo" />
			</a>
		</display:column>
		
		<display:column>
			<a href="estilo/editEstilo.do?styleId=${row.id}" method="GET">
				<spring:message	code="estilo.editEstilo" />
			</a>
		</display:column>
	</security:authorize>

</display:table>