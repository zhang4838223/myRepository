package com.zxj.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by xiaojun.zhang on 2016/2/27.
 */
public class MyTask extends RecursiveTask<Integer> {
    //任务的分割阈值
    private final static int THRESHOLD = 2;
    private int start;
    private int end;

    public MyTask(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        if((end - start) <= THRESHOLD){
            for (int i = start; i<= end; i++){
                sum += i;
            }
            return sum;
        }else{
            int middle = (start + end)/2;
            MyTask t1 = new MyTask(start, middle);
            MyTask t2 = new MyTask(middle + 1, end);

            //执行子任务
            t1.fork();
            t2.fork();
            //获取子任务结果
            int r1 = t1.join();
            int r2 = t2.join();

            sum = r1 + r2;

        }

        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        //生成一个逻辑任务，处理逻辑
        ForkJoinTask<Integer> result = pool.submit(new MyTask(1, 4));

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
