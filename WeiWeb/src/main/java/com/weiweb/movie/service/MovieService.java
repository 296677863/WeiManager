package com.weiweb.movie.service;

import java.util.Map;

import com.weiweb.core.mybatis.page.Pagination;
import com.weiweb.movie.domain.Movie;


/**
 * 
 * 
 * @author zed
 * @email 296677863@qq.com
 * @date 2019-08-13
 */
public interface MovieService {

    Pagination<Movie> findPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize);
	
	
	int deleteByPrimaryKey(Long id);
	
	
	int insert(Movie record);
	
	int insertSelective(Movie record);
		
	Movie selectByPrimaryKey(String id);
	
	
	int updateByPrimaryKeySelective(Movie  record);

    int updateByPrimaryKey(Movie  record);
	
	boolean deleteMovieByIds(String[] arryids);
	
	 
	
	 
}
