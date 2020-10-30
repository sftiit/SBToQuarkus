package com.leidos.demo.sbquarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import com.leidos.demo.sbquarkus.model.Lineitems;

/*
 * Adding @ApplicationScoped allows Quarkus to manage the bean.
 * PanacheRepository has some base methods that are sufficient for most CRUD operations.
 */

@ApplicationScoped
public class LineitemsRepository implements PanacheRepository<Lineitems> {}
