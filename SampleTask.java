package countDownLatchSample;
//https://qiita.com/nogitsune413/items/c0e59d34b7652a6dcca9

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

public class SampleTask implements Runnable{
//ワーカースレッド
	private static final SimpleDateFormat SDF =
			new SimpleDateFormat("HH:mm:ss");

	private int no;
	private int time;
	private CountDownLatch latch;
	private int result;

	public int getNo() {
		return no;
	}
	public int getResult() {
		return result;
	}
	public SampleTask(int no, int time, CountDownLatch latch) {
		this.no = no;
		this.time = time;
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("No." + no + " START 処理時間:" +
				time + " 現在時刻" + SDF.format(Calendar.getInstance().getTime()));

		try {
			//重い処理に見せるため、スリープさせる
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//実際の処理は、noに２を掛けるだけ
		result = no * 2;

		System.out.println("No." + no + " END 処理時間:" +
				time + " 現在時刻" + SDF.format(Calendar.getInstance().getTime()));

		//処理が終わったらラッチのカウントを1減らす
		latch.countDown();
	}
}