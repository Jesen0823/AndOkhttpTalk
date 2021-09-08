package com.jesen.custom_okhttp.custom;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Dispatcher2 {

    // 同时访问任务，最大限制64个
    private int maxRequests =64;
    // 同一个服务器域名，最大访问限制
    private int maxRequestsPerHost = 5;

    // 运行队列和等待队列
    private Deque<RealCall2.AsyncCall2> runningAsyncCalls = new ArrayDeque<>();
    private Deque<RealCall2.AsyncCall2> readyAsyncCalls = new ArrayDeque<>();

    // 缓存线程池
    public ExecutorService executorService(){
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable runnable) {
                        Thread thread = new Thread(runnable);
                        thread.setName("Custom thread");
                        thread.setDaemon(false);
                        return thread;
                    }
                });
        return executorService;
    }

    public void enqueue(RealCall2.AsyncCall2 call){
        // 如果同时运行的异步任务小于64 && 同时访问(同一个)服务器的请求数 小于5个 ----> 把运行的任务加入到 运行队列中 然后执行
        if (runningAsyncCalls.size() < maxRequests && runningCallsForHost(call) < maxRequestsPerHost) {
            // 加入到运行队列
            runningAsyncCalls.add(call);
            executorService().execute(call); // 线程池执行耗时操作
        } else {
            // 加入到等待队列
            readyAsyncCalls.add(call);
        }
    }

    /**
     * 判断AsyncCall2中运行队列的Host的个数
     * */
    private int runningCallsForHost(RealCall2.AsyncCall2 call){
        int count = 0;

        if (runningAsyncCalls.isEmpty()){
            return 0;
        }

        SocketRequestServer server = new SocketRequestServer();

        for (RealCall2.AsyncCall2 runningCall:runningAsyncCalls){
            if (server.getHost(runningCall.getRequest2()).equals(call.getRequest2())){
                count++;
            }
        }
        return count;
    }

    public void finished(RealCall2.AsyncCall2 asyncCall2) {
        // 回收当前运行的任务
        runningAsyncCalls.remove(asyncCall2);
        // 等待队列
        if (readyAsyncCalls.isEmpty()){
            return;
        }

        // 等待队列中的任务移动到运行队列
        for (RealCall2.AsyncCall2 readyAsyncCall : readyAsyncCalls) {
            readyAsyncCalls.remove(readyAsyncCall);
            runningAsyncCalls.add(readyAsyncCall);

            // 开始执行任务
            executorService().execute(readyAsyncCall);
        }
    }
}
