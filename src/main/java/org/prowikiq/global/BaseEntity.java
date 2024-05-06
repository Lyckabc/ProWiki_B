package org.prowikiq.global;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import org.prowikiq.browser.domain.entity.BrowserList;
import org.prowikiq.browser.domain.entity.BrowserList.Builder;
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

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity{
  @CreatedDate
  @Column(nullable = false, updatable = false,  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(nullable = true)
  private LocalDateTime modifiedAt;

  // Getters and setters
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(LocalDateTime modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  //builder
  public static class Builder {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BaseEntity.Builder createdAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public BaseEntity.Builder modifiedAt(LocalDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    private BaseEntity(BaseEntity.Builder builder) {
      this.createdAt = builder.createdAt;
      this.modifiedAt = builder.modifiedAt;
    }
  }


}
