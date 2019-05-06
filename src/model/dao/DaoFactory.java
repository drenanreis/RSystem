package model.dao;

import db.DB;
import model.dao.impl.SrqNfceDaoJDBC;

public class DaoFactory {
	
	public static SrqNfceDao creatSrqNfceDao() {
		return new SrqNfceDaoJDBC(DB.getConnection());
	}
}