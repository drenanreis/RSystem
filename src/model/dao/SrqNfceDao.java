package model.dao;

import model.entities.SrqNfce;

public interface SrqNfceDao {
	
	SrqNfce findByNumeroAndSerie(Integer numero, Integer Serie);
}