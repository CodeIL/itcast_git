package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.ReportDao;
import com.itheima.dao.SetmealDao;
import com.itheima.service.ReportService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService{

@Autowired
private ReportDao reportDao;


    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;


    @Override
    public List<Integer> getMembersByYearMoth(List<String> monthList) {

            List<Integer> memberCounts = new ArrayList<>();
        if (monthList != null && monthList.size()> 0) {
            for (String yearMonth : monthList) {
                String newYearMonth = yearMonth += "-31";
                int memberCount = reportDao.findMemberCountBeforeDate(newYearMonth);
                memberCounts.add(memberCount);
            }
        }
        return memberCounts;
    }

    @Override
    public Map getSetmealReport() {
        //"setmealNames":["套餐一","套餐二"]
        //"setmealCount":[{value:22,name:'套餐一'},{value:33,name:'套餐二'}]
        //select ts.name,count(*) value from t_setmeal ts,t_order o where ts.id = o.setmeal_id group by ts.name
        //1.获取每个套餐对应的预约数量
        List<Map> mapList = reportDao.setmealCount();
        //2.定义List<String>存放套餐名称
        List<String> setmealNames = new ArrayList<>();
        //3.获取套餐名称集合
        if(mapList != null && mapList.size()>0){
            for (Map map : mapList) {
                String setmealName = (String)map.get("name");
                setmealNames.add(setmealName);
            }
        }
        //4.定义map最终返回页面需要的数据
        Map<String,Object> map = new HashMap<>();
        map.put("setmealNames",setmealNames);
        map.put("setmealCount",mapList);
        return map;
    }

    @Override
    public Map<String, Object> getBusinessReportData() throws Exception {
        //今天日期
        String reportDate = DateUtils.parseDate2String(DateUtils.getToday());
        //本周第一天
        String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getFirstDayOfWeek(DateUtils.getToday()));
        // String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());

        //本月第一天
        String firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        //今天新增会员数  todayNewMember
        int todayNewMember= memberDao.todayNewMember(reportDate);
      //总会员数  totalMember
        int totalMember =memberDao.totalMember();
      //本周新增会员数  thisWeekNewMember
        int thisWeekNewMember =memberDao.findMemberCountAfterDate(thisWeekMonday);
      //本月新增会员数  thisMonthNewMember
        int thisMonthNewMember =memberDao.findMemberCountAfterDate(firstDay4ThisMonth);
      //今日预约数  todayOrderNumber
        int todayOrderNumber =orderDao.todayOrderNumber(reportDate);
      //今日到诊数  todayVisitsNumber
        int todayVisitsNumber =orderDao.todayVisitsNumber(reportDate);
      //本周预约数  thisWeekOrderNumber
        int thisWeekOrderNumber =orderDao.thisWeekOrderNumber(thisWeekMonday);
      //本周到诊数  thisWeekVisitsNumber
        int thisWeekVisitsNumber =orderDao.thisWeekVisitsNumber(thisWeekMonday);
      //本月预约数  thisMonthOrderNumber
        int thisMonthOrderNumber =orderDao.thisWeekOrderNumber(firstDay4ThisMonth);
      //本月到诊数  thisMonthVisitsNumber
        int thisMonthVisitsNumber =orderDao.thisWeekVisitsNumber(firstDay4ThisMonth);
      //
        Map<String,Object> map = new HashMap<>();
      //
        map.put("todayNewMember",todayNewMember);
        map.put("totalMember",totalMember);
        map.put("thisWeekNewMember",thisWeekNewMember);
        map.put("thisMonthNewMember",thisMonthNewMember);
        map.put("todayOrderNumber",todayOrderNumber);
        map.put("todayVisitsNumber",todayVisitsNumber);
        map.put("thisWeekOrderNumber",thisWeekOrderNumber);
        map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        map.put("thisMonthOrderNumber",thisMonthOrderNumber);
        map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        return map;
    }
}
