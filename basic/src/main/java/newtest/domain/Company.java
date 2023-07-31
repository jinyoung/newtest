package newtest.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import newtest.BasicApplication;
import newtest.domain.CompanyCreated;
import newtest.domain.CompanyDeleted;

@Entity
@Table(name = "Company_table")
@Data
public class Company {

    @Id
    private String name;

    private String industry;

    private LocalDate foundedDate;

    @PostPersist
    public void onPostPersist() {
        CompanyCreated companyCreated = new CompanyCreated(this);
        companyCreated.publishAfterCommit();

        CompanyDeleted companyDeleted = new CompanyDeleted(this);
        companyDeleted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static CompanyRepository repository() {
        CompanyRepository companyRepository = BasicApplication.applicationContext.getBean(
            CompanyRepository.class
        );
        return companyRepository;
    }

    public void updateCompany(UpdateCompanyCommand updateCompanyCommand) {
        //implement business logic here:

        CompanyUpdated companyUpdated = new CompanyUpdated(this);
        companyUpdated.set(updateCompanyCommand.get());
        companyUpdated.publishAfterCommit();
    }
}
