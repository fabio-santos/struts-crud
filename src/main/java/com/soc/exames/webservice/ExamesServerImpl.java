package com.soc.exames.webservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import com.soc.exames.dao.ExameDao;
import com.soc.exames.domain.Exame;

@WebService(endpointInterface = "com.soc.exames.webservice.ExamesServer")
public class ExamesServerImpl implements ExamesServer {
	
	private ExameDao dao = null;
	
	ExamesServerImpl() throws Exception {
		dao = new ExameDao();
	}
	
	@Override
	public List<Exame> getExamesList() {
		try {
			return dao.findAllExames();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Exame>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Exame>();
		}
	}
	
	@Override
	public Exame getExame(long id) {
		try {
			return dao.findExameById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Exame();
		} catch (Exception e) {
			e.printStackTrace();
			return new Exame();
		}
	}
	
}
