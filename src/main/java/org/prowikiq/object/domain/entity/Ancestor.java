package org.prowikiq.object.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class: Ancestor Project: prowikiQ Package: org.prowikiq.object.domain.entity
 * <p>
 * Description: Ancestor
 *
 * @author dong-hoshin
 * @date 5/7/24 17:53 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"ancestor\"")
public class Ancestor {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ancestor_id")
    private Long ancestorId;

    @Column(name = "ancestor")
    private String ancestor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_folder_id", referencedColumnName = "object_id")
    private StorageObject parentFolderId;

    @Column(name = "parent_folder")
    private String parentFolder;

}
