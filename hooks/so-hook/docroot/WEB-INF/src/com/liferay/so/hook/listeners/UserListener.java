/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Jonathan Lee
 */
public class UserListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (!associationClassName.equals(Role.class.getName())) {
				return;
			}

			Role role = RoleLocalServiceUtil.getRole((Long)associationClassPK);

			if (role.getName().equals(RoleConstants.POWER_USER)) {
				updateUserPublicLayoutSet((Long)classPK);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void updateUserPublicLayoutSet(long userId) throws Exception {
		User user = UserLocalServiceUtil.getUser(userId);

		Group group = user.getGroup();

		LayoutSetLocalServiceUtil.deleteLayoutSet(group.getGroupId(), false);
		LayoutSetLocalServiceUtil.deleteLayoutSet(group.getGroupId(), true);

		LayoutSetLocalServiceUtil.addLayoutSet(group.getGroupId(), false);
		LayoutSetLocalServiceUtil.addLayoutSet(group.getGroupId(), true);
	}

}