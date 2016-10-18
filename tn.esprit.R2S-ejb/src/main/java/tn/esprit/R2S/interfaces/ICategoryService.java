package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Category;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICategoryService {
    void create(Category category);

    Category edit(Category category);

    void remove(Category category);

    Category find(Object id);

    List<Category> findAll();
}
