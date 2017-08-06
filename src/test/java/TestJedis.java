import com.lzz.model.MasterNode;
import com.lzz.model.SlaveNode;
import com.lzz.util.RemoteShellTool;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        //String result = rms.exec( cmd );
        String result = rms.exec( "ls /" );
        System.out.println(result);
    }

    @Test
    public void test001(){
        RemoteShellTool rms = new RemoteShellTool("192.168.31.140", "lzz", "linzhouzhi", "utf-8");
        String result = rms.exec( "echo 'linzhouzhi' | sudo -S docker logs cdf894c838f2" );
        System.out.println(result);
    }

    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("127.0.0.1",8018);
        String s = jedis.asking();
        System.out.println( s );
        //jedis.clusterMeet("127.0.0.1", 8006);
    }

    @Test
    public void testMonitor1(){
        Jedis jedis = new Jedis("127.0.0.1",8007);
        String s = jedis.info();
        System.out.println( s );
    }

    @Test
    public void testNodes() throws IOException {
        List<SlaveNode> nodes = getNodes();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "cluster");
        List<MasterNode> children = new LinkedList<>();
        // 添加 master
        for(SlaveNode node : nodes){
            String role = node.getRole();
            if( "master".equals( role ) ){
                MasterNode masterNode = new MasterNode();
                masterNode.setNodeid( node.getNodeid() );
                masterNode.setMaster( node.getMaster() );
                masterNode.setStatus( node.getStatus() );
                masterNode.setHost( node.getHost() );
                masterNode.setIp( node.getIp() );
                masterNode.setPort( node.getPort() );
                masterNode.setRole( node.getRole() );
                children.add( masterNode );
            }
        }
        // 添加 slave
        for(MasterNode node : children){
            String nodeid = node.getNodeid();
            for(SlaveNode childrenNode : nodes){
                String master = childrenNode.getMaster();
                if( nodeid.equals( master ) ){
                    List<SlaveNode> childrens = node.getChildren();
                    if( null == childrens ){
                        childrens = new ArrayList<>();
                    }
                    childrens.add( childrenNode );
                }
            }
        }
        List multMaster = new LinkedList();
        List oneMaster = new LinkedList();
        // 将 master children 为空的转化成 slave，不然空值转 json 有问题
        for( MasterNode node : children ){
            if( null == node.getChildren() ){
                SlaveNode slaveNode = new SlaveNode();
                slaveNode.setNodeid( node.getNodeid() );
                slaveNode.setRole( node.getRole() );
                slaveNode.setPort( node.getPort() );
                slaveNode.setIp( node.getIp() );
                slaveNode.setHost( node.getHost() );
                slaveNode.setMaster( node.getMaster() );
                slaveNode.setStatus( node.getStatus() );
            }else {
                multMaster.add( node );
            }
        }
        multMaster.addAll(oneMaster);
        jsonObject.put("children", multMaster);
        System.out.println( jsonObject );

    }

    @Test
    public void test1(){
        Jedis jedis = new Jedis("127.0.0.1",8007);
        String nodestr = jedis.clusterNodes();
        System.out.println( nodestr );
    }

    public List<SlaveNode> getNodes() throws IOException {
        List<SlaveNode> list = new LinkedList<>();
        Jedis jedis = new Jedis("127.0.0.1",8007);
        String nodestr = jedis.clusterNodes();

        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(nodestr.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        String line;
        while ( (line = br.readLine()) != null ) {
            System.out.println( line );
            if(!line.trim().equals("")){
                String[] arr = line.split(" ");
                if( arr.length > 4 ){
                    SlaveNode node = new SlaveNode();
                    String nodeid = arr[0];
                    String host = arr[1];
                    String[] tmpArr = host.split(":");
                    String ip = tmpArr[0];
                    String port = tmpArr[1];
                    String role = arr[2];
                    if( StringUtils.contains(role, "master") ){
                        role = "master";
                    }else{
                        role = "slave";
                    }
                    String status = "success";
                    if( StringUtils.contains( arr[2], "fail") ){
                        status = "fail";
                    }
                    String master = arr[3];
                    node.setNodeid( nodeid );
                    node.setHost( host );
                    node.setIp( ip );
                    node.setPort( port );
                    node.setRole( role );
                    node.setStatus( status );
                    node.setMaster( master );
                    list.add( node );
                }
            }
        }
        return  list;
    }

}
