/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipientSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KaleoNotificationRecipientModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoNotificationRecipient table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipientImpl
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipientModel
 * @generated
 */
public class KaleoNotificationRecipientModelImpl extends BaseModelImpl<KaleoNotificationRecipient> {
	public static final String TABLE_NAME = "Kaleo_KaleoNotificationRecipient";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoRecipientId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "kaleoNotificationId", new Integer(Types.BIGINT) },
			{ "recipientClassName", new Integer(Types.VARCHAR) },
			{ "recipientClassPK", new Integer(Types.BIGINT) },
			{ "address", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Kaleo_KaleoNotificationRecipient (kaleoRecipientId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,kaleoNotificationId LONG,recipientClassName VARCHAR(75) null,recipientClassPK LONG,address VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Kaleo_KaleoNotificationRecipient";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoNotificationRecipient.kaleoRecipientId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Kaleo_KaleoNotificationRecipient.kaleoRecipientId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"),
			true);

	public static KaleoNotificationRecipient toModel(
		KaleoNotificationRecipientSoap soapModel) {
		KaleoNotificationRecipient model = new KaleoNotificationRecipientImpl();

		model.setKaleoRecipientId(soapModel.getKaleoRecipientId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setKaleoNotificationId(soapModel.getKaleoNotificationId());
		model.setRecipientClassName(soapModel.getRecipientClassName());
		model.setRecipientClassPK(soapModel.getRecipientClassPK());
		model.setAddress(soapModel.getAddress());

		return model;
	}

	public static List<KaleoNotificationRecipient> toModels(
		KaleoNotificationRecipientSoap[] soapModels) {
		List<KaleoNotificationRecipient> models = new ArrayList<KaleoNotificationRecipient>(soapModels.length);

		for (KaleoNotificationRecipientSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"));

	public KaleoNotificationRecipientModelImpl() {
	}

	public long getPrimaryKey() {
		return _kaleoRecipientId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoRecipientId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoRecipientId);
	}

	public long getKaleoRecipientId() {
		return _kaleoRecipientId;
	}

	public void setKaleoRecipientId(long kaleoRecipientId) {
		_kaleoRecipientId = kaleoRecipientId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getKaleoNotificationId() {
		return _kaleoNotificationId;
	}

	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationId = kaleoNotificationId;
	}

	public String getRecipientClassName() {
		if (_recipientClassName == null) {
			return StringPool.BLANK;
		}
		else {
			return _recipientClassName;
		}
	}

	public void setRecipientClassName(String recipientClassName) {
		_recipientClassName = recipientClassName;
	}

	public long getRecipientClassPK() {
		return _recipientClassPK;
	}

	public void setRecipientClassPK(long recipientClassPK) {
		_recipientClassPK = recipientClassPK;
	}

	public String getAddress() {
		if (_address == null) {
			return StringPool.BLANK;
		}
		else {
			return _address;
		}
	}

	public void setAddress(String address) {
		_address = address;
	}

	public KaleoNotificationRecipient toEscapedModel() {
		if (isEscapedModel()) {
			return (KaleoNotificationRecipient)this;
		}
		else {
			KaleoNotificationRecipient model = new KaleoNotificationRecipientImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setKaleoRecipientId(getKaleoRecipientId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setKaleoNotificationId(getKaleoNotificationId());
			model.setRecipientClassName(HtmlUtil.escape(getRecipientClassName()));
			model.setRecipientClassPK(getRecipientClassPK());
			model.setAddress(HtmlUtil.escape(getAddress()));

			model = (KaleoNotificationRecipient)Proxy.newProxyInstance(KaleoNotificationRecipient.class.getClassLoader(),
					new Class[] { KaleoNotificationRecipient.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					KaleoNotificationRecipient.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		KaleoNotificationRecipientImpl clone = new KaleoNotificationRecipientImpl();

		clone.setKaleoRecipientId(getKaleoRecipientId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoNotificationId(getKaleoNotificationId());
		clone.setRecipientClassName(getRecipientClassName());
		clone.setRecipientClassPK(getRecipientClassPK());
		clone.setAddress(getAddress());

		return clone;
	}

	public int compareTo(KaleoNotificationRecipient kaleoNotificationRecipient) {
		int value = 0;

		if (getKaleoRecipientId() < kaleoNotificationRecipient.getKaleoRecipientId()) {
			value = -1;
		}
		else if (getKaleoRecipientId() > kaleoNotificationRecipient.getKaleoRecipientId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KaleoNotificationRecipient kaleoNotificationRecipient = null;

		try {
			kaleoNotificationRecipient = (KaleoNotificationRecipient)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kaleoNotificationRecipient.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{kaleoRecipientId=");
		sb.append(getKaleoRecipientId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", kaleoNotificationId=");
		sb.append(getKaleoNotificationId());
		sb.append(", recipientClassName=");
		sb.append(getRecipientClassName());
		sb.append(", recipientClassPK=");
		sb.append(getRecipientClassPK());
		sb.append(", address=");
		sb.append(getAddress());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoRecipientId</column-name><column-value><![CDATA[");
		sb.append(getKaleoRecipientId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNotificationId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNotificationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientClassName</column-name><column-value><![CDATA[");
		sb.append(getRecipientClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientClassPK</column-name><column-value><![CDATA[");
		sb.append(getRecipientClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>address</column-name><column-value><![CDATA[");
		sb.append(getAddress());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoRecipientId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoNotificationId;
	private String _recipientClassName;
	private long _recipientClassPK;
	private String _address;
	private transient ExpandoBridge _expandoBridge;
}