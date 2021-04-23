package rs.ac.ni.pmf.oop3.synchronization;

public class ProducerConsumerDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		final SharedStorage sharedStorage = new SharedStorage();

		final Producer producer1 = new Producer("Producer1", sharedStorage);
		final Consumer consumer1 = new Consumer("Consumer1", sharedStorage);
		final Producer producer2 = new Producer("Producer2", sharedStorage);
		final Consumer consumer2 = new Consumer("Consumer2", sharedStorage);

		producer1.start();
		producer2.start();

		Thread.sleep(3000);

		consumer1.start();

		Thread.sleep(3000);

		consumer2.start();
	}
}
