package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Area;
/**
 * 
 * @author laolu
 *
 */
public interface AreaService {
	public Area findArea(Integer areaid);
	
	public List<Area> findAreas(Integer[] areaids);
	
	public List<Area> findAreas(String stype);
	
	public List<Area> findAreasCountry();
	
	public List<Area> findAreasProvince();
	
	public List<Area> findAreasCity();
	
	public List<Area> findAllArea();
	
	public Integer saveArea(Area area);
	
	public void updateArea(Area area);
	
	public void deleteArea(Area area);

}
