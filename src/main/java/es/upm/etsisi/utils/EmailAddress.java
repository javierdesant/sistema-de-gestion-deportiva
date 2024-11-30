package es.upm.etsisi.utils;

public class EmailAddress {
    private final String email;

    protected EmailAddress(String email) {
        this.email = email;
    }

    public static EmailAddress valueOf(String email, EmailValidator validator) {
        if (!validator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }
        return new EmailAddress(email);
    }

    public static EmailAddress valueOf(String email) {
        return valueOf(email, new DefaultEmailValidator());
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
