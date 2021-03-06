package com.soc.exames.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.soc.exames.util.DatabaseUtil;
import com.soc.exames.domain.Exame;
import com.soc.exames.amqp.ExameAmqp;

/*
CREATE TABLE EXAME (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nomepessoa VARCHAR(200),
    medico VARCHAR(200),
    tipoexame VARCHAR(200),
    createdat datetime,
    updatedat datetime,
    isactive tinyint(1)
);
 */

public class ExameDao {
	
	private static ExameAmqp exameamqp = new ExameAmqp();
	private static ExameDao exameDao = null;
	private static Connection connection = null;
	
	public ExameDao() throws Exception {
		try {
		    connection = DatabaseUtil.getConnection();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	public Exame saveExame(Exame exame) throws SQLException, Exception {		
		try {
			String sql = "INSERT INTO EXAME "
					+ "(NOMEPESSOA, MEDICO, TIPOEXAME, CREATEDAT, UPDATEDAT, ISACTIVE) "
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, exame.getNomePessoa());
			ps.setString(2, exame.getMedico());
			ps.setString(3, exame.getTipoExame());
			ps.setDate(4, new Date(System.currentTimeMillis()));
			ps.setDate(5, new Date(System.currentTimeMillis()));
			ps.setBoolean(6, exame.isActive());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		exameamqp.newExame(exame);		
		return exame;
	}
	
	public Exame updateExame(Exame exame) throws SQLException, Exception
	{
		try {
			String sql = "UPDATE EXAME SET "
					+ "NOMEPESSOA = ?,"
					+ "MEDICO = ?,"
					+ "TIPOEXAME = ?,"
					+ "UPDATEDAT = ? "
					+ "WHERE ID=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, exame.getNomePessoa());
			ps.setString(2, exame.getMedico());
			ps.setString(3, exame.getTipoExame());
			ps.setDate(4, new Date(System.currentTimeMillis()));
			ps.setLong(5, exame.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		exameamqp.updateExame(exame);
		return exame;
	}
 
    public void deleteExame(Long id) throws SQLException, Exception {
    	try {
			String sql = "UPDATE EXAME SET ISACTIVE = 0 WHERE ID=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
 
    public Exame findExameById(Long id) throws SQLException, Exception  {
    	ResultSet rs = null;
    	Exame exame = new Exame();
		try {
			String sql = "SELECT ID, NOMEPESSOA, MEDICO, TIPOEXAME, CREATEDAT, UPDATEDAT, ISACTIVE FROM EXAME WHERE ID=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			if (rs != null) {
				rs.next();
				exame.setId(rs.getLong("id"));
				exame.setNomePessoa(rs.getString("nomepessoa"));
				exame.setMedico(rs.getString("medico"));
				exame.setTipoExame(rs.getString("tipoexame"));
				exame.setCreatedAt(rs.getDate("createdAt"));
				exame.setUpdatedAt(rs.getDate("updatedAt"));
				exame.setActive(rs.getBoolean("isactive"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
         
        return exame;
    }
 
	public List<Exame> findAllExames() throws SQLException, Exception {
        List<Exame> examesList = new ArrayList<Exame>();        
        
        ResultSet rs = null;
		try {
			String sql = "SELECT ID, NOMEPESSOA, MEDICO, TIPOEXAME, CREATEDAT, UPDATEDAT, ISACTIVE FROM EXAME WHERE ISACTIVE = 1";
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					Exame exame = new Exame();
					exame.setId(rs.getLong("id"));
					exame.setNomePessoa(rs.getString("nomepessoa"));
					exame.setMedico(rs.getString("medico"));
					exame.setTipoExame(rs.getString("tipoexame"));
					exame.setCreatedAt(rs.getDate("createdAt"));
					exame.setUpdatedAt(rs.getDate("updatedAt"));
					exame.setActive(rs.getBoolean("isactive"));
					examesList.add(exame);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return examesList;
    }
     
    public static ExameDao getInstance() throws Exception {
        if(exameDao == null)
        	exameDao = new ExameDao();
         
        return exameDao;
    }
	
}