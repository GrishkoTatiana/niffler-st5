package guru.qa.niffler.data.entity;

import guru.qa.niffler.model.CurrencyValues;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class SpendEntity implements Serializable {

    private UUID id;

    private String username;

    private CurrencyValues currency;

    private Date spendDate;

    private Double amount;

    private String description;

    private CategoryEntity category;

}
