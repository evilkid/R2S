package tn.esprit.R2S.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class RecruitmentManager extends Users implements Serializable {

    @OneToMany(targetEntity = EmailModel.class, mappedBy = "recruitmentManager")
    private List<EmailModel> emailModels;

    @OneToMany(targetEntity = Notification.class, mappedBy = "recruitmentManager")
    private List<Notification> notifications;

    @OneToMany(targetEntity = Interview.class, mappedBy = "recruitmentManager")
    private List<Interview> interviews;

    public List<EmailModel> getEmailModels() {
        return this.emailModels;
    }

    public void setEmailModels(List<EmailModel> emailModels) {
        this.emailModels = emailModels;
    }

    public List<Notification> getNotifications() {
        return this.notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Interview> getInterviews() {
        return this.interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

}
