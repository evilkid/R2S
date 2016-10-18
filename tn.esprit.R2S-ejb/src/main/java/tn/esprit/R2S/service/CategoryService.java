package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Category;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("category")
public class CategoryService extends AbstractService<Category> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public CategoryService() {
        super(Category.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
