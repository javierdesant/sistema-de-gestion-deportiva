package es.upm.etsisi.utils;

public class RestrictedEmail extends EmailAddress {
    protected RestrictedEmail(String email, DomainPolicy domainPolicy) {
        super(email);
        if (!domainPolicy.isValidForDomain(email)) {
            throw new IllegalArgumentException("Email does not conform to the domain policy: " + email);
        }
    }
}
