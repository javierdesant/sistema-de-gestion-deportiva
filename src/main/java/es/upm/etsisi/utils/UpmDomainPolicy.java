package es.upm.etsisi.utils;

public class UpmDomainPolicy implements DomainPolicy {
    private static final String[] UPM_DOMAINS = {"@upm.es", "@alumnos.upm.es"};

    @Override
    public boolean isValidForDomain(String email) {
        if (email == null) {
            return false;
        }
        String lowerCaseEmail = email.toLowerCase();
        for (String domain : UPM_DOMAINS) {
            if (lowerCaseEmail.endsWith(domain)) {
                return true;
            }
        }
        return false;
    }
}
