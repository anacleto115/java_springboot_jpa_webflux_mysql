
package lib_domain.Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "audits")
public class Audits 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    
    @Column(name = "action", nullable = false, length = 150)
    private String Action;
    
    @Column(name = "description", nullable = false, length = 500)
    private String Description;
    
    @Column(name = "date", nullable = false)
    private Date Date;

    
    public Audits() 
    { 
        this.Date = new Date();
    }
    public Audits(String action, String description) 
    {
        this.Action = action;
        this.Description = description;
        this.Date = new Date();
    }
    
    
    public void setId(int v) { Id = v; }
    public int getId() { return Id; }
    
    public void setAction(String v) { Action = v; }
    public String getAction() { return Action; }
    
    public void setDescription(String v) { Description = v; }
    public String getDescription() { return Description; }
    
    public void setDate(Date v) { Date = v; }
    public Date getDate() { return Date; }
}
