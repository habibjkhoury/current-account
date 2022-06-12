package com.demo.currentaccount.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @JsonIgnore
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonIgnore
    @CreationTimestamp
    private Timestamp updatedAt;
}
