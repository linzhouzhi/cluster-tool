import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by lzz on 2017/8/5.
 *
 connected_clients:2
 blocked_clients:0
 used_memory:1500832
 used_memory_rss:2641920
 used_memory_peak:1670480
 mem_fragmentation_ratio:1.76
 aof_enabled:0
 total_connections_received:140
 total_commands_processed:139
 instantaneous_ops_per_sec:0
 total_net_input_bytes:3336
 total_net_output_bytes:148477
 instantaneous_input_kbps:0.00
 instantaneous_output_kbps:0.00
 rejected_connections:0
 sync_full:0
 sync_partial_ok:0
 sync_partial_err:0
 expired_keys:0
 evicted_keys:0
 keyspace_hits:0
 keyspace_misses:0
 pubsub_channels:0
 pubsub_patterns:0
 latest_fork_usec:0
 migrate_cached_sockets:0

 used_cpu_sys:70.38
 used_cpu_user:33.22
 used_cpu_sys_children:0.00
 used_cpu_user_children:0.00

 *
 */
public class TestQueue {
    @Test
    public void test1() {
        BlockingQueue detailQueue = new LinkedBlockingDeque(10);
        for(int i = 0; i < 12; i++){
            boolean res = detailQueue.offer( i );
            if( !res ){
                System.out.println( res );
            }
        }
        System.out.println( detailQueue );
        for(int j = 0; j < 20; j++){
            try{
                int res = (int) detailQueue.poll(2, TimeUnit.SECONDS);
                System.out.println( res );
            }catch (Exception e){
                System.out.println("time out : " + e );
            }
        }
    }
}
