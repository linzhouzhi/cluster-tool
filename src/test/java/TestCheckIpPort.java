import com.lzz.util.CheckIpPortUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2017/8/26.
 */
public class TestCheckIpPort {
    @Test
    public void test1(){
        List<String> list = new ArrayList();
        list.add("127.0.0.10");
        for(String ip : list){
            boolean res = CheckIpPortUtil.host(ip, 100);
            System.out.println( res );
        }
    }

    @Test
    public void test2(){
        Map<Integer, String> map = new HashMap();
        map.put(3306, "127.0.0.1");
        map.put(3305, "127.0.0.1");
        for(Map.Entry<Integer, String> item : map.entrySet()){
            System.out.println( item );
            String ip = item.getValue();
            int port = item.getKey();
            System.out.println( CheckIpPortUtil.ipPort(ip, port) );
        }

    }
}
