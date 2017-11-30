package cn.dlj.internal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.entity.WxXuncha;
import cn.dlj.app.entity.Xuncha;
import cn.dlj.app.service.XunchaService;
import cn.dlj.utils.DateUtils;
import cn.dlj.wx.service.WxXunchaService;

/**
 * 定时任务
 */
@Component("WxFlag")
public class WxFlag {

	private static final Logger log = LoggerFactory.getLogger(WxFlag.class);
	@Autowired
	private XunchaService xunchaService;
	@Autowired
	private WxXunchaService wxXunchaService;

	@Transactional
	public void task() {
		log.error("开始定时任务-----");
		//1.被整改单位没提交过
		String lastSevenDay = getLastSevenDay();

		//查询7天前待审核的巡查记录
		List<Xuncha> xunchas = xunchaService.findByXcTime(lastSevenDay);
		log.error("查询7天前待审核的巡查记录----begin,size=" + xunchas.size());
		for (Xuncha xc : xunchas) {
			updateFlag(xc.getId());
			log.error("流转网格，更新xcId:" + xc.getId());
		}
		log.error("查询7天前待审核的巡查记录----end");

		//2.查询审核流转
		List<WxXuncha> wxXunchas = wxXunchaService.getByLastTime();
		log.error("查询审核流转----begin,size=" + wxXunchas.size());
		for (WxXuncha wxXuncha : wxXunchas) {
			Integer lastTime = wxXuncha.getLastTime() - 1;
			int role = wxXuncha.getRole();//审核角色(1:平台巡查员 2:平台管理员)
			int status = wxXuncha.getStatus();//审核状态: '0' 待审核 ,'1' 审核通过' , '2' 审核不通过
			//如果巡查员还没审核
			if (role == 1 && status == 0) {//平台巡查员
				if (lastTime <= 0) {
					//流转到管理员
					wxXuncha.setEt(new Date());
					wxXuncha.setStatus(0);
					wxXuncha.setRole(2);
					wxXuncha.setLastTime(1);
					wxXunchaService.update(wxXuncha);
					log.error("流转到管理员,xunchaId=" + wxXuncha.getXunchaId() + ",lastTime=" + wxXuncha.getLastTime());
				} else {
					wxXuncha.setLastTime(lastTime);
					wxXunchaService.updateLastTime(wxXuncha);
					log.error("更新剩余时间,xunchaId=" + wxXuncha.getXunchaId() + ",lastTime=" + wxXuncha.getLastTime());
				}
			} else if (role == 2 && status == 0) {//如果管理员还没审核
				//流转到网格系统
				wxXuncha.setLastTime(0);
				wxXunchaService.update(wxXuncha);
				updateFlag(wxXuncha.getXunchaId());
				log.error("流转网格，更新xcId:" + wxXuncha.getXunchaId());
			} else {
				Xuncha xuncha = xunchaService.getById(wxXuncha.getXunchaId());
				int time = DateUtils.differentDaysByMillisecond(new Date(), xuncha.getXcTime());
				if (time > 7) {//如果已审核，超过7天就流转到网格
					wxXuncha.setLastTime(0);
					wxXunchaService.update(wxXuncha);
					updateFlag(wxXuncha.getXunchaId());
					log.error("流转网格，更新xcId:" + wxXuncha.getXunchaId());
				}
			}
		}
		log.error("结束定时任务-----");
	}

	private String getLastSevenDay() {
		Date lastSevenDay = DateUtils.nextDay(new Date(), -7);
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(lastSevenDay);
	}

	private void updateFlag(Integer xunchaId) {
		xunchaService.updateXcFlag(xunchaId, "6");
		xunchaService.updateXcRdFlag(xunchaId, "6");
		xunchaService.updateXcFlagFlag(xunchaId, "6");
	}

}
