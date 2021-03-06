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

package com.liferay.calendar.service.persistence;

import com.liferay.calendar.NoSuchResourceException;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.model.impl.CalendarResourceImpl;
import com.liferay.calendar.model.impl.CalendarResourceModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the calendar resource service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarResourcePersistence
 * @see CalendarResourceUtil
 * @generated
 */
public class CalendarResourcePersistenceImpl extends BasePersistenceImpl<CalendarResource>
	implements CalendarResourcePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CalendarResourceUtil} to access the calendar resource persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CalendarResourceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED,
			CalendarResourceImpl.class, FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED,
			CalendarResourceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ACTIVE = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED,
			CalendarResourceImpl.class, FINDER_CLASS_NAME_LIST, "findByActive",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVE = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByActive",
			new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_A = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED,
			CalendarResourceImpl.class, FINDER_CLASS_NAME_LIST, "findByG_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_A = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED,
			CalendarResourceImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_N_A = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED,
			CalendarResourceImpl.class, FINDER_CLASS_NAME_LIST, "findByG_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_N_A = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_C_N_A = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED,
			CalendarResourceImpl.class, FINDER_CLASS_NAME_LIST, "findByC_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_A = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByC_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED,
			CalendarResourceImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the calendar resource in the entity cache if it is enabled.
	 *
	 * @param calendarResource the calendar resource
	 */
	public void cacheResult(CalendarResource calendarResource) {
		EntityCacheUtil.putResult(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceImpl.class, calendarResource.getPrimaryKey(),
			calendarResource);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarResource.getUuid(),
				Long.valueOf(calendarResource.getGroupId())
			}, calendarResource);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				Long.valueOf(calendarResource.getClassNameId()),
				Long.valueOf(calendarResource.getClassPK())
			}, calendarResource);

		calendarResource.resetOriginalValues();
	}

	/**
	 * Caches the calendar resources in the entity cache if it is enabled.
	 *
	 * @param calendarResources the calendar resources
	 */
	public void cacheResult(List<CalendarResource> calendarResources) {
		for (CalendarResource calendarResource : calendarResources) {
			if (EntityCacheUtil.getResult(
						CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
						CalendarResourceImpl.class,
						calendarResource.getPrimaryKey(), this) == null) {
				cacheResult(calendarResource);
			}
		}
	}

	/**
	 * Clears the cache for all calendar resources.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CalendarResourceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CalendarResourceImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the calendar resource.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CalendarResource calendarResource) {
		EntityCacheUtil.removeResult(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceImpl.class, calendarResource.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarResource.getUuid(),
				Long.valueOf(calendarResource.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				Long.valueOf(calendarResource.getClassNameId()),
				Long.valueOf(calendarResource.getClassPK())
			});
	}

	/**
	 * Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	 *
	 * @param calendarResourceId the primary key for the new calendar resource
	 * @return the new calendar resource
	 */
	public CalendarResource create(long calendarResourceId) {
		CalendarResource calendarResource = new CalendarResourceImpl();

		calendarResource.setNew(true);
		calendarResource.setPrimaryKey(calendarResourceId);

		String uuid = PortalUUIDUtil.generate();

		calendarResource.setUuid(uuid);

		return calendarResource;
	}

	/**
	 * Removes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar resource
	 * @return the calendar resource that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarResource remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarResourceId the primary key of the calendar resource
	 * @return the calendar resource that was removed
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource remove(long calendarResourceId)
		throws NoSuchResourceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CalendarResource calendarResource = (CalendarResource)session.get(CalendarResourceImpl.class,
					Long.valueOf(calendarResourceId));

			if (calendarResource == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						calendarResourceId);
				}

				throw new NoSuchResourceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					calendarResourceId);
			}

			return calendarResourcePersistence.remove(calendarResource);
		}
		catch (NoSuchResourceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the calendar resource from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarResource the calendar resource
	 * @return the calendar resource that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarResource remove(CalendarResource calendarResource)
		throws SystemException {
		return super.remove(calendarResource);
	}

	@Override
	protected CalendarResource removeImpl(CalendarResource calendarResource)
		throws SystemException {
		calendarResource = toUnwrappedModel(calendarResource);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, calendarResource);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		CalendarResourceModelImpl calendarResourceModelImpl = (CalendarResourceModelImpl)calendarResource;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarResourceModelImpl.getUuid(),
				Long.valueOf(calendarResourceModelImpl.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				Long.valueOf(calendarResourceModelImpl.getClassNameId()),
				Long.valueOf(calendarResourceModelImpl.getClassPK())
			});

		EntityCacheUtil.removeResult(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceImpl.class, calendarResource.getPrimaryKey());

		return calendarResource;
	}

	@Override
	public CalendarResource updateImpl(
		com.liferay.calendar.model.CalendarResource calendarResource,
		boolean merge) throws SystemException {
		calendarResource = toUnwrappedModel(calendarResource);

		boolean isNew = calendarResource.isNew();

		CalendarResourceModelImpl calendarResourceModelImpl = (CalendarResourceModelImpl)calendarResource;

		if (Validator.isNull(calendarResource.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			calendarResource.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, calendarResource, merge);

			calendarResource.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
			CalendarResourceImpl.class, calendarResource.getPrimaryKey(),
			calendarResource);

		if (!isNew &&
				(!Validator.equals(calendarResource.getUuid(),
					calendarResourceModelImpl.getOriginalUuid()) ||
				(calendarResource.getGroupId() != calendarResourceModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					calendarResourceModelImpl.getOriginalUuid(),
					Long.valueOf(calendarResourceModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(calendarResource.getUuid(),
					calendarResourceModelImpl.getOriginalUuid()) ||
				(calendarResource.getGroupId() != calendarResourceModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					calendarResource.getUuid(),
					Long.valueOf(calendarResource.getGroupId())
				}, calendarResource);
		}

		if (!isNew &&
				((calendarResource.getClassNameId() != calendarResourceModelImpl.getOriginalClassNameId()) ||
				(calendarResource.getClassPK() != calendarResourceModelImpl.getOriginalClassPK()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
				new Object[] {
					Long.valueOf(
						calendarResourceModelImpl.getOriginalClassNameId()),
					Long.valueOf(calendarResourceModelImpl.getOriginalClassPK())
				});
		}

		if (isNew ||
				((calendarResource.getClassNameId() != calendarResourceModelImpl.getOriginalClassNameId()) ||
				(calendarResource.getClassPK() != calendarResourceModelImpl.getOriginalClassPK()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
				new Object[] {
					Long.valueOf(calendarResource.getClassNameId()),
					Long.valueOf(calendarResource.getClassPK())
				}, calendarResource);
		}

		return calendarResource;
	}

	protected CalendarResource toUnwrappedModel(
		CalendarResource calendarResource) {
		if (calendarResource instanceof CalendarResourceImpl) {
			return calendarResource;
		}

		CalendarResourceImpl calendarResourceImpl = new CalendarResourceImpl();

		calendarResourceImpl.setNew(calendarResource.isNew());
		calendarResourceImpl.setPrimaryKey(calendarResource.getPrimaryKey());

		calendarResourceImpl.setUuid(calendarResource.getUuid());
		calendarResourceImpl.setCalendarResourceId(calendarResource.getCalendarResourceId());
		calendarResourceImpl.setGroupId(calendarResource.getGroupId());
		calendarResourceImpl.setCompanyId(calendarResource.getCompanyId());
		calendarResourceImpl.setUserId(calendarResource.getUserId());
		calendarResourceImpl.setUserName(calendarResource.getUserName());
		calendarResourceImpl.setCreateDate(calendarResource.getCreateDate());
		calendarResourceImpl.setModifiedDate(calendarResource.getModifiedDate());
		calendarResourceImpl.setClassNameId(calendarResource.getClassNameId());
		calendarResourceImpl.setClassPK(calendarResource.getClassPK());
		calendarResourceImpl.setClassUuid(calendarResource.getClassUuid());
		calendarResourceImpl.setName(calendarResource.getName());
		calendarResourceImpl.setDescription(calendarResource.getDescription());
		calendarResourceImpl.setActive(calendarResource.isActive());

		return calendarResourceImpl;
	}

	/**
	 * Returns the calendar resource with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar resource
	 * @return the calendar resource
	 * @throws com.liferay.portal.NoSuchModelException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarResource findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the calendar resource with the primary key or throws a {@link com.liferay.calendar.NoSuchResourceException} if it could not be found.
	 *
	 * @param calendarResourceId the primary key of the calendar resource
	 * @return the calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByPrimaryKey(long calendarResourceId)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = fetchByPrimaryKey(calendarResourceId);

		if (calendarResource == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					calendarResourceId);
			}

			throw new NoSuchResourceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				calendarResourceId);
		}

		return calendarResource;
	}

	/**
	 * Returns the calendar resource with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar resource
	 * @return the calendar resource, or <code>null</code> if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarResource fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the calendar resource with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calendarResourceId the primary key of the calendar resource
	 * @return the calendar resource, or <code>null</code> if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource fetchByPrimaryKey(long calendarResourceId)
		throws SystemException {
		CalendarResource calendarResource = (CalendarResource)EntityCacheUtil.getResult(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
				CalendarResourceImpl.class, calendarResourceId, this);

		if (calendarResource == _nullCalendarResource) {
			return null;
		}

		if (calendarResource == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				calendarResource = (CalendarResource)session.get(CalendarResourceImpl.class,
						Long.valueOf(calendarResourceId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (calendarResource != null) {
					cacheResult(calendarResource);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CalendarResourceModelImpl.ENTITY_CACHE_ENABLED,
						CalendarResourceImpl.class, calendarResourceId,
						_nullCalendarResource);
				}

				closeSession(session);
			}
		}

		return calendarResource;
	}

	/**
	 * Returns all the calendar resources where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<CalendarResource> list = (List<CalendarResource>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<CalendarResource>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar resource in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		List<CalendarResource> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar resource in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		int count = countByUuid(uuid);

		List<CalendarResource> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendar resources before and after the current calendar resource in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the primary key of the current calendar resource
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource[] findByUuid_PrevAndNext(long calendarResourceId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = findByPrimaryKey(calendarResourceId);

		Session session = null;

		try {
			session = openSession();

			CalendarResource[] array = new CalendarResourceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, calendarResource, uuid,
					orderByComparator, true);

			array[1] = calendarResource;

			array[2] = getByUuid_PrevAndNext(session, calendarResource, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarResource getByUuid_PrevAndNext(Session session,
		CalendarResource calendarResource, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(calendarResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the calendar resource where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchResourceException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByUUID_G(String uuid, long groupId)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = fetchByUUID_G(uuid, groupId);

		if (calendarResource == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchResourceException(msg.toString());
		}

		return calendarResource;
	}

	/**
	 * Returns the calendar resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the calendar resource where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<CalendarResource> list = q.list();

				result = list;

				CalendarResource calendarResource = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					calendarResource = list.get(0);

					cacheResult(calendarResource);

					if ((calendarResource.getUuid() == null) ||
							!calendarResource.getUuid().equals(uuid) ||
							(calendarResource.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, calendarResource);
					}
				}

				return calendarResource;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (CalendarResource)result;
			}
		}
	}

	/**
	 * Returns all the calendar resources where active = &#63;.
	 *
	 * @param active the active
	 * @return the matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByActive(boolean active)
		throws SystemException {
		return findByActive(active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByActive(boolean active, int start,
		int end) throws SystemException {
		return findByActive(active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByActive(boolean active, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				active,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<CalendarResource> list = (List<CalendarResource>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ACTIVE,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

				list = (List<CalendarResource>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_ACTIVE,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ACTIVE,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar resource in the ordered set where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByActive_First(boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		List<CalendarResource> list = findByActive(active, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar resource in the ordered set where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByActive_Last(boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		int count = countByActive(active);

		List<CalendarResource> list = findByActive(active, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendar resources before and after the current calendar resource in the ordered set where active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the primary key of the current calendar resource
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource[] findByActive_PrevAndNext(
		long calendarResourceId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = findByPrimaryKey(calendarResourceId);

		Session session = null;

		try {
			session = openSession();

			CalendarResource[] array = new CalendarResourceImpl[3];

			array[0] = getByActive_PrevAndNext(session, calendarResource,
					active, orderByComparator, true);

			array[1] = calendarResource;

			array[2] = getByActive_PrevAndNext(session, calendarResource,
					active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarResource getByActive_PrevAndNext(Session session,
		CalendarResource calendarResource, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

		query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(calendarResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the calendar resources where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_A(long groupId, boolean active)
		throws SystemException {
		return findByG_A(groupId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the calendar resources where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_A(long groupId, boolean active,
		int start, int end) throws SystemException {
		return findByG_A(groupId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_A(long groupId, boolean active,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, active,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<CalendarResource> list = (List<CalendarResource>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_A,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(active);

				list = (List<CalendarResource>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_A,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_A,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByG_A_First(long groupId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		List<CalendarResource> list = findByG_A(groupId, active, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByG_A_Last(long groupId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		int count = countByG_A(groupId, active);

		List<CalendarResource> list = findByG_A(groupId, active, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendar resources before and after the current calendar resource in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the primary key of the current calendar resource
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource[] findByG_A_PrevAndNext(long calendarResourceId,
		long groupId, boolean active, OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = findByPrimaryKey(calendarResourceId);

		Session session = null;

		try {
			session = openSession();

			CalendarResource[] array = new CalendarResourceImpl[3];

			array[0] = getByG_A_PrevAndNext(session, calendarResource, groupId,
					active, orderByComparator, true);

			array[1] = calendarResource;

			array[2] = getByG_A_PrevAndNext(session, calendarResource, groupId,
					active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarResource getByG_A_PrevAndNext(Session session,
		CalendarResource calendarResource, long groupId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(calendarResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_A(long groupId, boolean active)
		throws SystemException {
		return filterFindByG_A(groupId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_A(long groupId, boolean active,
		int start, int end) throws SystemException {
		return filterFindByG_A(groupId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources that the user has permissions to view where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_A(long groupId, boolean active,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A(groupId, active, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CalendarResource.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CalendarResourceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CalendarResourceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(active);

			return (List<CalendarResource>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the calendar resources before and after the current calendar resource in the ordered set of calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param calendarResourceId the primary key of the current calendar resource
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource[] filterFindByG_A_PrevAndNext(
		long calendarResourceId, long groupId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_PrevAndNext(calendarResourceId, groupId, active,
				orderByComparator);
		}

		CalendarResource calendarResource = findByPrimaryKey(calendarResourceId);

		Session session = null;

		try {
			session = openSession();

			CalendarResource[] array = new CalendarResourceImpl[3];

			array[0] = filterGetByG_A_PrevAndNext(session, calendarResource,
					groupId, active, orderByComparator, true);

			array[1] = calendarResource;

			array[2] = filterGetByG_A_PrevAndNext(session, calendarResource,
					groupId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarResource filterGetByG_A_PrevAndNext(Session session,
		CalendarResource calendarResource, long groupId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CalendarResource.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CalendarResourceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CalendarResourceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(calendarResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the calendar resource where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.calendar.NoSuchResourceException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByC_C(long classNameId, long classPK)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = fetchByC_C(classNameId, classPK);

		if (calendarResource == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchResourceException(msg.toString());
		}

		return calendarResource;
	}

	/**
	 * Returns the calendar resource where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource fetchByC_C(long classNameId, long classPK)
		throws SystemException {
		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the calendar resource where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<CalendarResource> list = q.list();

				result = list;

				CalendarResource calendarResource = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs, list);
				}
				else {
					calendarResource = list.get(0);

					cacheResult(calendarResource);

					if ((calendarResource.getClassNameId() != classNameId) ||
							(calendarResource.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, calendarResource);
					}
				}

				return calendarResource;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (CalendarResource)result;
			}
		}
	}

	/**
	 * Returns all the calendar resources where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @return the matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_N_A(long groupId, String name,
		boolean active) throws SystemException {
		return findByG_N_A(groupId, name, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_N_A(long groupId, String name,
		boolean active, int start, int end) throws SystemException {
		return findByG_N_A(groupId, name, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_N_A(long groupId, String name,
		boolean active, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, name, active,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<CalendarResource> list = (List<CalendarResource>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_N_A,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_G_N_A_GROUPID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_N_A_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_N_A_NAME_2);
				}
			}

			query.append(_FINDER_COLUMN_G_N_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(active);

				list = (List<CalendarResource>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_N_A,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_N_A,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar resource in the ordered set where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByG_N_A_First(long groupId, String name,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		List<CalendarResource> list = findByG_N_A(groupId, name, active, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar resource in the ordered set where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByG_N_A_Last(long groupId, String name,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		int count = countByG_N_A(groupId, name, active);

		List<CalendarResource> list = findByG_N_A(groupId, name, active,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendar resources before and after the current calendar resource in the ordered set where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the primary key of the current calendar resource
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource[] findByG_N_A_PrevAndNext(long calendarResourceId,
		long groupId, String name, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = findByPrimaryKey(calendarResourceId);

		Session session = null;

		try {
			session = openSession();

			CalendarResource[] array = new CalendarResourceImpl[3];

			array[0] = getByG_N_A_PrevAndNext(session, calendarResource,
					groupId, name, active, orderByComparator, true);

			array[1] = calendarResource;

			array[2] = getByG_N_A_PrevAndNext(session, calendarResource,
					groupId, name, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarResource getByG_N_A_PrevAndNext(Session session,
		CalendarResource calendarResource, long groupId, String name,
		boolean active, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

		query.append(_FINDER_COLUMN_G_N_A_GROUPID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_A_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_N_A_NAME_2);
			}
		}

		query.append(_FINDER_COLUMN_G_N_A_ACTIVE_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (name != null) {
			qPos.add(name);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(calendarResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the calendar resources where groupId = any &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param name the name
	 * @param active the active
	 * @return the matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_N_A(long[] groupIds, String name,
		boolean active) throws SystemException {
		return findByG_N_A(groupIds, name, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources where groupId = any &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_N_A(long[] groupIds, String name,
		boolean active, int start, int end) throws SystemException {
		return findByG_N_A(groupIds, name, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources where groupId = any &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByG_N_A(long[] groupIds, String name,
		boolean active, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(groupIds), name, active,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<CalendarResource> list = (List<CalendarResource>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_N_A,
				finderArgs, this);

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

			boolean conjunctionable = false;

			if ((groupIds == null) || (groupIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < groupIds.length; i++) {
					query.append(_FINDER_COLUMN_G_N_A_GROUPID_5);

					if ((i + 1) < groupIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_4);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_N_A_NAME_6);
				}
				else {
					query.append(_FINDER_COLUMN_G_N_A_NAME_5);
				}
			}

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_N_A_ACTIVE_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (groupIds != null) {
					qPos.add(groupIds);
				}

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(active);

				list = (List<CalendarResource>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_G_N_A,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_N_A,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns all the calendar resources that the user has permission to view where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @return the matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_N_A(long groupId, String name,
		boolean active) throws SystemException {
		return filterFindByG_N_A(groupId, name, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources that the user has permission to view where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_N_A(long groupId, String name,
		boolean active, int start, int end) throws SystemException {
		return filterFindByG_N_A(groupId, name, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources that the user has permissions to view where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_N_A(long groupId, String name,
		boolean active, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_N_A(groupId, name, active, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_N_A_GROUPID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_A_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_N_A_NAME_2);
			}
		}

		query.append(_FINDER_COLUMN_G_N_A_ACTIVE_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CalendarResource.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CalendarResourceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CalendarResourceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (name != null) {
				qPos.add(name);
			}

			qPos.add(active);

			return (List<CalendarResource>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the calendar resources before and after the current calendar resource in the ordered set of calendar resources that the user has permission to view where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param calendarResourceId the primary key of the current calendar resource
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource[] filterFindByG_N_A_PrevAndNext(
		long calendarResourceId, long groupId, String name, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_N_A_PrevAndNext(calendarResourceId, groupId, name,
				active, orderByComparator);
		}

		CalendarResource calendarResource = findByPrimaryKey(calendarResourceId);

		Session session = null;

		try {
			session = openSession();

			CalendarResource[] array = new CalendarResourceImpl[3];

			array[0] = filterGetByG_N_A_PrevAndNext(session, calendarResource,
					groupId, name, active, orderByComparator, true);

			array[1] = calendarResource;

			array[2] = filterGetByG_N_A_PrevAndNext(session, calendarResource,
					groupId, name, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarResource filterGetByG_N_A_PrevAndNext(Session session,
		CalendarResource calendarResource, long groupId, String name,
		boolean active, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_N_A_GROUPID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_A_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_N_A_NAME_2);
			}
		}

		query.append(_FINDER_COLUMN_G_N_A_ACTIVE_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CalendarResource.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, CalendarResourceImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, CalendarResourceImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (name != null) {
			qPos.add(name);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(calendarResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the calendar resources that the user has permission to view where groupId = any &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param name the name
	 * @param active the active
	 * @return the matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_N_A(long[] groupIds,
		String name, boolean active) throws SystemException {
		return filterFindByG_N_A(groupIds, name, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources that the user has permission to view where groupId = any &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_N_A(long[] groupIds,
		String name, boolean active, int start, int end)
		throws SystemException {
		return filterFindByG_N_A(groupIds, name, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources that the user has permission to view where groupId = any &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupIds the group IDs
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> filterFindByG_N_A(long[] groupIds,
		String name, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupIds)) {
			return findByG_N_A(groupIds, name, active, start, end,
				orderByComparator);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean conjunctionable = false;

		if ((groupIds == null) || (groupIds.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < groupIds.length; i++) {
				query.append(_FINDER_COLUMN_G_N_A_GROUPID_5);

				if ((i + 1) < groupIds.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_A_NAME_4);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_6);
			}
			else {
				query.append(_FINDER_COLUMN_G_N_A_NAME_5);
			}
		}

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_N_A_ACTIVE_5);

		conjunctionable = true;

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CalendarResource.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupIds);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, CalendarResourceImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, CalendarResourceImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (groupIds != null) {
				qPos.add(groupIds);
			}

			if (name != null) {
				qPos.add(name);
			}

			qPos.add(active);

			return (List<CalendarResource>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the calendar resources where companyId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @return the matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByC_N_A(long companyId, String name,
		boolean active) throws SystemException {
		return findByC_N_A(companyId, name, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources where companyId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByC_N_A(long companyId, String name,
		boolean active, int start, int end) throws SystemException {
		return findByC_N_A(companyId, name, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources where companyId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findByC_N_A(long companyId, String name,
		boolean active, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, name, active,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<CalendarResource> list = (List<CalendarResource>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_N_A,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_A_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_A_NAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(active);

				list = (List<CalendarResource>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_N_A,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_N_A,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar resource in the ordered set where companyId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByC_N_A_First(long companyId, String name,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		List<CalendarResource> list = findByC_N_A(companyId, name, active, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar resource in the ordered set where companyId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a matching calendar resource could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource findByC_N_A_Last(long companyId, String name,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		int count = countByC_N_A(companyId, name, active);

		List<CalendarResource> list = findByC_N_A(companyId, name, active,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchResourceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendar resources before and after the current calendar resource in the ordered set where companyId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the primary key of the current calendar resource
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar resource
	 * @throws com.liferay.calendar.NoSuchResourceException if a calendar resource with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarResource[] findByC_N_A_PrevAndNext(long calendarResourceId,
		long companyId, String name, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = findByPrimaryKey(calendarResourceId);

		Session session = null;

		try {
			session = openSession();

			CalendarResource[] array = new CalendarResourceImpl[3];

			array[0] = getByC_N_A_PrevAndNext(session, calendarResource,
					companyId, name, active, orderByComparator, true);

			array[1] = calendarResource;

			array[2] = getByC_N_A_PrevAndNext(session, calendarResource,
					companyId, name, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarResource getByC_N_A_PrevAndNext(Session session,
		CalendarResource calendarResource, long companyId, String name,
		boolean active, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARRESOURCE_WHERE);

		query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_A_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_N_A_NAME_2);
			}
		}

		query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(CalendarResourceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (name != null) {
			qPos.add(name);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(calendarResource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarResource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the calendar resources.
	 *
	 * @return the calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar resources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @return the range of calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar resources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar resources
	 * @param end the upper bound of the range of calendar resources (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarResource> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<CalendarResource> list = (List<CalendarResource>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CALENDARRESOURCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDARRESOURCE.concat(CalendarResourceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CalendarResource>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CalendarResource>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the calendar resources where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (CalendarResource calendarResource : findByUuid(uuid)) {
			calendarResourcePersistence.remove(calendarResource);
		}
	}

	/**
	 * Removes the calendar resource where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = findByUUID_G(uuid, groupId);

		calendarResourcePersistence.remove(calendarResource);
	}

	/**
	 * Removes all the calendar resources where active = &#63; from the database.
	 *
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByActive(boolean active) throws SystemException {
		for (CalendarResource calendarResource : findByActive(active)) {
			calendarResourcePersistence.remove(calendarResource);
		}
	}

	/**
	 * Removes all the calendar resources where groupId = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_A(long groupId, boolean active)
		throws SystemException {
		for (CalendarResource calendarResource : findByG_A(groupId, active)) {
			calendarResourcePersistence.remove(calendarResource);
		}
	}

	/**
	 * Removes the calendar resource where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_C(long classNameId, long classPK)
		throws NoSuchResourceException, SystemException {
		CalendarResource calendarResource = findByC_C(classNameId, classPK);

		calendarResourcePersistence.remove(calendarResource);
	}

	/**
	 * Removes all the calendar resources where groupId = &#63; and name LIKE &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_N_A(long groupId, String name, boolean active)
		throws SystemException {
		for (CalendarResource calendarResource : findByG_N_A(groupId, name,
				active)) {
			calendarResourcePersistence.remove(calendarResource);
		}
	}

	/**
	 * Removes all the calendar resources where companyId = &#63; and name LIKE &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_N_A(long companyId, String name, boolean active)
		throws SystemException {
		for (CalendarResource calendarResource : findByC_N_A(companyId, name,
				active)) {
			calendarResourcePersistence.remove(calendarResource);
		}
	}

	/**
	 * Removes all the calendar resources from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CalendarResource calendarResource : findAll()) {
			calendarResourcePersistence.remove(calendarResource);
		}
	}

	/**
	 * Returns the number of calendar resources where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARRESOURCE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar resources where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARRESOURCE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar resources where active = &#63;.
	 *
	 * @param active the active
	 * @return the number of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countByActive(boolean active) throws SystemException {
		Object[] finderArgs = new Object[] { active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACTIVE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_ACTIVE_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACTIVE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar resources where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_A(long groupId, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_A, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar resources that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_A(long groupId, boolean active)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A(groupId, active);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_CALENDARRESOURCE_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CalendarResource.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(active);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of calendar resources where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_C(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar resources where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @return the number of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_N_A(long groupId, String name, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, name, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_N_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_G_N_A_GROUPID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_N_A_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_N_A_NAME_2);
				}
			}

			query.append(_FINDER_COLUMN_G_N_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_N_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar resources where groupId = any &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param name the name
	 * @param active the active
	 * @return the number of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_N_A(long[] groupIds, String name, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(groupIds), name, active
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_N_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_CALENDARRESOURCE_WHERE);

			boolean conjunctionable = false;

			if ((groupIds == null) || (groupIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < groupIds.length; i++) {
					query.append(_FINDER_COLUMN_G_N_A_GROUPID_5);

					if ((i + 1) < groupIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_4);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_N_A_NAME_6);
				}
				else {
					query.append(_FINDER_COLUMN_G_N_A_NAME_5);
				}
			}

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_N_A_ACTIVE_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (groupIds != null) {
					qPos.add(groupIds);
				}

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_N_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar resources that the user has permission to view where groupId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param active the active
	 * @return the number of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_N_A(long groupId, String name, boolean active)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_N_A(groupId, name, active);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_CALENDARRESOURCE_WHERE);

		query.append(_FINDER_COLUMN_G_N_A_GROUPID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_A_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_G_N_A_NAME_2);
			}
		}

		query.append(_FINDER_COLUMN_G_N_A_ACTIVE_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CalendarResource.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (name != null) {
				qPos.add(name);
			}

			qPos.add(active);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of calendar resources that the user has permission to view where groupId = any &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param groupIds the group IDs
	 * @param name the name
	 * @param active the active
	 * @return the number of matching calendar resources that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_N_A(long[] groupIds, String name, boolean active)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupIds)) {
			return countByG_N_A(groupIds, name, active);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_CALENDARRESOURCE_WHERE);

		boolean conjunctionable = false;

		if ((groupIds == null) || (groupIds.length > 0)) {
			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < groupIds.length; i++) {
				query.append(_FINDER_COLUMN_G_N_A_GROUPID_5);

				if ((i + 1) < groupIds.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			conjunctionable = true;
		}

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		if (name == null) {
			query.append(_FINDER_COLUMN_G_N_A_NAME_4);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_N_A_NAME_6);
			}
			else {
				query.append(_FINDER_COLUMN_G_N_A_NAME_5);
			}
		}

		conjunctionable = true;

		if (conjunctionable) {
			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_N_A_ACTIVE_5);

		conjunctionable = true;

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				CalendarResource.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupIds);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (groupIds != null) {
				qPos.add(groupIds);
			}

			if (name != null) {
				qPos.add(name);
			}

			qPos.add(active);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of calendar resources where companyId = &#63; and name LIKE &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @return the number of matching calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_N_A(long companyId, String name, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_N_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CALENDARRESOURCE_WHERE);

			query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_A_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_A_NAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar resources.
	 *
	 * @return the number of calendar resources
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDARRESOURCE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the calendar resource persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.calendar.model.CalendarResource")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CalendarResource>> listenersList = new ArrayList<ModelListener<CalendarResource>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CalendarResource>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CalendarResourceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = CalendarBookingPersistence.class)
	protected CalendarBookingPersistence calendarBookingPersistence;
	@BeanReference(type = CalendarEventPersistence.class)
	protected CalendarEventPersistence calendarEventPersistence;
	@BeanReference(type = CalendarResourcePersistence.class)
	protected CalendarResourcePersistence calendarResourcePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	private static final String _SQL_SELECT_CALENDARRESOURCE = "SELECT calendarResource FROM CalendarResource calendarResource";
	private static final String _SQL_SELECT_CALENDARRESOURCE_WHERE = "SELECT calendarResource FROM CalendarResource calendarResource WHERE ";
	private static final String _SQL_COUNT_CALENDARRESOURCE = "SELECT COUNT(calendarResource) FROM CalendarResource calendarResource";
	private static final String _SQL_COUNT_CALENDARRESOURCE_WHERE = "SELECT COUNT(calendarResource) FROM CalendarResource calendarResource WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "calendarResource.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "calendarResource.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(calendarResource.uuid IS NULL OR calendarResource.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "calendarResource.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "calendarResource.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(calendarResource.uuid IS NULL OR calendarResource.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "calendarResource.groupId = ?";
	private static final String _FINDER_COLUMN_ACTIVE_ACTIVE_2 = "calendarResource.active = ?";
	private static final String _FINDER_COLUMN_G_A_GROUPID_2 = "calendarResource.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_ACTIVE_2 = "calendarResource.active = ?";
	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "calendarResource.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "calendarResource.classPK = ?";
	private static final String _FINDER_COLUMN_G_N_A_GROUPID_2 = "calendarResource.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_N_A_GROUPID_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_N_A_GROUPID_2) + ")";
	private static final String _FINDER_COLUMN_G_N_A_NAME_1 = "calendarResource.name LIKE NULL AND ";
	private static final String _FINDER_COLUMN_G_N_A_NAME_2 = "calendarResource.name LIKE ? AND ";
	private static final String _FINDER_COLUMN_G_N_A_NAME_3 = "(calendarResource.name IS NULL OR calendarResource.name LIKE ?) AND ";
	private static final String _FINDER_COLUMN_G_N_A_NAME_4 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_N_A_NAME_1) + ")";
	private static final String _FINDER_COLUMN_G_N_A_NAME_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_N_A_NAME_2) + ")";
	private static final String _FINDER_COLUMN_G_N_A_NAME_6 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_N_A_NAME_3) + ")";
	private static final String _FINDER_COLUMN_G_N_A_ACTIVE_2 = "calendarResource.active = ?";
	private static final String _FINDER_COLUMN_G_N_A_ACTIVE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_G_N_A_ACTIVE_2) + ")";
	private static final String _FINDER_COLUMN_C_N_A_COMPANYID_2 = "calendarResource.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_1 = "calendarResource.name LIKE NULL AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_2 = "calendarResource.name LIKE ? AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_3 = "(calendarResource.name IS NULL OR calendarResource.name LIKE ?) AND ";
	private static final String _FINDER_COLUMN_C_N_A_ACTIVE_2 = "calendarResource.active = ?";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _FILTER_SQL_SELECT_CALENDARRESOURCE_WHERE = "SELECT DISTINCT {calendarResource.*} FROM CalendarResource calendarResource WHERE ";
	private static final String _FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {CalendarResource.*} FROM (SELECT DISTINCT calendarResource.calendarResourceId FROM CalendarResource calendarResource WHERE ";
	private static final String _FILTER_SQL_SELECT_CALENDARRESOURCE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN CalendarResource ON TEMP_TABLE.calendarResourceId = CalendarResource.calendarResourceId";
	private static final String _FILTER_SQL_COUNT_CALENDARRESOURCE_WHERE = "SELECT COUNT(DISTINCT calendarResource.calendarResourceId) AS COUNT_VALUE FROM CalendarResource calendarResource WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "calendarResource";
	private static final String _FILTER_ENTITY_TABLE = "CalendarResource";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "calendarResource.calendarResourceId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calendarResource.";
	private static final String _ORDER_BY_ENTITY_TABLE = "CalendarResource.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CalendarResource exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CalendarResource exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CalendarResourcePersistenceImpl.class);
	private static CalendarResource _nullCalendarResource = new CalendarResourceImpl() {
			public Object clone() {
				return this;
			}
		};
}