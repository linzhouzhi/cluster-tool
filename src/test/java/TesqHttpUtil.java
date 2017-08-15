import com.lzz.util.HttpUtil;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * Created by lzz on 2017/8/14.
 */
public class TesqHttpUtil {
    @Test
    public void registry_list(){
        JSONObject result = HttpUtil.jsonGet("http://192.168.31.140:5000/v2/_catalog");
        System.out.println( result );
    }

    @Test
    public void container_list(){
        JSONObject result = HttpUtil.jsonGet("http://192.168.31.140:2376/containers/json");
        System.out.println( result );

    }

    @Test
    public void container_detail(){
        JSONObject result = HttpUtil.jsonGet("http://192.168.31.140:2376/containers/054c234e5782/json");
        System.out.println( result );
    }

    @Test
    public void container_restart(){
        String s = HttpUtil.sendPost("http://192.168.31.140:2376/containers/054c234e5782/restart","t=1");
        System.out.println( s );
    }

    @Test
    public void container_stop(){
        String s = HttpUtil.sendPost("http://192.168.31.140:2376/containers/054c234e5782/stop","t=1");
        System.out.println( s );
    }

    @Test
    public void container_start(){
        String s = HttpUtil.sendPost("http://192.168.31.140:2376/containers/054c234e5782/start","t=1");
        System.out.println( s );
    }

    @Test
    public void container_rename(){
        String s = HttpUtil.sendPost("http://192.168.31.140:2376/containers/054c234e5782/rename","name=registry2.5");
        System.out.println( s );
    }
    @Test
    public void image_list(){
        JSONObject result = HttpUtil.jsonGet("http://192.168.31.140:2376/images/json");
        System.out.println( result );
    }

}
