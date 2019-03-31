package net.laoyeye.yyms.pojo.strategy;

import net.laoyeye.utils.IdUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @description: 自定义ID生成策略
 * @author: laoyeye.net
 * @date: 2019/3/28 10:13
 */
public class IdentifierGeneratorImpl implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {

        return IdUtils.getOrderNo();
    }
}