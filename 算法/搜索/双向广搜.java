/*字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：

每一对相邻的单词只差一个字母。
 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
sk == endWord
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。*/

// https://leetcode.cn/problems/word-ladder/

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class 双向广搜 {
 public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> ci = new HashSet<>(wordList);
        if(!ci.contains(endWord)) return 0;

        Set<String> strat = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> next = new HashSet<>();
        strat.add(beginWord);end.add(endWord);

        for(int len = 2;!strat.isEmpty();len=-~len){
            for(String w:strat){
                char[] ws = w.toCharArray();
                for(int i=0;i<ws.length;i=-~i){
                    char old = ws[i];
                    for(char c='a';c<='z';c=(char)(c+1)){
                        if(c==old) continue;
                        ws[i] = c;
                        String ns = String.valueOf(ws);
                        if(end.contains(ns)) return len;

                        if(ci.contains(ns)){
                            ci.remove(ns);
                            next.add(ns);
                        }
                    }
                    ws[i] = old;
                }
            }

            if(next.size()<=end.size()){
                Set<String> temp = strat;
                strat = next;
                next = temp;
            }else{
                Set<String> temp = strat;
                strat = end;
                end = next;
                next = temp;
            }

            next.clear();
        }

        return 0;
    }
}