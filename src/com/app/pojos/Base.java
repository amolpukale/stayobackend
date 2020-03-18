package com.app.pojos;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class Base
{
	@Version
	private Integer version;
}
