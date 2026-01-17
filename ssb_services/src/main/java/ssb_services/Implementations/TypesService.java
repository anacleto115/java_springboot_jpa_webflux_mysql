
package ssb_services.Implementations;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import lib_domain.Entities.Products;
import lib_domain.Entities.Types;
import lib_repositories.Interfaces.ITypesRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssb_services.Core.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Types")
public class TypesService
{      
    private Environment environment = null;
    private ITypesRepository IRepository = null;
    
    public TypesService(Environment environment, ITypesRepository iRepository) 
    {
        this.environment = environment;
        this.IRepository = iRepository;
        
        EntityManagerFactory entityManagerFactory = Configuration.getEntityManagerFactory(environment);
        this.IRepository.setEntityManagerFactory(entityManagerFactory);
    }
    
    @GetMapping("/Select") 
    public Flux<Types> Select(@RequestBody Types entity) throws Exception
    { 
        List<Types> response = new ArrayList<Types>();
        response = this.IRepository.Select();
        response.forEach((item) -> {
            item.setProducts(new ArrayList<Products>());
        });
        return Flux.fromIterable(response);
    }
    
    @PostMapping("/Where")
    public Mono<List<Types>> Where(@RequestBody Types entity) throws Exception
    {
        List<Types> response = new ArrayList<Types>();
        response = this.IRepository.Where(entity);
        return Mono.just(response);
    }
    
    @PostMapping("/Insert") 
    public Flux<Types> Insert(@RequestBody Types entity) throws Exception 
    { 
        entity = this.IRepository.Insert(entity);
        return Flux.just(entity);
    }
    
    @PutMapping("/Update") 
    public Flux<Types> Update(@RequestBody Types entity) throws Exception
    { 
        entity = this.IRepository.Update(entity);
        return Flux.just(entity);
    }
    
    @DeleteMapping("/Delete") 
    public Flux<Types> Delete(@RequestBody Types entity) throws Exception
    { 
        entity = this.IRepository.Delete(entity);
        return Flux.just(entity);
    }
}