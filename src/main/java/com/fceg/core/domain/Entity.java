package com.fceg.core.domain;

import javax.persistence.Id;
import java.io.Serializable;

public class Entity implements Serializable {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
