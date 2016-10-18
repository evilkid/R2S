package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.ICategoryService;
import tn.esprit.R2S.model.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class CategoryService extends AbstractService<Category> implements ICategoryService {

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
