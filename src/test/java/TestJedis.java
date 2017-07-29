import com.lzz.util.RemoteShellTool;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lzz on 2017/7/29.
 */
public class TestJedis {
    public static void main(String[] args){
        RemoteShellTool rms = new RemoteShellTool("127.0.0.1", "lzz", "363216", "utf-8");
        String[] cmds = new String[5];
        cmds[0] = "cd /Users/lzz/soft_install/redis/my-redis";
        cmds[1] = "/usr/local/bin/wget http://localhost:8080/package/redis-install.sh";
        cmds[2] = "chmod 775 redis-install.sh";
        cmds[3] = "./redis-install.sh";
        String cmd = StringUtils.join( cmds, ";");
        //System.out.println( cmd );
        String result = rms.exec( cmd );
        System.out.println(result);
    }
}
