package com.weiweb.movie.dao;

import com.weiweb.movie.domain.Movie;

/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2019-08-13
 */
public interface MovieMapper {

	int deleteByPrimaryKey(Long id);
	
	int insert(Movie record);
	 
	int insertSelective(Movie record); 
	
	Movie selectByPrimaryKey(Long id);
	
	int updateByPrimaryKeySelective(Movie record);

	int updateByPrimaryKey(Movie record);
	 
	
}
