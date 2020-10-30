package com.leidos.demo.sbquarkus.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.jboss.logmanager.LogManager;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.leidos.demo.sbquarkus.model.Lineitems;
import com.leidos.demo.sbquarkus.service.LineitemsService;


/*
 * @Inject annotation to inject UserRepository. The annotation added to the service constructor.
 * 
 */

@RequestScoped
@Path("/api/li")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LineitemsController {
	
	private static final Logger _logger = Logger.getLogger(LineitemsController.class);
	
	private final LineitemsService lineitemsService;
	
	@Inject
	public LineitemsController(LineitemsService lineitemsService) {
		this.lineitemsService = lineitemsService;
	}
	
    @GET
    public List<Lineitems> getLineitems() {
        return lineitemsService.getLineitems();
    }

    @GET
    @Path("/{id}")
    public Lineitems getLineitem(@PathParam("id") long id) {
    	_logger.log(Level.INFO,"id supplied.."+id);
        return lineitemsService.getOneLi(id);
    }
    
    /*
     * curl -X POST "http://localhost:8082/api/li" -H  "accept: application/json" -H  "Content-Type: application/json" -d 
     * "{\"description\":\"li description\",\"lineItemNumber\":\"0010\",\"nomenclature\":\"software\",\"quantity\":5,\"totalCost\":5000,\"unitPrice\":1000}"
     */
    @POST
    public Lineitems createLineitem(@Valid LineItemDto lineItemDto) {
        return lineitemsService.saveLineitem(lineItemDto.toLineitem());
    }
    
    @PUT
    @Path("/{id}")
    public Lineitems updateLineitem(@PathParam("id") long id, @Valid LineItemDto lineItemDto)  {
        try {
			return lineitemsService.updateLineitem(id, lineItemDto.toLineitem());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteLineitem(@PathParam("id") long id)  {
    	try {
			lineitemsService.deleteLineitem(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Response.status(Response.Status.NO_CONTENT).build();
    }

	
	/*
	 * D T O
	 * 
	 */
    public static class LineItemDto {

        @NotBlank
        private String description;

        @NotBlank
        private String lineItemNumber;
        
        @NotBlank
        private String nomenclature;

        @Min(value = 1, message = "The value must be more than 0")
        @Max(value = 99, message = "The value must be less than 99")
        private int quantity;
        
        private double unitPrice;
        
        private double totalCost;



        public String getDescription() {
			return description;
		}



		public void setDescription(String description) {
			this.description = description;
		}



		public String getLineItemNumber() {
			return lineItemNumber;
		}



		public void setLineItemNumber(String lineItemNumber) {
			this.lineItemNumber = lineItemNumber;
		}



		public String getNomenclature() {
			return nomenclature;
		}



		public void setNomenclature(String nomenclature) {
			this.nomenclature = nomenclature;
		}



		public int getQuantity() {
			return quantity;
		}



		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}



		public double getUnitPrice() {
			return unitPrice;
		}



		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}



		public double getTotalCost() {
			return totalCost;
		}



		public void setTotalCost(double totalCost) {
			this.totalCost = totalCost;
		}



		public Lineitems toLineitem() {
			Lineitems lineitem = new Lineitems();
			lineitem.setDescription(description);
			lineitem.setLineItemNumber(lineItemNumber);
			lineitem.setNomenclature(nomenclature);
			lineitem.setQuantity(quantity);
			lineitem.setTotalCost(totalCost);
			lineitem.setUnitPrice(unitPrice);
            return lineitem;
        }
    }
}
