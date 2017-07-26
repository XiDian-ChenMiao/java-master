package cn.xidian.other;
public class WordListOrderTest {
	private String[] store;
	private int result = -1;
	private void swap(String[] arr,int m,int n) {
		String temp = arr[m];
		arr[m] = arr[n];
		arr[n] = temp;
	}

	private boolean justify(String[] arr) {
		int length = arr.length;
		for (int i = 0; i < length - 1; ++i) {
			int j = i + 1;
			if (arr[i].charAt(arr[i].length() - 1) != arr[j].charAt(0)) {
				return false;
			}
		}
		return true;
	}
	private void perumtation(String[] arr,int begin,int length) {
		if (begin == length) {
			if (justify(arr)) {
				result = 1;
				return;
			}
		}
		for (int i = begin; i <= length; i++) {
			swap(arr,i,begin);
			perumtation(arr,begin + 1,length);
			swap(arr,i,begin);
		}
	}
	public int getResult(String[] arr) {
		if (arr == null) {
			return -1;
		}
		store = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			store[i] = arr[i];
		}
		perumtation(arr,0,arr.length - 1);
		return result;
	}

	public static void main(String[] args) {
		if (args.length == 1) {
			System.out.println("-1");
		} else {
			WordListOrderTest test = new WordListOrderTest();
			String[] data = {"ac","ce","eg","gh","h","h","h","h","hj"};
			System.out.println(test.getResult(data));
		}
	}
}