package cz.muni.pa165.pneuservis.backend.domain;

//import cz.fi.muni.pa165.entity.OrderItem;
import cz.muni.pa165.pneuservis.backend.enums.OrderState;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Michal Travnicek on 10/26/2016.
 */
@Entity
@Table(name = "Orders")
public class Order extends AbstractEntity {

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    @DecimalMin("0.0")
    @NotNull
    private BigDecimal price;

    @ManyToOne(optional = false)
    @NotNull
    private Tire tire;

    @Min(1)
    @NotNull
    private Integer tireQuantity;

    @ManyToMany
    private List<AdditionalService> additionalServices;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Enumerated
    @NotNull
    private OrderState state;

    public Order() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public Integer getTireQuantity() {
        return tireQuantity;
    }

    public void setTireQuantity(Integer tireQuantity) {
        this.tireQuantity = tireQuantity;
    }

    public List<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" + 
                "id=" + getId() +
                ", user=" + user +
                ", phone=" + phone +
                ", address=" + address +
                ", dateCreated=" + dateCreated +
                ", state=" + state +                 
                ", price=" + price +
                ", tire=" + tire +
                ", tireQuantity=" + tireQuantity +
                ", additionalServices=" + additionalServices +                
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(getUser());
        hash = 29 * hash + Objects.hashCode(getDateCreated());        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Order)) {
            return false;
        }

        final Order other = (Order) obj;
        
        if (!Objects.equals(getDateCreated(), other.getDateCreated())) {
            return false;
        }
        if (!Objects.equals(getUser(), other.getUser())) {
            return false;
        }
        return true;
    }

}
