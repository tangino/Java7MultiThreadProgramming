package chapter_three_高级线程同步.在集合点的同步;

public class Results {

	private int data[];

	public Results(int size) {
		data = new int[size];
	}

	public void setData(int position, int value) {
		data[position] = value;
	}

	public int[] getData() {
		return data;
	}
}
