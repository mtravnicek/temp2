package cz.muni.pa165.pneuservis.persistence.domain;

import cz.muni.pa165.pneuservis.persistence.enums.TireType;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 30/10/2016.
 */
@Entity
public class Tire extends AbstractEntity {
    @NotNull
    @Column(nullable=false)
    private String name;

    @Enumerated
    @NotNull
    private TireType tireType;

    @NotNull
    private String size;

    @NotNull
    private String manufacturer;

    @NotNull
    private String vehicleType;

    @DecimalMin("0.0")
    @NotNull
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TireType getTireType() {
        return tireType;
    }

    public void setTireType(TireType tireType) {
        this.tireType = tireType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tire)) return false;

        Tire tire = (Tire) o;

        if (getName() != null ? !getName().equals(tire.getName()) : tire.getName() != null) return false;
        if (getTireType() != tire.getTireType()) return false;
        if (getSize() != null ? !getSize().equals(tire.getSize()) : tire.getSize() != null) return false;
        if (getManufacturer() != null ? !getManufacturer().equals(tire.getManufacturer()) : tire.getManufacturer() != null)
            return false;
        if (getVehicleType() != null ? !getVehicleType().equals(tire.getVehicleType()) : tire.getVehicleType() != null)
            return false;
        if (getPrice() != null) {
            return (getPrice().compareTo(tire.getPrice()) == 0);
        } else {
            return tire.getPrice() == null;
        }

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getTireType() != null ? getTireType().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
        result = 31 * result + (getVehicleType() != null ? getVehicleType().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tire{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", tireType=" + tireType +
                ", size='" + size + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", price=" + price +
                '}';
    }
}
