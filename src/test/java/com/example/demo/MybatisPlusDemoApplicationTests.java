package com.example.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusDemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		List<User> list = userMapper.selectList(null);
		Assert.assertEquals(5, list.size());
//		list.forEach(System.out::println);
		for (User user : list) {
			System.out.println(user.getName() + " " + user.getId() + " " + user.getEmail() + " " + user.getAge());
		}
	}

	@Test
	public void insert() {
		for (int i = 6; i <= 10; i++) {
			User u = new User();
			u.setAge(23 + i);
			u.setEmail("124" + i + "@123.com");
			u.setId((long) 6 + i);
			u.setName("pp" + i);
			int w = userMapper.insert(u);
			System.out.println(w);
		}
	}

	@Test
	public void deleteById() {
		int i = userMapper.deleteById(6);
		System.out.println(i);
	}

	@Test
	public void deleteByMap() {
		Map<String, Object> map = new HashMap<>();
//		map.put("id", 6);
		map.put("age", 30);
		int i = userMapper.deleteByMap(map);
		System.out.println(i);
	}

	/**
	 * 删除用户id为12的用户
	 */
	@Test
	public void deleteByWrapper() {
		int i = userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getId, 12));
		System.out.println(i);
	}

	/**
	 * 根据 id 批量删除
	 */
	@Test
	public void deleteBatchIds() {
		Set<Integer> s = new HashSet<>();
		s.add(13);
		s.add(14);
		s.add(15);
		int i = userMapper.deleteBatchIds(s);
		System.out.println(i);
	}

	@Test
	public void updateById() {
		User u = new User();
//		u.setAge(23);
//		u.setEmail("124@123.com");
		u.setId((long) 6);
		u.setName("ppyy");
		int i = userMapper.updateById(u);
		System.out.println(i);
	}

	@Test
	public void updateByWrapper() {
		int i = userMapper.update(new User(),
				new UpdateWrapper<User>().lambda().set(User::getAge, 3).eq(User::getId, 1));
		System.out.println(i);
	}

	@Test
	public void selectById() {
		User user = userMapper.selectById(12);
		System.out.println(user.getId() + " " + user.getName());
	}

	@Test
	public void selectBatchIds() {
		Set<Integer> s = new HashSet<>();
		s.add(13);
		s.add(14);
		s.add(15);
		List<User> list = userMapper.selectBatchIds(s);
		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName());
		}
	}

	@Test
	public void selectByMap() {
		Map<String, Object> m = new HashMap<>();
		m.put("name", "ppyy");
		List<User> list = userMapper.selectByMap(m);
		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName());
		}
	}

	@Test
	public void selectOne() {
		User u = new User();
		u.setAge(30);
		User user = userMapper.selectOne(new QueryWrapper<User>(u));
		System.out.println(user.getId() + " " + user.getName());
	}

	@Test
	public void selectCount() {
//		Integer count = userMapper.selectCount(new QueryWrapper<User>().lambda().eq(User::getName, "ppyy"));
		User u = new User();
		u.setName("ppyy");
		Integer count = userMapper.selectCount(new QueryWrapper<User>(u));
//		Integer count = userMapper.selectCount(new QueryWrapper<User>(u, "name"));
		System.out.println(count);
	}

	@Test
	public void selectList1() {
		List<User> list = userMapper.selectList(null);
		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getRoleId());
		}
	}

	@Test
	public void selectList2() {
		User u = new User();
		u.setName("ppyy");
		List<User> list = userMapper.selectList(new QueryWrapper<User>(u));
		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getRoleId());
		}
	}

	@Test
	public void selectList3() {
		List<User> list = userMapper.selectList(new QueryWrapper<User>().eq("name", "ppyy"));
//		List<User> list = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getName, "ppyy"));

		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getRoleId());
		}
	}

	/**
	 * 多表连接查询
	 */
	@Test
	public void selectList4() {
		List<User> list = userMapper
				.selectList(new QueryWrapper<User>().inSql("role_id", "select id from role where id = 1"));

		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getRoleId());
		}
	}

	@Test
	public void selectList5() {

		QueryWrapper<User> qWrapper = new QueryWrapper<>();
		qWrapper.nested(i -> i.eq("role_id", 1L).or().eq("role_id", 2L));
		List<User> list = userMapper.selectList(qWrapper);
//		List<User> list = userMapper.selectList(new QueryWrapper<User>()
//				.nested(i -> i.eq("role_id", 1L).or().eq("role_id", 2L)).and(i -> i.ge("age", 24)));
		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getRoleId() + " " + user.getAge());
		}
	}

	@Test
	public void selectList6() {
		QueryWrapper<User> qWrapper = new QueryWrapper<>();
		qWrapper.apply("role_id =2");
		List<User> list = userMapper.selectList(qWrapper);
		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getRoleId() + " " + user.getAge());
		}
	}

	@Test
	public void selectMaps() {
		QueryWrapper<User> qWrapper = new QueryWrapper<>();
		qWrapper.eq("role_id", 2L);
		List<Map<String, Object>> maps = userMapper.selectMaps(qWrapper);
		for (Map<String, Object> map : maps) {
			Set<String> set = map.keySet();
			for (String s : set) {
				System.out.print(s + " : " + map.get(s) + "\t");
			}
			System.out.println();
		}
	}

	@Test
	public void selectObjs() {
		QueryWrapper<User> qWrapper = new QueryWrapper<>();
		qWrapper.eq("role_id", 2L);
		List<Object> list = userMapper.selectObjs(qWrapper);
		for (Object object : list) {
			System.out.println(object.toString());
		}
	}

	@Test
	public void selectPage() {
		QueryWrapper<User> qWrapper = new QueryWrapper<>();
		qWrapper.eq("age", 24);
		Page<User> page = new Page<>(1, 2);
		IPage<User> users = userMapper.selectPage(page, qWrapper);
		System.out.println(users.getCurrent());
		System.out.println(users.getPages());
		System.out.println(users.getTotal());
		System.out.println(users.getSize());
		System.out.println(users.getRecords().size());
//		System.out.println(users.getRecords());
		List<User> list = users.getRecords();
		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getRoleId() + " " + user.getAge());
		}
	}

	@Test
	public void s() {
		User user = userMapper.selectById(1);
		System.out.println(user.getName());
	}
}
