
package lib_repositories.Interfaces;

import lib_domain.Entities.Types;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface ITypesRepository 
{
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory);
    
    List<Types> Select() throws Exception;
    List<Types> Where(Types entity) throws Exception;
    Types Insert(Types entity) throws Exception;
    Types Update(Types entity) throws Exception;
    Types Delete(Types entity) throws Exception;
}