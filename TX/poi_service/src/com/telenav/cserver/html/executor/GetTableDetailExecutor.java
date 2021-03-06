/**
 * (c) Copyright 2012 TeleNav.
 * All Rights Reserved.
 */
package com.telenav.cserver.html.executor;

import com.telenav.cserver.framework.executor.AbstractExecutor;
import com.telenav.cserver.framework.executor.ExecutorContext;
import com.telenav.cserver.framework.executor.ExecutorException;
import com.telenav.cserver.framework.executor.ExecutorRequest;
import com.telenav.cserver.framework.executor.ExecutorResponse;
/**
 * @TODO	Call the executor to implement business logic
 * @author  
 * @version 1.0 
 */
public class GetTableDetailExecutor extends AbstractExecutor {

	@Override
	public void doExecute(ExecutorRequest req, ExecutorResponse resp,
			ExecutorContext context) throws ExecutorException {

		// Get the request and response
		GetTableDetailRequest rRequest = (GetTableDetailRequest) req;
		GetTableDetailResponse rResponse = (GetTableDetailResponse) resp;
		HtmlRestaurantProxy.getTableDetail(rRequest, rResponse);

	}

}
