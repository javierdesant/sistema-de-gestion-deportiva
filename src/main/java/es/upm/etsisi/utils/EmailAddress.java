package es.upm.etsisi.utils;

public class EmailAddress {
    private final String email;

    protected EmailAddress(String email, EmailValidator validator) {
        this.email = email;
        if (!validator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }
    }

    protected EmailAddress(String email) {
        this(email, new DefaultEmailValidator());
    }

    public static EmailAddress valueOf(String email) throws IllegalArgumentException {
        return new EmailAddress(email);
    }

    @Override
    public String toString() {
        return this.email;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        EmailAddress that = (EmailAddress) object;
        return this.email.equalsIgnoreCase(that.email);
    }

    @Override
    public int hashCode() {
        return this.email.toLowerCase().hashCode();
    }
}
