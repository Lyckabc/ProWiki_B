package org.prowikiq.object.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prowikiq.global.BaseEntity;

/**
 * Class: Object Project: prowikiQ Package: org.prowikiq.object.domain.entity
 * <p>
 * Description: Object Entity
 *
 * @author dong-hoshin
 * @date 4/29/24 21:34 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"object\"")
public class Object extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "is_folder")
    private Boolean isFolder;

}