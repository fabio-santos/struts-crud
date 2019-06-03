package com.soc.exames.webservice;

import javax.xml.ws.Endpoint;

import com.soc.exames.webservice.ExamesServerImpl;

public class ExamesPlublisher {
	public static void main(String[] args) throws Exception {
		Endpoint.publish("http://localhost:9876/exames", new ExamesServerImpl());
	}
}