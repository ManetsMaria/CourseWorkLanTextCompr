package compress.rle;

import compress.Compress;

public class RLE implements Compress {

    @Override
    public String compress(String text) {
        if (text == null || text.isEmpty()){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        char prev = text.charAt(0);
        for (char c: text.toCharArray()){
            if (c == prev){
                counter++;
                continue;
            }
            stringBuilder.append(prev);
            stringBuilder.append(counter);
            prev = c;
            counter = 1;
        }
        stringBuilder.append(prev);
        if (counter == 0){
            counter = 1;
        }
        stringBuilder.append(counter);
        return stringBuilder.toString();
    }

}
