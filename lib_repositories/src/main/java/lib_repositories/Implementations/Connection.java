
package lib_repositories.Implementations;

import java.util.HashMap;
import lib_repositories.Interfaces.IConnection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class Connection implements IConnection
{
    private EntityManagerFactory EntityManagerFactory = null;
        
    @Override
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) 
    {
        this.EntityManagerFactory = entityManagerFactory;
    }
    
    @Override
    public <T extends Object> List Select(Class type) 
    {
        EntityManager entityManager = EntityManagerFactory.createEntityManager();
        try 
        {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(type);
            Root<T> root = cq.from(type);
            cq.select(root);
            return entityManager.createQuery(cq).getResultList();
        } 
        catch (Exception ex) 
        {
            throw ex;
        } 
        finally 
        {
            entityManager.close();
        }
    }
    
    @Override
    public <T extends Object> List Join(Class type, HashMap<String, Object> inner)
    {
        EntityManager entityManager = EntityManagerFactory.createEntityManager();
        try 
        {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(type);
            Root<T> root = cq.from(type);
            
            for (Map.Entry<String, Object> entry : inner.entrySet())
                root.fetch(entry.getValue().toString(), JoinType.INNER);
            
            cq.select(root);
            return entityManager.createQuery(cq).getResultList();
        } 
        catch (Exception ex) 
        {
            throw ex;
        } 
        finally 
        {
            entityManager.close();
        }
    }
    
    @Override
    public <T extends Object> List Where(Class type, String where, HashMap<String, Object> parameters)
    {
        EntityManager entityManager = EntityManagerFactory.createEntityManager();
        try 
        {
            Query query = entityManager.createQuery(where, type);            
            for (Map.Entry<String, Object> entry : parameters.entrySet())
                query.setParameter(entry.getKey(), entry.getValue());
            query.setMaxResults(100);
            query.setFirstResult(0);
            return query.getResultList();
        } 
        catch (Exception ex) 
        {
            throw ex;
        } 
        finally 
        {
            entityManager.close();
        }
    }
    
    @Override
    public Object Insert(Object entity)
    {
        EntityManager entityManager = EntityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try 
        {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity;
        } 
        catch (Exception ex) 
        {
            if (transaction != null && transaction.isActive()) 
                transaction.rollback();
            throw ex;
        } 
        finally 
        {
            entityManager.close();
        }
    }
    
    @Override
    public Object Update(Object entity)
    {
        EntityManager entityManager = EntityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try 
        {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity = entityManager.merge(entity);
            transaction.commit();
            return entity;
        } 
        catch (Exception ex) 
        {
            if (transaction != null && transaction.isActive()) 
                transaction.rollback();
            throw ex;
        } 
        finally 
        {
            entityManager.close();
        }
    }
    
    @Override
    public <T extends Object> Object Delete(Class type, int id)
    {
        EntityManager entityManager = EntityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try 
        {
            transaction = entityManager.getTransaction();
            transaction.begin();            
            Object entity = entityManager.find(type,id);
            if (entity != null) 
            {
                entityManager.remove(entity);
            }
            transaction.commit();
            return entity;
        } 
        catch (Exception ex) 
        {
            if (transaction != null && transaction.isActive()) 
                transaction.rollback();
            throw ex;
        } 
        finally 
        {
            entityManager.close();
        }
    }
}