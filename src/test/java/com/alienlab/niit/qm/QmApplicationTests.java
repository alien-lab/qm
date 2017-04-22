package com.alienlab.niit.qm;

import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.repository.BaseDepartmentRepository;
import com.alienlab.niit.qm.repository.QmJudgeConfigRepository;
import com.alienlab.niit.qm.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QmApplicationTests {
	@Autowired
	private BaseClassesService baseClassesService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private BaseStudentService studentService;
	@Autowired
	private BaseTermService baseTermService;
	@Autowired
	private BaseStudentService baseStudentService;
	@Autowired
	private BaseTeacherService baseTeacherService;
	@Autowired
	private QmJudgeConfigService qmJudgeConfigService;
	@Autowired
	private QmJudgeConfigRepository qmJudgeConfigRepository;
	@Autowired
	private BaseDepartmentService baseDepartmentService;
	@Autowired
	private BaseDepartmentRepository baseDepartmentRepository;
	@Test
	public void contextLoads() throws Exception {
		//List<BaseClassesEntity> baseDepartmentEntities = baseClassesService.findBaseClassesByDepNoAndClassYear("2249","2014");
		//List<BaseClassesEntity> baseClassesEntities = baseClassesService.getBaseClassesByDepNoAndClassYearAndKey("2249","2014","机自");

		//System.out.print(baseClassesEntities);

		//System.out.print("---------------------");
		//System.out.print(baseStudentEntities );*/
		//List<BaseTermEntity> baseTermEntities = baseTermService.getAllTerm();
		//Page<BaseClassesEntity> baseStudentEntities = baseClassesService.findBaseClassesByDepNoAndClassYear("2240","2009",new PageRequest(0,3));
        //Page<BaseTeacherEntity> baseTermEntities = baseTeacherService.getteacherByDepNoAndTypeAndKey("2240","外聘","朱仁淼",new PageRequest(0,5));
		//List<QmJudgeConfigEntity> qmJudgeConfigEntities = qmJudgeConfigService.getQmJudgeByYear(2015);
		//List<QmJudgeConfigEntity> qmJudgeConfigEntities = qmJudgeConfigRepository.findAll();
		//BaseTeacherEntity baseTeacherEntity = baseTeacherService.getTeacherByteacherNo("0002660");
		//BaseDepartmentEntity baseDepartmentEntity = baseDepartmentService.getBaseDepartmentBydepNo("3454");
		boolean n = baseDepartmentService.deleteDepartment("3454");
		System.out.print("---------------------");
		System.out.print(n);
	}

}
