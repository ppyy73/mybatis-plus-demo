package com.example.demo.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;

public interface UserMapper extends BaseMapper<User> {

	@Select("select * from user where id = #{id}")
	User selectById(int id);
}
