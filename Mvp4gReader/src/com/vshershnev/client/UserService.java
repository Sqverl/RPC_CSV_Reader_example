package com.vshershnev.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath( "userService" )
public interface UserService extends RemoteService {

	public String getFile( String input ) throws IllegalArgumentException;;	

}
