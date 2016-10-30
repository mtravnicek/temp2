package cz.muni.pa165.pneuservis.backend.domain;

import cz.muni.pa165.pneuservis.backend.enums.TireType;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 30/10/2016.
 */
@Entity
public class Tire extends AbstractEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

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
    private BigDecimal value;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Tire tire = (Tire) o;

        if (!id.equals(tire.id)) return false;
        if (!name.equals(tire.name)) return false;
        if (tireType != tire.tireType) return false;
        if (!size.equals(tire.size)) return false;
        if (!manufacturer.equals(tire.manufacturer)) return false;
        return vehicleType.equals(tire.vehicleType) && value.equals(tire.value);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + tireType.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + manufacturer.hashCode();
        result = 31 * result + vehicleType.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tireType=" + tireType +
                ", size='" + size + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", value=" + value +
                '}';
    }
}
