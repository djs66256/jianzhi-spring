package com.jianzhi.core.job.dao;

/**
 * Created by daniel on 2016/1/24.
 */
public class JobDaoImpl implements JobDaoCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public JobDetailInfo findDetailInfoOne(Long id) {
        @SqlResultSetMapping(name = "jobDetailInfo",
                entities = @EntityResult(entityClass = JobDetailInfo.class,
                        fields = {
                                @FieldResult(name="jid", column="jid"),
                                @FieldResult(name="cid", column="cid"),
                                @FieldResult(name="uid", column="uid")
                        }))
        Query query = entityManager.createNativeQuery(
                "SELECT j.id AS jid, j.title AS jtitle, u.id AS uid, c.id AS cid " +
                        "FROM job AS j " +
                        "JOIN user AS u ON j.user=u.id " +
                        "LEFT JOIN company AS c ON u.id=c.user " +
                        "WHERE j.id="+id, JobDetailInfo.class);
        List obj = query.getResultList();

        return null;
    }

}
