package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.EmailModel;

import javax.ejb.Remote;
import java.util.List;

@Remote

public interface IEmailModelService {
    void create(EmailModel emailModel);

    EmailModel edit(EmailModel emailModel);

    void remove(EmailModel emailModel);

    EmailModel find(Object id);

    List<EmailModel> findAll();

    void sendEmail(Long emailModelId, Long cin, Long jobId);

    String getVariables();

    String parseEmail(Long emailModelId, Long cin, Long jobId);

}
