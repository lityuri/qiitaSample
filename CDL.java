package countDownLatch;

import java.util.concurrent.CountDownLatch;

//https://qiita.com/sakito/items/207779c3af915cd3da66

public class CDL {
	private static final int LOOP_COUNT = 10;
	private static CountDownLatch countDownLatch =
			new CountDownLatch(LOOP_COUNT);

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < LOOP_COUNT; i ++) {
			new Thread() {
				public void run() {
					System.out.println("wait...");
					countDownLatch.countDown();
				}
			}.start();
		}
		countDownLatch.await();
		System.out.println("finish!");
	}
}
