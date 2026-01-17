
package ut_presentation.Repositories;

import lib_domain.Entities.Audits;
import java.util.List;
import lib_repositories.Interfaces.IConnection;
import lib_repositories.Implementations.Connection;
import lib_repositories.Interfaces.IAuditsRepository;
import lib_repositories.Implementations.AuditsRepository;
import org.junit.Test;
import org.springframework.util.Assert;
import ut_presentation.Core.EntitiesCore;

public class AuditsTest 
{
    private IAuditsRepository IRepository = null;
    private List<Audits> list = null;
    private Audits entity = null;

    public AuditsTest() throws Exception
    {
        IConnection IConnection = new Connection();
        IRepository = new AuditsRepository(IConnection);
    }
    
    @Test
    public void Execute() throws Exception
    {
        Assert.isTrue(Insert());
    }
    
    private boolean Insert() throws Exception
    {
        this.entity = EntitiesCore.Audits();
        this.entity = (Audits)this.IRepository.Insert(this.entity);
        return true;
    }
}