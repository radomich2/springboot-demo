package com.opuscapita.demo.sequences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

@Service
public class SequenceNumberService {

    @Autowired
    private EntityManager em;

    @Transactional(propagation = Propagation.MANDATORY)
    public long getNextOrderNumber() {
        return em.find(SequenceNumber.class, "ORDER_NUMBER", LockModeType.PESSIMISTIC_WRITE)
            .pickNextValue();
    }
}
