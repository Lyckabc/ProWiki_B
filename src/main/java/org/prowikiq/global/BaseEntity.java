package org.prowikiq.global;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Class: BaseEntity Project: prowikiQ Package: org.prowikiq.global
 * <p>
 * Description: BaseEntity
 *
 * @author dong-hoshin
 * @date 4/23/24 21:59 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity{
  @CreatedDate
  @Column(nullable = false, updatable = false,  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = true)
  private LocalDateTime modifiedAt;

  @LastModifiedDate
  @Column(name = "latested_at", nullable = true)
  private LocalDateTime latestedAt;

  public void updateModifiedAt() {
    this.modifiedAt = LocalDateTime.now();
  }


}
