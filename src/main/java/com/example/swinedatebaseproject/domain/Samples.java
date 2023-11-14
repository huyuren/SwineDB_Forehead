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
 * @TableName sample_runs
 */
@TableName(value ="sample_runs")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Samples implements Serializable {

    @TableId(value = "Runs")
    private String runs;

    @TableField(value = "Project")
    private String BioProject;

    @TableField(value = "Phenotypes")
    private String Phenotypes;

    @TableField(value = "ExperimentalDesign")
    private String ExperimentalDesign;

    @TableField(value = "Group")
    private String Group;

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

    @TableField(value = "Layout")
    private String Layout;

    @TableField(value = "SequencingTool")
    private String SequencingTool;

    @TableField(value = "ReleaseDate")
    private String ReleaseDate;



    @Serial
    private static final long serialVersionUID = 1L;
//
//    public String getRuns() {
//        return runs;
//    }
//
//    public void setRuns(String runs) {
//        this.runs = runs;
//    }
//
//    public String getBioProject() {
//        return bioProject;
//    }
//
//    public void setBioProject(String bioProject) {
//        this.bioProject = bioProject;
//    }
//
//    public String getBioSample() {
//        return bioSample;
//    }
//
//    public void setBioSample(String bioSample) {
//        this.bioSample = bioSample;
//    }
//
//    public String getExperiment() {
//        return experiment;
//    }
//
//    public void setExperiment(String experiment) {
//        this.experiment = experiment;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getInstrument() {
//        return instrument;
//    }
//
//    public void setInstrument(String instrument) {
//        this.instrument = instrument;
//    }
//
//    public String getPlatform() {
//        return platform;
//    }
//
//    public void setPlatform(String platform) {
//        this.platform = platform;
//    }
//
//    public String getSraStudy() {
//        return sraStudy;
//    }
//
//    public void setSraStudy(String sraStudy) {
//        this.sraStudy = sraStudy;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Samples samples = (Samples) o;

        if (!runs.equals(samples.runs)) return false;
        if (BioProject != null ? !BioProject.equals(samples.BioProject) : samples.BioProject != null) return false;
        if (Phenotypes != null ? !Phenotypes.equals(samples.Phenotypes) : samples.Phenotypes != null) return false;
        if (ExperimentalDesign != null ? !ExperimentalDesign.equals(samples.ExperimentalDesign) : samples.ExperimentalDesign != null)
            return false;
        if (Group != null ? !Group.equals(samples.Group) : samples.Group != null) return false;
        if (country != null ? !country.equals(samples.country) : samples.country != null) return false;
        if (Category != null ? !Category.equals(samples.Category) : samples.Category != null) return false;
        if (Breed != null ? !Breed.equals(samples.Breed) : samples.Breed != null) return false;
        if (GrowthStage != null ? !GrowthStage.equals(samples.GrowthStage) : samples.GrowthStage != null) return false;
        if (IsolationLocation != null ? !IsolationLocation.equals(samples.IsolationLocation) : samples.IsolationLocation != null)
            return false;
        if (Age != null ? !Age.equals(samples.Age) : samples.Age != null) return false;
        if (Weight != null ? !Weight.equals(samples.Weight) : samples.Weight != null) return false;
        if (Sex != null ? !Sex.equals(samples.Sex) : samples.Sex != null) return false;
        if (Layout != null ? !Layout.equals(samples.Layout) : samples.Layout != null) return false;
        if (SequencingTool != null ? !SequencingTool.equals(samples.SequencingTool) : samples.SequencingTool != null)
            return false;
        return ReleaseDate != null ? ReleaseDate.equals(samples.ReleaseDate) : samples.ReleaseDate == null;
    }

    @Override
    public int hashCode() {
        int result = runs.hashCode();
        result = 31 * result + (BioProject != null ? BioProject.hashCode() : 0);
        result = 31 * result + (Phenotypes != null ? Phenotypes.hashCode() : 0);
        result = 31 * result + (ExperimentalDesign != null ? ExperimentalDesign.hashCode() : 0);
        result = 31 * result + (Group != null ? Group.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (Category != null ? Category.hashCode() : 0);
        result = 31 * result + (Breed != null ? Breed.hashCode() : 0);
        result = 31 * result + (GrowthStage != null ? GrowthStage.hashCode() : 0);
        result = 31 * result + (IsolationLocation != null ? IsolationLocation.hashCode() : 0);
        result = 31 * result + (Age != null ? Age.hashCode() : 0);
        result = 31 * result + (Weight != null ? Weight.hashCode() : 0);
        result = 31 * result + (Sex != null ? Sex.hashCode() : 0);
        result = 31 * result + (Layout != null ? Layout.hashCode() : 0);
        result = 31 * result + (SequencingTool != null ? SequencingTool.hashCode() : 0);
        result = 31 * result + (ReleaseDate != null ? ReleaseDate.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Samples{");
        sb.append("runs='").append(runs).append('\'');
        sb.append(", BioProject='").append(BioProject).append('\'');
        sb.append(", Phenotypes='").append(Phenotypes).append('\'');
        sb.append(", ExperimentalDesign='").append(ExperimentalDesign).append('\'');
        sb.append(", Group='").append(Group).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", Category='").append(Category).append('\'');
        sb.append(", Breed='").append(Breed).append('\'');
        sb.append(", GrowthStage='").append(GrowthStage).append('\'');
        sb.append(", IsolationLocation='").append(IsolationLocation).append('\'');
        sb.append(", Age=").append(Age);
        sb.append(", Weight='").append(Weight).append('\'');
        sb.append(", Sex='").append(Sex).append('\'');
        sb.append(", Layout='").append(Layout).append('\'');
        sb.append(", SequencingTool='").append(SequencingTool).append('\'');
        sb.append(", ReleaseDate='").append(ReleaseDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
