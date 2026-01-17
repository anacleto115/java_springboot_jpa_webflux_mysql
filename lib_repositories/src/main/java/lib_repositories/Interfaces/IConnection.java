
package lib_repositories.Interfaces;

import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface IConnection 
{
    void setEntityManagerFactory(EntityManagerFactory emf);
    
    <T extends Object> List Select(Class type);
    <T extends Object> List Join(Class type, HashMap<String, Object> inner);
    <T extends Object> List Where(Class type, String where, HashMap<String, Object> parameters);
    Object Insert(Object entity);
    Object Update(Object entity);
    <T extends Object> Object Delete(Class type, int id);
}