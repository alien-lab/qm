package com.alienlab.niit.qm;

import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.repository.BaseDepartmentRepository;
import com.alienlab.niit.qm.service.BaseDepartmentService;
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
	private BaseDepartmentService baseDepartmentService;
	@Test
	public void contextLoads() {
		List<BaseDepartmentEntity> baseDepartmentEntities = baseDepartmentService.getDepartment();
		System.out.print(baseDepartmentEntities);
	}

}
