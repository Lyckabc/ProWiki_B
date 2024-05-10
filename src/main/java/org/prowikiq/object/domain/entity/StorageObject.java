package org.prowikiq.object.domain.entity;

import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "\"storage_object\"")
public class StorageObject extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "object_title")
    private String objectTitle;

    @Column(name = "is_folder")
    private Boolean isFolder;

    //filePath for Object
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "object_path_id", referencedColumnName = "file_path_id")
    private FilePath objectPathId;

    @Column(name = "object_path")
    private String objectPath;

    @Column(name = "object_size")
    private BigInteger objectSize;

    @Column(name = "object_format")
    private String objectFormat;


}