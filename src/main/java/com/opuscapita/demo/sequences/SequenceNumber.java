package com.opuscapita.demo.sequences;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "sequence_numbers")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SequenceNumber {

    @Id
    private String sequenceName;

    private long nextValue;

    @UpdateTimestamp
    private Instant updatedAt;

    public long pickNextValue() {
        return nextValue++;
    }
}
