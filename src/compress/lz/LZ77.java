package compress.lz;

import compress.Compress;

import java.util.*;

public class LZ77 implements Compress {

    public String compress(String txt){
        StringBuilder stringBuilder = new StringBuilder();
        compress2(txt, stringBuilder, 4);
        return stringBuilder.toString();
    }
    private void compress1(String src, StringBuilder out, int windowSize){
        int n = src.length();
        for(int i = 0; i < n; i++){
            char target = src.charAt(i);
            // find longest match
            boolean found = false;
            int start = 0;
            int matchLen = 0;
            char nonMatchChar = 0xff;
            for(int s = Math.max(0, i - windowSize); s < i; s++){
                if(target == src.charAt(s)){
                    int len = getMatchedLen(src, s + 1, i + 1, n) + 1;
                    if(len > matchLen){
                        start = i - s;
                        matchLen = len;
                        nonMatchChar = (char)0xff;
                        if((i + matchLen) < n){
                            nonMatchChar = src.charAt(i + matchLen);
                        }
                    }
                    found = true;
                }
            }
            if(found){
                out.append((char)start)
                        .append((char)matchLen)
                        .append(nonMatchChar);
                i += matchLen;
            } else{
                out.append((char)0x00).append((char)0x00).append(target);
            }
        }
    }
    private int getMatchedLen(CharSequence src, int i1, int i2, int end){
        int n = Math.min(i2 - i1, end - i2);
        for(int i = 0; i < n; i++){
            if(src.charAt(i1++) != src.charAt(i2++)) return i;
        }
        return 0;
    }
    private void compress2(CharSequence src, StringBuilder out, int windowSize){
        Map<Character, List<Integer>> startPoss = new HashMap<Character, List<Integer>>();
        int n = src.length();
        for(int i = 0; i < n; i++){
            char target = src.charAt(i);
            // find longest match
            boolean found = false;
            int start = 0;
            int matchLen = 0;
            char nonMatchChar = 0xff;
            List<Integer> poss = startPoss.get(target);
            if(poss != null){
                Iterator<Integer> it = poss.iterator();
                while(it.hasNext()){
                    int s = it.next();
                    if((i - s) > windowSize){
                        it.remove();
                        continue;
                    }
                    int len = getMatchedLen(src, s + 1, i + 1, n) + 1;
                    if(len > matchLen){
                        start = i - s;
                        matchLen = len;
                        nonMatchChar = (char)0xff;
                        if((i + matchLen) < n){
                            nonMatchChar = src.charAt(i + matchLen);
                        }
                    }
                    found = true;
                }
                poss.add(i);
                int jn = Math.min(i + matchLen + 1, n);
                for(int j = i + 1; j < jn; j++){
                    List<Integer> p = startPoss.get(src.charAt(j));
                    if(p == null){
                        p = new LinkedList<Integer>();
                        startPoss.put(src.charAt(j), p);
                    }
                    p.add(j);
                }
            } else{
                poss = new LinkedList<Integer>();
                poss.add(i);
                startPoss.put(target, poss);
            }
            if(found){
                out.append((char)start)
                        .append((char)matchLen)
                        .append(nonMatchChar);
                i += matchLen;
            } else{
                out.append((char)0x00).append((char)0x00).append(target);
            }
        }
    }
}
