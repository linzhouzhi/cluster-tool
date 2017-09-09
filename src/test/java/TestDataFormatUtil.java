import com.lzz.util.DataFormatUtil;
import org.junit.Test;

import java.util.Map;

/**
 * Created by lzz on 2017/9/9.
 */
public class TestDataFormatUtil {
    @Test
    public void test1(){
        String[] fields = new String[7];
        fields[0] = "field1";
        fields[1] = "field2";
        fields[2] = "field3";
        fields[3] = "field4";
        fields[4] = "field5";
        fields[5] = "field6";
        fields[6] = "field7";
        Map<String, String> machine = new java.util.HashMap<>();
        machine.put("ip",  "192.168.31.147");
        machine.put("username", "lzz");
        machine.put("password", "363216");
        String filter = "'" + "dfd" + "'";
        String res = DataFormatUtil.formatAccessLog( machine, "/Users/lzz/test.log", fields, 1232, filter, 6);
        System.out.println( res );
    }
    
}
