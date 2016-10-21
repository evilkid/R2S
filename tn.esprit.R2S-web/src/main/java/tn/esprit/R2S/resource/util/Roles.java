package tn.esprit.R2S.resource.util;

public enum Roles {
    EMPLOYEE("Employee"),
    CANDIDATE("Candidate"),
    RECRUITMENT_MANAGER("RecruitmentManager"),
    CHIEF_HUMAN_RESOURCES_OFFICER("ChiefHumanResourcesOfficer"),
    ANONYMOUS("Anonymous");

    private final String role;

    Roles(String value) {
        this.role = value;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
