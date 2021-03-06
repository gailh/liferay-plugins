<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<c:if test="<%= enableKBArticleAssetCategories || enableKBArticleAssetTags %>">
	<liferay-util:buffer var="html">
		<c:if test="<%= enableKBArticleAssetCategories %>">
			<liferay-ui:asset-categories-summary
				className="<%= KBArticle.class.getName() %>"
				classPK="<%= kbArticle.getClassPK() %>"
				portletURL="<%= renderResponse.createRenderURL() %>"
			/>
		</c:if>

		<c:if test="<%= enableKBArticleAssetTags %>">
			<liferay-ui:asset-tags-summary
				className="<%= KBArticle.class.getName() %>"
				classPK="<%= kbArticle.getClassPK() %>"
				message="tags"
				portletURL="<%= renderResponse.createRenderURL() %>"
			/>
		</c:if>
	</liferay-util:buffer>

	<c:if test="<%= Validator.isNotNull(html.trim()) %>">
		<div class="kb-article-assets">
			<%= html %>
		</div>
	</c:if>
</c:if>