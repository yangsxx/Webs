package top.yangsc.Schedule;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 描述：top.yangsc.swiftcache.Schedule
 *
 * @author yang
 * @date 2025/5/16 20:34
 */
import org.springframework.scheduling.annotation.Scheduled;
import top.yangsc.base.mapper.ExecutionLogHistoryMapper;
import top.yangsc.base.mapper.SimpleMapper;
import top.yangsc.base.pojo.ExecutionLogHistory;
import top.yangsc.tools.TimestampUtil;

@Component
public class CountTask {

    @Resource
    private ExecutionLogHistoryMapper mapper;
    @Resource
    private SimpleMapper simpleMapper;

    // 添加定时注解（每天凌晨3点执行）
    @Scheduled(cron = "0 0 3 * * ?", zone = "Asia/Shanghai")
    public void count() {
        List<ExecutionLogHistory> executionLogHistory =  simpleMapper.countHistory();

        for (ExecutionLogHistory logHistory : executionLogHistory){
            logHistory.setDay(TimestampUtil.format(new Timestamp(System.currentTimeMillis())));
        }
        mapper.insertBatchSomeColumn(executionLogHistory);


    }
}
