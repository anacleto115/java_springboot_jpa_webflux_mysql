
package lib_repositories.Interfaces;

import java.util.HashMap;
import javax.persistence.EntityManagerFactory;

public interface IApisRepository
{
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory);
    
    HashMap<String, Object> Select() throws Exception;
}