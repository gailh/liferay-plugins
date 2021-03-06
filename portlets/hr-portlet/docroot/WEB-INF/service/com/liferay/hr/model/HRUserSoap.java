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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Wesley Gong
 * @generated
 */
public class HRUserSoap implements Serializable {
	public static HRUserSoap toSoapModel(HRUser model) {
		HRUserSoap soapModel = new HRUserSoap();

		soapModel.setHrUserId(model.getHrUserId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrEmploymentTypeId(model.getHrEmploymentTypeId());
		soapModel.setHrJobTitleId(model.getHrJobTitleId());
		soapModel.setHrOfficeId(model.getHrOfficeId());
		soapModel.setHrTerminationTypeId(model.getHrTerminationTypeId());
		soapModel.setHrWageTypeId(model.getHrWageTypeId());
		soapModel.setHireDate(model.getHireDate());
		soapModel.setTerminationDate(model.getTerminationDate());
		soapModel.setWageAmount(model.getWageAmount());
		soapModel.setWageCurrencyCode(model.getWageCurrencyCode());
		soapModel.setBenefitsExempt(model.getBenefitsExempt());
		soapModel.setOvertimeExempt(model.getOvertimeExempt());

		return soapModel;
	}

	public static HRUserSoap[] toSoapModels(HRUser[] models) {
		HRUserSoap[] soapModels = new HRUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRUserSoap[][] toSoapModels(HRUser[][] models) {
		HRUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRUserSoap[] toSoapModels(List<HRUser> models) {
		List<HRUserSoap> soapModels = new ArrayList<HRUserSoap>(models.size());

		for (HRUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRUserSoap[soapModels.size()]);
	}

	public HRUserSoap() {
	}

	public long getPrimaryKey() {
		return _hrUserId;
	}

	public void setPrimaryKey(long pk) {
		setHrUserId(pk);
	}

	public long getHrUserId() {
		return _hrUserId;
	}

	public void setHrUserId(long hrUserId) {
		_hrUserId = hrUserId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public String getUserName() {
		return _userName;
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

	public long getHrEmploymentTypeId() {
		return _hrEmploymentTypeId;
	}

	public void setHrEmploymentTypeId(long hrEmploymentTypeId) {
		_hrEmploymentTypeId = hrEmploymentTypeId;
	}

	public long getHrJobTitleId() {
		return _hrJobTitleId;
	}

	public void setHrJobTitleId(long hrJobTitleId) {
		_hrJobTitleId = hrJobTitleId;
	}

	public long getHrOfficeId() {
		return _hrOfficeId;
	}

	public void setHrOfficeId(long hrOfficeId) {
		_hrOfficeId = hrOfficeId;
	}

	public long getHrTerminationTypeId() {
		return _hrTerminationTypeId;
	}

	public void setHrTerminationTypeId(long hrTerminationTypeId) {
		_hrTerminationTypeId = hrTerminationTypeId;
	}

	public long getHrWageTypeId() {
		return _hrWageTypeId;
	}

	public void setHrWageTypeId(long hrWageTypeId) {
		_hrWageTypeId = hrWageTypeId;
	}

	public Date getHireDate() {
		return _hireDate;
	}

	public void setHireDate(Date hireDate) {
		_hireDate = hireDate;
	}

	public Date getTerminationDate() {
		return _terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		_terminationDate = terminationDate;
	}

	public double getWageAmount() {
		return _wageAmount;
	}

	public void setWageAmount(double wageAmount) {
		_wageAmount = wageAmount;
	}

	public String getWageCurrencyCode() {
		return _wageCurrencyCode;
	}

	public void setWageCurrencyCode(String wageCurrencyCode) {
		_wageCurrencyCode = wageCurrencyCode;
	}

	public boolean getBenefitsExempt() {
		return _benefitsExempt;
	}

	public boolean isBenefitsExempt() {
		return _benefitsExempt;
	}

	public void setBenefitsExempt(boolean benefitsExempt) {
		_benefitsExempt = benefitsExempt;
	}

	public boolean getOvertimeExempt() {
		return _overtimeExempt;
	}

	public boolean isOvertimeExempt() {
		return _overtimeExempt;
	}

	public void setOvertimeExempt(boolean overtimeExempt) {
		_overtimeExempt = overtimeExempt;
	}

	private long _hrUserId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrEmploymentTypeId;
	private long _hrJobTitleId;
	private long _hrOfficeId;
	private long _hrTerminationTypeId;
	private long _hrWageTypeId;
	private Date _hireDate;
	private Date _terminationDate;
	private double _wageAmount;
	private String _wageCurrencyCode;
	private boolean _benefitsExempt;
	private boolean _overtimeExempt;
}