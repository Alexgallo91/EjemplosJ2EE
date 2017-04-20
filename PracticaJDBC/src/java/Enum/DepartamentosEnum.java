
package Enum;

public enum DepartamentosEnum {
    
            ADMINISTRATION(10),
            MARKETING(20),
            PURCHASING(30),
            HUMAN_RESOURCE(40),
            SHIPPING(50),
            IT(60),
            PUBLIC_RELATIONS(70),
            SALES(80),
            EXECUTIVE(90),
            FINANCE(100),
            ACCOUNTING(110),
            TREASURY(120),
            CORPORATE_TAX(130),
            CONTROL_AND_CREDIT(140),
            SHAREHOLDER_SERVICES(150),
            BENEFITS(160),
            MANUFACTURING(170),
            CONSTRUCTION(180),
            CONTRACTING(190),
            OPERATIONS(200),
            IT_SUPPORT(210),
            NOC(220),
            IT_HELPDESK(230),
            GOVERNMENT_SALES(240),
            RETAIL_SALES(250),
            RECRUITING(260),
            PAYROLL(270);
            
            private int numeroDep;

    private DepartamentosEnum(int a) 
    {
        this.numeroDep = a;
    }
    
    public int getNumeroDep()
    {
        return this.numeroDep;
    }
    
}
