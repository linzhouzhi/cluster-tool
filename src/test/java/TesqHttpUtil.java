import com.lzz.util.HttpUtil;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * Created by lzz on 2017/8/14.
 */
public class TesqHttpUtil {
    @Test
    public void test1(){

        String s = HttpUtil.sendGet("http://192.168.31.140:2376/containers/json", "");
        System.out.println( s );
        s = "{'result':" + s + "}";
        JSONObject result = JSONObject.fromObject( s );
        System.out.println( result );

    }

}
