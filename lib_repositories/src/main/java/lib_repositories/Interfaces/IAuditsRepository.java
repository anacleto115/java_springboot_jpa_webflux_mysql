
package lib_repositories.Interfaces;

import javax.persistence.EntityManagerFactory;
import lib_domain.Entities.Audits;

public interface IAuditsRepository
{
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory);
    
    Audits Insert(Audits entity) throws Exception;
}