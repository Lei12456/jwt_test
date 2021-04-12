package com.yl.admin.dao;

import com.yl.admin.domain.TProduct;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Description:
 *
 * @author YangLei
 * @date Created on 2021/4/6
 */
@Repository
public class ProductQueryRepository {

    @PersistenceContext()
    private EntityManager entityManager;


    public List<TProduct> getProductByFilter(Long brand,Long type,String name,Integer page,Integer pageSize) {
        Session session = entityManager.unwrap(Session.class);
        String sql = getProductFilterSql(brand,type,name,page,pageSize);
        if (StringUtils.isBlank(sql)) {
            return null;
        }
        //hibernate createQuery()查询结果放到对象中
        Query query = session.createQuery(sql);
        //分页
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }
    public void deleteProduct(List<Long> ids){
        String idString = ids.toString();
        StringBuffer stringBuffer = new StringBuffer(idString);
        stringBuffer.substring(1,stringBuffer.length());
        System.out.println(stringBuffer);
        String hql = "delete from t_product tp where tp.id in ("+stringBuffer+")";
        Session session = entityManager.unwrap(Session.class);
        if (!StringUtils.isBlank(hql)) {
            Query query = session.createQuery(hql);
            //query.setParameterList("ids", ids).executeUpdate();
        }
    }
    public String getProductFilterSql(Long brand,Long type,String name,Integer page,Integer pageSize){
        StringBuffer sql = new StringBuffer("select tp from t_product tp\n");
        boolean isWhere = false;
        if(name != null && !"".equals(name)){
            if (isWhere){
                sql.append("and tp.name like '%"+name+"%'\n");
            }else {
                sql.append("where tp.name like '%"+name+"%'\n");
                isWhere = true;
            }
        }
        if(type != null && type != 0){
            if (isWhere){
                sql.append("and tp.productSubType = "+type+"\n");
            }else {
                sql.append("where tp.productSubType  = " + type+"\n");
                isWhere = true;
            }
        }
        if(brand != null && brand != 0) {
            if (isWhere) {
                sql.append("and tp.brand = " + brand + "\n");
            } else {
                sql.append("where tp.brand = " + brand + "\n");
                isWhere = true;
            }
        }
        return sql.toString();
    }
}
