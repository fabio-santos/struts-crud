package com.soc.exames.teste;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import com.soc.exames.util.DatabaseUtil;

public class teste {
	
	public static void main(String[] args) throws Exception {
		Connection connection = DatabaseUtil.getConnection();
		
		try {
			String sql = "UPDATE EXAME SET "
					+ "NOMEPESSOA = ?,"
					+ "MEDICO = ?,"
					+ "TIPOEXAME = ?,"
					+ "UPDATEDAT = ? "
					+ "WHERE ID=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "a");
			ps.setString(2, "b");
			ps.setString(3, "c");
			ps.setDate(4, new Date(System.currentTimeMillis()));
			ps.setLong(5, 1);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
         
        System.out.println("amigou estou aqui");
	}
	
	
	
}
