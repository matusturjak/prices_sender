package sk.matusturjak.price_sender.model.db;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document("items")
public class Item {
    @Id
    private ObjectId id;
    private String name;
    private double normalCost;
    private double actualCost;
    private Date validTo;
    private String pictureURL;
    private String superDepartment;
    private String department;
    private Date createdOn;
    private Date modifiedOn;

    public Item() {
        this.id = ObjectId.get();
    }

    public Item(ObjectId id, String name, double normalCost, double actualCost, Date validTo, String pictureURL, String superDepartment, String department, Date createdOn, Date modifiedOn) {
        this.id = id;
        this.name = name;
        this.normalCost = normalCost;
        this.actualCost = actualCost;
        this.validTo = validTo;
        this.pictureURL = pictureURL;
        this.superDepartment = superDepartment;
        this.department = department;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNormalCost() {
        return normalCost;
    }

    public void setNormalCost(double normalCost) {
        this.normalCost = normalCost;
    }

    public double getActualCost() {
        return actualCost;
    }

    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getSuperDepartment() {
        return superDepartment;
    }

    public void setSuperDepartment(String superDepartment) {
        this.superDepartment = superDepartment;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", normalCost=" + normalCost +
                ", actualCost=" + actualCost +
                ", validTo=" + validTo +
                ", pictureURL='" + pictureURL + '\'' +
                ", superDepartment='" + superDepartment + '\'' +
                ", department='" + department + '\'' +
                ", createdOn=" + createdOn +
                ", modifiedOn=" + modifiedOn +
                '}';
    }
}
