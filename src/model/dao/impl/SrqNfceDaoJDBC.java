package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import model.dao.SrqNfceDao;
import model.entities.SrqNfce;

public class SrqNfceDaoJDBC implements SrqNfceDao {
	
	private Connection conn;
	
	public SrqNfceDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public SrqNfce findByNumeroAndSerie(Integer numero, Integer serie) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT SERIE_NFCE, CHAVE_NFCE, VPROD_NFCE FROM SRQNFCE WHERE CHAVE_NFCE LIKE ? AND SERIE_NFCE = ?");
			st.setString(1, String.valueOf("%" + numero + "%"));
			st.setInt(2, serie);
			rs = st.executeQuery();
			if (rs.next()) {
				SrqNfce obj = instantiatSrqNfce(rs);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private SrqNfce instantiatSrqNfce(ResultSet rs) throws SQLException {
		SrqNfce obj = new SrqNfce();
		new SrqNfce();
		obj.setSerie(rs.getInt("SERIE_NFCE"));
		obj.setChave(rs.getString("CHAVE_NFCE"));
		obj.setValor(rs.getDouble("VPROD_NFCE"));
		return obj;
	}
}