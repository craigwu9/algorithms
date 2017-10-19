package pi.craig.algorithms.allcombin;

import java.util.ArrayList;
import java.util.List;

/**
 * 从一个二维数组里找出所有可能的组合
 不改变顺序的组合
 例如
 [
 [a1, a2],
 [b1, b2, b3]
 ]
 预期结果
 a1,b1
 a1,b2
 a1,b3
 a2,b1
 a2,b2
 a2,b3
 思路：
 所有排列组合对应的下标，有一定的规律，例如上面的组合的下标就是
 0,0---0
 0,1---1
 0,2---2
 1,0---3
 1,1---4
 1,2---5
 设：第一个数组的长度为l1=2, 第二个数组的长度为l2=3, 每种排列对应的顺序从0开始为n，可以得出一个公式
 第一个数组的index i1=n/l2
 第二个数组的index (i2=n%l2)/1
 化为通用的，计算过程就是
 1. 计算所有组合的数目 m=l0*l1*...*ln
 2. 从0～m循环, 当前值记位i
 3. 第一次计算 i=0
 3.1 第一个数组的下标 = i/(l1*...ln)=i/(m/l0)
 3.2 第二个数组的下标 = (i % (m/l0)) / (m/l0/l1)
 以次进行递归

 */
public class AllCombination {
    public static void getValue(String[][] values, int index, int countIndex, int base, StringBuilder sb) {
        if (index >= values.length) {
            return;
        }
        int subBase = base / values[index].length;
        int subIndex = countIndex / subBase;
        sb.append(values[index][subIndex]);
        sb.append("_");
        index++;
        getValue(values, index, countIndex % subBase, subBase, sb);
    }

    public static List<String> getAllCombination(String[][] values) {
        List<String> result = new ArrayList<>();

        int allCombinationCount = 0;
        if (values.length > 0) {
            allCombinationCount = values[0].length;
            if (values.length > 1) {
                for (int i=1; i<values.length; i++) {
                    allCombinationCount = allCombinationCount * values[i].length;
                }
            }
        }

        System.out.printf("All combination count: %d\n",allCombinationCount);

        for (int i=0; i<allCombinationCount; i++) {
            StringBuilder sb = new StringBuilder();
            getValue(values, 0, i, allCombinationCount, sb);
            System.out.println(sb.toString());
        }

        return result;
    }


    public static void main(String[] args) {
        String[][] values = {{"a1","a2"}, {"b1", "b2", "b3"}, {"c1", "c2"}, {"d1","d2","d3","d4"}};
        List<String> result = getAllCombination(values);
    }
}
