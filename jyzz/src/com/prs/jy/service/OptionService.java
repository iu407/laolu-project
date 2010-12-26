package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Option;
/**
 * 
 * @author laolu
 *
 */
public interface OptionService {
	public Option findOption(Integer optionid);
	
	public List<Option> findOptions(Integer[] optionids);
	
	public List<Option> findAllOption();
	
	public Integer saveOption(Option option);
	
	public void updateOption(Option option);
	
	public void deleteOption(Option option);

}
