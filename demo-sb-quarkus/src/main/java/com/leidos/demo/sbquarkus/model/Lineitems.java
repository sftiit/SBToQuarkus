package com.leidos.demo.sbquarkus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Lineitems")
public class Lineitems {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "line_item_number", nullable = false)
    @NotNull(message="{NotNull"
    		+ ".Lineitem.lineItemNumber}")
    private String lineItemNumber;
    
    @Column
    @NotNull(message="{NotNull.Lineitem.nomenclature}")
    private String nomenclature;
    
    @Column
    @NotNull(message="{NotNull.Lineitem.description}")
    private String description;
    
    @Column
    @NotNull(message="{NotNull.Lineitem.quantity}")
    private int quantity;
    
    @Column(name = "unit_price", nullable = false)
    @NotNull(message="{NotNull.Lineitem.unitPrice}")
    private double unitPrice;
    
    @Column(name = "total_cost", nullable = false)
    @NotNull(message="{NotNull.Lineitem.totalCost}")
    private double totalCost;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
