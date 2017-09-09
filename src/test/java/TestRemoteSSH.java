import com.lzz.util.RemoteShellTool;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by lzz on 2017/8/26.
 */
public class TestRemoteSSH {
    public static void main(String[] args){
        RemoteShellTool rms = new RemoteShellTool("192.168.31.140", "lzz", "linzhouzhi", "utf-8");
        String[] cmds = new String[3];
        cmds[0] = "cd /home/lzz/work/cluster-test";
        cmds[1] = "wget http://192.168.31.147:8080/shell/install.sh";
        cmds[2] = "sh install.sh zookeeper-3.4.8.tar.gz 0 'start.sh aa'";
        String cmd = StringUtils.join( cmds, ";");
        System.out.println( cmd );
        String result = rms.exec( cmd );
        System.out.println(result);
    }

    @Test
    public void test1(){
        RemoteShellTool rms = new RemoteShellTool("192.168.31.147", "lzz", "363216", "utf-8");
        String result = rms.exec( "tail -100 /Users/lzz/work/wechat/demo/app.js" );
        System.out.println(result);
    }
}
