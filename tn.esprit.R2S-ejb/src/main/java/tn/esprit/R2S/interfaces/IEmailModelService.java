package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.EmailModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IEmailModelService {
    void create(EmailModel emailModel);

    EmailModel edit(EmailModel emailModel);

    void remove(EmailModel emailModel);

    EmailModel find(Object id);

    List<EmailModel> findAll();
}
