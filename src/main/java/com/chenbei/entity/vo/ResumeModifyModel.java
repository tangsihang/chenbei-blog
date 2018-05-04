package com.chenbei.entity.vo;

import com.chenbei.entity.Resume;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 简历修改表单
 * <p>
 *
 * @author James
 * @date 17-12-4
 */
@Setter
@Getter
@NoArgsConstructor
public class ResumeModifyModel {

  @NotEmpty
  private Integer id;

  @NotEmpty
  private String title;

  private String mdMaterial;

  private String description;

  public ResumeModifyModel(Resume article) {
    this.id = article.getId();
    this.title = article.getTitle();
    this.mdMaterial = article.getMdMaterial();
    this.description = article.getIntroduction();
  }
}
