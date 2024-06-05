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
	name="tutoriales" requestURI="${requestURI}" id="row">
	
	<spring:message code="tutorial.titulo" var="tituloHeader" />
	<display:column property="titulo" title="${tituloHeader}" sortable="true" />

	<spring:message code="tutorial.descripcion" var="descripcionHeader" />
	<display:column property="descripcion" title="${descripcionHeader}" sortable="true" />
	
	<spring:message code="tutorial.video" var="videoHeader" />
    <display:column title="${videoHeader}" sortable="true">
        <c:choose>
            <c:when test="${not empty row.video}">
                <iframe width="320" height="240" src="${row.video}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen>
                    Your browser does not support the iframe tag.
                </iframe>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </display:column>
    
    <security:authorize access="hasRole('ACADEMIA')">
    <display:column>
			<a href="tutorial/deleteTutorial.do?tutorialId=${row.id}" method="GET">
				<spring:message	code="tutorial.borrarTutorial" />
			</a>
		</display:column>

			<display:column>
			<a href="tutorial/editTutorial.do?tutorialId=${row.id}" method="GET">
				<spring:message	code="tutorial.editTutorial" />
			</a>
		</display:column>
	</security:authorize>


</display:table>