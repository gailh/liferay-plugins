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

package com.liferay.knowledgebase.util.comparator;

import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBTemplateEngineTypeComparator extends OrderByComparator {

	public static String ORDER_BY_ASC = "engineType ASC";

	public static String ORDER_BY_DESC = "engineType DESC";

	public static String[] ORDER_BY_FIELDS = {"engineType"};

	public KBTemplateEngineTypeComparator() {
		this(false);
	}

	public KBTemplateEngineTypeComparator(boolean ascending) {
		_ascending = ascending;
	}

	public int compare(Object obj1, Object obj2) {
		KBTemplate kbTemplate1 = (KBTemplate)obj1;
		KBTemplate kbTemplate2 = (KBTemplate)obj2;

		int value = 0;

		if (kbTemplate1.getEngineType() < kbTemplate2.getEngineType()) {
			value = -1;
		}
		else if (kbTemplate1.getEngineType() > kbTemplate2.getEngineType()) {
			value = 1;
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}