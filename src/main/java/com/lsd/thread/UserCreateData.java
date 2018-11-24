package com.lsd.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lsd.model.User;
import com.lsd.service.IUserService;
import com.lsd.utils.Counter;
import com.lsd.utils.RandomDataUtil;
import com.lsd.utils.UUIDUtil;

/**
 * @Description: 用户表创建数据线程
 * @Author: lishengdong
 * @DateTime: 2015年11月30日 上午10:29:46
 * @Version: 1.0.0
 * @Copyright:
 * @Company:
 */
public class UserCreateData {

	private ExecutorService threadPool = null;

	private IUserService userService;

	private int threadNum;

	private int createNum;

	private int step;

	private long begin;

	/**
	 * 构造方法: 总量 = 线程数量 X 每个线程执行的数据量
	 * 
	 * @param userServiceImpl
	 * @param threadNum
	 *            线程数量
	 * @param createNum
	 *            每个线程执行的数据量
	 * @param step
	 *            每个线程执行时的步长
	 */
	public UserCreateData(IUserService userService, int threadNum, int createNum, int step) {
		this.userService = userService;
		this.threadNum = threadNum;
		threadPool = Executors.newFixedThreadPool(threadNum);
		this.createNum = createNum;
		this.step = step;
		this.begin = System.currentTimeMillis();
	}

	/**
	 * @Description: 创建数据
	 *
	 */
	public void create() {
		for (int i = 0; i < threadNum; i++) {
			threadPool.execute(new CreateDataThread(createNum, step));
		}
		threadPool.shutdown();
	}

	/**
	 * @Description: 打印信息
	 *
	 */
	public void printInfo() {
		while (true) {
			if (threadPool != null && threadPool.isTerminated()) {
				long end = System.currentTimeMillis();
				long usedTime = end - begin;
				System.out.println("\n==========================结 果==========================");
				System.out.println("创建数据总量: " + Counter.getInstance().getTotal());
				System.out.println("总耗时时：[" + Math.ceil((new Double(usedTime) / 1000 / 60)) + "]分钟, [" + Math.ceil((new Double(usedTime) / 1000)) + "]秒, ["
						+ usedTime + "]毫秒");
				break;
			}
		}
	}

	/**
	 * @Description: 内部类：执行作业的线程
	 * @Author: lishengdong
	 * @DateTime: 2015年12月3日 上午10:02:16
	 * @Version: 1.0.0
	 * @Copyright:
	 * @Company:
	 */
	private class CreateDataThread implements Runnable {

		private long threadBegin = System.currentTimeMillis();

		private int threadCreateNum;

		private int threadStep;

		private int dataTotal = 0;

		private int batchNum = 0; // 批次计数器

		public CreateDataThread(int threadCreateNum, int threadStep) {
			this.threadCreateNum = threadCreateNum;
			this.threadStep = threadStep;
		}

		/**
		 * @Description: 取得指定数量的数据
		 * @param count
		 * @return
		 */
		private List<User> getNewUserList(int count) {
			List<User> list = null;
			if (count > 0) {
				list = new ArrayList<User>();
				for (int i = 0; i < count; i++) {
					User user = new User();
					user.setId(UUIDUtil.getUUID());
					user.setSex(RandomDataUtil.getRandomSex());
					if (user.getSex().equals("男")) {
						user.setName(RandomDataUtil.getRandomBoyName());
					} else if (user.getSex().equals("女")) {
						user.setName(RandomDataUtil.getRandomGirlName());
					}
					user.setAge(RandomDataUtil.getNum(18, 70));
					user.setPhone(RandomDataUtil.getRandomPhone());
					user.setCreateTime(new Date());

					list.add(user); // 将对象放入集合
				}
			}

			return list;
		}

		/**
		 * @Description: 添加数据
		 * @param insertNum
		 */
		private void insert(int insertNum) {
			List<User> userList = getNewUserList(insertNum);
			try {
				dataTotal += userService.batchInsert(userList);
				userList = null;
				batchNum++;
				Counter.getInstance().addAndGet(insertNum);
				System.out.println(
						Thread.currentThread().getName() + " " + "第 " + batchNum + " 批（" + insertNum + " 条）数据插入成功!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			if (threadCreateNum <= 0 || threadStep < 0 || threadCreateNum < threadStep) {
				try {
					throw new Exception("输入参数有误!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			while (true) {
				if (threadCreateNum > threadStep) {
					insert(threadStep);
					threadCreateNum = threadCreateNum - threadStep;
				} else {
					if (threadCreateNum > 0) {
						insert(threadCreateNum);
					}

					break;
				}
			}
			long threadEnd = System.currentTimeMillis();
			long threadUsedTime = threadEnd - threadBegin;
			System.out.println("------------------------------------------------" + Thread.currentThread().getName() + "完成任务, 添加数据: " + dataTotal + " 条" + "总耗时时：["
					+ Math.ceil((new Double(threadUsedTime) / 1000 / 60)) + "]分钟, [" + Math.ceil((new Double(threadUsedTime) / 1000)) + "]秒, ["
					+ threadUsedTime + "]毫秒");
		}
	}
}
