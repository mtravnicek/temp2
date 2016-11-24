package cz.muni.pa165.pneuservis.dto;

import java.math.BigDecimal;

/**
 * @author Martin Spisiak <spisiak@mail.muni.cz> on 23/11/2016.
 */
public class TireDTO {
    private Long id;
    private String name;
    private TireTypeDTO tireType;
    private String size;
    private String manufacturer;
    private String vehicleType;
    private BigDecimal price;

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public TireTypeDTO getTireType() { return tireType; }

    public void setTireType(TireTypeDTO tireType) {
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
}
