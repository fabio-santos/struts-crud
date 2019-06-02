package com.soc.exames.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.soc.exames.dao.ExameDao;
import com.soc.exames.domain.Exame;

public class ExameAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	List<Exame> examesList = new ArrayList<Exame>();
	private ExameDao dao = null;
	private Exame exame = new Exame();
	
	public ExameAction() throws Exception
	{
		dao = new ExameDao();
	}
	
	public Exame getModel()
	{
		return exame;
	}
	
	public String list() throws SQLException, Exception
	{
		examesList = dao.findAllExames();
		return SUCCESS;
	}
	
	public String saveExame() throws SQLException, Exception
	{
		dao.saveExame(exame);
		return SUCCESS;
	}
	
	public String updateExame() throws SQLException, Exception
	{
		dao.updateExame(exame);
		return SUCCESS;
	}
	
	public String deleteExame() throws NumberFormatException, SQLException, Exception
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		dao.deleteExame(Long.parseLong( request.getParameter("id")));
		return SUCCESS;
	}
	
	public String editExame() throws NumberFormatException, SQLException, Exception
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		exame = dao.findExameById(Long.parseLong( request.getParameter("id")));
		return SUCCESS;
	}
	
	public Exame getExame() 
	{
		return exame;
	}
	
	public void setExame(Exame exame)
	{
		this.exame = exame;
	}

	public List<Exame> getExamesList() 
	{
		return examesList;
	}

}