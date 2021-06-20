
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.tracuu.Tracuu_edit_util;

public class TestDatabase {

    public static void passArray() {
        try {

            DaoConnect db = new DaoConnect();
            Connection con = db.getConnect();
            List<Object> lsupdate = new ArrayList<>();
            Object obj = new Object[]{"NGUYEN","PHU"};
            lsupdate.add(obj);
            Object array[] = lsupdate.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("ARRY_TABLE", con);
            ARRAY array_to_pass = new ARRAY(des, con, array);

            CallableStatement st = con.prepareCall("call proc1(?,?,?)");

            // Passing an array to the procedure - 
            st.setArray(1, array_to_pass);

            st.registerOutParameter(2, Types.INTEGER);
            st.registerOutParameter(3, OracleTypes.ARRAY, "ARRAY_INT");
            st.execute();

            System.out.println("size : " + st.getInt(2));

            // Retrieving array from the resultset of the procedure after execution -
            ARRAY arr = ((OracleCallableStatement) st).getARRAY(3);
            BigDecimal[] recievedArray = (BigDecimal[]) (arr.getArray());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
//        passArray();
        HashMap <String, String> hmap = new HashMap<String, String>();
        hmap.put("ngay_bc", "30-nov-2015");
        
       String so_ku=hmap.get("so_ku");
       
       System.err.println(so_ku);
        System.err.println(hmap.get("ngay_bc"));
    }
}
