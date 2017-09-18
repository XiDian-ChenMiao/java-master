package cn.xidian.algorithm.leetcode;

import java.util.*;

/**
 * 在DNA序列中找出10个字符组成的至少出现2次的子串
 */
public class RepeatedDNASequencesTest {

	public List<String> findRepeatedDnaSequences(String s) {
     	List<String> result = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<>();
     	if (s == null || s.length() <= 10) {
     		return result;
     	}
     	for (int i = 0; i <= s.length() - 10; i++) {
     	    String substr = s.substring(i, i + 10);
     		if (map.containsKey(substr))
     		    map.put(substr, map.get(substr) + 1);
     		else
     		    map.put(substr, 1);
     	}
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
     	    if (entry.getValue() > 1)
     	        result.add(entry.getKey());
        }
     	return result;
    }

    public static void main(String[] args) {
        List<String> result = new RepeatedDNASequencesTest().findRepeatedDnaSequences("AAAAAAAAAAA");
        System.out.println(Arrays.toString(result.toArray()));
    }
}