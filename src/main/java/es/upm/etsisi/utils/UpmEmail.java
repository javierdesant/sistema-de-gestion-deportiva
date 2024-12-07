package es.upm.etsisi.utils;

public class UpmEmail extends RestrictedEmail {
    private static final DomainPolicy UPM_POLICY = new UpmDomainPolicy();

    private UpmEmail(String email) {
        super(email, UPM_POLICY);
    }

    public static UpmEmail valueOf(String email) throws IllegalArgumentException {
        return new UpmEmail(email);
    }
}
