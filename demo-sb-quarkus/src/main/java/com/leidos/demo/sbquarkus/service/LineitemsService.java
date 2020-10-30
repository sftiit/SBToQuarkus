package com.leidos.demo.sbquarkus.service;

import java.util.List;

import com.leidos.demo.sbquarkus.model.Lineitems;

public interface LineitemsService {
	
	Lineitems getOneLi(long id);
	
	List<Lineitems> getLineitems();
	
	Lineitems saveLineitem(Lineitems lineitems);
	
	Lineitems updateLineitem(long id, Lineitems lineitems) throws Exception;
	
	void deleteLineitem(long id) throws Exception;
	
}
