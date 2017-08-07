import com.lzz.util.RemoteShellTool;
import org.junit.Test;

/**
 * Created by lzz on 2017/8/7.
 */
public class TestDocker {
    private static final String ip = "192.168.31.140";
    @Test
    public void getImages(){
        RemoteShellTool rms = new RemoteShellTool(ip, "lzz", "linzhouzhi", "utf-8");
        String result = rms.exec( "echo 'linzhouzhi' | sudo -S docker images -a");
        System.out.println(result);
    }

    @Test
    public void getRun(){
        RemoteShellTool rms = new RemoteShellTool(ip, "lzz", "linzhouzhi", "utf-8");
        String result = rms.exec( "echo 'linzhouzhi' | sudo -S docker run -d test");
        System.out.println(result);
    }

    @Test
    public void stopImages(){
        RemoteShellTool rms = new RemoteShellTool(ip, "lzz", "linzhouzhi", "utf-8");
        String result = rms.exec( "echo 'linzhouzhi' | sudo docker stop `sudo -S docker ps | grep test | awk '{print $1}'`");
        System.out.println(result);
    }

    @Test
    public void getLog(){
        RemoteShellTool rms = new RemoteShellTool(ip, "lzz", "linzhouzhi", "utf-8");
        String result = rms.exec( "echo 'linzhouzhi' | sudo -S docker logs cdf894c838f2" );
        System.out.println(result);
    }

}
