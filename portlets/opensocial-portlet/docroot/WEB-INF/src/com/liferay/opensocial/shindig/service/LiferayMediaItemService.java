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

package com.liferay.opensocial.shindig.service;

import com.liferay.opensocial.util.SerializerUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.core.model.AddressImpl;
import org.apache.shindig.social.core.model.MediaItemImpl;
import org.apache.shindig.social.opensocial.model.Address;
import org.apache.shindig.social.opensocial.model.MediaItem.Type;
import org.apache.shindig.social.opensocial.model.MediaItem;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.MediaItemService;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 * @author Dennis Ju
 */
public class LiferayMediaItemService implements MediaItemService {

	public Future<Void> createMediaItem(
			UserId userId, String appId, String albumId, MediaItem mediaItem,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			doCreateMediaItem(
				userId, appId, albumId, mediaItem, securityToken);

			return ImmediateFuture.newInstance(null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e,e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<Void> deleteMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			doDeleteMediaItem(
				userId, appId, albumId, mediaItemId, securityToken);

			return ImmediateFuture.newInstance(null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<MediaItem> getMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			Set<String> fields, SecurityToken securityToken)
		throws ProtocolException {

		try {
			MediaItem mediaItem = doGetMediaItem(
				userId, appId, albumId, mediaItemId, fields, securityToken);

			return ImmediateFuture.newInstance(mediaItem);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<RestfulCollection<MediaItem>> getMediaItems(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionOptions,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<MediaItem> mediaItems = doGetMediaItems(
				userIds, groupId, appId, fields, collectionOptions,
				securityToken);

			return ImmediateFuture.newInstance(mediaItems);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<RestfulCollection<MediaItem>> getMediaItems(
			UserId userId, String appId, String albumId, Set<String> fields,
			CollectionOptions collectionOptions, SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<MediaItem> mediaItems = doGetMediaItems(
				userId, appId, albumId, fields, collectionOptions,
				securityToken);

			return ImmediateFuture.newInstance(mediaItems);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<RestfulCollection<MediaItem>> getMediaItems(
			UserId userId, String appId, String albumId,
			Set<String> mediaItemIds, Set<String> fields,
			CollectionOptions collectionOptions, SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<MediaItem> mediaItems = doGetMediaItems(
				userId, appId, albumId, mediaItemIds, fields, collectionOptions,
				securityToken);

			return ImmediateFuture.newInstance(mediaItems);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<Void> updateMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			MediaItem mediaItem, SecurityToken securityToken)
		throws ProtocolException {

		try {
			doUpdateMediaItem(
				userId, appId, albumId, mediaItemId, mediaItem, securityToken);

			return ImmediateFuture.newInstance(null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e,e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	protected void doCreateMediaItem(
			UserId userId, String appId, String albumId, MediaItem mediaItem,
			SecurityToken securityToken)
		throws Exception {

		doUpdateMediaItem(
			userId, appId, albumId, null, mediaItem, securityToken);
	}

	protected void doDeleteMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			SecurityToken securityToken)
		throws Exception {

		long mediaItemIdLong = GetterUtil.getLong(mediaItemId);

		DLAppLocalServiceUtil.deleteFileEntry(mediaItemIdLong);
	}

	protected MediaItem doGetMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			Set<String> fields, SecurityToken securityToken)
		throws Exception {

		long mediaItemIdLong = GetterUtil.getLong(mediaItemId);

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			mediaItemIdLong);

		return getMediaItem(fileEntry, fields, securityToken);
	}

	protected RestfulCollection<MediaItem> doGetMediaItems(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionOptions,
			SecurityToken securityToken)
		throws Exception {

		List<MediaItem> mediaItems = new ArrayList<MediaItem>();

		for (UserId userId : userIds) {
			long userIdLong = GetterUtil.getLong(
				userId.getUserId(securityToken));

			User user = UserLocalServiceUtil.getUserById(userIdLong);

			List<FileEntry> fileEntries = new ArrayList<FileEntry>();

			GroupId.Type groupIdType = groupId.getType();

			if (groupIdType.equals(GroupId.Type.all) ||
				groupIdType.equals(GroupId.Type.friends) ||
				groupIdType.equals(GroupId.Type.groupId)) {

				List<User> socialUsers = UserLocalServiceUtil.getSocialUsers(
					user.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				for (User socialUser : socialUsers) {
					Group group = socialUser.getGroup();

					List<FileEntry> friendFileEntries =
						DLAppLocalServiceUtil.getGroupFileEntries(
							group.getGroupId(), collectionOptions.getFirst(),
							collectionOptions.getMax());

					fileEntries.addAll(friendFileEntries);
				}
			}
			else if (groupIdType.equals(GroupId.Type.self)) {
				Group group = user.getGroup();

				fileEntries = DLAppLocalServiceUtil.getGroupFileEntries(
					group.getGroupId(), collectionOptions.getFirst(),
					collectionOptions.getMax());
			}

			for (FileEntry fileEntry : fileEntries) {
				MediaItem.Type mediaItemType = getMediaItemType(fileEntry);

				if (mediaItemType == null) {
					continue;
				}

				MediaItem mediaItem = getMediaItem(
					fileEntry, fields, securityToken);

				mediaItems.add(mediaItem);
			}
		}

		return new RestfulCollection<MediaItem>(
			mediaItems, collectionOptions.getFirst(), mediaItems.size(),
			collectionOptions.getMax());
	}

	protected RestfulCollection<MediaItem> doGetMediaItems(
			UserId userId, String appId, String albumId, Set<String> fields,
			CollectionOptions collectionOptions, SecurityToken securityToken)
		throws Exception {

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		User user = UserLocalServiceUtil.getUserById(userIdLong);

		Group group = user.getGroup();

		long groupIdLong = group.getGroupId();

		long albumIdLong = GetterUtil.getLong(albumId);

		List<FileEntry> fileEntries = DLAppLocalServiceUtil.getFileEntries(
			groupIdLong, albumIdLong);

		List<MediaItem> mediaItems = new ArrayList<MediaItem>();

		for (FileEntry fileEntry : fileEntries) {
			MediaItem.Type mediaItemType = getMediaItemType(fileEntry);

			if (mediaItemType == null) {
				continue;
			}

			MediaItem mediaItem = getMediaItem(
				fileEntry, fields, securityToken);

			mediaItems.add(mediaItem);
		}

		return new RestfulCollection<MediaItem>(
			mediaItems, collectionOptions.getFirst(), mediaItems.size(),
			collectionOptions.getMax());
	}

	protected RestfulCollection<MediaItem> doGetMediaItems(
			UserId userId, String appId, String albumId,
			Set<String> mediaItemIds, Set<String> fields,
			CollectionOptions collectionOptions, SecurityToken securityToken)
		throws Exception {

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		User user = UserLocalServiceUtil.getUserById(userIdLong);

		Group group = user.getGroup();

		long groupIdLong = group.getGroupId();

		long albumIdLong = GetterUtil.getLong(albumId);

		List<FileEntry> fileEntries = DLAppLocalServiceUtil.getFileEntries(
			groupIdLong, albumIdLong);

		List<MediaItem> mediaItems = new ArrayList<MediaItem>();

		for (FileEntry fileEntry : fileEntries) {
			MediaItem.Type mediaItemType = getMediaItemType(fileEntry);

			if (mediaItemType == null) {
				continue;
			}

			MediaItem mediaItem = getMediaItem(
				fileEntry, fields, securityToken);

			if (mediaItemIds.contains(mediaItem.getId())) {
				mediaItems.add(mediaItem);
			}
		}

		return new RestfulCollection<MediaItem>(
			mediaItems, collectionOptions.getFirst(), mediaItems.size(),
			collectionOptions.getMax());
	}

	protected void doUpdateMediaItem(
			UserId userId, String appId, String albumId, String mediaItemId,
			MediaItem mediaItem, SecurityToken securityToken)
		throws Exception {

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		User user = UserLocalServiceUtil.getUserById(userIdLong);

		Group group = user.getGroup();

		long groupIdLong = group.getGroupId();

		Http.Options options = new Http.Options();

		options.setLocation(mediaItem.getUrl());

		byte[] byteArray = HttpUtil.URLtoByteArray(options);

		String fileName = getFileName(mediaItem, options);

		if (mediaItemId == null) {
			long albumIdLong = GetterUtil.getLong(albumId);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddCommunityPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setScopeGroupId(groupIdLong);

			DLAppLocalServiceUtil.addFileEntry(
				userIdLong, groupIdLong, albumIdLong, fileName,
				mediaItem.getDescription(), StringPool.BLANK, byteArray,
				serviceContext);
		}
		else {
			long mediaItemIdLong = GetterUtil.getLong(mediaItemId);

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				mediaItemIdLong);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddCommunityPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			serviceContext.setCreateDate(fileEntry.getCreateDate());
			serviceContext.setModifiedDate(fileEntry.getModifiedDate());
			serviceContext.setScopeGroupId(groupIdLong);

			DLAppLocalServiceUtil.updateFileEntry(
				userIdLong, fileEntry.getFileEntryId(), fileName,
				mediaItem.getTitle(), mediaItem.getDescription(),
				StringPool.BLANK, false, byteArray, serviceContext);
		}
	}

	protected Address getAddress(JSONObject jsonObject) {
		Address address = new AddressImpl();

		SerializerUtil.copyProperties(jsonObject, address, _ADDRESS_FIELDS);

		return address;
	}

	protected String getFileEntryURL(
		FileEntry fileEntry, SecurityToken securityToken) {

		StringBuilder sb = new StringBuilder(6);

		sb.append(securityToken.getDomain());
		sb.append(PortalUtil.getPathContext());
		sb.append("/documents/");
		sb.append(fileEntry.getRepositoryId());
		sb.append(StringPool.SLASH);
		sb.append(fileEntry.getUuid());

		return sb.toString();
	}

	protected String getFileName(MediaItem mediaItem, Http.Options options) {
		Http.Response response = options.getResponse();

		String contentDisposition = response.getHeader(
			HttpHeaders.CONTENT_DISPOSITION);

		if (contentDisposition == null) {
			return FileUtil.getShortFileName(mediaItem.getUrl());
		}

		Matcher fileNameMatcher = _fileNamePattern.matcher(contentDisposition);

		if (fileNameMatcher.find()) {
			return fileNameMatcher.group(1);
		}
		else {
			return mediaItem.getTitle();
		}
	}

	protected JSONObject getLocation(Address address) {
		if (address == null) {
			return null;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		SerializerUtil.copyProperties(address, jsonObject, _ADDRESS_FIELDS);

		return jsonObject;
	}

	protected MediaItem getMediaItem(
			FileEntry fileEntry, Set<String> fields,
			SecurityToken securityToken)
		throws Exception {

		MediaItem mediaItem = new MediaItemImpl();

		mediaItem.setAlbumId(String.valueOf(fileEntry.getFolderId()));
		mediaItem.setCreated(String.valueOf(fileEntry.getCreateDate()));
		mediaItem.setDescription(fileEntry.getDescription());
		mediaItem.setId(String.valueOf(fileEntry.getFileEntryId()));
		mediaItem.setLastUpdated(String.valueOf(fileEntry.getModifiedDate()));
		mediaItem.setMimeType(
			MimeTypesUtil.getContentType(fileEntry.getExtension()));
		mediaItem.setNumViews(String.valueOf(fileEntry.getReadCount()));
		mediaItem.setTitle(fileEntry.getTitle());
		mediaItem.setType(getMediaItemType(fileEntry));
		mediaItem.setUrl(getFileEntryURL(fileEntry, securityToken));

		return mediaItem;
	}

	protected MediaItem.Type getMediaItemType(FileEntry fileEntry) {
		String contentType = MimeTypesUtil.getContentType(
			fileEntry.getExtension());

		if (contentType.startsWith("audio")) {
			return Type.AUDIO;
		}
		else if (contentType.startsWith("image")) {
			return Type.IMAGE;
		}
		else if (contentType.startsWith("video")) {
			return Type.VIDEO;
		}
		else {
			return null;
		}
	}

	private static final Address.Field[] _ADDRESS_FIELDS = {
		Address.Field.COUNTRY, Address.Field.FORMATTED,
		Address.Field.LATITUDE, Address.Field.LOCALITY,
		Address.Field.LONGITUDE, Address.Field.POSTAL_CODE,
		Address.Field.PRIMARY, Address.Field.REGION,
		Address.Field.STREET_ADDRESS, Address.Field.TYPE
	};

	private static final MediaItem.Field[] _MEDIA_ITEM_FIELDS = {
		MediaItem.Field.FILE_SIZE, MediaItem.Field.LANGUAGE,
		MediaItem.Field.NUM_COMMENTS, MediaItem.Field.NUM_VOTES,
		MediaItem.Field.RATING, MediaItem.Field.START_TIME,
		MediaItem.Field.TAGGED_PEOPLE, MediaItem.Field.TAGS,
		MediaItem.Field.THUMBNAIL_URL
	};

	private static Log _log = LogFactoryUtil.getLog(
		LiferayMediaItemService.class);

	private static Pattern _fileNamePattern = Pattern.compile(
		".*?filename=\"?([^\";]+)");

}