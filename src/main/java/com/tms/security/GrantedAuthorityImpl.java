package com.tms.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

	public GrantedAuthority grantedAuthority;
	
	public GrantedAuthorityImpl(){}
	
	public GrantedAuthorityImpl(GrantedAuthority grandAuthority){
		this.grantedAuthority = grandAuthority;
	}
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}
