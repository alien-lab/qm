package com.alienlab.niit.qm.service.impl;

import com.alienlab.niit.qm.entity.*;
import com.alienlab.niit.qm.entity.dto.AttendanceDto;
import com.alienlab.niit.qm.entity.dto.CheckStu;
import com.alienlab.niit.qm.entity.dto.StuCheckDto;
import com.alienlab.niit.qm.repository.*;
import com.alienlab.niit.qm.service.QmStuCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Master QB on 2017/4/29.
 */
@Service
public class QmStuCheckServiceImpl implements QmStuCheckService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    BaseTaskScheRepository baseTaskScheRepository;
    @Autowired
    BaseTeachTaskRepository baseTeachTaskRepository;
    @Autowired
    BaseClassesRepository baseClassesRepository;
    @Autowired
    QmStuCheckMainRepository qmStuCheckMainRepository;
    @Autowired
    QmStuCheckRepository qmStuCheckRepository;
    @Autowired
    BaseClassLogicRepository baseClassLogicRepository;



    @Override
    public StuCheckDto getAttendByscheNoAndWeek(long scheNo, int week,String termNo,String type) {
        String classNo ="";
        StuCheckDto stuCheckDto = new StuCheckDto();
        List<CheckStu> checkStus = new ArrayList<>();
        BaseTaskScheEntity baseTaskScheEntity = baseTaskScheRepository.findOne(scheNo);
        if (baseTaskScheEntity!=null){
            BaseTeachTaskEntity baseTeachTaskEntity =baseTeachTaskRepository.findOne(baseTaskScheEntity.getTaskNo());
            classNo = baseTeachTaskEntity.getClassNo();
        }
        BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo(classNo);
        if (baseClassesEntity==null){
            String student_sql = "SELECT b.stu_no,b.stu_name FROM base_class_logic a,base_student b,base_task_sche c,base_teach_task d WHERE c.task_no=d.task_no AND c.sche_no='"+scheNo+"' AND d.task_no=a.task_no " +
                    "AND a.term_no='"+termNo+"' AND a.student_no = b.stu_no ";

            List <Map<String,Object>> totallist = jdbcTemplate.queryForList(student_sql);
            for (int i=0;i<totallist.size();i++){
                CheckStu checkStu = new CheckStu();
                checkStu.setStuNo((String) totallist.get(i).get("stu_no"));
                checkStu.setStuName((String) totallist.get(i).get("stu_name"));
                checkStu.setCheckStatus("正常");
                checkStus.add(checkStu);
            }

            String mysql = "SELECT * FROM qm_stu_check_main a WHERE a.sche_no='"+scheNo+"' AND a.`check_week`='"+week+"' ";
            List <Map<String,Object>> mylist = jdbcTemplate.queryForList(mysql);

            String sql ="SELECT a.*,b.check_no,b.stu_no,b.check_status,c.stu_name FROM qm_stu_check_main a,qm_stu_check b,base_student c " +
                    "WHERE a.check_main_no=b.check_main_no AND b.stu_no=c.stu_no AND a.check_week='"+week+"' AND a.sche_no='"+scheNo+"' ";
            List <Map<String,Object>> list = jdbcTemplate.queryForList(sql);
            if (mylist.size()==0){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                QmStuCheckMainEntity qmStuCheckMainEntity = new QmStuCheckMainEntity();
                qmStuCheckMainEntity.setScheNo(scheNo);
                qmStuCheckMainEntity.setCheckWeek(week);
                qmStuCheckMainEntity.setCheckTime(Timestamp.valueOf(df.format(new Date())));
                qmStuCheckMainEntity.setTermNo(termNo);
                qmStuCheckMainEntity.setCheckMainStatus(0);
                if (type.equals("实训课")){
                    qmStuCheckMainEntity.setCheckSx("1");
                }else {
                    qmStuCheckMainEntity.setCheckSx("0");
                }
                qmStuCheckMainEntity.setCheckCount(totallist.size());
                qmStuCheckMainEntity.setCheckJsws("5");
                qmStuCheckMainEntity.setCheckKtjl("5");
                QmStuCheckMainEntity qmStuCheckMainEntity1 = qmStuCheckMainRepository.save(qmStuCheckMainEntity);
                stuCheckDto.setCheckMainNo(qmStuCheckMainEntity1.getCheckMainNo());
                stuCheckDto.setScheNo(qmStuCheckMainEntity1.getScheNo());
                stuCheckDto.setCheckWeek(qmStuCheckMainEntity1.getCheckWeek());
                stuCheckDto.setCheckTime(qmStuCheckMainEntity1.getCheckTime());
                stuCheckDto.setTermNo(qmStuCheckMainEntity1.getTermNo());
                stuCheckDto.setCheckSx(qmStuCheckMainEntity1.getCheckSx());
                stuCheckDto.setCheckMainStatus(qmStuCheckMainEntity1.getCheckMainStatus());
                stuCheckDto.setCheckKk(qmStuCheckMainEntity1.getCheckKk());
                stuCheckDto.setCheckCd(qmStuCheckMainEntity1.getCheckCd());
                stuCheckDto.setCheckZt(qmStuCheckMainEntity1.getCheckZt());
                stuCheckDto.setCheckQj(qmStuCheckMainEntity1.getCheckQj());
                stuCheckDto.setCheckRatio(qmStuCheckMainEntity1.getCheckRatio());
                stuCheckDto.setCheckCount(qmStuCheckMainEntity1.getCheckCount());
                stuCheckDto.setCheckJsws(qmStuCheckMainEntity1.getCheckJsws());
                stuCheckDto.setCheckKtjl(qmStuCheckMainEntity1.getCheckKtjl());
            }else {
                if (list.size()==0){
                    stuCheckDto.setCheckMainNo((Long) mylist.get(0).get("check_main_no"));
                    stuCheckDto.setScheNo((Long) mylist.get(0).get("sche_no"));
                    stuCheckDto.setCheckWeek((Integer) mylist.get(0).get("check_week"));
                    stuCheckDto.setCheckTime((Timestamp) mylist.get(0).get("check_time"));
                    stuCheckDto.setTermNo((String) mylist.get(0).get("term_no"));
                    stuCheckDto.setCheckSx((String) mylist.get(0).get("check_sx"));
                    stuCheckDto.setCheckMainStatus((Integer) mylist.get(0).get("check_main_status"));
                    stuCheckDto.setCheckKk((String) mylist.get(0).get("check_kk"));
                    stuCheckDto.setCheckCd((String) mylist.get(0).get("check_cd"));
                    stuCheckDto.setCheckZt((String) mylist.get(0).get("check_zt"));
                    stuCheckDto.setCheckQj((String) mylist.get(0).get("check_qj"));
                    stuCheckDto.setCheckRatio((String) mylist.get(0).get("check_ratio"));
                    stuCheckDto.setCheckCount((Integer) mylist.get(0).get("check_count"));
                    stuCheckDto.setCheckJsws((String) mylist.get(0).get("check_jsws"));
                    stuCheckDto.setCheckKtjl((String) mylist.get(0).get("check_ktjl"));

                }else {
                    for (int j=0;j<list.size();j++){
                        stuCheckDto.setCheckMainNo((Long) list.get(j).get("check_main_no"));
                        stuCheckDto.setScheNo((Long) list.get(j).get("sche_no"));
                        stuCheckDto.setCheckWeek((Integer) list.get(j).get("check_week"));
                        stuCheckDto.setCheckTime((Timestamp) list.get(j).get("check_time"));
                        stuCheckDto.setTermNo((String) list.get(j).get("term_no"));
                        stuCheckDto.setCheckSx((String) list.get(j).get("check_sx"));
                        stuCheckDto.setCheckMainStatus((Integer) list.get(j).get("check_main_status"));
                        stuCheckDto.setCheckKk((String) list.get(j).get("check_kk"));
                        stuCheckDto.setCheckCd((String) list.get(j).get("check_cd"));
                        stuCheckDto.setCheckZt((String) list.get(j).get("check_zt"));
                        stuCheckDto.setCheckQj((String) list.get(j).get("check_qj"));
                        stuCheckDto.setCheckRatio((String) list.get(j).get("check_ratio"));
                        stuCheckDto.setCheckCount((Integer) list.get(j).get("check_count"));
                        stuCheckDto.setCheckJsws((String) list.get(j).get("check_jsws"));
                        stuCheckDto.setCheckKtjl((String) list.get(j).get("check_ktjl"));
                    }
                    for (int n=0;n<list.size();n++){
                        for (int m =0;m<checkStus.size();m++){
                            String stuNo = (String) list.get(n).get("stu_no");
                            if (stuNo.equals(checkStus.get(m).getStuNo())){
                                checkStus.get(m).setCheckStatus((String) list.get(n).get("check_status"));
                                checkStus.get(m).setCheckNo((Long) list.get(n).get("check_no"));
                            }
                        }
                    }
                }

            }
            stuCheckDto.setCheckStus(checkStus);
        }else {
            String studentsql = "SELECT DISTINCT e.stu_no,e.stu_name  FROM qm_stu_check_main a,base_task_sche b,base_teach_task c,base_term_student d,base_student e WHERE " +
                    "a.sche_no='"+scheNo+"' AND a.sche_no= b.sche_no AND b.task_no=c.task_no AND c.class_no=d.class_no AND d.term_no='"+termNo+"' AND e.stu_no = d.stu_no";

            List <Map<String,Object>> totallist = jdbcTemplate.queryForList(studentsql);

            for (int i=0;i<totallist.size();i++){
                CheckStu checkStu = new CheckStu();
                checkStu.setStuNo((String) totallist.get(i).get("stu_no"));
                checkStu.setStuName((String) totallist.get(i).get("stu_name"));
                checkStu.setCheckStatus("正常");
                checkStus.add(checkStu);
            }

            String mysql = "SELECT * FROM qm_stu_check_main a WHERE a.sche_no='"+scheNo+"' AND a.`check_week`='"+week+"' ";
            List <Map<String,Object>> mylist = jdbcTemplate.queryForList(mysql);

            String sql ="SELECT a.*,b.check_no,b.stu_no,b.check_status,c.stu_name FROM qm_stu_check_main a,qm_stu_check b,base_student c " +
                    "WHERE a.check_main_no=b.check_main_no AND b.stu_no=c.stu_no AND a.check_week='"+week+"' AND a.sche_no='"+scheNo+"' ";

            List <Map<String,Object>> list = jdbcTemplate.queryForList(sql);
            if (mylist.size()==0){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                QmStuCheckMainEntity qmStuCheckMainEntity = new QmStuCheckMainEntity();
                qmStuCheckMainEntity.setScheNo(scheNo);
                qmStuCheckMainEntity.setCheckWeek(week);
                qmStuCheckMainEntity.setCheckTime(Timestamp.valueOf(df.format(new Date())));
                qmStuCheckMainEntity.setCheckMainStatus(0);
                qmStuCheckMainEntity.setCheckCount(totallist.size());
                if (type.equals("实训课")){
                    qmStuCheckMainEntity.setCheckSx("1");
                }else {
                    qmStuCheckMainEntity.setCheckSx("0");
                }
                qmStuCheckMainEntity.setTermNo(termNo);
                qmStuCheckMainEntity.setCheckJsws("5");
                qmStuCheckMainEntity.setCheckKtjl("5");
                QmStuCheckMainEntity qmStuCheckMainEntity1 = qmStuCheckMainRepository.save(qmStuCheckMainEntity);
                stuCheckDto.setCheckMainNo(qmStuCheckMainEntity1.getCheckMainNo());
                stuCheckDto.setScheNo(qmStuCheckMainEntity1.getScheNo());
                stuCheckDto.setCheckWeek(qmStuCheckMainEntity1.getCheckWeek());
                stuCheckDto.setCheckTime(qmStuCheckMainEntity1.getCheckTime());
                stuCheckDto.setTermNo(qmStuCheckMainEntity1.getTermNo());
                stuCheckDto.setCheckSx(qmStuCheckMainEntity1.getCheckSx());
                stuCheckDto.setCheckMainStatus(qmStuCheckMainEntity1.getCheckMainStatus());
                stuCheckDto.setCheckKk(qmStuCheckMainEntity1.getCheckKk());
                stuCheckDto.setCheckCd(qmStuCheckMainEntity1.getCheckCd());
                stuCheckDto.setCheckZt(qmStuCheckMainEntity1.getCheckZt());
                stuCheckDto.setCheckQj(qmStuCheckMainEntity1.getCheckQj());
                stuCheckDto.setCheckRatio(qmStuCheckMainEntity1.getCheckRatio());
                stuCheckDto.setCheckCount(qmStuCheckMainEntity1.getCheckCount());
                stuCheckDto.setCheckJsws(qmStuCheckMainEntity1.getCheckJsws());
                stuCheckDto.setCheckKtjl(qmStuCheckMainEntity1.getCheckKtjl());
            }else {
                if (list.size()==0){
                    stuCheckDto.setCheckMainNo((Long) mylist.get(0).get("check_main_no"));
                    stuCheckDto.setScheNo((Long) mylist.get(0).get("sche_no"));
                    stuCheckDto.setCheckWeek((Integer) mylist.get(0).get("check_week"));
                    stuCheckDto.setCheckTime((Timestamp) mylist.get(0).get("check_time"));
                    stuCheckDto.setTermNo((String) mylist.get(0).get("term_no"));
                    stuCheckDto.setCheckSx((String) mylist.get(0).get("check_sx"));
                    stuCheckDto.setCheckMainStatus((Integer) mylist.get(0).get("check_main_status"));
                    stuCheckDto.setCheckKk((String) mylist.get(0).get("check_kk"));
                    stuCheckDto.setCheckCd((String) mylist.get(0).get("check_cd"));
                    stuCheckDto.setCheckZt((String) mylist.get(0).get("check_zt"));
                    stuCheckDto.setCheckQj((String) mylist.get(0).get("check_qj"));
                    stuCheckDto.setCheckRatio((String) mylist.get(0).get("check_ratio"));
                    stuCheckDto.setCheckCount((Integer) mylist.get(0).get("check_count"));
                    stuCheckDto.setCheckJsws((String) mylist.get(0).get("check_jsws"));
                    stuCheckDto.setCheckKtjl((String) mylist.get(0).get("check_ktjl"));
                }else {
                    for (int j=0;j<list.size();j++){
                        stuCheckDto.setCheckMainNo((Long) list.get(j).get("check_main_no"));
                        stuCheckDto.setScheNo((Long) list.get(j).get("sche_no"));
                        stuCheckDto.setCheckWeek((Integer) list.get(j).get("check_week"));
                        stuCheckDto.setCheckTime((Timestamp) list.get(j).get("check_time"));
                        stuCheckDto.setTermNo((String) list.get(j).get("term_no"));
                        stuCheckDto.setCheckSx((String) list.get(j).get("check_sx"));
                        stuCheckDto.setCheckMainStatus((Integer) list.get(j).get("check_main_status"));
                        stuCheckDto.setCheckKk((String) list.get(j).get("check_kk"));
                        stuCheckDto.setCheckCd((String) list.get(j).get("check_cd"));
                        stuCheckDto.setCheckZt((String) list.get(j).get("check_zt"));
                        stuCheckDto.setCheckQj((String) list.get(j).get("check_qj"));
                        stuCheckDto.setCheckRatio((String) list.get(j).get("check_ratio"));
                        stuCheckDto.setCheckCount((Integer) list.get(j).get("check_count"));
                        stuCheckDto.setCheckJsws((String) list.get(j).get("check_jsws"));
                        stuCheckDto.setCheckKtjl((String) list.get(j).get("check_ktjl"));
                    }
                    for (int n=0;n<list.size();n++){
                        for (int m =0;m<checkStus.size();m++){
                            String stuNo = (String) list.get(n).get("stu_no");
                            if (stuNo.equals(checkStus.get(m).getStuNo())){
                                checkStus.get(m).setCheckStatus((String) list.get(n).get("check_status"));
                                checkStus.get(m).setCheckNo((Long) list.get(n).get("check_no"));
                            }
                        }
                    }
                }

           }
            stuCheckDto.setCheckStus(checkStus);
        }


        return stuCheckDto;
    }

    @Override
    public QmStuCheckEntity updateStuCheck(long checkMainNo,String status, String stuNo) {
        int kk=0;
        int cd=0;
        int zt=0;
        int qj=0;
        double ratio=0;
        DecimalFormat floatdf   = new DecimalFormat("######0.00");
        String sql ="SELECT * FROM qm_stu_check a WHERE a.check_main_no='"+checkMainNo+"' AND a.stu_no='"+stuNo+"'";
        List <Map<String,Object>> stuChecklist = jdbcTemplate.queryForList(sql);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        if (stuChecklist.size()==0){
            QmStuCheckEntity qmStuCheckEntity = new QmStuCheckEntity();
            qmStuCheckEntity.setCheckMainNo(checkMainNo);
            qmStuCheckEntity.setStuNo(stuNo);
            qmStuCheckEntity.setCheckStatus(status);
            qmStuCheckEntity.setCheckTime(Timestamp.valueOf(df.format(new Date())));
            QmStuCheckEntity   qmStuCheckEntity1 = qmStuCheckRepository.save(qmStuCheckEntity);
            List<QmStuCheckEntity> qmStuCheckEntities = qmStuCheckRepository.findByCheckMainNo(checkMainNo);
            for (int i =0;i<qmStuCheckEntities.size();i++){
                if (qmStuCheckEntities.get(i).getCheckStatus().equals("旷课")){
                    kk++;
                }else if(qmStuCheckEntities.get(i).getCheckStatus().equals("迟到")){
                    cd++;
                }else if(qmStuCheckEntities.get(i).getCheckStatus().equals("早退")){
                    zt++;
                }else  if(qmStuCheckEntities.get(i).getCheckStatus().equals("请假")){
                    qj++;
                }
            }
            QmStuCheckMainEntity qmStuCheckMainEntity = qmStuCheckMainRepository.findOne(checkMainNo);
            int studentNumber = qmStuCheckMainEntity.getCheckCount();
            ratio = ((double)(studentNumber-kk-cd-zt)/studentNumber)*100;
            qmStuCheckMainEntity.setCheckKk(String.valueOf(kk));
            qmStuCheckMainEntity.setCheckCd(String.valueOf(cd));
            qmStuCheckMainEntity.setCheckZt(String.valueOf(zt));
            qmStuCheckMainEntity.setCheckQj(String.valueOf(qj));
            qmStuCheckMainEntity.setCheckRatio(String.valueOf(floatdf.format(ratio)));
            qmStuCheckMainRepository.save(qmStuCheckMainEntity);

            return  qmStuCheckEntity1;

        }else {
           List<QmStuCheckEntity> qmStuCheckEntity =qmStuCheckRepository.findByCheckMainNoAndStuNo(checkMainNo,stuNo);
            QmStuCheckEntity qmStuCheckEntity2 = new QmStuCheckEntity();
            for (int i=0;i<qmStuCheckEntity.size();i++){
                if (status.equals("正常")){
                    qmStuCheckRepository.delete(qmStuCheckEntity.get(i));
                    List<QmStuCheckEntity> qmStuCheckEntities = qmStuCheckRepository.findByCheckMainNo(checkMainNo);
                    for (int m =0;m<qmStuCheckEntities.size();m++){
                        if (qmStuCheckEntities.get(m).getCheckStatus().equals("旷课")){
                            kk++;
                        }else if(qmStuCheckEntities.get(m).getCheckStatus().equals("迟到")){
                            cd++;
                        }else if(qmStuCheckEntities.get(m).getCheckStatus().equals("早退")){
                            zt++;
                        }else  if(qmStuCheckEntities.get(m).getCheckStatus().equals("请假")){
                            qj++;
                        }
                    }
                    QmStuCheckMainEntity qmStuCheckMainEntity = qmStuCheckMainRepository.findOne(checkMainNo);
                    int studentNumber = qmStuCheckMainEntity.getCheckCount();
                    ratio = ((double)(studentNumber-kk-cd-zt)/studentNumber)*100;
                    qmStuCheckMainEntity.setCheckKk(String.valueOf(kk));
                    qmStuCheckMainEntity.setCheckCd(String.valueOf(cd));
                    qmStuCheckMainEntity.setCheckZt(String.valueOf(zt));
                    qmStuCheckMainEntity.setCheckQj(String.valueOf(qj));
                    qmStuCheckMainEntity.setCheckRatio(String.valueOf(floatdf.format(ratio)));
                    qmStuCheckMainRepository.save(qmStuCheckMainEntity);
                }else {
                    QmStuCheckEntity qmStuCheckEntity1 = qmStuCheckEntity.get(i);
                    qmStuCheckEntity1.setCheckStatus(status);
                    qmStuCheckEntity1.setCheckTime(Timestamp.valueOf(df.format(new Date())));
                    qmStuCheckEntity2 = qmStuCheckRepository.save(qmStuCheckEntity1);
                    List<QmStuCheckEntity> qmStuCheckEntities = qmStuCheckRepository.findByCheckMainNo(checkMainNo);
                    for (int n =0;n<qmStuCheckEntities.size();n++){
                        if (qmStuCheckEntities.get(n).getCheckStatus().equals("旷课")){
                            kk++;
                        }else if(qmStuCheckEntities.get(n).getCheckStatus().equals("迟到")){
                            cd++;
                        }else if(qmStuCheckEntities.get(n).getCheckStatus().equals("早退")){
                            zt++;
                        }else  if(qmStuCheckEntities.get(n).getCheckStatus().equals("请假")){
                            qj++;
                        }
                    }
                    QmStuCheckMainEntity qmStuCheckMainEntity = qmStuCheckMainRepository.findOne(checkMainNo);
                    int studentNumber = qmStuCheckMainEntity.getCheckCount();
                    ratio = ((double)(studentNumber-kk-cd-zt)/studentNumber)*100;
                    qmStuCheckMainEntity.setCheckKk(String.valueOf(kk));
                    qmStuCheckMainEntity.setCheckCd(String.valueOf(cd));
                    qmStuCheckMainEntity.setCheckZt(String.valueOf(zt));
                    qmStuCheckMainEntity.setCheckQj(String.valueOf(qj));
                    qmStuCheckMainEntity.setCheckRatio(String.valueOf(floatdf.format(ratio)));
                    qmStuCheckMainRepository.save(qmStuCheckMainEntity);
                }
            }
            return  qmStuCheckEntity2;
        }
    }

    @Override
    public QmStuCheckMainEntity updateJswsAndKtjl(long checkMainNo, String jsws, String ktjl) {
        QmStuCheckMainEntity qmStuCheckMainEntity =qmStuCheckMainRepository.getOne(checkMainNo);
        qmStuCheckMainEntity.setCheckJsws(jsws);
        qmStuCheckMainEntity.setCheckKtjl(ktjl);
        QmStuCheckMainEntity qmStuCheckMainEntity1 = qmStuCheckMainRepository.save(qmStuCheckMainEntity);
        return qmStuCheckMainEntity1;
    }

    @Override
    public QmStuCheckMainEntity submitAttend(long checkMainNo) {
        QmStuCheckMainEntity qmStuCheckMainEntity =qmStuCheckMainRepository.getOne(checkMainNo);
        qmStuCheckMainEntity.setCheckMainStatus(1);
        QmStuCheckMainEntity qmStuCheckMainEntity1 = qmStuCheckMainRepository.save(qmStuCheckMainEntity);
        return qmStuCheckMainEntity1;
    }

    @Override
    public List<AttendanceDto> getByScheNoAndTermNo(long scheNo, String TermNo) {

        List<AttendanceDto> attendanceDtos = new ArrayList<>();
        String sql = "SELECT a.*,b.`task_no`,c.`course_week`,c.course_type,c.term_no,c.`course_name`,c.`class_no` FROM qm_stu_check_main a,base_task_sche b,base_teach_task c WHERE a.`sche_no`=b.`sche_no` AND a.`sche_no`='" + scheNo + "' AND a.`term_no`='" + TermNo + "' AND a.`sche_no`=b.`sche_no` AND b.`task_no` =c.`task_no`";
        List<Map<String, Object>> atteendlist = jdbcTemplate.queryForList(sql);
        //如果没有考勤记录
        if (atteendlist.size() == 0) {
            String schesql = "SELECT * FROM base_task_sche a,base_teach_task b WHERE a.`sche_no`='" + scheNo + "' AND a.`task_no`=b.`task_no` AND b.`term_no`='" + TermNo + "'";
            List<Map<String, Object>> tasklist = jdbcTemplate.queryForList(schesql);
            for (int i = 0; i < tasklist.size(); i++) {
                String week = (String) tasklist.get(i).get("course_week");
                if (week.contains(",")) {
                    String[] weeks = week.split(",");
                    for (int j = weeks.length - 1; j >= 0; j--) {
                        String[] oneweek = weeks[j].split("-");
                        int endweek = Integer.parseInt(oneweek[1]);
                        int startweek = Integer.parseInt(oneweek[0]);
                        for (int m = endweek; m >= startweek; m--) {
                            AttendanceDto attendanceDto = new AttendanceDto();
                            attendanceDto.setWeek(m);
                            attendanceDto.setCheckKk(null);
                            attendanceDto.setCheckCd(null);
                            attendanceDto.setCheckZt(null);
                            attendanceDto.setCheckQj(null);
                            attendanceDto.setCheckRatio(null);
                            attendanceDto.setCheckMainStatus(null);
                            BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo((String) tasklist.get(i).get("class_no")) ;
                            if (baseClassesEntity!=null){
                                attendanceDto.setClassName(baseClassesEntity.getClassName());
                            }else {
                                try {
                                    attendanceDto.setClassName("逻辑班级"+baseClassLogicRepository.findByTaskNo((Long) tasklist.get(i).get("task_no")).get(0).getLogicName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            attendanceDto.setCourseName((String) tasklist.get(i).get("course_name"));
                            attendanceDto.setScheNo((Long) tasklist.get(i).get("sche_no"));
                            attendanceDto.setTermNo((String) tasklist.get(i).get("term_no"));
                            attendanceDto.setCourseType((String) tasklist.get(i).get("course_type"));

                            attendanceDtos.add(attendanceDto);
                        }
                    }
                } else {
                    String[] oneweek = week.split("-");
                    int endweek = Integer.parseInt(oneweek[1]);
                    int startweek = Integer.parseInt(oneweek[0]);
                    for (int m = endweek; m >= startweek; m--) {
                        AttendanceDto attendanceDto = new AttendanceDto();
                        attendanceDto.setWeek(m);
                        attendanceDto.setCheckKk(null);
                        attendanceDto.setCheckCd(null);
                        attendanceDto.setCheckZt(null);
                        attendanceDto.setCheckQj(null);
                        attendanceDto.setCheckRatio(null);
                        attendanceDto.setCheckMainStatus(null);
                        BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo((String) tasklist.get(i).get("class_no")) ;
                        if (baseClassesEntity!=null){
                            attendanceDto.setClassName(baseClassesEntity.getClassName());
                        }else {
                            try {
                                attendanceDto.setClassName("逻辑班级"+baseClassLogicRepository.findByTaskNo((Long) tasklist.get(i).get("task_no")).get(0).getLogicName());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        attendanceDto.setCourseName((String) tasklist.get(i).get("course_name"));
                        attendanceDto.setScheNo((Long) tasklist.get(i).get("sche_no"));
                        attendanceDto.setTermNo((String) tasklist.get(i).get("term_no"));
                        attendanceDto.setCourseType((String) tasklist.get(i).get("course_type"));
                        attendanceDtos.add(attendanceDto);
                    }
                }
            }
        } else {
            List<String> weeklist = new ArrayList<>();
            String week = (String) atteendlist.get(0).get("course_week");
            for (int m = 0; m < atteendlist.size(); m++) {
                weeklist.add(String.valueOf( atteendlist.get(m).get("check_week")));
            }
            if (week.contains(",")){
                String[] weeks = week.split(",");
                for (int j=weeks.length-1;j>=0;j--){
                    String []oneweek = weeks[j].split("-");
                    int endweek =Integer.parseInt(oneweek[1]);
                    int startweek = Integer.parseInt(oneweek[0]);
                    for (int a=endweek;a>=startweek;a--){
                        if (weeklist.contains(String.valueOf(a))){
                            for (int c = 0; c < atteendlist.size(); c++){
                                int thisweek = (int) atteendlist.get(c).get("check_week");
                                if (a==thisweek){
                                    AttendanceDto attendanceDto = new AttendanceDto();
                                    attendanceDto.setCheck(true);
                                    attendanceDto.setWeek(a);
                                    BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo((String) atteendlist.get(c).get("class_no")) ;
                                    if (baseClassesEntity!=null){
                                        attendanceDto.setClassName(baseClassesEntity.getClassName());
                                    }else {
                                        try {
                                            attendanceDto.setClassName("逻辑班级"+baseClassLogicRepository.findByTaskNo((Long) atteendlist.get(c).get("task_no")).get(0).getLogicName());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    attendanceDto.setCourseName((String) atteendlist.get(c).get("course_name"));
                                    attendanceDto.setScheNo((Long) atteendlist.get(c).get("sche_no"));
                                    attendanceDto.setTermNo((String) atteendlist.get(c).get("term_no"));
                                    attendanceDto.setCourseType((String) atteendlist.get(c).get("course_type"));
                                    attendanceDto.setCheckKk((String) atteendlist.get(c).get("check_kk"));
                                    attendanceDto.setCheckCd((String) atteendlist.get(c).get("check_cd"));
                                    attendanceDto.setCheckZt((String) atteendlist.get(c).get("check_zt"));
                                    attendanceDto.setCheckQj((String) atteendlist.get(c).get("check_qj"));
                                    attendanceDto.setCheckRatio((String) atteendlist.get(c).get("check_ratio"));
                                    attendanceDto.setCheckMainStatus((Integer) atteendlist.get(c).get("check_main_status"));
                                    attendanceDtos.add(attendanceDto);
                                }
                            }
                        }else {
                            AttendanceDto attendanceDto = new AttendanceDto();
                            attendanceDto.setWeek(a);
                            attendanceDto.setScheNo((Long) atteendlist.get(0).get("sche_no"));
                            attendanceDto.setTermNo((String) atteendlist.get(0).get("term_no"));
                            attendanceDto.setCourseType((String) atteendlist.get(0).get("course_type"));
                            BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo((String) atteendlist.get(0).get("class_no")) ;
                            if (baseClassesEntity!=null){
                                attendanceDto.setClassName(baseClassesEntity.getClassName());
                            }else {
                                try {
                                    attendanceDto.setClassName("逻辑班级"+baseClassLogicRepository.findByTaskNo((Long) atteendlist.get(0).get("task_no")).get(0).getLogicName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            attendanceDto.setCourseName((String) atteendlist.get(0).get("course_name"));
                            attendanceDto.setCheckKk(null);
                            attendanceDto.setCheckCd(null);
                            attendanceDto.setCheckZt(null);
                            attendanceDto.setCheckQj(null);
                            attendanceDto.setCheckRatio(null);
                            attendanceDto.setCheckMainStatus(null);
                            attendanceDtos.add(attendanceDto);
                        }
                    }
                }

            }else {
                String []oneweek = week.split("-");
                int endweek =Integer.parseInt(oneweek[1]);
                int startweek = Integer.parseInt(oneweek[0]);
                for (int a=endweek;a>=startweek;a--){
                    if (weeklist.contains(String.valueOf(a))){
                        for (int c = 0; c < atteendlist.size(); c++){
                            int thisweek = (int) atteendlist.get(c).get("check_week");
                            if (a==thisweek){
                                AttendanceDto attendanceDto = new AttendanceDto();
                                attendanceDto.setCheck(true);
                                attendanceDto.setWeek(a);
                                BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo((String) atteendlist.get(c).get("class_no")) ;
                                if (baseClassesEntity!=null){
                                    attendanceDto.setClassName(baseClassesEntity.getClassName());
                                }else {
                                    try {
                                        attendanceDto.setClassName("逻辑班级"+baseClassLogicRepository.findByTaskNo((Long) atteendlist.get(c).get("task_no")).get(0).getLogicName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                attendanceDto.setCourseName((String) atteendlist.get(c).get("course_name"));
                                attendanceDto.setScheNo((Long) atteendlist.get(c).get("sche_no"));
                                attendanceDto.setTermNo((String) atteendlist.get(c).get("term_no"));
                                attendanceDto.setCourseType((String) atteendlist.get(c).get("course_type"));
                                attendanceDto.setCheckKk((String) atteendlist.get(c).get("check_kk"));
                                attendanceDto.setCheckCd((String) atteendlist.get(c).get("check_cd"));
                                attendanceDto.setCheckZt((String) atteendlist.get(c).get("check_zt"));
                                attendanceDto.setCheckQj((String) atteendlist.get(c).get("check_qj"));
                                attendanceDto.setCheckRatio((String) atteendlist.get(c).get("check_ratio"));
                                attendanceDto.setCheckMainStatus((Integer) atteendlist.get(c).get("check_main_status"));
                                attendanceDtos.add(attendanceDto);
                            }
                        }
                    }else {
                        AttendanceDto attendanceDto = new AttendanceDto();
                        attendanceDto.setWeek(a);
                        attendanceDto.setScheNo((Long) atteendlist.get(0).get("sche_no"));
                        attendanceDto.setTermNo((String) atteendlist.get(0).get("term_no"));
                        attendanceDto.setCourseType((String) atteendlist.get(0).get("course_type"));
                        BaseClassesEntity baseClassesEntity = baseClassesRepository.findByClassNo((String) atteendlist.get(0).get("class_no")) ;
                        if (baseClassesEntity!=null){
                            attendanceDto.setClassName(baseClassesEntity.getClassName());
                        }else {
                            try {
                                attendanceDto.setClassName("逻辑班级"+baseClassLogicRepository.findByTaskNo((Long) atteendlist.get(0).get("task_no")).get(0).getLogicName());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        attendanceDto.setCourseName((String) atteendlist.get(0).get("course_name"));
                        attendanceDto.setCheckKk(null);
                        attendanceDto.setCheckCd(null);
                        attendanceDto.setCheckZt(null);
                        attendanceDto.setCheckQj(null);
                        attendanceDto.setCheckRatio(null);
                        attendanceDto.setCheckMainStatus(null);
                        attendanceDtos.add(attendanceDto);
                    }
                }

            }




        }
        return attendanceDtos;
    }


}
