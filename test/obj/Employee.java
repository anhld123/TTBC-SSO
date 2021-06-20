package obj;

import java.math.BigDecimal; 
import java.sql.SQLException; 
import oracle.jdbc.driver.OracleConnection; 
import oracle.sql.CustomDatum; 
import oracle.sql.CustomDatumFactory; 
import oracle.sql.Datum; 
import oracle.sql.STRUCT; 
import oracle.sql.StructDescriptor; 
 
public class Employee implements CustomDatum, CustomDatumFactory // line 10
{ 
 
  static final Employee _employeeFactory = new Employee(null, null); //line 13
 
  public static CustomDatumFactory getFactory() 
  { 
    return _employeeFactory; 
  }                                                         // line 18
 
  /* constructor */                                         // line 20
  public Employee(String empName, BigDecimal empNo) 
  { 
    this.empName = empName; 
    this.empNo = empNo; 
  }                                                         // line 25
 
  /* CustomDatum interface */                               // line 27
  public Datum toDatum(OracleConnection c) throws SQLException 
  { 
    StructDescriptor sd = 
       StructDescriptor.createDescriptor("IMS.EMPLOYEE", c); 
 
    Object [] attributes = { empName, empNo }; 
 
    return new STRUCT(sd, c, attributes); 
  }                                                         // line 36
 
  /* CustomDatumFactory interface */                        // line 38
  public CustomDatum create(Datum d, int sqlType) throws SQLException 
  { 
    if (d == null) return null; 
 
    System.out.println(d); 
 
    Object [] attributes = ((STRUCT) d).getAttributes(); 
 
    return new Employee((String) attributes[0], 
                        (BigDecimal) attributes[1]); 
  }                                                         // line 49
 
  /* fields */    
  public String empName; 
  public BigDecimal empNo; 
}  