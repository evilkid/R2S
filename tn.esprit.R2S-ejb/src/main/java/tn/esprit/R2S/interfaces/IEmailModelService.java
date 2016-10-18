package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.EmailModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IEmailModelService {
    void create(EmailModel entity);

    EmailModel edit(EmailModel entity);

    void remove(EmailModel entity);

    EmailModel find(Object id);

    List<EmailModel> findAll();
}
