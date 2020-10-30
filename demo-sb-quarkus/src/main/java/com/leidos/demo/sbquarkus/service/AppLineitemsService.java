package com.leidos.demo.sbquarkus.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.leidos.demo.sbquarkus.controller.LineitemsController;
import com.leidos.demo.sbquarkus.model.Lineitems;
import com.leidos.demo.sbquarkus.repository.LineitemsRepository;


/*
 * Service impl class
 * @Inject annotation to inject LineitemsRepository. The annotation added to the service constructor.
 * 
 */
@ApplicationScoped
public class AppLineitemsService implements LineitemsService{
	
	private static final Logger _logger = Logger.getLogger(AppLineitemsService.class);
	
	private final LineitemsRepository lineitemsRepository;
	
	@Inject
	public AppLineitemsService(LineitemsRepository lineitemsRepository) {
		this.lineitemsRepository = lineitemsRepository;
	}
	
    public Lineitems getOneLi(long id) {
        try {
        	_logger.log(Level.INFO,"id supplied....."+id);
			return lineitemsRepository.findByIdOptional(id).orElseThrow(() -> new Exception("not found"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public List<Lineitems> getLineitems() {
    	_logger.log(Level.INFO,"get all line items.....");
        return lineitemsRepository.listAll();
    }
    
    @Transactional
    public Lineitems saveLineitem(Lineitems lineitem) {
    	lineitemsRepository.persistAndFlush(lineitem);
        return lineitem;
    }
	
    @Transactional
    public Lineitems updateLineitem(long id, Lineitems lineitem) throws Exception {
    	Lineitems existingLi = getOneLi(id);
    	existingLi.setDescription(lineitem.getDescription());
    	existingLi.setLineItemNumber(lineitem.getLineItemNumber());
    	existingLi.setNomenclature(lineitem.getNomenclature());
    	existingLi.setQuantity(lineitem.getQuantity());
    	existingLi.setUnitPrice(lineitem.getUnitPrice());
    	existingLi.setTotalCost(lineitem.getTotalCost());
    	lineitemsRepository.persist(existingLi);
        return existingLi;
    }
    
    @Transactional
    public void deleteLineitem(long id) throws Exception {
    	lineitemsRepository.delete(getOneLi(id));
    }
}
