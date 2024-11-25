package es.upm.etsisi.utils;

import java.time.LocalDate;

public record TimeFrame(LocalDate startDate, LocalDate endDate) {
    public TimeFrame {
        assert startDate.isBefore(endDate);
    }

    public boolean includes(LocalDate date) {
        return this.startDate.isBefore(date) && this.endDate.isAfter(date);
    }

    public boolean includesNow() {
        return this.includes(LocalDate.now());
    }
}