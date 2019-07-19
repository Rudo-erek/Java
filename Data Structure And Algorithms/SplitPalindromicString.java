package com.ryan.echo.com.ryan.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heli09138
 * @version 1.0
 * @DATE 2019/7/19
 */
public class SplitPalindromicString {
    /**
     *
     * 一. 简单思路就是从头到尾，依次对字符串进行递归，递归的过程中记录结果
     * 缺点：会增加复杂度，返回结果要一次遍历list，把当前字符添加进入，复杂度：O(n*n);
     *
     * 二. 用一个标记数组记录字符串中可以被分割成回文串的位置，当遍历完数组时，记录结果，这样不用循环拷贝加入当前字符串
     *
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;

        String sub = "";
        List<String> innerList;
        List<List<String>> outerList;
        for (int i = 0; i < s.length(); i++) {
            sub = s.substring(0, i+1);
            if (isPalindromic(sub)) {
                if (i < s.length() - 1) {
                    outerList = partition(s.substring(i + 1));
                    for (List temp : outerList) {
                        innerList = new ArrayList<>();
                        innerList.add(sub);
                        innerList.addAll(temp);
                        result.add(innerList);
                    }
                } else {
                    innerList = new ArrayList<>();
                    innerList.add(sub);
                    result.add(innerList);
                }
            }
        }
        return result;
    }

    private boolean isPalindromic(String str) {
        if (str.length() <= 1) return true;

        int head = 0, tail = str.length() - 1;
        while (head < tail) {
            if (str.charAt(head) == str.charAt(tail)) {
                head++;
                tail--;
            } else break;
        }

        return head == tail ? true : (head == tail + 1) ? true : false;
    }

    public static void main(String[] args) {
        String s = "a";
        SplitPalindromicString obj = new SplitPalindromicString();
        List<List<String>> result = obj.partition(s);
        for (List<String> l : result) {
            for (String st : l) {
                System.out.print(st + " ");
            }
            System.out.println();
        }
    }
}
