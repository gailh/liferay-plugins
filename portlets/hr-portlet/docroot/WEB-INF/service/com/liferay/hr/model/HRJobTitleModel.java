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

package com.liferay.hr.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the HRJobTitle service. Represents a row in the &quot;HRJobTitle&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.hr.model.impl.HRJobTitleModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.hr.model.impl.HRJobTitleImpl}.
 * </p>
 *
 * @author Wesley Gong
 * @see HRJobTitle
 * @see com.liferay.hr.model.impl.HRJobTitleImpl
 * @see com.liferay.hr.model.impl.HRJobTitleModelImpl
 * @generated
 */
public interface HRJobTitleModel extends BaseModel<HRJobTitle>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a h r job title model instance should use the {@link HRJobTitle} interface instead.
	 */

	/**
	 * Returns the primary key of this h r job title.
	 *
	 * @return the primary key of this h r job title
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this h r job title.
	 *
	 * @param primaryKey the primary key of this h r job title
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the hr job title ID of this h r job title.
	 *
	 * @return the hr job title ID of this h r job title
	 */
	public long getHrJobTitleId();

	/**
	 * Sets the hr job title ID of this h r job title.
	 *
	 * @param hrJobTitleId the hr job title ID of this h r job title
	 */
	public void setHrJobTitleId(long hrJobTitleId);

	/**
	 * Returns the group ID of this h r job title.
	 *
	 * @return the group ID of this h r job title
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this h r job title.
	 *
	 * @param groupId the group ID of this h r job title
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this h r job title.
	 *
	 * @return the company ID of this h r job title
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this h r job title.
	 *
	 * @param companyId the company ID of this h r job title
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this h r job title.
	 *
	 * @return the user ID of this h r job title
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this h r job title.
	 *
	 * @param userId the user ID of this h r job title
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this h r job title.
	 *
	 * @return the user uuid of this h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this h r job title.
	 *
	 * @param userUuid the user uuid of this h r job title
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this h r job title.
	 *
	 * @return the user name of this h r job title
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this h r job title.
	 *
	 * @param userName the user name of this h r job title
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this h r job title.
	 *
	 * @return the create date of this h r job title
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this h r job title.
	 *
	 * @param createDate the create date of this h r job title
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this h r job title.
	 *
	 * @return the modified date of this h r job title
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this h r job title.
	 *
	 * @param modifiedDate the modified date of this h r job title
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this h r job title.
	 *
	 * @return the name of this h r job title
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this h r job title.
	 *
	 * @param name the name of this h r job title
	 */
	public void setName(String name);

	/**
	 * Returns the description of this h r job title.
	 *
	 * @return the description of this h r job title
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this h r job title.
	 *
	 * @param description the description of this h r job title
	 */
	public void setDescription(String description);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(HRJobTitle hrJobTitle);

	public int hashCode();

	public HRJobTitle toEscapedModel();

	public String toString();

	public String toXmlString();
}