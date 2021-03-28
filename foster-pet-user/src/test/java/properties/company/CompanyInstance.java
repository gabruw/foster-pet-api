package properties.company;

import com.foster.pet.entity.Company;

public class CompanyInstance extends CompanyProperties {

    public static Company instace() {
        Company company = new Company();
        company.setId(ID);
        company.setCnpj(CNPJ);
        company.setTradeName(TRADE_NAME);
        company.setCompanyName(COMPANY_NAME);

        return company;
    }
}
