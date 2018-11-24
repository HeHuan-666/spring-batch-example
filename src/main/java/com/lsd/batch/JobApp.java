package com.lsd.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lsd.utils.Counter;

/**
 * @Description: SpringBatch入口
 * @Author: lishengdong
 * @DateTime: 2015年12月2日 下午10:32:57
 * @Version: 1.0.0
 * @Copyright: 
 * @Company:
 */
public class JobApp {
	private static ApplicationContext context;

	public static void main(String[] args) {
		long begin = System.currentTimeMillis(); // 获取开始时间
		
		context = new ClassPathXmlApplicationContext("resources/batch.xml");
		JobLauncher launcher = (JobLauncher) context.getBean("jobLauncherPush");
		
		// 运行Job
		try {
			Job helloWorldJob = (Job) context.getBean("helloWorldJob");
			JobExecution result = launcher.run(helloWorldJob, new JobParameters());
			
//			JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
//	        jobParametersBuilder.addString("name", "李%");
//	        jobParametersBuilder.addString("outputFilePath", "D://userInfo.csv");
//			Job userJob = (Job) context.getBean("userJob");
//			JobExecution result = launcher.run(userJob, jobParametersBuilder.toJobParameters());
			
			ExitStatus es = result.getExitStatus();
	        if (es.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
	            System.out.println("\n\n任务正常完成");
	        } else {
	            System.out.println("任务失败，exitCode=" + es.getExitCode());
	        }
	        
	        System.out.println("读取数据总量: " + Counter.getInstance().getTotal());
	 
	        long end = System.currentTimeMillis(); // 获取结束时间
	        long usedTime = end - begin;
	        System.out.println("创建数据总量: " + Counter.getInstance().getTotal());
			System.out.println("总耗时时：[" + Math.ceil((new Double(usedTime) / 1000 / 60)) + "]分钟, [" + Math.ceil((new Double(usedTime) / 1000)) + "]秒, ["
					+ usedTime + "]毫秒");
		} catch (BeansException e1) {
			e1.printStackTrace();
			System.exit(1);
		} catch (JobExecutionAlreadyRunningException e1) {
			e1.printStackTrace();
			System.exit(1);
		} catch (JobRestartException e1) {
			e1.printStackTrace();
			System.exit(1);
		} catch (JobInstanceAlreadyCompleteException e1) {
			e1.printStackTrace();
			System.exit(1);
		} catch (JobParametersInvalidException e1) {
			e1.printStackTrace();
			System.exit(1);
		} finally {
			System.exit(0);
		}
	}
}
