package model.service;

import model.dao.DaoFactory;
import model.dao.SrqNfceDao;
import model.entities.SrqNfce;

public class SrqNfceService {
	
	private SrqNfceDao dao = DaoFactory.creatSrqNfceDao();
	
	public SrqNfce findByNumeroAndSerie(SrqNfce obj) {
		return dao.findByNumeroAndSerie(obj.getNumero(), obj.getSerie());
	}
}