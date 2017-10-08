package ua.miratech.rudenko.docstore.jobsExecution;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by RomanR on 2/12/14.
 */
@Component
public class JobsThreadPool {

    public static final Logger LOG = Logger.getLogger("rootLogger");
    public static final Integer POLL_SIZE = 1;
    private ExecutorService threadPool;


    public JobsThreadPool() {
        threadPool = Executors.newFixedThreadPool(POLL_SIZE);
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }
}
