package com.weiweb.admin.movie.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weiweb.admin.movie.dao.MovieMapper;
import com.weiweb.admin.movie.domain.Movie;
import com.weiweb.core.mybatis.BaseMybatisDao;
import com.weiweb.core.mybatis.page.Pagination;




@Service
public class MovieServiceImpl extends BaseMybatisDao<MovieMapper> implements MovieService {
	@Autowired
	private MovieMapper movieMapper;
	
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return movieMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(Movie record) {
		return movieMapper.insert(record);
	}
	
	@Override
	public int insertSelective(Movie record) {
		return movieMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKey(Movie record) {
		return movieMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Movie record) {
		return movieMapper.updateByPrimaryKeySelective(record);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<Movie> findPage(Map<String, Object> resultMap,
			Integer pageNo, Integer pageSize) {
		return (Pagination<Movie>)super.findPage(resultMap, pageNo, pageSize);
	}
	
	@Override
	@Transactional
	public boolean deleteMovieByIds(String[] arryids){
		for(String id:arryids){
			 movieMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		return true;
	}

	@Override
	public Movie selectByPrimaryKey(String id) {
		return movieMapper.selectByPrimaryKey(Long.parseLong(id));
	}
	
}
