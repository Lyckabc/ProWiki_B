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
import lombok.experimental.SuperBuilder;
import org.prowikiq.global.BaseEntity;
import org.prowikiq.object.domain.dto.StorageObjectDto;
import org.prowikiq.wiki.domain.dto.WikiPageDto;

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
@SuperBuilder
@Entity
@Table(name = "\"storage_object\"")
public class StorageObject extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "is_folder")
    private Boolean isFolder;

    @Column(name = "object_path")
    private String objectPath;

    @Column(name = "object_size")
    private BigInteger objectSize;

    @Column(name = "object_format")
    private String objectFormat;



    // Custom logic can be placed in methods like these
    public void updateStorageObject(String objectName, Boolean isFolder, String objectPath) {
        if (objectName != null) setObjectName(objectName);
        if (isFolder != null) setIsFolder(isFolder);
        if (objectPath != null) setObjectPath(objectPath);
        updateModifiedAt();
    }

    public StorageObjectDto toDto() {
        return StorageObjectDto.builder()
            .objectId(this.objectId)
            .objectName(this.objectName)
            .isFolder(this.isFolder)
            .objectPath(this.objectPath)
            .objectSize(this.objectSize)
            .objectFormat(this.objectFormat)
            .createdAt(this.getCreatedAt())
            .modifiedAt(this.getModifiedAt())
            .latestedAt(this.getLatestedAt())
            .build();
    }

}