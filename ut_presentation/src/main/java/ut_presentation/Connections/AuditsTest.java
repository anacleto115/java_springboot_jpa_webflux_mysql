
package ut_presentation.Connections;

import lib_domain.Entities.Audits;
import lib_repositories.Implementations.Connection;
import lib_repositories.Interfaces.IConnection;
import java.util.List;
import org.junit.Test;
import org.springframework.util.Assert;
import ut_presentation.Core.EntitiesCore;

public class AuditsTest 
{
    private IConnection IConnection = null;
    private List<Audits> list = null;
    private Audits entity = null;

    public AuditsTest() throws Exception
    {
        IConnection = new Connection();
    }
    
    @Test
    public void Execute()
    {
        Assert.isTrue(Insert());
    }
    
    private boolean Insert()
    {
        this.entity = EntitiesCore.Audits();
        this.entity = (Audits)this.IConnection.Insert(this.entity);
        return true;
    }
}