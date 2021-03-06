/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.zip;

import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactory;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Raymond Augé
 */
public class ZipReaderFactoryImpl implements ZipReaderFactory {

	public ZipReader getZipReader(File file) {
		ClassLoader portalClassLoader =
			PACLClassLoaderUtil.getPortalClassLoader();

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(portalClassLoader);
			}

			return new ZipReaderImpl(file);
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public ZipReader getZipReader(InputStream inputStream) throws IOException {
		ClassLoader portalClassLoader =
			PACLClassLoaderUtil.getPortalClassLoader();

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(portalClassLoader);
			}

			return new ZipReaderImpl(inputStream);
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

}