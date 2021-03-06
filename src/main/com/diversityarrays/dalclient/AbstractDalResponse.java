/*
 * dalclient library - provides utilities to assist in using KDDart-DAL servers
 * Copyright (C) 2015,2016,2017 Diversity Arrays Technology
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.diversityarrays.dalclient;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Provide the rudiments of a DalResponse.
 * Concrete sub-classes must provide the parsing behaviour depending
 * on the type of response data.
 * @author brian
 */
public abstract class AbstractDalResponse implements DalResponse {
	
	static public boolean SHOW_TIMING = Boolean.getBoolean(AbstractDalResponse.class.getName()+".TIMING"); //$NON-NLS-1$

	private final String url;
	private final HttpResponseInfo responseInfo;
	
	private boolean wantEmptyRecords;

	public AbstractDalResponse(String url, HttpResponseInfo responseInfo) {
		this.url = url;
		this.responseInfo = responseInfo;
	}
	
	@Override
	public void setWantEmptyRecords(boolean b) {
	    wantEmptyRecords = b;
	}
	
	@Override
    public boolean getWantEmptyRecords() {
	    return wantEmptyRecords;
	}
	
	@Override
	public boolean getResponseIsDTD() {
		return DalUtil.looksLikeDoctype(getRawResponse());
	}
	
	@Override
	public HttpResponseInfo getHttpResponseInfo() {
		return responseInfo;
	}
	
	@Override
	public String getUrl() {
		return url;
	}
	
	@Override
	public String getRawResponse() {
		return responseInfo.serverResponse;
	}

	@Override
	public void printOn(PrintStream out) {
		out.println(getRawResponse());
	}
	
	@Override
	public void printOn(PrintWriter pw) {
		pw.println(getRawResponse());
	}

}
