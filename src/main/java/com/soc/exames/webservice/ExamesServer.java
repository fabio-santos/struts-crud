package com.soc.exames.webservice;

import java.util.List;

import javax.jws.WebService;
import javax.jws.WebMethod;

import com.soc.exames.domain.Exame;

@WebService
public interface ExamesServer {
	
	@WebMethod
	public List<Exame> getExamesList();
	
	@WebMethod
	public Exame getExame(long id);
}

