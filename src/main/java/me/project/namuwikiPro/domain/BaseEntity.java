package me.project.namuwikiPro.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@EntityListeners(value = {AuditingEntityListener.class})
@Getter @Setter
@MappedSuperclass
public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "regTime",updatable = false)
    private LocalDateTime registerTime;
    @LastModifiedDate
    @Column(name = "upTime",updatable = true)
    private LocalDateTime updateTime;
    @CreatedBy
    private String boardCreater;
    @LastModifiedBy
    private String boardUpdater;


}
