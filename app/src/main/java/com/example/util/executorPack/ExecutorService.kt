package com.example.util.executorPack

import java.util.concurrent.*

/**
 * 线程池的理解
 * Created by Administrator on 2018/3/19 0019.
 * newFixedThreadPool创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程
 * newScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行
 * newSingleThreadExecutor创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 *
 * 例子：
ExecutorService executorService = Executors.newCachedThreadPool();
for (int i = 0; i < 100; i++) {
Runnable syncRunnable = new Runnable() {
@Override
public void run() {
Log.e(TAG, Thread.currentThread().getName());
}
};
executorService.execute(syncRunnable);
}
 */
interface ExecutorService : Executor {

    fun shutdown()//顺次地关闭ExecutorService,停止接收新的任务，等待所有已经提交的任务执行完毕之后，关闭ExecutorService

    fun shutdownNow(): List<Runnable> //阻止等待任务启动并试图停止当前正在执行的任务，停止接收新的任务，返回处于等待的任务列表

    fun isShutdown(): Boolean//判断线程池是否已经关闭

    fun isTerminated(): Boolean//如果关闭后所有任务都已完成，则返回 true。注意，除非首先调用 shutdown 或 shutdownNow，否则 isTerminated 永不为 true。

    fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean//等待（阻塞）直到关闭或最长等待时间或发生中断,timeout - 最长等待时间 ,unit - timeout 参数的时间单位  如果此执行程序终止，则返回 true；如果终止前超时期满，则返回 false

    fun <T> submit(task: Callable<T>): Future<T> //提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。该 Future 的 get 方法在成功完成时将会返回该任务的结果。

    fun <T> submit(task: Runnable, result: T): Future<T>;//提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。该 Future 的 get 方法在成功完成时将会返回给定的结果。

    fun submit(task: Runnable): Future<*>//提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。该 Future 的 get 方法在成功 完成时将会返回 null

    //执行给定的任务，如果在给定的超时期满前某个任务已成功完成（也就是未抛出异常），则返回其结果。一旦正常或异常返回后，则取消尚未完成的任务。
    @Throws(InterruptedException::class, ExecutionException::class)
    fun <T> invokeAll(tasks: Collection<Callable<T>>): T

    //执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。返回列表的所有元素的 Future.isDone() 为 true。
    @Throws(InterruptedException::class, ExecutionException::class)
    fun <T> invokeAll(tasks: Collection<Callable<T>>, timeout: Long, unit: TimeUnit): List<Future<T>>

    //执行给定的任务，如果在给定的超时期满前某个任务已成功完成（也就是未抛出异常），则返回其结果。一旦正常或异常返回后，则取消尚未完成的任务。
    @Throws(InterruptedException::class, ExecutionException::class)
    fun <T> invokeAny(tasks: Collection<Callable<T>>): List<Future<T>>

    @Throws(InterruptedException::class, ExecutionException::class)
    fun <T> invokeAny(tasks: Collection<Callable<T>>, timeout: Long, unit: TimeUnit): List<Future<T>>


}
