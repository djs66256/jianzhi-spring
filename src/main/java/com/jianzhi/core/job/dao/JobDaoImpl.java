package com.jianzhi.core.job.dao;

import com.jianzhi.core.job.model.JobDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by daniel on 2016/1/24.
 */
public class JobDaoImpl implements JobDaoCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public JobDetailInfo findDetailInfoOne(Long id) {
        Query query = entityManager.createNativeQuery(
                "SELECT j.id AS jid, j.title, j.detail, j.salary, j.salary_type, " +
                        "u.id AS uid, u.nick_name, u.head_image, " +
                        "c.id AS cid, c.name, c.description, c.address " +
                        "FROM job AS j " +
                        "JOIN user AS u ON j.user=u.id " +
                        "LEFT JOIN company AS c ON u.id=c.user " +
                        "WHERE j.id="+id);
        Object[] obj = (Object[])query.getSingleResult();

        if (obj == null || obj.length == 0) {
            return null;
        }

        JobDetailInfo jobDetailInfo = new JobDetailInfo();
        int i = 0;

        jobDetailInfo.setJid(((BigInteger)obj[i++]).longValue());
        jobDetailInfo.setJtitle((String)obj[i++]);
        jobDetailInfo.setJdetail((String)obj[i++]);
        jobDetailInfo.setSalary(((Integer) obj[i++]).intValue());
        jobDetailInfo.setSalaryType(((Integer)obj[i++]).intValue());

        jobDetailInfo.setUid(((BigInteger)obj[i++]).longValue());
        jobDetailInfo.setNickName((String)obj[i++]);
        jobDetailInfo.setHeadImage((String)obj[i++]);

        jobDetailInfo.setCid(((BigInteger)obj[i++]).longValue());
        jobDetailInfo.setCname((String)obj[i++]);
        jobDetailInfo.setCdescription((String)obj[i++]);
        jobDetailInfo.setAddress((String)obj[i++]);

        return jobDetailInfo;
    }

}
