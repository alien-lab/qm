package com.alienlab.niit.qm;

import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.entity.TbMenuEntity;
import com.alienlab.niit.qm.entity.TbUserEntity;
import com.alienlab.niit.qm.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QmApplicationTests {

	@Autowired
	private MenuService menuService;
	@Test
	public void contextLoads() throws Exception {
		List<TbMenuEntity> baseDepartmentEntities = menuService.getAllMenus();
		System.out.print(baseDepartmentEntities);
	}

}
