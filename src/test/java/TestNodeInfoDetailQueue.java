import com.lzz.util.CommonUtil;
import com.lzz.util.NodeInfoProducer;
import org.junit.Test;

/**
 * Created by lzz on 2017/8/5.
 */
public class TestNodeInfoDetailQueue {
    @Test
    public void test1(){
        NodeInfoProducer.producer.run();
    }

    @Test
    public void test2(){
        System.out.println(CommonUtil.getTime());
    }
}
