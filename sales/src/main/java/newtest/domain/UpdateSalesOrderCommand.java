package newtest.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class UpdateSalesOrderCommand {

    private String salesPerson;
    private String companyId;
    private String salesType;
    private List<SalesItem> salesItems;
}
