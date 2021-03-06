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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;

import java.util.List;

/**
 * The persistence utility for the kaleo task assignment service. This utility wraps {@link KaleoTaskAssignmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentPersistence
 * @see KaleoTaskAssignmentPersistenceImpl
 * @generated
 */
public class KaleoTaskAssignmentUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KaleoTaskAssignment kaleoTaskAssignment) {
		getPersistence().clearCache(kaleoTaskAssignment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTaskAssignment remove(
		KaleoTaskAssignment kaleoTaskAssignment) throws SystemException {
		return getPersistence().remove(kaleoTaskAssignment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoTaskAssignment update(
		KaleoTaskAssignment kaleoTaskAssignment, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoTaskAssignment, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoTaskAssignment update(
		KaleoTaskAssignment kaleoTaskAssignment, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(kaleoTaskAssignment, merge, serviceContext);
	}

	/**
	* Caches the kaleo task assignment in the entity cache if it is enabled.
	*
	* @param kaleoTaskAssignment the kaleo task assignment
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment) {
		getPersistence().cacheResult(kaleoTaskAssignment);
	}

	/**
	* Caches the kaleo task assignments in the entity cache if it is enabled.
	*
	* @param kaleoTaskAssignments the kaleo task assignments
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments) {
		getPersistence().cacheResult(kaleoTaskAssignments);
	}

	/**
	* Creates a new kaleo task assignment with the primary key. Does not add the kaleo task assignment to the database.
	*
	* @param kaleoTaskAssignmentId the primary key for the new kaleo task assignment
	* @return the new kaleo task assignment
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment create(
		long kaleoTaskAssignmentId) {
		return getPersistence().create(kaleoTaskAssignmentId);
	}

	/**
	* Removes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	* @return the kaleo task assignment that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment remove(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence().remove(kaleoTaskAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTaskAssignment, merge);
	}

	/**
	* Returns the kaleo task assignment with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException} if it could not be found.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	* @return the kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByPrimaryKey(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence().findByPrimaryKey(kaleoTaskAssignmentId);
	}

	/**
	* Returns the kaleo task assignment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	* @return the kaleo task assignment, or <code>null</code> if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByPrimaryKey(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTaskAssignmentId);
	}

	/**
	* Returns all the kaleo task assignments where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo task assignments where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoTaskAssignmentId,
			companyId, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignments where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo task assignments where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoTaskAssignmentId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignments where kaleoTaskId = &#63;.
	*
	* @param kaleoTaskId the kaleo task ID
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoTaskId(kaleoTaskId);
	}

	/**
	* Returns a range of all the kaleo task assignments where kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the kaleo task ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoTaskId(kaleoTaskId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the kaleo task ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoTaskId(kaleoTaskId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the kaleo task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoTaskId_First(
		long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoTaskId_First(kaleoTaskId, orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the kaleo task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoTaskId_Last(
		long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoTaskId_Last(kaleoTaskId, orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param kaleoTaskId the kaleo task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKaleoTaskId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoTaskId_PrevAndNext(kaleoTaskAssignmentId,
			kaleoTaskId, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignments where kaleoNodeId = &#63; and kaleoTaskId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param kaleoTaskId the kaleo task ID
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKNI_KTI(
		long kaleoNodeId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKNI_KTI(kaleoNodeId, kaleoTaskId);
	}

	/**
	* Returns a range of all the kaleo task assignments where kaleoNodeId = &#63; and kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the kaleo node ID
	* @param kaleoTaskId the kaleo task ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKNI_KTI(
		long kaleoNodeId, long kaleoTaskId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKNI_KTI(kaleoNodeId, kaleoTaskId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where kaleoNodeId = &#63; and kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the kaleo node ID
	* @param kaleoTaskId the kaleo task ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKNI_KTI(
		long kaleoNodeId, long kaleoTaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKNI_KTI(kaleoNodeId, kaleoTaskId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoNodeId = &#63; and kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the kaleo node ID
	* @param kaleoTaskId the kaleo task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKNI_KTI_First(
		long kaleoNodeId, long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKNI_KTI_First(kaleoNodeId, kaleoTaskId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoNodeId = &#63; and kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the kaleo node ID
	* @param kaleoTaskId the kaleo task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKNI_KTI_Last(
		long kaleoNodeId, long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKNI_KTI_Last(kaleoNodeId, kaleoTaskId,
			orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoNodeId = &#63; and kaleoTaskId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param kaleoNodeId the kaleo node ID
	* @param kaleoTaskId the kaleo task ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKNI_KTI_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoNodeId, long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKNI_KTI_PrevAndNext(kaleoTaskAssignmentId,
			kaleoNodeId, kaleoTaskId, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignments where kaleoTaskId = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoTaskId the kaleo task ID
	* @param assigneeClassName the assignee class name
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKTI_ACN(
		long kaleoTaskId, java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKTI_ACN(kaleoTaskId, assigneeClassName);
	}

	/**
	* Returns a range of all the kaleo task assignments where kaleoTaskId = &#63; and assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the kaleo task ID
	* @param assigneeClassName the assignee class name
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKTI_ACN(
		long kaleoTaskId, java.lang.String assigneeClassName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKTI_ACN(kaleoTaskId, assigneeClassName, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where kaleoTaskId = &#63; and assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the kaleo task ID
	* @param assigneeClassName the assignee class name
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKTI_ACN(
		long kaleoTaskId, java.lang.String assigneeClassName, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKTI_ACN(kaleoTaskId, assigneeClassName, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoTaskId = &#63; and assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the kaleo task ID
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKTI_ACN_First(
		long kaleoTaskId, java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKTI_ACN_First(kaleoTaskId, assigneeClassName,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoTaskId = &#63; and assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the kaleo task ID
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKTI_ACN_Last(
		long kaleoTaskId, java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKTI_ACN_Last(kaleoTaskId, assigneeClassName,
			orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoTaskId = &#63; and assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param kaleoTaskId the kaleo task ID
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKTI_ACN_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoTaskId,
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKTI_ACN_PrevAndNext(kaleoTaskAssignmentId,
			kaleoTaskId, assigneeClassName, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignments.
	*
	* @return the kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo task assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo task assignments where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the kaleo task assignments where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Removes all the kaleo task assignments where kaleoTaskId = &#63; from the database.
	*
	* @param kaleoTaskId the kaleo task ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoTaskId(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoTaskId(kaleoTaskId);
	}

	/**
	* Removes all the kaleo task assignments where kaleoNodeId = &#63; and kaleoTaskId = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param kaleoTaskId the kaleo task ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKNI_KTI(long kaleoNodeId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKNI_KTI(kaleoNodeId, kaleoTaskId);
	}

	/**
	* Removes all the kaleo task assignments where kaleoTaskId = &#63; and assigneeClassName = &#63; from the database.
	*
	* @param kaleoTaskId the kaleo task ID
	* @param assigneeClassName the assignee class name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKTI_ACN(long kaleoTaskId,
		java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKTI_ACN(kaleoTaskId, assigneeClassName);
	}

	/**
	* Removes all the kaleo task assignments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo task assignments where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo task assignments where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo task assignments where kaleoTaskId = &#63;.
	*
	* @param kaleoTaskId the kaleo task ID
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoTaskId(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoTaskId(kaleoTaskId);
	}

	/**
	* Returns the number of kaleo task assignments where kaleoNodeId = &#63; and kaleoTaskId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param kaleoTaskId the kaleo task ID
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKNI_KTI(long kaleoNodeId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKNI_KTI(kaleoNodeId, kaleoTaskId);
	}

	/**
	* Returns the number of kaleo task assignments where kaleoTaskId = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoTaskId the kaleo task ID
	* @param assigneeClassName the assignee class name
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKTI_ACN(long kaleoTaskId,
		java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKTI_ACN(kaleoTaskId, assigneeClassName);
	}

	/**
	* Returns the number of kaleo task assignments.
	*
	* @return the number of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTaskAssignmentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTaskAssignmentPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoTaskAssignmentPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoTaskAssignmentUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(KaleoTaskAssignmentPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(KaleoTaskAssignmentUtil.class,
			"_persistence");
	}

	private static KaleoTaskAssignmentPersistence _persistence;
}