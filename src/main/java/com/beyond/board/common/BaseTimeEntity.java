package com.beyond.board.common;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @CreationTimestamp
    private LocalDateTime createdTime;

    @CreationTimestamp
    private LocalDateTime updatedTime;
}
