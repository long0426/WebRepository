package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MchefBean;
import model.MchefDAO;

@Repository(value = "mchefDao")
public class MchefDAOHibernate implements MchefDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public MchefBean select(MchefBean bean) {
		return this.getSession().get(MchefBean.class, bean.getMc_id());
	}

	@Override
	public int insert(MchefBean bean) {
		return (int) this.getSession().save(bean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MchefBean> selectAll() {
		Query query = this.getSession().createQuery("from MchefBean");
		return (List<MchefBean>) query.getResultList();
	}

	@Override
	public MchefBean update(MchefBean bean) {
		if (null != select(bean)) {
			getSession().clear();
			this.getSession().update(bean);
			return select(bean);
		}else{
			return null;
		}
	}

}
