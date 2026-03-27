package br.com.ejb.resources;

import br.com.ejb.dao.BeneficioDao;
import br.com.ejb.exception.ExceptionVazio;
import br.com.ejb.pojo.Beneficio;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/beneficio")
public class BeneficioService {

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        // Simulação de dados (em produção você buscaria do banco via DAO/EJB)
        List<Beneficio> beneficios = new ArrayList<>();
        beneficios.add(new Beneficio(1, "Vale Transporte", "Auxílio transporte", 200.00, true));
        beneficios.add(new Beneficio(2, "Vale Refeição", "Auxílio alimentação", 500.00, true));

        return Response.ok(beneficios).build();
    }
    
    @GET
    @Path("/listarTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos()
    {
        BeneficioDao beneficioDao = new BeneficioDao();

        try{
            var listBeneficios = beneficioDao.listarTodos();
            return Response.ok(listBeneficios).build();
        }catch (ExceptionVazio e) 
        {
            // Tratamento específico para resultado vazio
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(Map.of("Aviso", e.getMessage()))
                           .build();
        }   
        catch(Exception e){
            e.printStackTrace();
             return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                   .entity(Map.of("Erro", e.getMessage()))
                   .build();
        }    
    }
}