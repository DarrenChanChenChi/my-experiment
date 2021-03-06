/**
 * (c) Copyright 2009 TeleNav.
 *  All Rights Reserved.
 */
package com.telenav.cserver.backend.addresssharing;

import java.util.List;

import com.telenav.cserver.backend.datatypes.addresssharing.SharedAddressItem;

/**
 * The response aid to get trigger action for FetchSharedAddress pin.
 * @author pzhang
 * 2010-08-03
 */
public class FetchSharedAddressResponse
{
    private String statusCode;
    private String statusMessage;
    
    private List<SharedAddressItem> sharedAddressList;

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ResendPinResponse=[");
        sb.append(", statusCode=").append(this.statusCode);
        sb.append(", statusMessage=").append(this.statusMessage);
        sb.append("]");
        return sb.toString();
    }

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public List<SharedAddressItem> getSharedAddressList() {
		return sharedAddressList;
	}

	public void setSharedAddressList(List<SharedAddressItem> sharedAddressList) {
		this.sharedAddressList = sharedAddressList;
	}


}
