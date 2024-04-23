package EJBs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Controller {

    @POST
    @Path("/calc")
    public Calculation calculate(Calculation calculation) {
        int result = calculation.calculate();
        calculation.setResult(result);
        calculation.save();
        return calculation;
    }
    
    
    @PersistenceContext(unitName = "hello")
    private EntityManager entityManager;

    @GET
    @Path("/calculations")
    public List<Calculation> getCalculations() {
        return entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class).getResultList();
    }
}
