package org.prowikiq.browser.domain.dto;

<<<<<<< HEAD
import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

=======
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
import org.prowikiq.browser.domain.entity.BrowserList;
=======
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72
import org.prowikiq.object.domain.entity.FilePath;

/**
 * Class: BrowserListCreateDto Project: prowikiQ Package: org.prowikiq.browser.domain.dto
 * <p>
 * Description: BrowserListCreateDto
 *
 * @author dong-hoshin
 * @date 4/29/24 21:16 Copyright (c) 2024 Lyckabc
 * @see <a href="https://github.com/lyckabc">GitHub Repository</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrowserListCreateDto {

    private Long browserListId;
    private FilePath filePath;
    private String pageTitle;
    private String pageCategory;
    private LocalDateTime targetDay;
    private LocalDateTime finishedDay;
    private Boolean isFolder;
<<<<<<< HEAD
    //BaseEntity
    private LocalDateTime createdAt; // Include createdAt
    private LocalDateTime modifiedAt; // Include modifiedAt

    public BrowserList toBrowserList() {
        BrowserList browserList = new BrowserList();
        browserList.setBrowserListId(this.browserListId);
        browserList.setPageTitle(this.pageTitle);
        browserList.setPageCategory(this.pageCategory);
//        browserList.setTargetDay(this.targetDay);
//        browserList.setFinishedDay(this.finishedDay);
        browserList.setIsFolder(this.isFolder);
//        browserList.setCreatedAt(this.createdAt);
//        browserList.setModifiedAt(this.modifiedAt);
        return browserList;
    }
=======
>>>>>>> 70eb822267a566fdaf65a07b7fcb7c65d8d73a72

}
