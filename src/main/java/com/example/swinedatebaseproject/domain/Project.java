package com.example.swinedatebaseproject.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * &#064;TableName  Project
 */

@TableName(value ="project")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Project implements Serializable {

    @TableId(value = "Project")
    private String Project;

    @TableField(value = "Phenotypes")
    private String Phenotypes;

    @TableField(value = "ExperimentalDesign")
    private String ExperimentalDesign;

    @TableField(value = "country")
    private String country;

    @TableField(value = "Category")
    private String Category;

    @TableField(value = "Breed")
    private String Breed;

    @TableField(value = "GrowthStage")
    private String GrowthStage;

    @TableField(value = "IsolationLocation")
    private String IsolationLocation;

    @TableField(value = "Age")
    private Integer Age;

    @TableField(value = "Weight")
    private String Weight;
    @TableField(value = "Sex")
    private String Sex;
    @TableField(value = "SequencingTool")
    private String SequencingTool;

    @TableField(value = "SampleNumber")
    private Integer SampleNumber;

    @TableField(value = "Layout")
    private String Layout;

    @TableField(value = "Title")
    private String Title;

    @TableField(value = "Link")
    private String Link;

    @TableField(value = "ReleaseDate")
    private String ReleaseDate;



    @Serial
    private static final long serialVersionUID = 1L;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (!Project.equals(project.Project)) return false;
        if (Phenotypes != null ? !Phenotypes.equals(project.Phenotypes) : project.Phenotypes != null) return false;
        if (ExperimentalDesign != null ? !ExperimentalDesign.equals(project.ExperimentalDesign) : project.ExperimentalDesign != null)
            return false;
        if (country != null ? !country.equals(project.country) : project.country != null) return false;
        if (Category != null ? !Category.equals(project.Category) : project.Category != null) return false;
        if (Breed != null ? !Breed.equals(project.Breed) : project.Breed != null) return false;
        if (GrowthStage != null ? !GrowthStage.equals(project.GrowthStage) : project.GrowthStage != null) return false;
        if (IsolationLocation != null ? !IsolationLocation.equals(project.IsolationLocation) : project.IsolationLocation != null)
            return false;
        if (Age != null ? !Age.equals(project.Age) : project.Age != null) return false;
        if (Weight != null ? !Weight.equals(project.Weight) : project.Weight != null) return false;
        if (Sex != null ? !Sex.equals(project.Sex) : project.Sex != null) return false;
        if (SequencingTool != null ? !SequencingTool.equals(project.SequencingTool) : project.SequencingTool != null)
            return false;
        if (SampleNumber != null ? !SampleNumber.equals(project.SampleNumber) : project.SampleNumber != null)
            return false;
        if (Layout != null ? !Layout.equals(project.Layout) : project.Layout != null) return false;
        if (Title != null ? !Title.equals(project.Title) : project.Title != null) return false;
        if (Link != null ? !Link.equals(project.Link) : project.Link != null) return false;
        return ReleaseDate != null ? ReleaseDate.equals(project.ReleaseDate) : project.ReleaseDate == null;
    }

    @Override
    public int hashCode() {
        int result = Project.hashCode();
        result = 31 * result + (Phenotypes != null ? Phenotypes.hashCode() : 0);
        result = 31 * result + (ExperimentalDesign != null ? ExperimentalDesign.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (Category != null ? Category.hashCode() : 0);
        result = 31 * result + (Breed != null ? Breed.hashCode() : 0);
        result = 31 * result + (GrowthStage != null ? GrowthStage.hashCode() : 0);
        result = 31 * result + (IsolationLocation != null ? IsolationLocation.hashCode() : 0);
        result = 31 * result + (Age != null ? Age.hashCode() : 0);
        result = 31 * result + (Weight != null ? Weight.hashCode() : 0);
        result = 31 * result + (Sex != null ? Sex.hashCode() : 0);
        result = 31 * result + (SequencingTool != null ? SequencingTool.hashCode() : 0);
        result = 31 * result + (SampleNumber != null ? SampleNumber.hashCode() : 0);
        result = 31 * result + (Layout != null ? Layout.hashCode() : 0);
        result = 31 * result + (Title != null ? Title.hashCode() : 0);
        result = 31 * result + (Link != null ? Link.hashCode() : 0);
        result = 31 * result + (ReleaseDate != null ? ReleaseDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("Project='").append(Project).append('\'');
        sb.append(", Phenotypes='").append(Phenotypes).append('\'');
        sb.append(", ExperimentalDesign='").append(ExperimentalDesign).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", Category='").append(Category).append('\'');
        sb.append(", Breed='").append(Breed).append('\'');
        sb.append(", GrowthStage='").append(GrowthStage).append('\'');
        sb.append(", IsolationLocation='").append(IsolationLocation).append('\'');
        sb.append(", Age=").append(Age);
        sb.append(", Weight='").append(Weight).append('\'');
        sb.append(", Sex='").append(Sex).append('\'');
        sb.append(", SequencingTool='").append(SequencingTool).append('\'');
        sb.append(", SampleNumber=").append(SampleNumber);
        sb.append(", Layout='").append(Layout).append('\'');
        sb.append(", Title='").append(Title).append('\'');
        sb.append(", Link='").append(Link).append('\'');
        sb.append(", ReleaseDate='").append(ReleaseDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
