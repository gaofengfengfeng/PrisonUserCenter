package com.gaofeng.prisonusercenter.service;

import com.didi.meta.javalib.Conversion;
import com.didi.meta.javalib.IdUtil;
import com.didi.meta.javalib.JLog;
import com.gaofeng.prisonDBlib.model.Prisoner;
import com.gaofeng.prisonDBlib.model.PrisonerMapper;
import com.gaofeng.prisonDBlib.model.Role;
import com.gaofeng.prisonusercenter.beans.prisoner.LoginReq;
import com.gaofeng.prisonusercenter.beans.prisoner.RegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: gaofeng
 * @Date: 2018-08-21
 * @Description:
 */
@Service
public class PrisonerService {

    private PrisonerMapper pm;

    @Autowired
    public PrisonerService(PrisonerMapper prisonerMapper) {
        this.pm = prisonerMapper;
    }

    /**
     * 犯人注册
     *
     * @param registerReq
     *
     * @return 0:失败 1：成功 2:该用户已经注册过(以后添加校验之后可能会返回更多种数字结果)
     */
    public Integer register(RegisterReq registerReq) {
        JLog.info("prisoner register service prisonerCodeNum=" + registerReq.getPrisonerCodeNum() + " " +
                "prisonerName=" + registerReq.getPrisonerName());

        // 根据prisonerCodeNum查找数据库，看该用户是否已经注册过
        Prisoner prisonerInDB = pm.findByPrisonerCodeNum(registerReq.getPrisonerCodeNum());
        if (prisonerInDB != null) {
            JLog.info("have registered prisonerCodeNum=" + registerReq.getPrisonerCodeNum());
            return 2;
        }
        // 构造入库的犯人对象，暂时头像信息和权限等级字段没有置入。
        // 暂时还有加校验
        Prisoner prisoner = new Prisoner();
        prisoner.setPrisonerId(IdUtil.generateLongId());
        prisoner.setPrisonerCodeNum(registerReq.getPrisonerCodeNum());
        String salt = IdUtil.createSalt();
        prisoner.setPassword(Conversion.getMD5(registerReq.getPassword() + salt));
        prisoner.setSalt(salt);
        prisoner.setPrisonAddress(registerReq.getPrisonAddress());
        prisoner.setPrisonArea(registerReq.getPrisonArea());
        prisoner.setCriminalType(registerReq.getCriminalType());
        prisoner.setPrisonTerm(registerReq.getPrisonTerm());
        prisoner.setPrisonerName(registerReq.getPrisonerName());
        prisoner.setIdCardNo(registerReq.getIdCardNo());
        prisoner.setGender(registerReq.getGender());
        prisoner.setAge(registerReq.getAge());
        prisoner.setBirthDate(registerReq.getBirthDate());
        prisoner.setHomeAddress(registerReq.getHomeAddress());
        prisoner.setRoleId(Role.Type.PRISONER);
        // 阳光积分初始化设置成100
        prisoner.setScore(100);
        prisoner.setBeginTime(registerReq.getBeginTime());
        prisoner.setRegisterTime(System.currentTimeMillis() / 1000);

        Integer saveRet = pm.savePrisoner(prisoner);
        if (saveRet != 1) {
            JLog.error("save prisoner failed prisonerCodeNum=" + registerReq.getPrisonerCodeNum()
                    , 101222050);
            return 0;
        }
        JLog.info("save prisoner success prisonerCodeNum=" + registerReq.getPrisonerCodeNum());
        return 1;
    }

    /**
     * 犯人登陆
     *
     * @param loginReq
     *
     * @return 1:登陆成功 2：用户不存在 3：密码错误
     */
    public Integer login(LoginReq loginReq) {
        JLog.info("prisoner login service prisonerCodeNum=" + loginReq.getPrisonerCodeNum());
        Prisoner prisoner = pm.findByPrisonerCodeNum(loginReq.getPrisonerCodeNum());
        if (prisoner == null) {
            // 用户不存在
            JLog.warn("no prisoner in db prisonerCodeNum=" + loginReq.getPrisonerCodeNum());
            return 2;
        } else if (!prisoner.getPassword().equals(Conversion.getMD5(loginReq.getPassword() +
                prisoner.getSalt()))) {
            // 密码错误
            JLog.warn("password error");
            return 3;
        }
        return 1;
    }
}
