package newtest.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import newtest.SalesApplication;
import newtest.domain.SalesOrderCreated;
import newtest.domain.SalesOrderDeleted;
import newtest.domain.SalesOrderUpdated;

@Entity
@Table(name = "SalesOrder_table")
@Data
public class SalesOrder {

    @Id
    private String id;

    private String salesPerson;

    private SalesType salesType;

    @ElementCollection
    private List<SalesItem> salesItems;

    @Embedded
    private CompanyId companyId;

    @PostPersist
    public void onPostPersist() {
        SalesOrderCreated salesOrderCreated = new SalesOrderCreated(this);
        salesOrderCreated.publishAfterCommit();

        SalesOrderUpdated salesOrderUpdated = new SalesOrderUpdated(this);
        salesOrderUpdated.publishAfterCommit();

        SalesOrderDeleted salesOrderDeleted = new SalesOrderDeleted(this);
        salesOrderDeleted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    @PreUpdate
    public void onPreUpdate() {}

    public static SalesOrderRepository repository() {
        SalesOrderRepository salesOrderRepository = SalesApplication.applicationContext.getBean(
            SalesOrderRepository.class
        );
        return salesOrderRepository;
    }
}
