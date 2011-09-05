package com.vshershnev.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	public 	void getFile(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
