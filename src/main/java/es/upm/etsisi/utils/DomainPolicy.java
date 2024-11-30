package es.upm.etsisi.utils;

public interface DomainPolicy {
    boolean isValidForDomain(String email);
}
