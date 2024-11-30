package es.upm.etsisi.utils;

public class RestrictedEmail extends EmailAddress {
    private final DomainPolicy domainPolicy;

    protected RestrictedEmail(String email, DomainPolicy domainPolicy) {
        super(email);
        if (!domainPolicy.isValidForDomain(email)) {
            throw new IllegalArgumentException("Email does not conform to the domain policy: " + email);
        }
        this.domainPolicy = domainPolicy;
    }

    public static RestrictedEmail valueOf(String email, DomainPolicy domainPolicy) {
        return new RestrictedEmail(email, domainPolicy);
    }

    public DomainPolicy getDomainPolicy() {
        return this.domainPolicy;
    }
}