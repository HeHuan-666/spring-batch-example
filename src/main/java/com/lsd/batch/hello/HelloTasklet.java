package com.lsd.batch.hello;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @Description: Hello SrpingBatch
 * @Author: lishengdong
 * @DateTime: 2015年12月1日 下午1:25:16
 * @Version: 1.0.0
 * @Copyright: 
 * @Company:
 */
public class HelloTasklet implements Tasklet {
	private String message;
	
	public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		System.out.println(message);
		
        return RepeatStatus.FINISHED;
	}
}
