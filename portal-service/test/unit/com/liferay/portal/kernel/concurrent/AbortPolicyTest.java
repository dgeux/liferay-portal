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

package com.liferay.portal.kernel.concurrent;

import com.liferay.portal.kernel.test.TestCase;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuyang Zhou
 */
public class AbortPolicyTest extends TestCase {

	public void testAbortPolicy() {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 2, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 3,
			new AbortPolicy(), Executors.defaultThreadFactory(),
			new ThreadPoolHandlerAdapter());

		threadPoolExecutor.shutdown();

		try {
			threadPoolExecutor.execute(new MarkerBlockingJob());

			fail();
		}
		catch (RejectedExecutionException ree) {
		}
	}

}