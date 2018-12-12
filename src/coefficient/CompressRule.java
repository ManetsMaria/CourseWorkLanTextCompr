package coefficient;

import compress.lz.LZ77;
import compress.lz.LZ78;
import compress.lz.LZW;
import compress.rle.RLE;

public class CompressRule {
    public String RLE(String text){
        StringBuilder stringBuilder = new StringBuilder("RLE: ");
        RLE rle = new RLE();
        long startTime = System.nanoTime();
        String text1 = rle.compress(text);
        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime)/100000;
        System.out.println(elapsedTime);
        double coef = (elapsedTime*0.3)+ 1+(1.0*text1.length())/text.length();
        stringBuilder.append(coef);
        return stringBuilder.toString();
    }

    public String LZ77(String text){
        StringBuilder stringBuilder = new StringBuilder("LZ77: ");
        LZ77 rle = new LZ77();
        long startTime = System.nanoTime();
        String text1 = rle.compress(text);
        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime)/100000;
        System.out.println(elapsedTime);
        double coef = (elapsedTime*0.3)+ 1+(1.0*text1.length())/text.length();
        stringBuilder.append(coef);
        return stringBuilder.toString();
    }

    public String LZ78(String text){
        StringBuilder stringBuilder = new StringBuilder("LZ78: ");
        LZ78 rle = new LZ78();
        long startTime = System.nanoTime();
        String text1 = rle.compress(text);
        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime)/100000;
        System.out.println(elapsedTime);
        double coef = (elapsedTime*0.3)+ 1+(1.0*text1.length())/text.length();
        stringBuilder.append(coef);
        return stringBuilder.toString();
    }

    public String LZW(String text){
        StringBuilder stringBuilder = new StringBuilder("LZW: ");
        LZW rle = new LZW();
        long startTime = System.nanoTime();
        String text1 = rle.compress(text);
        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime)/100000;
        System.out.println(elapsedTime);
        double coef = (elapsedTime*0.3)+ 1+(1.0*text1.length())/text.length();
        stringBuilder.append(coef);
        return stringBuilder.toString();
    }

    public String RLE_LZ77(String text){
        StringBuilder stringBuilder = new StringBuilder("RLE_LZ77: ");
        RLE rle = new RLE();
        LZ77 lz77 = new LZ77();
        long startTime = System.nanoTime();
        String text1 = rle.compress(text);
        text1 = lz77.compress(text1);
        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime)/100000;
        System.out.println(elapsedTime);
        double coef = (elapsedTime*0.3)+ 1+(1.0*text1.length())/text.length();
        stringBuilder.append(coef);
        return stringBuilder.toString();
    }

    public String RLE_LZ78(String text){
        StringBuilder stringBuilder = new StringBuilder("RLE_LZ78: ");
        RLE rle = new RLE();
        LZ78 lz77 = new LZ78();
        long startTime = System.nanoTime();
        String text1 = rle.compress(text);
        text1 = lz77.compress(text1);
        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime)/100000;
        System.out.println(elapsedTime);
        double coef = (elapsedTime*0.3)+ 1+(1.0*text1.length())/text.length();
        stringBuilder.append(coef);
        return stringBuilder.toString();
    }

    public String RLE_LZW(String text){
        StringBuilder stringBuilder = new StringBuilder("RLE_LZW: ");
        RLE rle = new RLE();
        LZW lz77 = new LZW();
        long startTime = System.nanoTime();
        String text1 = rle.compress(text);
        text1 = lz77.compress(text1);
        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime)/100000;
        System.out.println(elapsedTime);
        double coef = (elapsedTime*0.3)+ 1+(1.0*text1.length())/text.length();
        stringBuilder.append(coef);
        return stringBuilder.toString();
    }
}
