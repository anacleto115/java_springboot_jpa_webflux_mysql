
package lib_domain.Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id = 0;
    
    @Column(name = "name", nullable = false, length = 150)
    private String Name = null;
    
    @Column(name = "price", nullable = false)
    private double Price = 0.0;
    
    @Column(name = "expire", nullable = false)
    private Date Expire = null;
    
    @JoinColumn(name = "type", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Types Type = null;
    
    @Column(name = "active", nullable = false)
    private boolean Active = false;
    
    @Column(name = "image", nullable = true)
    private String Image = null;
    
    
    public Products() { }

    
    public void setId(int v) { Id = v; }
    public int getId() { return Id; }
    
    public void setName(String v) { Name = v; }
    public String getName() { return Name; }
    
    public void setPrice(double v) { Price = v; }
    public double getPrice() { return Price; }
    
    public void setExpire(Date v) { Expire = v; }
    public Date getExpire() { return Expire; }
    
    public void setType(Types v) { Type = v; }
    public Types getType() { return Type; }
    
    public void setActive(boolean v) { Active = v; }
    public boolean getActive() { return Active; }
    
    public void setImage(String v) { Image = v; }
    public String getImage() { return Image; }
}
